package tianqiw.myapplication.ui.activities;

import android.support.v4.app.Fragment;

import tianqiw.myapplication.ui.fragments.DocChecklistFragment;
import tianqiw.myapplication.ui.fragments.HeaderFragment;

public class MainActivity extends MultiFragmentActivity {

    @Override
    protected Fragment createHeaderFragment() {
        return new HeaderFragment();
    }

    @Override
    protected Fragment createBodyFragment() {
        return new DocChecklistFragment();
    }

    @Override
    protected Fragment createFooterFragment() {
        return null;
    }

}
