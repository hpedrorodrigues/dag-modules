# Gradle Modules

An Android Gradle plugin to list an adjacency list of internal modules in a 
multi-module project.

**Note**: This project is just a PoC.

```groovy
buildscript {

    repositories {
        maven { url "file:///${System.getenv("HOME")}/.gradle/caches" }
    }

    dependencies {
        classpath 'com.hpedrorodrigues.dagmodules:dagmodules:1.0.0'
    }
}

apply plugin: 'com.hpedrorodrigues.dagmodules'

dagmodules {
    filter = "library"
    output = "json"
}
```
