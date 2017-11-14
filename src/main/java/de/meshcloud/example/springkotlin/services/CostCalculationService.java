package de.meshcloud.example.springkotlin.services;

import de.meshcloud.example.springkotlin.exceptions.NotFoundException;
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
        if (project == null) {
            throw new NotFoundException("Project with id " + projectId + " does not exist");
        }

        return project.getResources().stream()
                .mapToDouble(r -> calculateResourceCost(r))
                .sum();
    }

    private double calculateResourceCost(Resource resource) {
        double cost = getPlatformCost(resource);
        cost = applyIllegalUseCost(resource, cost);
        return cost;
    }

    private double getPlatformCost(Resource resource) {
        double cost;
        /*
         * Simplified cost calculation. Should consider i.e. running VMs, networks, etc in OpenStack or
         * Apps and Services in Cloud Foundry, etc.
         */
        switch (resource.getType()) {
            case OPENSTACK:
                cost = 0.03;
                break;
            case CLOUDFOUNDRY:
                cost = 0.02;
                break;
            case KUBERNETES:
                cost = 0.01;
                break;
            default:
                throw new IllegalArgumentException("Resource Type '" + resource.getType() + "' not supported.");
        }
        return resource.durationInHours() * cost;
    }

    private double applyIllegalUseCost(Resource resource, double cost) {
        String description = resource.getProject().getDescription();
        if (description != null && description.contains("Bitcoin")) {
            return cost * 10;
        }
        return cost;
    }
}
