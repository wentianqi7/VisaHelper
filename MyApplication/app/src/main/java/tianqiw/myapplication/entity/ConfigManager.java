package tianqiw.myapplication.entity;

import android.content.Context;

import tianqiw.myapplication.database.ConfigCRUD;
import tianqiw.myapplication.database.DBAdapter;
import tianqiw.myapplication.model.ConfigData;
import tianqiw.myapplication.model.enums.VisaType;

/**
 * Created by STuotuo.Wen on 2016/4/14.
 */
public class ConfigManager implements ConfigCRUD {
    private static ConfigManager manager;
    private Context appContext;
    DBAdapter adapter = null;

    public ConfigManager(Context context) {
        appContext = context;
        adapter = new DBAdapter(context);
    }

    public static ConfigManager get(Context context) {
        if (manager == null) {
            manager = new ConfigManager(context.getApplicationContext());
        }
        return manager;
    }

    public void createConfig(ConfigData config) {
        adapter.createConfig(config);
    }

    public ConfigData readConfig(int cid) {
        return adapter.readConfig(cid);
    }

    public void updateConfig(int cid, VisaType newType) {
        adapter.updateConfig(cid, newType);
    }

    public void deleteConfig(int cid) {
        adapter.deleteConfig(cid);
    }
}
