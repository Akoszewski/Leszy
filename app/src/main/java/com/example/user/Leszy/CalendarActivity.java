package com.example.user.Leszy;

import android.content.Intent;
import android.content.res.Configuration;
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
import android.widget.CalendarView;

import java.util.Locale;

public class CalendarActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private static final String TAG="CalendarActivity";


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        CalendarView simpleCalendarView = (CalendarView) findViewById(R.id.calendarView);
        simpleCalendarView.setFirstDayOfWeek(2);

        String languageToLoad  = "pl";
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        this.setContentView(R.layout.activity_calendar);

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
                Intent a = new Intent(CalendarActivity.this, MainActivity.class);
                startActivity(a);
                break;
            case R.id.nav_login:
                Intent b = new Intent(CalendarActivity.this, LoginActivity.class);
                startActivity(b);
                break;
            case R.id.nav_calendar:
                break;
            case R.id.nav_notes:
                Intent d = new Intent(CalendarActivity.this, NoteSelect.class);
                startActivity(d);
                break;
            case R.id.nav_map:
                Intent e = new Intent(CalendarActivity.this, MapActivity.class);
                startActivity(e);
                break;
            case R.id.nav_twitter:
                Intent f = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/nasze_lasy"));
                startActivity(f);
                break;
            case R.id.nav_help:
                //  Intent g = new Intent(MainActivity.this, HelpActivity.class);
                //  startActivity(g);
                break;
            case R.id.nav_location:
                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                Menu menu = navigationView.getMenu();
                MenuItem nav_location = menu.findItem(R.id.nav_location);

                if (!AppData.isLocationSharingOn) {
                    nav_location.setTitle(R.string.stop_sharing_location);
                    AppData.isLocationSharingOn = true;
                    Intent startIntent = new Intent(CalendarActivity.this, LocationSenderService.class);
                    startService(startIntent);
                } else {
                    nav_location.setTitle(R.string.share_location);
                    AppData.isLocationSharingOn = false;
                    Intent stopIntent = new Intent(CalendarActivity.this, LocationSenderService.class);
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
