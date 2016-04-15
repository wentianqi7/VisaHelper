package tianqiw.myapplication.database;

import tianqiw.myapplication.model.ConfigData;
import tianqiw.myapplication.model.enums.VisaType;

/**
 * Created by STuotuo.Wen on 2016/4/14.
 */
public interface ConfigCRUD {
    void createConfig(ConfigData config);
    ConfigData readConfig(int cid);
    void updateConfig(int cid, VisaType newType);
    void deleteConfig(int cid);
}
