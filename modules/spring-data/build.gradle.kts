@file:Suppress("UnstableApiUsage", "unused")

plugins {
  `java-library`
  jacoco
  `jvm-test-suite`
  `maven-publish`
  alias(libs.plugins.jreleaser)
}

java {
  withJavadocJar()
  withSourcesJar()
  toolchain { languageVersion.set(JavaLanguageVersion.of(17)) }
}

repositories { mavenCentral() }

dependencies {
  api(libs.jspecify)

  implementation(libs.springData)
  implementation(libs.jakartaPersistenceApi)
}

testing {
  suites {
    val test by
      getting(JvmTestSuite::class) {
        useJUnitJupiter()
        dependencies {
          implementation(platform(libs.junitBom))
          implementation(libs.junitJupiterApi)
          implementation(libs.junitJupiterParams)
          implementation(libs.assertjCore)
          implementation(project(":modules:random"))

          runtimeOnly(libs.junitPlatformLauncher)
          runtimeOnly(libs.junitJupiterEngine)
        }
      }
  }
}

tasks.jacocoTestReport { reports { xml.required = true } }
