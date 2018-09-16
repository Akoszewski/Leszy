package com.example.user.Leszy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends SideMenuActivity {

    private static final String TAG = "MainActivity";

    private WebView mywebView;

    private Button btnSignInOut;
    private Button btnStartLocationService;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);

        mywebView = (WebView)findViewById(R.id.webView);
        WebSettings webSettings = mywebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mywebView.loadUrl("http://www.lasy.gov.pl/pl/informacje/aktualnosci");
        mywebView.setWebViewClient(new WebViewClient());


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
                Intent intent = new Intent(MainActivity.this, NoteSelectActivity.class);
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
}
