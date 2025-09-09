package company.project;

public class Project {

    private String projectName;
    private WorkItem[] items;
    private int taskCount;

    public Project(String projectName) {
        this.projectName = projectName;
        this.taskCount = 0;
    }

    public void addTask(Task task) {
        if (taskCount < items.length)
            items[taskCount++] = task;
        else
            System.out.println("No more tasks can be added!");
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public WorkItem[] getWorkItems() {
        return items;
    }

    public void setTasks(WorkItem[] items) {
        this.items = items;
    }

    public int getTaskCount() {
        return taskCount;
    }
}
