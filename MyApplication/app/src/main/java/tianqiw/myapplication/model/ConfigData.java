package tianqiw.myapplication.model;

import tianqiw.myapplication.model.enums.VisaType;

/**
 * Created by STuotuo.Wen on 2016/4/14.
 */
public class ConfigData {
    private VisaType type;

    public ConfigData() {
        type = VisaType.UNDEFINED;
    }

    public ConfigData(VisaType type) {
        this.type = type;
    }

    public void setType(VisaType type) {
        this.type = type;
    }

    public VisaType getType() {
        return type;
    }
}
