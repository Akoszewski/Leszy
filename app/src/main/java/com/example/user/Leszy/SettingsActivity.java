package com.example.user.Leszy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;



public class SettingsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    Button changelog;
    Button changepass;
    Button changemail;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.settings_layout);

        changelog = (Button) findViewById(R.id.butt);
        changepass = (Button) findViewById(R.id.butt2);
        changemail = (Button) findViewById(R.id.butt3);


//Drawer
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
//
    }

    //Drawer methods
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
                Intent a = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(a);
                break;
            case R.id.nav_login:
                Intent b = new Intent(SettingsActivity.this, LoginActivity.class);
                startActivity(b);
                break;
            case R.id.nav_calendar:
                Intent c = new Intent(SettingsActivity.this, CalendarActivity.class);
                startActivity(c);
                break;
            case R.id.nav_notes:
                Intent d = new Intent(SettingsActivity.this, NoteSelect.class);
                startActivity(d);
                break;
            case R.id.nav_map:
                Intent e = new Intent(SettingsActivity.this, MapActivity.class);
                startActivity(e);
                break;
            case R.id.nav_twitter:
                Intent f = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/ktrebski"));
                startActivity(f);
                break;
            case R.id.nav_help:
                //  Intent g = new Intent(MainActivity.this, HelpActivity.class);
                //  startActivity(g);
                break;
            case R.id.nav_firstaid:
                //  Intent h = new Intent(MainActivity.this, FirstAidActivity.class);
                //  startActivity(h);
                break;
            case R.id.nav_warn:
                //  Intent i = new Intent(MainActivity.this, WarnActivity.class);
                //  startActivity(i);
                break;
            case R.id.nav_tips:
                //  Intent j = new Intent(MainActivity.this, InfoActivity.class);
                //  startActivity(j);
                break;
            case R.id.nav_settings:
                break;
            case R.id.nav_location:
                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                Menu menu = navigationView.getMenu();
                MenuItem nav_location = menu.findItem(R.id.nav_location);

                if (!AppData.isLocationSharingOn) {
                    nav_location.setTitle(R.string.stop_sharing_location);
                    AppData.isLocationSharingOn = true;
                    Intent startIntent = new Intent(SettingsActivity.this, LocationSenderService.class);
                    startService(startIntent);
                } else {
                    nav_location.setTitle(R.string.share_location);
                    AppData.isLocationSharingOn = false;
                    Intent stopIntent = new Intent(SettingsActivity.this, LocationSenderService.class);
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
