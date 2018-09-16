package com.example.user.Leszy;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class SideMenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {

    protected void onCreateDrawer() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        onCreateDrawer();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO settings
/*        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent b= new Intent(MainActivity.this, Settings.class);
            startActivity(b);
        }
*/
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.nav_home:
                if (this instanceof MainActivity) { break; }
                Intent a = new Intent(this, MainActivity.class);
                startActivity(a);
                break;
            case R.id.nav_login:
                if (this instanceof LoginActivity) { break; }
                Intent b = new Intent(this, LoginActivity.class);
                startActivity(b);
                break;
            case R.id.nav_calendar:
                if (this instanceof CalendarActivity) { break; }
                Intent c = new Intent(this, CalendarActivity.class);
                startActivity(c);
                break;
            case R.id.nav_notes:
                if (this instanceof NoteSelectActivity) { break; }
                Intent d = new Intent(this, NoteSelectActivity.class);
                startActivity(d);
                break;
            case R.id.nav_map:
                if (this instanceof MapActivity) { break; }
                Intent e = new Intent(this, MapActivity.class);
                startActivity(e);
                break;
            case R.id.nav_twitter:
                Intent f = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/ktrebski"));
                startActivity(f);
                break;
            case R.id.nav_help:
                //if (this instanceof HelpActivity) { break; }
                //  Intent g = new Intent(this, HelpActivity.class);
                //  startActivity(g);
                break;
            case R.id.nav_firstaid:
                //if (this instanceof FirstAidActivity) { break; }
                //  Intent h = new Intent(this, FirstAidActivity.class);
                //  startActivity(h);
                break;
            case R.id.nav_warn:
                //if (this instanceof WarnActivity) { break; }
                //  Intent i = new Intent(this, WarnActivity.class);
                //  startActivity(i);
                break;
            case R.id.nav_tips:
                //if (this instanceof InfoActivity) { break; }
                //  Intent j = new Intent(this, InfoActivity.class);
                //  startActivity(j);
                break;
            case R.id.nav_settings:
                if (this instanceof SettingsActivity) { break; }
                Intent k = new Intent(this, SettingsActivity.class);
                startActivity(k);
                break;
            case R.id.nav_location:
                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                Menu menu = navigationView.getMenu();
                MenuItem nav_location = menu.findItem(R.id.nav_location);

                if (!AppData.isLocationSharingOn) {
                    nav_location.setTitle(R.string.stop_sharing_location);
                    AppData.isLocationSharingOn = true;
                    Intent startIntent = new Intent(this, LocationSenderService.class);
                    startService(startIntent);
                } else {
                    nav_location.setTitle(R.string.share_location);
                    AppData.isLocationSharingOn = false;
                    Intent stopIntent = new Intent(this, LocationSenderService.class);
                    stopService(stopIntent);
                }
                navigationView.setNavigationItemSelectedListener(this);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
