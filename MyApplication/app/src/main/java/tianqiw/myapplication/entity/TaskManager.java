package tianqiw.myapplication.entity;

import android.content.Context;

import java.util.List;

import tianqiw.myapplication.database.DBAdapter;
import tianqiw.myapplication.database.TaskCRUD;
import tianqiw.myapplication.model.TaskItem;
import tianqiw.myapplication.model.enums.TaskStatus;
import tianqiw.myapplication.model.enums.VisaType;

/**
 * Created by STuotuo.Wen on 2016/4/14.
 */
public class TaskManager implements TaskCRUD {
    private static TaskManager manager;
    private Context appContext;
    DBAdapter adapter = null;

    public TaskManager(Context context) {
        appContext = context;
        adapter = new DBAdapter(context);
    }

    public static TaskManager get(Context context) {
        if (manager == null) {
            manager = new TaskManager(context.getApplicationContext());
        }
        return manager;
    }

    public void createTask(TaskItem task) {
        adapter.createTask(task);
    }

    public TaskItem readTaskById(int tid) {
        return adapter.readTaskById(tid);
    }

    public List<TaskItem> readTasksByStatus(TaskStatus status) {
        return manager.readTasksByStatus(status);
    }

    public List<TaskItem> readAllTasksWithType(VisaType type) {
        return adapter.readAllTasksWithType(type);
    }

    public void updateTaskStatus(int tid, TaskStatus newStatus) {
        adapter.updateTaskStatus(tid, newStatus);
    }

    public void deleteTask(int tid) {
        adapter.deleteTask(tid);
    }
}
