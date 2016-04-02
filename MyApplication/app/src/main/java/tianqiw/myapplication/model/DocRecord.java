package tianqiw.myapplication.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by STuotuo.Wen on 2016/3/27.
 */
public class DocRecord {
    private int docId;
    //private int page;
    private String title;
    private String date;

    public DocRecord(int docId, String title) {
        this.docId = docId;
        this.title = title;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.date = sdf.format(new Date());
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
}
