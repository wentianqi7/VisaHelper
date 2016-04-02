package tianqiw.myapplication.ui.activities;

import android.support.v4.app.Fragment;

import tianqiw.myapplication.ui.fragments.DocContentFragment;
import tianqiw.myapplication.ui.fragments.DocImageListFragment;

/**
 * Created by STuotuo.Wen on 2016/3/27.
 */
public class DocActivity extends MultiFragmentActivity {
    @Override
    protected Fragment createHeaderFragment() {
        return null;
    }

    @Override
    protected Fragment createBodyFragment() {
        return new DocContentFragment();
    }

    @Override
    protected Fragment createFooterFragment() {
        return new DocImageListFragment();
    }
}
