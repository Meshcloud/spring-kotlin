package de.meshcloud.example.springkotlin.web

import de.meshcloud.example.springkotlin.dto.ProjectListDTO
import de.meshcloud.example.springkotlin.repositories.ProjectRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("projects")
class ProjectListController(
    private val projectRepository: ProjectRepository
) {

  @GetMapping("overview")
  fun overview(): List<ProjectListDTO> {
    return projectRepository
        .findAll()
        .map { ProjectListDTO(it) }
  }
}
