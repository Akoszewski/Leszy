package com.example.user.Leszy;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;

public class MapActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    MapView map = null;
    private static final int PERMISSION_FINE_LOCATION = 99;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        setContentView(R.layout.activity_map);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSION_FINE_LOCATION);
        }

        map = (MapView) findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);

        final double lat = 52.29377413882149;
        final double lon = 19.401969817518;

        GeoPoint point = new GeoPoint(lat, lon);
        MapController mapController = (MapController) map.getController();
        mapController.setZoom(7);
        mapController.setCenter(point);

        Place place = new Place(map, "Pałac Kultury i Nauki", 52.2322, 21.0083);

        UserLocationOverlay mylocation = new UserLocationOverlay(new GpsMyLocationProvider(getApplicationContext()), map);
        mylocation.enableMyLocation();
        mylocation.scalePersonIcon(2, 2);
        map.getOverlays().add(mylocation);

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

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_FINE_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    // block maps
                }
                break;
            }
        }
    }

    public void onResume(){
        super.onResume();
        map.onResume();
    }

    public void onPause(){
        super.onPause();
        map.onPause();
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
                Intent a = new Intent(MapActivity.this, MainActivity.class);
                startActivity(a);
                break;
            case R.id.nav_login:
                Intent b = new Intent(MapActivity.this, LoginActivity.class);
                startActivity(b);
                break;
            case R.id.nav_calendar:
                Intent c = new Intent(MapActivity.this, CalendarActivity.class);
                startActivity(c);
                break;
            case R.id.nav_notes:
                Intent d = new Intent(MapActivity.this, NoteSelect.class);
                startActivity(d);
                break;
            case R.id.nav_map:
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
            case R.id.nav_location:
                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                Menu menu = navigationView.getMenu();
                MenuItem nav_location = menu.findItem(R.id.nav_location);

                if (!AppData.isLocationSharingOn) {
                    nav_location.setTitle(R.string.stop_sharing_location);
                    AppData.isLocationSharingOn = true;
                    Intent startIntent = new Intent(MapActivity.this, LocationSenderService.class);
                    startService(startIntent);
                } else {
                    nav_location.setTitle(R.string.share_location);
                    AppData.isLocationSharingOn = false;
                    Intent stopIntent = new Intent(MapActivity.this, LocationSenderService.class);
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