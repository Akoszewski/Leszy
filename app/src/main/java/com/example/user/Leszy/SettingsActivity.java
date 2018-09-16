package com.example.user.Leszy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;



public class SettingsActivity extends SideMenuActivity {

    Button changelog;
    Button changepass;
    Button changemail;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.settings_layout);

        changelog = (Button) findViewById(R.id.butt);
        changepass = (Button) findViewById(R.id.butt2);
        changemail = (Button) findViewById(R.id.butt3);
    }
}
