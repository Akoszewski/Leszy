package com.example.user.Leszy;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends SideMenuActivity {

    Button btnLogin;
    EditText editLogin, editPassword;

    TextView textView;
    int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button)findViewById(R.id.button);
        editLogin = (EditText)findViewById(R.id.editText);
        editPassword = (EditText)findViewById(R.id.editText2);

        textView = (TextView)findViewById(R.id.textView3);
        textView.setVisibility(View.GONE);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editLogin.getText().toString().equals("admin") &&
                        editPassword.getText().toString().equals("admin")) {
                    AppData.isUserSignedIn = true;
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
                    textView.setVisibility(View.VISIBLE);
                    textView.setBackgroundColor(Color.RED);
                    counter--;
                    textView.setText(Integer.toString(counter));

                    if (counter == 0) {
                        btnLogin.setEnabled(false);
                        textView.setText("Konto zablokowane!");
                    }
                }
            }
        });
    }
}
