package de.meshcloud.example.springkotlin.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Resource {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    private double costPerHour;

    private LocalDate startDate;

    private LocalDate endDate;

    @ManyToOne()
    @JoinColumn(name = "project_id")
    private Project project;

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

    public double getCostPerHour() {
        return costPerHour;
    }

    public void setCostPerHour(double costPerHour) {
        this.costPerHour = costPerHour;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resource resource = (Resource) o;

        if (Double.compare(resource.costPerHour, costPerHour) != 0) return false;
        if (!id.equals(resource.id)) return false;
        if (name != null ? !name.equals(resource.name) : resource.name != null) return false;
        if (description != null ? !description.equals(resource.description) : resource.description != null) return false;
        return project != null ? project.equals(resource.project) : resource.project == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        temp = Double.doubleToLongBits(costPerHour);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (project != null ? project.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", costPerHour=" + costPerHour +
                ", project=" + project +
                '}';
    }
}
