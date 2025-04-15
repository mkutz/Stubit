plugins {
  `java-platform`
  `maven-publish`
}

dependencies {
  constraints {
    (parent?.subprojects)
      ?.filter { it != project && it.name != "manual" && it.subprojects.isEmpty() }
      ?.sortedBy { it.name }
      ?.forEach { api(it) }
  }
}
