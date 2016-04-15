package tianqiw.myapplication.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import tianqiw.myapplication.model.enums.TaskStatus;

/**
 * Created by STuotuo.Wen on 2016/4/2.
 */
public class TaskItem {
    private int tid;
    private String title;
    private String date;
    private TaskStatus status;
    private String description;

    public TaskItem() {
        tid = 0;
        title = "Undefined";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date = sdf.format(new Date());
        status = TaskStatus.NEW;
        description = "";
    }

    public TaskItem(int tid, String title, String description) {
        this.tid = tid;
        this.title = title;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.date = sdf.format(new Date());
        this.status = TaskStatus.NEW;
        this.description = description;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getTid() {
        return tid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
