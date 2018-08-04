package com.hpedrorodrigues.dagmodules.entity

data class AdjacencyListItem(val module: String, val dependencies: Set<String>) {

  override fun toString(): String = "$module -> $dependencies"
}
