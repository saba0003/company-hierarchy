package company.project;

import exception.MissingDescriptionException;

import java.util.Objects;

public abstract class WorkItem {

    protected String description;
    protected boolean completed;

    protected WorkItem(String description) throws MissingDescriptionException {
        if (description == null || description.isBlank())
            throw new MissingDescriptionException("Work item description cannot be null or empty");
        this.completed = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markCompleted() {
        this.completed = true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof WorkItem other)) return false;
        return Objects.equals(description, other.description);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(description);
    }

    @Override
    public String toString() {
        return description + " | Status: " + ((completed) ? "Completed" : "Active");
    }
}
