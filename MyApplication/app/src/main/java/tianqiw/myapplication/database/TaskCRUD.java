package tianqiw.myapplication.database;

import java.util.List;

import tianqiw.myapplication.model.enums.TaskStatus;

/**
 * Created by STuotuo.Wen on 2016/4/14.
 */
public interface TaskCRUD {
    void createTask(int tid, TaskStatus status);
    TaskStatus readTaskById(int tid);
    List<Integer> readTaskByStatus(TaskStatus status);
    List<TaskStatus> readAllTasks();
    void updateTask(int tid, TaskStatus newStatus);
    void deleteTask(int tid);
}
