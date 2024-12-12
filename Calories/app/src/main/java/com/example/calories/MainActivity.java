package com.example.calories;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1=(Button) findViewById(R.id.start);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
                //return 0;
            }
        });


    }
    public void start() {
        Intent intent = new Intent(MainActivity.this, Calories2.class);
        startActivity(intent);
    }




}