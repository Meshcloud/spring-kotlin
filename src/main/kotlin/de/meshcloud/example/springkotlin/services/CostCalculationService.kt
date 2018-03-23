package de.meshcloud.example.springkotlin.services

import de.meshcloud.example.springkotlin.model.Resource
import de.meshcloud.example.springkotlin.model.ResourceType
import de.meshcloud.example.springkotlin.repositories.ProjectRepository
import de.meshcloud.example.springkotlin.repositories.findOneOrThrow
import org.springframework.stereotype.Service

@Service
class CostCalculationService(private val projectRepository: ProjectRepository) {

  fun calculateCosts(projectId: Long): Double {
    val project = projectRepository.findOneOrThrow(projectId)

    return project.resources
        .map { calculateResourceCost(it) }
        .sum()
  }

  private fun calculateResourceCost(resource: Resource) =
      getPlatformCost(resource).let {
        applyIllegalUseCost(resource, it)
      }

  private fun getPlatformCost(resource: Resource): Double {
    /*
     * Simplified cost calculation. Should consider i.e. running VMs, networks, etc in OpenStack or
     * Apps and Services in Cloud Foundry, etc.
     */
    val cost: Double = when (resource.type) {
      ResourceType.OPENSTACK -> 0.03
      ResourceType.CLOUDFOUNDRY -> 0.02
      ResourceType.KUBERNETES -> 0.01
    }
    
    return resource.durationInHours() * cost
  }

  private fun applyIllegalUseCost(resource: Resource, cost: Double): Double {
    val description = resource.project.description
    return if (description?.contains("Bitcoin") == true) {
      cost * 10
    } else cost
  }
}
