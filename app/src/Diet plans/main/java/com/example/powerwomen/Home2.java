package com.example.powerwomen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home2 extends AppCompatActivity {
    private Button btn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        btn2 =(Button)findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openmydiet(); }
        });
    }
    public void openmydiet(){
        Intent intent = new Intent(Home2.this,mydiet.class);
        startActivity(intent);
    }




}