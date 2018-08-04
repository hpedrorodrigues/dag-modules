package com.hpedrorodrigues.dagmodules.adapter

import com.hpedrorodrigues.dagmodules.entity.InternalExtension
import com.hpedrorodrigues.dagmodules.entity.TaskConfig
import com.hpedrorodrigues.dagmodules.enumeration.ModuleFilter
import com.hpedrorodrigues.dagmodules.enumeration.OutputFormat

object TaskConfigAdapter {

  private val supportedFilters by lazy {
    ModuleFilter.values().map { it.name.toLowerCase() }
  }

  private val supportedOutputs by lazy {
    OutputFormat.values().map { it.name.toLowerCase() }
  }

  fun adapt(extension: InternalExtension): TaskConfig {
    if (!supportedFilters.contains(extension.filter.toLowerCase())) {
      throw IllegalArgumentException(
        "Unsupported module filter: \"${extension.filter}\". Supported values: $supportedFilters."
      )
    }

    if (!supportedOutputs.contains(extension.output.toLowerCase())) {
      throw IllegalArgumentException(
        "Unsupported output format: \"${extension.output}\". Supported values: $supportedOutputs."
      )
    }

    val filter = ModuleFilter
      .values()
      .find { it.name.equals(extension.filter, ignoreCase = true) }
      ?: ModuleFilter.All

    val output = OutputFormat
      .values()
      .find { it.name.equals(extension.output, ignoreCase = true) }
      ?: OutputFormat.PlainText

    return TaskConfig(
      filter = filter,
      output = output,
      fullPath = extension.fullPath
    )
  }
}
