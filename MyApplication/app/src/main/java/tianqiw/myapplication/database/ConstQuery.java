package tianqiw.myapplication.database;

/**
 * Created by STuotuo.Wen on 2016/4/14.
 */
public interface ConstQuery {
    String DATABASE = "VisaHelper";

    String CREATE_CONFIG_TABLE = "CREATE TABLE config" +
            "(cid INTEGER PRIMARY KEY," +
            "type INTEGER);";

    String CREATE_TASK_TABLE = "CREATE TABLE task" +
            "(tid INTEGER PRIMARY KEY," +
            "title VARCHAR(50)," +
            "type INTEGER," +
            "date VARCHAR(30)," +
            "status INTEGER," +
            "description TEXT);";
}
