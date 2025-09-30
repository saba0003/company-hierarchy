package company.project;

import enums.ProjectStatus;

import java.util.Set;

public class Project {

    private String projectName;
    private ProjectStatus status;
    private Set<WorkItem> items;

    public Project(String projectName, ProjectStatus status) {
        this.projectName = projectName;
        this.status = status;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public Set<WorkItem> getWorkItems() {
        return items;
    }

    public void setTasks(Set<WorkItem> items) {
        this.items = items;
    }
}
