plugins {
  `stubit-module`
  `stubit-publish`
  id("info.solidsoft.pitest") version "1.15.0"
}

repositories { mavenCentral() }

dependencies {
  testImplementation(platform("org.junit:junit-bom:5.10.2"))
  testImplementation("org.junit.jupiter:junit-jupiter-api")
  testImplementation("org.junit.jupiter:junit-jupiter-params")
  testImplementation("org.assertj:assertj-core:3.25.3")

  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.withType<Test> { useJUnitPlatform() }

pitest {
  junit5PluginVersion.set("1.0.0")
  outputFormats.addAll("XML", "HTML")
  timestampedReports.set(false)
}
