package com.example.user.Leszy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class NotepadActivity extends AppCompatActivity {

    EditText EditText1;
    EditText editNote;

    private static final String TAG="NotepadActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notepad_layout);

        EditText1 = (EditText) findViewById(R.id.EditText1);
        editNote = (EditText) findViewById(R.id.editText);

        final FloatingActionButton fab3 = (FloatingActionButton) findViewById(R.id.fab3);
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText1.setText(Open("Note" + editNote.getText() + ".txt"));
                fab3.setEnabled(false);
            }
        });

        final FloatingActionButton fab4 = (FloatingActionButton) findViewById(R.id.fab4);
        fab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteFile("Note" + editNote.getText() + ".txt");
                Delete("Note" + editNote.getText() + ".txt");
                fab3.setEnabled(true);
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i=1;
                if(fab3.isEnabled())
                {
                    for (i = 1; i < 10; i++) {
                        if (FileExists("Note" + i + ".txt"))
                        {}
                            else {
                            Save("Note" + i + ".txt");
                            break;
                        }
                    }
                }
                else {
                    Save("Note" + editNote.getText() + ".txt");
                    fab3.setEnabled(true);
                    }
            }
        });


        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(NotepadActivity.this, NoteSelectActivity.class);
                NotepadActivity.this.startActivity(myIntent);
            }
        });
    }

    public String Open(String fileName) {
        String content = "";
        if (FileExists(fileName)) {
            try {
                InputStream in = openFileInput(fileName);
                if ( in != null) {
                    InputStreamReader tmp = new InputStreamReader( in );
                    BufferedReader reader = new BufferedReader(tmp);
                    String str;
                    StringBuilder buf = new StringBuilder();
                    while ((str = reader.readLine()) != null) {
                        buf.append(str + "\n");
                    } in .close();
                    content = buf.toString();
                }
            } catch (java.io.FileNotFoundException e) {} catch (Throwable t) {
                Toast.makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
            }
        }
        return content;
    }

    public boolean FileExists(String fname) {
        File file = getBaseContext().getFileStreamPath(fname);
        return file.exists();
    }

    public void Save(String fileName) {
        try {
            OutputStreamWriter out =
                    new OutputStreamWriter(openFileOutput(fileName, 0));
            out.write(EditText1.getText().toString());
            out.close();
            Toast.makeText(this, "Notatka zapisana!", Toast.LENGTH_SHORT).show();
        } catch (Throwable t) {
            Toast.makeText(this, "Wyjatek: " + t.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public void Delete(String fileName) {
        try {
            deleteFile(fileName);
            Toast.makeText(this, "Notatka usunięta!", Toast.LENGTH_SHORT).show();
        } catch (Throwable t) {
            Toast.makeText(this, "Wyjątek: " + t.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
