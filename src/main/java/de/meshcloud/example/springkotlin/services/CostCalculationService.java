package de.meshcloud.example.springkotlin.services;

import de.meshcloud.example.springkotlin.model.Project;
import de.meshcloud.example.springkotlin.model.Resource;
import de.meshcloud.example.springkotlin.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.time.temporal.ChronoUnit;

@Service
public class CostCalculationService {

    private ProjectRepository projectRepository;

    public CostCalculationService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Double calculateCosts(Long projectId) {
        Project project = projectRepository.findOne(projectId);
        return project.getResources().stream()
                .mapToDouble(r -> calculateResourceCost(r))
                .sum();
    }

    private Double calculateResourceCost(Resource resource) {
        switch (resource.getType()) {
            case OPENSTACK:
                return resource.durationInHours() * 0.03;
            case CLOUDFOUNDRY:
                return resource.durationInHours() * 0.02;
            case KUBERNETES:
                return resource.durationInHours() * 0.01;
            default:
                throw new IllegalArgumentException("Resource Type '" + resource.getType() + "' not supported.");
        }
    }
}
