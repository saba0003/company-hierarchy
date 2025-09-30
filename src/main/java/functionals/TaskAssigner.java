package functionals;

import company.project.Project;
import company.project.WorkItem;

@FunctionalInterface
public interface TaskAssigner {
    void assign(Project project, WorkItem... tasks);
}