plugins {
  base
  alias(libs.plugins.sonar)
  alias(libs.plugins.nexusPublish)
  alias(libs.plugins.spotless)
}

repositories { mavenCentral() }

sonar {
  properties {
    property("sonar.projectKey", "mkutz_stubit")
    property("sonar.organization", "mkutz")
    property("sonar.host.url", "https://sonarcloud.io")
  }
}

nexusPublishing {
  repositories {
    sonatype {
      nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
      snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
    }
  }
}

spotless {
  format("misc") {
    target("**/*.md", "**/*.xml", "**/*.yml", "**/*.yaml", "**/*.html", "**/*.css", ".gitignore")
    targetExclude("**/build/**/*", "**/.idea/**")
    trimTrailingWhitespace()
    endWithNewline()
    leadingTabsToSpaces(2)
  }

  java {
    target("**/*.java")
    targetExclude("**/build/**/*")
    googleJavaFormat().reflowLongStrings()
    removeUnusedImports()
    leadingTabsToSpaces(2)
  }

  kotlinGradle {
    target("**/*.gradle.kts")
    targetExclude("**/build/**/*.gradle.kts")
    ktfmt().googleStyle().configure { it.setManageTrailingCommas(false) }
  }

  freshmark { target("*.md") }
}
