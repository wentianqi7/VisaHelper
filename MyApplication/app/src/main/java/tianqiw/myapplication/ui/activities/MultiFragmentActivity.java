package tianqiw.myapplication.ui.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import tianqiw.myapplication.R;

/**
 * Created by STuotuo.Wen on 2016/3/27.
 */
public abstract class MultiFragmentActivity extends FragmentActivity {
    protected abstract Fragment createHeaderFragment();
    protected abstract Fragment createBodyFragment();
    protected abstract Fragment createFooterFragment();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.multi_fragment_template);
        FragmentManager manager = getSupportFragmentManager();

        // content fragment
        Fragment listFragment = manager.findFragmentById(R.id.bodyContainer);
        if (listFragment == null) {
            listFragment = createBodyFragment();
            if (listFragment != null) {
                manager.beginTransaction()
                        .add(R.id.bodyContainer, listFragment)
                        .commit();
            }
        }

        // header fragment
        Fragment headerFragment = manager.findFragmentById(R.id.headerContainer);
        if (headerFragment == null) {
            headerFragment = createHeaderFragment();
            if (headerFragment != null) {
                manager.beginTransaction()
                        .add(R.id.headerContainer, headerFragment)
                        .commit();
            }
        }

        // footer fragment
        Fragment footerFragment = manager.findFragmentById(R.id.footerContainer);
        if (footerFragment == null) {
            footerFragment = createFooterFragment();
            if (footerFragment != null) {
                manager.beginTransaction()
                        .add(R.id.footerContainer, footerFragment)
                        .commit();
            }
        }
    }
}
