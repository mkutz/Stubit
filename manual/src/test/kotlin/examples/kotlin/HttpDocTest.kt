package examples.kotlin

import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.toRequestBody
import org.assertj.core.api.Assertions.assertThat

import okhttp3.Request
import org.junit.jupiter.api.Test
import org.stubit.http.HttpStub
import org.stubit.http.StubbedResponse
import org.stubit.http.Stubbing

class HttpDocTest {

  private val okHttpClient = OkHttpClient()

  @Test
  fun address() {
    // tag::create[]
    HttpStub().use { httpStub ->
      // tag::address[]
      val stubAddress = httpStub.address()
      // end::address[]

      assertThat(stubAddress).isNotNull().startsWith("http://localhost:")
    }
    // end::create[]
  }

  @Test
  fun unstubbed_get() {
    HttpStub().use { httpStub ->
      // tag::get-unstubbed[]
      val request = Request.Builder().url("${httpStub.address()}/nothing/here").build()
      val response = okHttpClient.newCall(request).execute()

      assertThat(response.code).isEqualTo(404)
      assertThat(response.body!!.string()).isEqualTo("No stubbing for GET /nothing/here")
      // end::get-unstubbed[]
    }
  }

  @Test
  fun unstubbed_post() {
    HttpStub().use { httpStub ->
      // tag::post-unstubbed[]
      val postRequest =
          Request.Builder()
              .url("${httpStub.address()}/nothing/here")
              .post("Hello".toRequestBody())
              .build()
      val postResponse = okHttpClient.newCall(postRequest).execute()

      assertThat(postResponse.code).isEqualTo(201)
      assertThat(postResponse.body!!.string()).isEqualTo("Added stubbing for /nothing/here")
      assertThat(postResponse.header("Location")).startsWith("http://localhost")
      // end::post-unstubbed[]

      // tag::get-posted[]
      val request = Request.Builder().url(postResponse.header("Location")!!).build()
      val response = okHttpClient.newCall(request).execute()

      assertThat(response.code).isEqualTo(200)
      assertThat(response.body!!.string()).isEqualTo("Hello")
      // end::get-posted[]
    }
  }

  @Test
  fun stubbed_get() {
    HttpStub().use { httpStub ->
      // tag::create-stubbing[]
      val stubbing =
          Stubbing.stub()
              .path("/some/where") // <1>
              .method("GET") // <2>
              .returns(StubbedResponse.response().body("Hello").statusCode(200)) // <3>
      // end::create-stubbing[]
      // tag::add-stubbing[]
      httpStub.add(stubbing)
      // end::add-stubbing[]

      // tag::request-stubbing[]
      val request = Request.Builder().url("${httpStub.address()}/some/where").build()
      val response = okHttpClient.newCall(request).execute()

      assertThat(response.code).isEqualTo(200)
      assertThat(response.body!!.string()).isEqualTo("Hello")
      // end::request-stubbing[]
    }
  }
}
