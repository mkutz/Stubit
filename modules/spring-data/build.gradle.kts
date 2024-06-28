plugins {
  `stubit-module`
  `stubit-publish`
}

repositories { mavenCentral() }

dependencies {
  implementation(libs.springData)
  implementation(libs.jakartaPersistenceApi)

  testImplementation(platform(libs.junitBom))
  testImplementation(libs.junitJupiterApi)
  testImplementation(libs.junitJupiterParams)
  testImplementation(libs.assertjCore)
  testImplementation(project(":modules:random"))

  testRuntimeOnly(libs.junitPlatformLauncher)
  testRuntimeOnly(libs.junitJupiterEngine)
}

tasks.withType<Test> { useJUnitPlatform() }

tasks.jacocoTestReport { reports { xml.required.set(true) } }
