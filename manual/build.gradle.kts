plugins {
  alias(libs.plugins.asciidoctor)
  java
}

repositories { mavenCentral() }

dependencies {
  testImplementation(project(":modules:http"))
  testImplementation(project(":modules:random"))
  testImplementation(project(":modules:spring-data"))

  testImplementation(platform(libs.junitBom))
  testImplementation(libs.junitJupiterApi)
  testImplementation(libs.junitJupiterParams)
  testImplementation(libs.assertjCore)
  testImplementation(libs.okHttp)
  testImplementation(libs.springData)

  testRuntimeOnly(libs.junitPlatformLauncher)
  testRuntimeOnly(libs.junitJupiterEngine)
}

java { toolchain { languageVersion.set(JavaLanguageVersion.of(17)) } }

tasks.withType<Test> { useJUnitPlatform() }

tasks.withType<org.asciidoctor.gradle.jvm.AsciidoctorTask> { baseDirFollowsSourceFile() }

asciidoctorj { modules { diagram.use() } }
