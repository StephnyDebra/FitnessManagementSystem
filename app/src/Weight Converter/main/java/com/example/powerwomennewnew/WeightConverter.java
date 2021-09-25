package com.example.powerwomennewnew;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class WeightConverter extends AppCompatActivity {
    EditText et_weight;
    RadioButton rd_btn_P;
    RadioButton rd_btn_S;
    Button btn_calculate;
    TextView tv_answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_converter);

        et_weight = findViewById(R.id.et_weight);
        rd_btn_P = findViewById(R.id.rd_btn_P);
        rd_btn_S = findViewById(R.id.rd_btn_S);
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

    private void calculateAnswer() {
        Calculations cal = new Calculations();
        String value = et_weight.getText().toString();

        if(TextUtils.isEmpty(value)) {
            Toast.makeText(this, "Enter the Weight", Toast.LENGTH_SHORT).show();
        }else {
            Float weight = Float.parseFloat(value);
            if(rd_btn_P.isChecked()){
                weight = cal.convertKilogramToPounds(weight);
            } else if(rd_btn_S.isChecked()){
                weight = cal.convertKilogramToStone(weight);
            } else {
                Toast.makeText(this, "Select the radio button", Toast.LENGTH_SHORT).show();
                weight = 0.0f;
            }
            tv_answer.setText(new Float(weight).toString());
        }
    }
}
