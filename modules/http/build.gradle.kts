plugins {
  `stubit-module`
  `stubit-publish`
  `stubit-style`
  `stubit-testing`
}

repositories { mavenCentral() }

dependencies {
  testImplementation(platform("org.junit:junit-bom:5.9.3"))
  testImplementation("org.junit.jupiter:junit-jupiter-api")
  testImplementation("org.junit.jupiter:junit-jupiter-params")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

  testImplementation("org.assertj:assertj-core:3.24.2")
}
