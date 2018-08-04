import java.net.URI

group = "com.hpedrorodrigues.dagmodules"
version = "1.0.0"

plugins {
  kotlin("jvm") version ("1.3.41")
  id("java-gradle-plugin")
  id("maven-publish")
}

repositories {
  mavenCentral()
  jcenter()
}

dependencies {
  implementation(kotlin("stdlib-jdk8"))
  implementation("com.google.code.gson:gson:2.8.5")

  testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

gradlePlugin {
  plugins {
    register(rootProject.name) {
      id = group.toString()
      displayName = "Directed Acyclic Graph Modules Plugin"
      description = "A Module Tree Plugin"
      implementationClass = "$id.DagModulesPlugin"
    }
  }
}

publishing {
  repositories {
    maven { url = URI("file:///${System.getenv("HOME")}/.gradle/caches") }
  }
}
