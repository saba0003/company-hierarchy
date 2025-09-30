package com.solvd.companyhierarchy.company.project;

import com.solvd.companyhierarchy.exception.MissingDescriptionException;

public class Task extends WorkItem {

    public Task(String description) throws MissingDescriptionException {
        super(description);
    }

    @Override
    public String toString() {
        return "Task: " + super.toString();
    }
}
