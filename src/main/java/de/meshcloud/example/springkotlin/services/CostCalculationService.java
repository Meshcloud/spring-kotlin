package de.meshcloud.example.springkotlin.services;

import de.meshcloud.example.springkotlin.model.Project;
import de.meshcloud.example.springkotlin.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class CostCalculationService {

    private ProjectRepository projectRepository;

    public CostCalculationService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Long calculateCosts(Long projectId) {
        Project project = projectRepository.findOne(projectId);
        return 0L;
    }
}
