package company.project;

public class Task extends WorkItem {

    public Task(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "Task: " + super.toString();
    }
}
