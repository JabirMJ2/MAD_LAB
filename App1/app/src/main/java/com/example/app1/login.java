package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;

public class login extends AppCompatActivity {
    Button back;
    CheckBox backCh;
    RadioButton male;
    RadioButton female;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        back = findViewById(R.id.back_btn);
         backCh = findViewById(R.id.back_ch);
         male = findViewById(R.id.male);
         female = findViewById(R.id.female);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean checked = backCh.isChecked();
                Log.d("back", checked.toString());

                Intent i = new Intent(login.this, MainActivity.class);
                startActivity(i);


            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences sh = getSharedPreferences("login_preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sh.edit();

        editor.putBoolean("goback", backCh.isChecked());
        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sh = getSharedPreferences("login_preferences", MODE_PRIVATE);
        backCh.setChecked(sh.getBoolean("goback", false));
    }
}