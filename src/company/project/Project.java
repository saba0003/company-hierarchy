package company.project;

import java.util.Set;

public class Project {

    private String projectName;
    private Set<WorkItem> items;

    public Project(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Set<WorkItem> getWorkItems() {
        return items;
    }

    public void setTasks(Set<WorkItem> items) {
        this.items = items;
    }
}
