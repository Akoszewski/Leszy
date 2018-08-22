package com.example.user.Leszy;

import android.content.Context;
import android.location.Location;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

public class MapActivity extends AppCompatActivity {
    MapView map = null;
    private double latitude;
    private double longitude;
    private double altitude;
    private double accuracy;
    MapController mapController;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        setContentView(R.layout.activity_map);

        map = (MapView) findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);

        final double lat = 52.29377413882149 * 1000000;
        final double lon = 19.401969817518 * 1000000;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                GeoPoint point = new GeoPoint((int) lat,(int) lon);
                mapController = (MapController) map.getController();
                mapController.setZoom(7);
                mapController.setCenter(point);
            }
        }, 200);

        MyLocationNewOverlay mylocation = new MyLocationNewOverlay(new GpsMyLocationProvider(getApplicationContext()), map);
        mylocation.enableMyLocation();

        map.getOverlays().add(mylocation);

    }

    public void onLocationChanged(Location location) {

        latitude = location.getLatitude();
        longitude = location.getLongitude();
        altitude = location.getAltitude();
        accuracy = location.getAccuracy();

        GeoPoint p = new GeoPoint((int)latitude,  (int)longitude);

        mapController.animateTo(p);
        mapController.setCenter(p);

        MyLocationNewOverlay mylocation = new MyLocationNewOverlay(new GpsMyLocationProvider(getApplicationContext()), map);

        mylocation.enableMyLocation();
        //mylocation.enableFollowLocation();
        map.getOverlays().add(mylocation);
    }

    public void onResume(){
        super.onResume();
        map.onResume();
    }

    public void onPause(){
        super.onPause();
        map.onPause();
    }
}