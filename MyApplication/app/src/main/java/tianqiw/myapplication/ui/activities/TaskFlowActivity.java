package tianqiw.myapplication.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import tianqiw.myapplication.R;
import tianqiw.myapplication.entity.ConfigManager;
import tianqiw.myapplication.model.ConfigData;
import tianqiw.myapplication.model.TaskItem;
import tianqiw.myapplication.model.enums.VisaType;
import tianqiw.myapplication.ui.fragments.DocChecklistFragment;
import tianqiw.myapplication.ui.fragments.HeaderFragment;
import tianqiw.myapplication.ui.fragments.TaskListFragment;

/**
 * Created by STuotuo.Wen on 2016/4/2.
 */
public class TaskFlowActivity extends MultiFragmentActivity {
    @Override
    protected Fragment createHeaderFragment() {
        // add padding on the top for the toolbar
        return new HeaderFragment();
    }

    @Override
    protected Fragment createBodyFragment() {
        Fragment bodyFragment = new TaskListFragment();
        int intType = getIntent().getIntExtra(getString(R.string.visa_type), 0);
        Bundle bundle = new Bundle();
        bundle.putInt(getString(R.string.visa_type), intType);
        bodyFragment.setArguments(bundle);
        return bodyFragment;
    }

    @Override
    protected Fragment createFooterFragment() {
        return null;
    }
}
