package tianqiw.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tianqiw.myapplication.model.ConfigData;
import tianqiw.myapplication.model.TaskItem;
import tianqiw.myapplication.model.enums.TaskStatus;
import tianqiw.myapplication.model.enums.VisaType;

/**
 * Created by STuotuo.Wen on 2016/4/14.
 */
public class DBAdapter implements TaskCRUD, ConfigCRUD, ConstQuery {
    private SQLiteDatabase db;
    private DBOpenHelper conn;

    public DBAdapter(Context context) {
        conn = new DBOpenHelper(context, DATABASE, null, 1);
    }

    public void open() throws SQLException {
        db = conn.getWritableDatabase();
    }

    public void close() {
        if (db != null) {
            db.close();
        }
    }

    public void createTask(TaskItem task) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ContentValues tempTask = new ContentValues();
        tempTask.put("tid", task.getTid());
        tempTask.put("title", task.getTitle());
        tempTask.put("type", task.getType().getValue());
        tempTask.put("date", task.getDate());
        tempTask.put("status", task.getStatus().getValue());
        tempTask.put("description", task.getDescription());

        db.insert("task", null, tempTask);
        close();
    }

    public TaskItem readTaskById(int tid) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        TaskItem task = null;
        Cursor cursor = db.query("task", null, "tid=" + tid, null, null, null, null);
        if (cursor.moveToNext()) {
            try {
                task = new TaskItem(cursor.getInt(0), cursor.getString(1),
                        VisaType.values()[cursor.getInt(2)],cursor.getString(3),
                        TaskStatus.values()[cursor.getInt(4)], cursor.getString(5));
            } catch (Exception e) {
                e.printStackTrace();
                task = new TaskItem(TaskStatus.UNDEFINED);
            }
        }
        close();
        return task;
    }

    /**
     * @param status
     * @return list of task ids that match the status
     */
    public List<TaskItem> readTasksByStatus(TaskStatus status) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<TaskItem> taskList = new ArrayList<TaskItem>();
        // task item: tid, title, date, status, description
        Cursor cursor = db.query("task", null, "status=" + status.getValue(), null, null, null, null);
        while (cursor.moveToNext()) {
            try {
                taskList.add(new TaskItem(cursor.getInt(0), cursor.getString(1),
                        VisaType.values()[cursor.getInt(2)],cursor.getString(3),
                        TaskStatus.values()[cursor.getInt(4)], cursor.getString(5)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        close();
        return taskList;
    }

    /**
     * @return all task status in id order
     * @param type
     */
    public List<TaskItem> readAllTasksWithType(VisaType type) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<TaskItem> taskList = new ArrayList<TaskItem>();
        Cursor cursor = db.query("task", null, "type=" + type.getValue(), null, null, null, null);
        while (cursor.moveToNext()) {
            taskList.add(new TaskItem(cursor.getInt(0), cursor.getString(1),
                    VisaType.values()[cursor.getInt(2)],cursor.getString(3),
                    TaskStatus.values()[cursor.getInt(4)], cursor.getString(5)));
        }
        close();
        return taskList;
    }

    public void updateTaskStatus(int tid, TaskStatus newStatus) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ContentValues args = new ContentValues();
        args.put("status", newStatus.getValue());
        db.update("task", args, "tid=" + tid, null);
        close();
    }

    public void deleteTask(int tid) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.delete("task", "tid=" + tid, null);
        close();
    }

    public void createConfig(ConfigData config) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ContentValues tempConfig = new ContentValues();
        tempConfig.put("cid", 0);
        tempConfig.put("type", config.getType().getValue());

        db.insert("config", null, tempConfig);
        close();
    }

    public ConfigData readConfig(int cid) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConfigData config = new ConfigData();
        Cursor cursor = db.query("config", null, "cid=" + cid, null, null, null, null);
        if (cursor.moveToNext()) {
            try {
                config.setType(VisaType.values()[cursor.getInt(1)]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        close();
        return config;
    }

    public void updateConfig(int cid, VisaType newType) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ContentValues args = new ContentValues();
        args.put("type", newType.getValue());
        db.update("config", args, "cid=" + cid, null);
        close();
    }

    public void deleteConfig(int cid) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.delete("config", "cid=" + cid, null);
        close();
    }
}
