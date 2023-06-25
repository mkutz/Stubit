plugins {
  id("org.asciidoctor.jvm.convert") version "3.3.2"
  `stubit-style`
  java
}

dependencies {
  testImplementation(project(":modules:http"))

  testImplementation(platform("org.junit:junit-bom:5.9.3"))
  testImplementation("org.junit.jupiter:junit-jupiter-api")
  testImplementation("org.junit.jupiter:junit-jupiter-params")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

  testImplementation("org.assertj:assertj-core:3.24.2")
}

java { toolchain { languageVersion.set(JavaLanguageVersion.of(17)) } }

tasks.withType<Test> { useJUnitPlatform() }

tasks.withType<org.asciidoctor.gradle.jvm.AsciidoctorTask> { baseDirFollowsSourceFile() }

asciidoctorj { modules { diagram.use() } }
