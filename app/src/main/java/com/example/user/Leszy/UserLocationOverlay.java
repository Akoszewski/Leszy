package com.example.user.Leszy;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.mylocation.IMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

public class UserLocationOverlay extends MyLocationNewOverlay {

    public UserLocationOverlay(IMyLocationProvider myLocationProvider, MapView mapView) {
        super(mapView);
    }

    public void scalePersonIcon(float scaleWidth, float scaleHeight) {
        mPersonBitmap = getScaledBitmap(mPersonBitmap, scaleWidth, scaleHeight);
    }

    private Bitmap getScaledBitmap(Bitmap bm, float scaleWidth, float scaleHeight) {
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizedBitmap =
                Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, false);
        bm.recycle();
        return resizedBitmap;
    }
}
