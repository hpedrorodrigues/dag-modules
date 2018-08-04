package com.hpedrorodrigues.dagmodules

import com.hpedrorodrigues.dagmodules.adapter.TaskConfigAdapter
import com.hpedrorodrigues.dagmodules.entity.InternalExtension
import com.hpedrorodrigues.dagmodules.task.InternalTask
import org.gradle.api.Project
import org.gradle.api.Plugin

class DagModulesPlugin : Plugin<Project> {

  override fun apply(target: Project) {
    val name = "dagmodules"
    val extension = target.extensions.create(name, InternalExtension::class.java)

    target.tasks.register(name, InternalTask::class.java) {
      it.config = TaskConfigAdapter.adapt(extension)
    }
  }
}
