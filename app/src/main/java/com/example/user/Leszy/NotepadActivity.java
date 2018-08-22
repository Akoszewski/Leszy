package com.example.user.Leszy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class NotepadActivity extends AppCompatActivity {

    private static final String TAG="NotepadActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notepad_layout);
    }
    public void back2(View view) {
        onBackPressed();
    }
}
