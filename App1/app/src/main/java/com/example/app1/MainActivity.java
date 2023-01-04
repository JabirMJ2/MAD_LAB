package com.example.app1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Array;

public class MainActivity extends AppCompatActivity {
    enum COUNTRIESE {
        INDIA,
        USA,
        CHINA,
        KOREA,
    }

    private static final String USERNAME = "jabirmj";
    private static final String PASSWORD = "root";
    private static final String COUNTRY = "India";
    private static final COUNTRIESE[] COUNTRIES =  COUNTRIESE.values();

    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText usernameET = findViewById(R.id.username);
        EditText passwordET = findViewById(R.id.password);
        Button submit = findViewById(R.id.submit);
        EditText country = findViewById(R.id.countryEt);
        ListView list = (ListView) findViewById(R.id.list);

//        Create navigation
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.on_create);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        try{
//            EditText tv1 = (EditText) findViewById(1);
//            tv1.setText("hair");
//        }
//        catch(Exception e){
//            Toast.makeText(getApplicationContext(), "No ET found", Toast.LENGTH_LONG).show();
//        }

//        Dynamic spinner
        Spinner countries = new Spinner(this);
        ArrayAdapter<COUNTRIESE> adapter = new ArrayAdapter<COUNTRIESE>(this, android.R.layout.simple_spinner_dropdown_item, COUNTRIES);
        countries.setAdapter(adapter);
        list.setAdapter(adapter);
        LinearLayout linear = (LinearLayout) findViewById(R.id.linear);
        LinearLayout.LayoutParams layoutParams= new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        countries.setDropDownWidth(1000);
        countries.setMinimumWidth(500);
        countries.setBackgroundResource(R.color.white);
        linear.addView(countries, layoutParams);

//        Alert
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Hi there");
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

//        Images
        ImageView image1 = findViewById(R.id.image1);
        ImageView image2 = findViewById(R.id.image2);

//        google
        Button google = (Button) findViewById(R.id.google);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = String.valueOf(usernameET.getText());
                String password = String.valueOf(passwordET.getText());

                if(username.equals(USERNAME) && password.equals(PASSWORD)) {
                    Toast.makeText(getApplicationContext(), "Login Succesfull", Toast.LENGTH_LONG).show();
                    Intent in = new Intent(MainActivity.this, login.class);
                    startActivity(in);
                }
            }
        });

        countries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selected_country = String.valueOf(countries.getSelectedItem().toString());
                country.setText(selected_country);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image1.setVisibility(View.GONE);
                image2.setVisibility(View.VISIBLE);
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image2.setVisibility(View.GONE);
                image1.setVisibility(View.VISIBLE);
            }
        });

//        google.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
////                startActivity(intent);
//
//                DBHelper db = new DBHelper(MainActivity.this);
//                boolean result = db.insertData("jabirmj");
//                if(!result) {
//                    Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
//            }
//        });

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(MainActivity.this);
                Cursor res = db.getData();
                if(res.getCount() == 0){
                    Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("id: " + res.getString(0) + "\n");
                    buffer.append("name: " + res.getString(1) + "\n");
                    buffer.append("contact: " + res.getString(2) + "\n");
                }

                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                builder1.setTitle("data");
                builder1.setMessage(buffer.toString());
                builder1.setCancelable(true);
                AlertDialog alertDialog1 = builder1.create();
                alertDialog1.show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_LONG).show();
        return super.onOptionsItemSelected(item);
    }

    //    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//
//        builder.setMessage("do you wanna exit");
//        builder.setTitle("Alert!");
//        builder.setCancelable(false);
//
//        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
//            Toast.makeText(getApplicationContext(), which, Toast.LENGTH_LONG).show();
//            finish();
//        });
//
//        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
//            dialog.cancel();
//        });
//
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//    }
}