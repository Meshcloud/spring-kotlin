package de.meshcloud.example.springkotlin.dto

import de.meshcloud.example.springkotlin.model.Project

data class ProjectListDTO(
    val id: Long,
    val name: String,
    val description: String?
) {
  constructor(project: Project) : this (
    id = project.id!!,
    name = project.name,
    description = project.description
  )
}
