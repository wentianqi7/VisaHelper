package tianqiw.myapplication.model.enums;

/**
 * Created by STuotuo.Wen on 2016/4/14.
 */
public enum VisaType {
    UNDEFINED("Undefined", 0), F1("F1", 1), J1("J1", 2);

    String name;
    int value;

    VisaType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return name;
    }
}
