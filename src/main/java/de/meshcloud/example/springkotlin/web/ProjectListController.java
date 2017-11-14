package de.meshcloud.example.springkotlin.web;

import de.meshcloud.example.springkotlin.dto.ProjectListDTO;
import de.meshcloud.example.springkotlin.repositories.ProjectRepository;
import de.meshcloud.example.springkotlin.services.CostCalculationService;
import de.meshcloud.example.springkotlin.util.PerformanceLogger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("projects")
public class ProjectListController {

    private ProjectRepository projectRepository;

    public ProjectListController(
            ProjectRepository projectRepository
    ) {
        this.projectRepository = projectRepository;
    }

    @GetMapping("overview")
    public List<ProjectListDTO> overview() {
        return projectRepository
                .findAll()
                .stream()
                .map(p -> new ProjectListDTO(p))
                .collect(Collectors.toList());
    }
}
