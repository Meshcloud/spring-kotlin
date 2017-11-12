package de.meshcloud.example.springkotlin.config;

import de.meshcloud.example.springkotlin.model.Project;
import de.meshcloud.example.springkotlin.model.Resource;
import de.meshcloud.example.springkotlin.model.ResourceType;
import de.meshcloud.example.springkotlin.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class DatabaseInitConfig implements ApplicationRunner {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initDB();
    }

    private void initDB() {
        Project project = new Project();
        project.setName("P1");
        project.setDescription("My first project");

        Resource resourceCF = new Resource(
                "Cloud Foundry",
                "Simply runs my apps",
                ResourceType.CLOUDFOUNDRY,
                LocalDateTime.of(2017, 11, 11, 11, 11, 0),
                LocalDateTime.of(2017, 11, 11, 22, 22, 0),
                project
        );

        Resource resourceOS = new Resource(
                "OpenStack",
                "All the IaaS magic",
                ResourceType.OPENSTACK,
                LocalDateTime.of(2017, 11, 5, 10, 15, 0),
                LocalDateTime.of(2017, 11, 8, 18, 30, 0),
                project
        );

        project.getResources().addAll(Arrays.asList(resourceCF, resourceOS));

        projectRepository.save(project);
    }


}
