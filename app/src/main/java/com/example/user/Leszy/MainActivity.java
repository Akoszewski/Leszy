package com.example.user.Leszy;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Button gotocal;
    private Button gotonote;
    private Button btnSignInOut;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);

        AppData.isUserSignedIn = getIntent().getBooleanExtra("isUserSignedIn", false);
        btnSignInOut = findViewById(R.id.sign_inout_button);

        gotocal = (Button)findViewById(R.id.gotocal);
        gotocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(intent);

            }
        });

        gotonote = (Button)findViewById(R.id.gotonote);
        gotonote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NotepadActivity.class);
                startActivity(intent);

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
            btnSignInOut.setText("Zaloguj");
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        if (AppData.isUserSignedIn) {
            btnSignInOut.setText("Wyloguj");
        } else {
            btnSignInOut.setText("Zaloguj");
        }
    }
}
