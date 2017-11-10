package de.meshcloud.example.springkotlin.config;

import de.meshcloud.example.springkotlin.model.Project;
import de.meshcloud.example.springkotlin.model.Resource;
import de.meshcloud.example.springkotlin.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

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

        Resource resourceCF = new Resource();
        resourceCF.setName("Cloud Foundry");
        resourceCF.setDescription("Simply runs my apps");
        resourceCF.setCostPerHour(0.03);
        resourceCF.setProject(project);

        Resource resourceOS = new Resource();
        resourceOS.setName("OpenStack");
        resourceOS.setDescription("All the IaaS magic");
        resourceOS.setCostPerHour(0.08);
        resourceOS.setProject(project);

        project.getResources().addAll(Arrays.asList(resourceCF, resourceOS));

        projectRepository.save(project);
    }


}
