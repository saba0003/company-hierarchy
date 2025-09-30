package com.solvd.companyhierarchy.functionals;

import com.solvd.companyhierarchy.company.project.Project;
import com.solvd.companyhierarchy.company.project.WorkItem;

@FunctionalInterface
public interface TaskAssigner {
    void assign(Project project, WorkItem... tasks);
}