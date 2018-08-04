package com.hpedrorodrigues.dagmodules

import com.hpedrorodrigues.dagmodules.adapter.TaskConfigAdapter
import com.hpedrorodrigues.dagmodules.entity.InternalExtension
import com.hpedrorodrigues.dagmodules.enumeration.ModuleFilter
import com.hpedrorodrigues.dagmodules.enumeration.OutputFormat
import java.lang.IllegalArgumentException
import kotlin.test.Test
import kotlin.test.assertEquals

class TaskConfigAdapterTest {

  @Test
  fun `Should parse extension with a valid filter type`() {
    val filter = ModuleFilter.All
    val extension = InternalExtension(filter = filter.name)
    val taskConfig = TaskConfigAdapter.adapt(extension)

    assertEquals(taskConfig.filter, filter)
  }

  @Test(expected = IllegalArgumentException::class)
  fun `Should throw an error with an invalid filter type`() {
    val extension = InternalExtension(filter = "Invalid filter")

    TaskConfigAdapter.adapt(extension)
  }

  @Test
  fun `Should parse extension with a valid output format type`() {
    val outputFormat = OutputFormat.PlainText
    val extension = InternalExtension(output = outputFormat.name)
    val taskConfig = TaskConfigAdapter.adapt(extension)

    assertEquals(taskConfig.output, outputFormat)
  }

  @Test(expected = IllegalArgumentException::class)
  fun `Should throw an error with an invalid output format type`() {
    val extension = InternalExtension(output = "Invalid output format")

    TaskConfigAdapter.adapt(extension)
  }
}
