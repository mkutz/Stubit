plugins {
  `stubit-module`
  `stubit-publish`
  `stubit-style`
  `stubit-testing`
}

repositories { mavenCentral() }

dependencies {
  testImplementation(platform("org.junit:junit-bom:5.10.2"))
  testImplementation("org.junit.jupiter:junit-jupiter-api")
  testImplementation("org.junit.jupiter:junit-jupiter-params")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

  testImplementation("org.assertj:assertj-core:3.25.3")
}
