package com.hpedrorodrigues.dagmodules.entity

import com.hpedrorodrigues.dagmodules.enumeration.ModuleFilter
import com.hpedrorodrigues.dagmodules.enumeration.OutputFormat

open class InternalExtension(var filter: String = ModuleFilter.All.name,
                             var output: String = OutputFormat.PlainText.name,
                             var fullPath: Boolean = false)
