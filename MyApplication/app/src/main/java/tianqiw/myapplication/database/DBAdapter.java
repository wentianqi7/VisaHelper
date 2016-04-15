package tianqiw.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tianqiw.myapplication.model.ConfigData;
import tianqiw.myapplication.model.enums.TaskStatus;
import tianqiw.myapplication.model.enums.VisaType;

/**
 * Created by STuotuo.Wen on 2016/4/14.
 */
public class DBAdapter implements TaskCRUD, ConfigCRUD, ConstQuery {
    private SQLiteDatabase db;
    private DBOpenHelper dbConn;

    public DBAdapter(Context context) {
        dbConn = new DBOpenHelper(context, DATABASE, null, 1);
    }

    public void open() throws SQLException {
        db = dbConn.getWritableDatabase();
    }

    public void close() {
        if (db != null) {
            db.close();
        }
    }

    public void createTask(int tid, TaskStatus status) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ContentValues tempTask = new ContentValues();
        tempTask.put("tid", tid);
        tempTask.put("status", status.getValue());

        db.insert("task", null, tempTask);
        close();
    }

    public TaskStatus readTaskById(int tid) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        TaskStatus status = null;
        Cursor cursor = db.query("task", null, "tid=" + tid, null, null, null, null);
        if (cursor.moveToNext()) {
            try {
                status = TaskStatus.values()[cursor.getInt(1)];
            } catch (Exception e) {
                e.printStackTrace();
                status = TaskStatus.UNDEFINED;
            }
        }
        close();
        return status;
    }

    /**
     * @param status
     * @return list of task ids that match the status
     */
    public List<Integer> readTaskByStatus(TaskStatus status) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Integer> taskList = new ArrayList<Integer>();
        Cursor cursor = db.query("task", null, "status=" + status.getValue(), null, null, null, null);
        while (cursor.moveToNext()) {
            try {
                taskList.add(cursor.getInt(0));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        close();
        return taskList;
    }

    /**
     * @return all task status in id order
     */
    public List<TaskStatus> readAllTasks() {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<TaskStatus> taskList = new ArrayList<TaskStatus>();
        Cursor cursor = db.query("task", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            taskList.add(TaskStatus.values()[cursor.getInt(1)]);
        }
        close();
        return taskList;
    }

    public void updateTask(int tid, TaskStatus newStatus) {
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
