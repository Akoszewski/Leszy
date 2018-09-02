package com.example.user.Leszy;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private static final String TAG = "MainActivity";

    private Button gotocal;
    private Button gotonotes;
    private Button btnSignInOut;
    private Button btnStartLocationService;

    private boolean isLocationShared = false;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        btnSignInOut = findViewById(R.id.sign_inout_button);
        if (!AppData.isUserSignedIn) {
            btnSignInOut.setText(R.string.sign_in);
        } else {
            btnSignInOut.setText(R.string.sign_out);
        }

        gotocal = (Button)findViewById(R.id.gotocal);
        gotocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(intent);

            }
        });

        gotonotes = (Button)findViewById(R.id.gotonotes);
        gotonotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NoteSelect.class);
                startActivity(intent);
            }
        });

        btnStartLocationService = (Button)findViewById(R.id.start_service_button);
        if (!AppData.isLocationSharingOn) {
            btnStartLocationService.setText(R.string.share_location);
        } else {
            btnStartLocationService.setText(R.string.stop_sharing_location);
        }
        btnStartLocationService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!AppData.isLocationSharingOn) {
                    // TODO: Check location permissions
                    AppData.isLocationSharingOn = true;
                    btnStartLocationService.setText(R.string.stop_sharing_location);
                    Intent startIntent = new Intent(MainActivity.this, LocationSenderService.class);
                    startService(startIntent);
                } else {
                    AppData.isLocationSharingOn = false;
                    btnStartLocationService.setText(R.string.share_location);
                    Intent stopIntent = new Intent(MainActivity.this, LocationSenderService.class);
                    stopService(stopIntent);
                }
            }
        });
    }

    public void map(View view) {
        Intent intent = new Intent(MainActivity.this, MapActivity.class);
        startActivity(intent);
    }

    public void signInOut(View view) {
        if (!AppData.isUserSignedIn) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        } else {
            AppData.isUserSignedIn = false;
            btnSignInOut.setText(R.string.sign_in);
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        if (AppData.isUserSignedIn) {
            btnSignInOut.setText(R.string.sign_out);
        } else {
            btnSignInOut.setText(R.string.sign_in);
        }
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

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
