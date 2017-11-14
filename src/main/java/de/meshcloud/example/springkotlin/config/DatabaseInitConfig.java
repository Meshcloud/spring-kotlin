package de.meshcloud.example.springkotlin.config;

import de.meshcloud.example.springkotlin.model.Project;
import de.meshcloud.example.springkotlin.model.Resource;
import de.meshcloud.example.springkotlin.model.ResourceType;
import de.meshcloud.example.springkotlin.repositories.ProjectRepository;
import de.meshcloud.example.springkotlin.repositories.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class DatabaseInitConfig implements ApplicationRunner {

    @Value("${app.projectName}")
    private String projectName;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    public DatabaseInitConfig() {
        if (projectName == null || projectName.equals("Really Bad Name")) {
            projectName = "Slightly better name!";
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initDB();
    }

    private void initDB() {
        for (int i = 1; i <= 5; i++) {
            Project project = createProject(i);

            Resource resourceCF = createCFResource(project);
            Resource resourceOS = createOSResource(project);
            project.getResources().addAll(Arrays.asList(resourceCF, resourceOS));

            projectRepository.save(project);
        }
    }

    private Project createProject(int i) {
        Project project = new Project();
        project.setName(projectName + " " + i);
        project.setDescription("Just a test project");
        return projectRepository.save(project);
    }

    private Resource createOSResource(Project project) {
        Resource resource = new Resource(
                "OpenStack",
                "All the IaaS magic",
                ResourceType.OPENSTACK,
                LocalDateTime.of(2017, 11, 5, 10, 15, 0),
                LocalDateTime.of(2017, 11, 8, 18, 30, 0),
                project
        );
        return resourceRepository.save(resource);
    }

    private Resource createCFResource(Project project) {
        Resource resource = new Resource(
                "Cloud Foundry",
                "Simply runs my apps",
                ResourceType.CLOUDFOUNDRY,
                LocalDateTime.of(2017, 11, 11, 11, 11, 0),
                LocalDateTime.of(2017, 11, 11, 22, 22, 0),
                project
        );
        return resourceRepository.save(resource);
    }
}
