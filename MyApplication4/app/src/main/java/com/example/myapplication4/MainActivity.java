package com.example.myapplication4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText t1=findViewById(R.id.n1);
        EditText t2=findViewById(R.id.n2);
        Button add=findViewById(R.id.b1);
        Button sub=findViewById(R.id.b2);
        Button mul=findViewById(R.id.b3);
        Button div=findViewById(R.id.b4);
        TextView tv1=findViewById(R.id.t1);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double a1 = Double.parseDouble(t1.getText().toString());
                Double a2 = Double.parseDouble(t2.getText().toString());
                Double r = a1 + a2;
                tv1.setText("Result is: " + String.valueOf(r));
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double s1=Double.parseDouble(t1.getText().toString());
                Double s2=Double.parseDouble(t2.getText().toString());
                Double r=s1-s2;
                tv1.setText("Result is: "+String.valueOf(r));
            }
        });
        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double m1 = Double.parseDouble(t1.getText().toString());
                Double m2 = Double.parseDouble(t2.getText().toString());
                Double r = m1 * m2;
                tv1.setText("Result is" + String.valueOf(r));

            }
        });
        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double d1=Double.parseDouble(t1.getText().toString());
                Double d2=Double.parseDouble(t2.getText().toString());
                Double r=d1/d2;
                tv1.setText("Result is" +String.valueOf(r));
            }
        });


    }

}
