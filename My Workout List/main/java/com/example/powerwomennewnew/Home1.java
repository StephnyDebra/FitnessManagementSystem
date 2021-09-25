package com.example.powerwomennewnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home1 extends AppCompatActivity {
    private Button workout_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home1);


        workout_btn =(Button)findViewById(R.id.workout_btn);
        workout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openHome2(); }
        });
    }
    public void openHome2(){
        Intent intent = new Intent(Home1.this,Home2.class);
        startActivity(intent);
    }
}

