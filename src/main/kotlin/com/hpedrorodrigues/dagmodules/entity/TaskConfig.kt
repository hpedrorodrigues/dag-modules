package com.hpedrorodrigues.dagmodules.entity

import com.hpedrorodrigues.dagmodules.enumeration.ModuleFilter
import com.hpedrorodrigues.dagmodules.enumeration.OutputFormat

data class TaskConfig(val filter: ModuleFilter = ModuleFilter.All,
                      val output: OutputFormat = OutputFormat.PlainText,
                      val fullPath: Boolean = false)
