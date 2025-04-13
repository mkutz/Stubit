plugins {
  `java-platform`
  `maven-publish`
  alias(libs.plugins.jreleaser)
}

dependencies {
  constraints {
    (parent?.subprojects)
      ?.filter { it != project && it.name != "manual" && it.subprojects.isEmpty() }
      ?.sortedBy { it.name }
      ?.forEach { api(it) }
  }
}
