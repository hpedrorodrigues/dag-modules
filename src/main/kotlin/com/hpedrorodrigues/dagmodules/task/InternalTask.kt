package com.hpedrorodrigues.dagmodules.task

import com.hpedrorodrigues.dagmodules.entity.TaskConfig
import com.hpedrorodrigues.dagmodules.processor.InternalProcessor
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

@CacheableTask
open class InternalTask : DefaultTask() {

  @Input
  lateinit var config: TaskConfig

  @TaskAction
  private fun action() {
    InternalProcessor(config).process(project)
  }
}
