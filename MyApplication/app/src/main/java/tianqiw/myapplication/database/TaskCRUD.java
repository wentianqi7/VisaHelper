package tianqiw.myapplication.database;

import java.util.List;

import tianqiw.myapplication.model.TaskItem;
import tianqiw.myapplication.model.enums.TaskStatus;
import tianqiw.myapplication.model.enums.VisaType;

/**
 * Created by STuotuo.Wen on 2016/4/14.
 */
public interface TaskCRUD {
    void createTask(TaskItem task);
    TaskItem readTaskById(int tid);
    List<TaskItem> readTasksByStatus(TaskStatus status);
    List<TaskItem> readAllTasksWithType(VisaType type);
    void updateTaskStatus(int tid, TaskStatus newStatus);
    void deleteTask(int tid);
}
