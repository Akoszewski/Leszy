package com.example.user.Leszy;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG="MainActivity";

    private Button gotocal;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);
        gotocal=(Button) findViewById(R.id.gotocal);

        gotocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,CalendarAcitivity.class);
                startActivity(intent);

            }
        });
    }


    public void map(View view) {
        Intent intent = new Intent(MainActivity.this, MapActivity.class);
        startActivity(intent);
    }


    public void exit1(View view) {
        finish();
        System.exit(0);
    }

}
