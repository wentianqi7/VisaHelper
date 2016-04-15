package tianqiw.myapplication.model.enums;

/**
 * Created by STuotuo.Wen on 2016/4/14.
 */
public enum TaskStatus {
    UNDEFINED(0), NEW(1), IN_PROGRESS(2), COMPLETED(3), FAILED(4), DELETED(5);

    private int value;

    TaskStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
