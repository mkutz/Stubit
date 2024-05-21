plugins {
  `stubit-module`
  `stubit-publish`
}

repositories { mavenCentral() }

dependencies {
  testImplementation(platform(libs.junitBom))
  testImplementation(libs.junitJupiterApi)
  testImplementation(libs.junitJupiterParams)
  testImplementation(libs.assertjCore)

  testRuntimeOnly(libs.junitPlatformLauncher)
  testRuntimeOnly(libs.junitJupiterEngine)
}

tasks.withType<Test> { useJUnitPlatform() }

tasks.jacocoTestReport { reports { xml.required.set(true) } }
