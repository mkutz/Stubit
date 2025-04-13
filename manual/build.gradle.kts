@file:Suppress("UnstableApiUsage", "unused")

plugins {
  alias(libs.plugins.asciidoctor)
  java
  alias(libs.plugins.kotlinJvm)
}

repositories { mavenCentral() }

val asciidoctorExt by configurations.creating

dependencies { asciidoctorExt(libs.asciidoctorBlockSwitch) }

java { toolchain { languageVersion.set(JavaLanguageVersion.of(17)) } }

testing {
  suites {
    val test by
      getting(JvmTestSuite::class) {
        useJUnitJupiter()
        dependencies {
          implementation(project(":modules:http"))
          implementation(project(":modules:random"))
          implementation(project(":modules:spring-data"))

          implementation(platform(libs.junitBom))
          implementation(libs.junitJupiterApi)
          implementation(libs.junitJupiterParams)
          implementation(libs.assertjCore)
          implementation(libs.okHttp)
          implementation(libs.springData)

          runtimeOnly(libs.junitPlatformLauncher)
          runtimeOnly(libs.junitJupiterEngine)
        }
      }
  }
}

tasks.withType<org.asciidoctor.gradle.jvm.AsciidoctorTask> {
  baseDirFollowsSourceFile()
  configurations("asciidoctorExt")
}

asciidoctorj { modules { diagram.use() } }
