package de.meshcloud.example.springkotlin.web;

import de.meshcloud.example.springkotlin.model.Project;
import de.meshcloud.example.springkotlin.repositories.ProjectRepository;
import de.meshcloud.example.springkotlin.services.CostCalculationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("projects")
public class ProjectController {

    private CostCalculationService costCalculationService;

    public ProjectController(CostCalculationService costCalculationService) {
        this.costCalculationService = costCalculationService;
    }

    @GetMapping("{projectId}/costs")
    public Long calculateCosts(@PathVariable("projectId") Long projectId) {
        return costCalculationService.calculateCosts(projectId);
    }

}
