package org.stubit.http;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.junit.jupiter.api.Test;

@SuppressWarnings("ALL")
class HttpDocTest {

  OkHttpClient okHttpClient = new OkHttpClient();

  @Test
  void address() {
    // tag::create[]
    try (var httpStub = new HttpStub()) {
      // tag::address[]
      String stubAddress = httpStub.address();
      // end::address[]

      assertThat(stubAddress).isNotNull().startsWith("http://localhost:");
    }
    // end::create[]
  }

  @Test
  void unstubbed_get() throws IOException {
    try (var httpStub = new HttpStub()) {
      // tag::get-unstubbed[]
      var request = new Request.Builder().url(httpStub.address() + "/nothing/here").build();
      var response = okHttpClient.newCall(request).execute();

      assertThat(response.code()).isEqualTo(404);
      assertThat(response.body().string()).isEqualTo("No stubbing for GET /nothing/here");
      // end::get-unstubbed[]
    }
  }

  @Test
  void unstubbed_post() throws IOException {
    try (var httpStub = new HttpStub()) {
      // tag::post-unstubbed[]
      var postRequest =
          new Request.Builder()
              .url(httpStub.address() + "/nothing/here")
              .post(RequestBody.create("Hello".getBytes(StandardCharsets.UTF_8)))
              .build();
      var postResponse = okHttpClient.newCall(postRequest).execute();

      assertThat(postResponse.code()).isEqualTo(201);
      assertThat(postResponse.body().string()).isEqualTo("Added stubbing for /nothing/here");
      assertThat(postResponse.header("Location")).startsWith("http://localhost/nothing/here/");
      // end::post-unstubbed[]

      // tag::get-posted[]
      var request = new Request.Builder().url(postResponse.header("Location")).build();
      var response = okHttpClient.newCall(request).execute();

      assertThat(response.code()).isEqualTo(200);
      assertThat(response.body().string()).isEqualTo("Hello");
      // end::get-posted[]
    }
  }

  @Test
  void stubbed_get() throws IOException {
    try (var httpStub = new HttpStub()) {
      // tag::create-stubbing[]
      Stubbing stubbing =
          Stubbing.stub()
              .path("/some/where") // <1>
              .method("GET") // <2>
              .returns(StubbedResponse.response().body("Hello").statusCode(200)); // <3>
      // end::create-stubbing[]
      // tag::add-stubbing[]
      httpStub.add(stubbing);
      // end::add-stubbing[]

      // tag::request-stubbing[]
      var request = new Request.Builder().url(httpStub.address() + "/some/where").build();
      var response = okHttpClient.newCall(request).execute();

      assertThat(response.code()).isEqualTo(200);
      assertThat(response.body().string()).isEqualTo("Hello");
      // end::request-stubbing[]
    }
  }
}
