package com.example.user.Leszy;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class Place {
    private Marker marker;

    public Place(MapView map, String title, double latitude, double longitude) {
        marker = new Marker(map);
        marker.setTextLabelBackgroundColor(0xfbf6e6);
        marker.setTextLabelForegroundColor(0x000000);
        marker.setTitle(title);
        marker.setIcon(null);
        marker.setPosition(new GeoPoint(latitude, longitude));
        map.getOverlays().add(marker);
    }
}
