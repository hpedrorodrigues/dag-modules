package com.hpedrorodrigues.dagmodules.processor

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hpedrorodrigues.dagmodules.entity.AdjacencyListItem
import com.hpedrorodrigues.dagmodules.entity.TaskConfig
import com.hpedrorodrigues.dagmodules.enumeration.AndroidArtifact
import com.hpedrorodrigues.dagmodules.enumeration.FileExtension
import com.hpedrorodrigues.dagmodules.enumeration.ModuleFilter
import com.hpedrorodrigues.dagmodules.enumeration.OutputFormat
import com.hpedrorodrigues.dagmodules.util.FileUtil
import org.gradle.api.Project
import org.gradle.api.artifacts.ProjectDependency
import java.io.File

class InternalProcessor(private val config: TaskConfig) {

  private val gson: Gson by lazy { GsonBuilder().setPrettyPrinting().create() }

  fun process(project: Project) {
    val list = filterByConfig(config, project.subprojects)
      .let { getProjectToDependencyMapping(it) }
      .let { getAdjacencyList(it) }

    val (extension, content) = getOutputByConfig(config, list)

    println(content)

    File(project.buildDir.path, DIRECTORY_NAME).let { outputDir ->
      FileUtil.writeToFile(
        directory = outputDir,
        fileName = "$FILE_NAME.${extension.value}",
        content = content
      )
    }
  }

  private fun filterByConfig(config: TaskConfig,
                             projects: Set<Project>) = projects.filter { project ->
    val isLibrary = project.plugins.hasPlugin(AndroidArtifact.Library.value)
    val isApplication = project.plugins.hasPlugin(AndroidArtifact.Application.value)

    when (config.filter) {
      ModuleFilter.Library -> isLibrary
      ModuleFilter.Application -> isApplication
      else -> isLibrary || isApplication
    }
  }

  private fun getProjectToDependencyMapping(projects: List<Project>) = projects
    .flatMap { project -> project.configurations.map { project to it } }
    .flatMap { (project, configuration) ->
      configuration
        .dependencies
        .withType(ProjectDependency::class.java)
        .map { project to it.dependencyProject }
    }
    .map { (project, dependency) ->
      if (config.fullPath) project.path to dependency.path
      else project.name to dependency.name
    }

  private fun getAdjacencyList(dependencies: List<Pair<String, String>>) = dependencies
    .fold(mapOf<String, Set<String>>()) { map, (project, dependency) ->
      val projectDependencies =
        if (map.contains(project)) map.getValue(project).plus(dependency)
        else setOf(dependency)

      map.plus(project to projectDependencies)
    }
    .entries
    .map { AdjacencyListItem(module = it.key, dependencies = it.value) }

  private fun getOutputByConfig(config: TaskConfig, list: List<AdjacencyListItem>) = if (config.output == OutputFormat.PlainText) {
    FileExtension.PLAIN_TEXT to list.joinToString("\n") { it.toString() }
  } else {
    FileExtension.JSON to gson.toJson(list)
  }

  companion object {
    const val DIRECTORY_NAME = "DagModules"
    const val FILE_NAME = "adjacency-list"
  }
}
