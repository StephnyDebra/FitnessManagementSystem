package com.example.powerwomennewnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home2 extends AppCompatActivity {
    private Button home_btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        home_btn2 =(Button)findViewById(R.id.home_btn2);
        home_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openMyWorkout(); }
        });
    }
    public void openMyWorkout(){
        Intent intent = new Intent(Home2.this,MyWorkout.class);
        startActivity(intent);
    }
}
