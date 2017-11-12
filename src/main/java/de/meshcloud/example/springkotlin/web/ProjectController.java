package de.meshcloud.example.springkotlin.web;

import de.meshcloud.example.springkotlin.services.CostCalculationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("projects")
public class ProjectController {

    private CostCalculationService costCalculationService;

    public ProjectController(CostCalculationService costCalculationService) {
        this.costCalculationService = costCalculationService;
    }

    @GetMapping("{projectId}/costs")
    public Double calculateCosts(@PathVariable("projectId") Long projectId) {
        return costCalculationService.calculateCosts(projectId);
    }

}
