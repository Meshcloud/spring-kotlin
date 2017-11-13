package de.meshcloud.example.springkotlin.dto;

import de.meshcloud.example.springkotlin.model.Project;

public class ProjectListDTO {
    private Long id;

    private String name;

    private String description;

    public ProjectListDTO(Project project) {
        this.id = project.getId();
        this.name = project.getName();
        this.description = project.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
