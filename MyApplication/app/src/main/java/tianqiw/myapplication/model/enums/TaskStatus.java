package tianqiw.myapplication.model.enums;

/**
 * Created by STuotuo.Wen on 2016/4/14.
 */
public enum TaskStatus {
    UNDEFINED(0), IN_PROGRESS(1), COMPLETED(2);

    private int value;

    TaskStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
