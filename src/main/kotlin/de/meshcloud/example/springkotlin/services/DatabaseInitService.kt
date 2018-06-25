package de.meshcloud.example.springkotlin.services

import de.meshcloud.example.springkotlin.config.DatabaseInitConfig
import de.meshcloud.example.springkotlin.model.Project
import de.meshcloud.example.springkotlin.model.Resource
import de.meshcloud.example.springkotlin.model.ResourceType
import de.meshcloud.example.springkotlin.repositories.ProjectRepository
import de.meshcloud.example.springkotlin.repositories.ResourceRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class DatabaseInitService(
    private val projectRepository: ProjectRepository,
    private val resourceRepository: ResourceRepository,
    private val databaseInitConfig: DatabaseInitConfig
) : ApplicationRunner {

  @Throws(Exception::class)
  override fun run(args: ApplicationArguments) {
    initDB()
  }

  private fun initDB() {
    for (i in 1..5) {
      val project = createProject(i)

      val resourceCF = createCFResource(project)
      val resourceOS = createOSResource(project)
      project.resources.addAll(listOf(resourceCF, resourceOS))

      projectRepository.save(project)
    }
  }

  private fun createProject(i: Int): Project {
    val project = Project(
        name = "${optimizedProjectName()} $i",
        description = "Just a test project"
    )

    return projectRepository.save(project)
  }

  private fun optimizedProjectName(): String {
    return if (databaseInitConfig.projectName == "Really Bad Name") {
      "Slightly better name!"
    } else databaseInitConfig.projectName
  }

  private fun createOSResource(project: Project): Resource {
    val resource = Resource(
        name = "OpenStack",
        description = "All the IaaS magic",
        type = ResourceType.OPENSTACK,
        startDate = Instant.now().minusMillis(10000000),
        endDate = Instant.now().minusMillis(5000),
        project = project
    )
    return resourceRepository.save(resource)
  }

  private fun createCFResource(project: Project): Resource {
    val resource = Resource(
        name = "Cloud Foundry",
        description = "Simply runs my apps",
        type = ResourceType.CLOUDFOUNDRY,
        startDate = Instant.now().minusMillis(12000000),
        endDate = Instant.now().minusMillis(6000),
        project = project
    )
    return resourceRepository.save(resource)
  }
}
