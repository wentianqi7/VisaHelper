package tianqiw.myapplication.entity;

import android.content.Context;

import java.util.List;

import tianqiw.myapplication.database.DBAdapter;
import tianqiw.myapplication.database.TaskCRUD;
import tianqiw.myapplication.model.enums.TaskStatus;

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

    public void createTask(int tid, TaskStatus status) {
        adapter.createTask(tid, status);
    }

    public TaskStatus readTaskById(int tid) {
        return adapter.readTaskById(tid);
    }

    public  List<Integer> readTaskByStatus(TaskStatus status) {
        return manager.readTaskByStatus(status);
    }

    public List<TaskStatus> readAllTasks() {
        return adapter.readAllTasks();
    }

    public void updateTask(int tid, TaskStatus newStatus) {
        adapter.updateTask(tid, newStatus);
    }

    public void deleteTask(int tid) {
        adapter.deleteTask(tid);
    }
}
