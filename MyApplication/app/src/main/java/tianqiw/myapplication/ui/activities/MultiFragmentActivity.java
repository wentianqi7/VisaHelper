package tianqiw.myapplication.ui.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;

import tianqiw.myapplication.R;

/**
 * Created by STuotuo.Wen on 2016/3/27.
 */
public abstract class MultiFragmentActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    protected abstract Fragment createHeaderFragment();
    protected abstract Fragment createBodyFragment();
    protected abstract Fragment createFooterFragment();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        setContentView(R.layout.ui_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camara) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
