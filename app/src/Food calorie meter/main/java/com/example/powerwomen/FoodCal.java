package com.example.powerwomen;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class FoodCal extends AppCompatActivity {
    EditText et_size;
    EditText et_name;
    RadioButton rd_btn_C;
    RadioButton rd_btn_P;
    RadioButton rd_btn_F;
    RadioButton rd_btn_T;
    Button btn_calculate;
    TextView tv_answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_cal);

        et_size = findViewById(R.id.et_size);
        rd_btn_C = findViewById(R.id.rd_btn_C);
        rd_btn_P = findViewById(R.id.rd_btn_P);
        rd_btn_F = findViewById(R.id.rd_btn_F);
        rd_btn_T = findViewById(R.id.rd_btn_T);
        btn_calculate = findViewById(R.id.btn_calculate);
        tv_answer = findViewById(R.id.tv_answer);
    }
    @Override
    protected void onResume() {
        super.onResume();
        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateAnswer();
            }
        });

    }

    private void calculateAnswer() {  // calculation method
        Calculations cal = new Calculations();
        String value = et_size.getText().toString();

        if(TextUtils.isEmpty(value)) {
            Toast.makeText(this, "Enter the  Serving Size", Toast.LENGTH_SHORT).show(); // displaying a message to enter the serving size
        }else {
            Float size = Float.parseFloat(value);
            if(rd_btn_C.isChecked()){
                size = cal.convertCabohydrate(size);
            } else if(rd_btn_P.isChecked()) {
                size = cal.convertProtein(size);
            } else if (rd_btn_F.isChecked()) {
                size = cal.convertFat(size);
            } else if (rd_btn_T.isChecked()) {
                size = cal.convertTotal(size);
            } else {
                Toast.makeText(this, "Select the radio button", Toast.LENGTH_SHORT).show(); // display the options with radio buttons
                size = 0.0f;
            }

            tv_answer.setText(new Float(size).toString()); //to show the answer according to the radio button.
        }

    }
}