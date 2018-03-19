package de.meshcloud.example.springkotlin.web

import de.meshcloud.example.springkotlin.services.CostCalculationService
import de.meshcloud.example.springkotlin.util.PerformanceLogger
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("projects")
class ProjectController(
    private val costCalculationService: CostCalculationService
) {

  @GetMapping("{projectId}/costs")
  fun calculateCosts(@PathVariable("projectId") projectId: Long): Double {
    return PerformanceLogger.logPerformance(
        { costCalculationService.calculateCosts(projectId) },
        "Cost Calculation for $projectId"
    )
  }

}
