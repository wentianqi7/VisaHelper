package tianqiw.myapplication.ui.activities;

import android.support.v4.app.Fragment;

import tianqiw.myapplication.ui.fragments.DocChecklistFragment;
import tianqiw.myapplication.ui.fragments.HeaderFragment;
import tianqiw.myapplication.ui.fragments.TaskListFragment;

/**
 * Created by STuotuo.Wen on 2016/4/2.
 */
public class FlowActivity extends MultiFragmentActivity {
    @Override
    protected Fragment createHeaderFragment() {
        return new HeaderFragment();
    }

    @Override
    protected Fragment createBodyFragment() {
        return new TaskListFragment();
    }

    @Override
    protected Fragment createFooterFragment() {
        return null;
    }
}
