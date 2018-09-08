package com.example.user.Leszy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";

    private Button btnSignInOut;
    private Button btnStartLocationService;

    private boolean isLocationShared = false;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);

        btnSignInOut = findViewById(R.id.sign_inout_button);
        if (!AppData.isUserSignedIn) {
            btnSignInOut.setText(R.string.sign_in);
        } else {
            btnSignInOut.setText(R.string.sign_out);
        }

        Button gotocal = (Button) findViewById(R.id.gotocal);
        gotocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

        Button gotonotes = (Button) findViewById(R.id.gotonotes);
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){

            case R.id.nav_home:
                break;
            case R.id.nav_login:
                Intent b = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(b);
                break;
            case R.id.nav_calendar:
                Intent c = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(c);
                break;
            case R.id.nav_notes:
                Intent d = new Intent(MainActivity.this, NoteSelect.class);
                startActivity(d);
                break;
            case R.id.nav_map:
                Intent e = new Intent(MainActivity.this, MapActivity.class);
                startActivity(e);
                break;
            case R.id.nav_twitter:
                Intent f = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/nasze_lasy"));
                startActivity(f);
                break;
            case R.id.nav_location:
                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                Menu menu = navigationView.getMenu();
                MenuItem nav_location = menu.findItem(R.id.nav_location);

                if (!AppData.isLocationSharingOn) {
                    nav_location.setTitle(R.string.stop_sharing_location);
                    AppData.isLocationSharingOn = true;
                    Intent startIntent = new Intent(MainActivity.this, LocationSenderService.class);
                    startService(startIntent);
                } else {
                    nav_location.setTitle(R.string.share_location);
                    AppData.isLocationSharingOn = false;
                    Intent stopIntent = new Intent(MainActivity.this, LocationSenderService.class);
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
