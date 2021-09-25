package com.example.powerwomennewnew;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MyWorkout extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editName,editType,editTime,editDay,editId;
    Button btnAddData;
    Button btnViewAll;
    Button btnViewUpdate;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_workout);
        myDb = new DatabaseHelper(this);

        editName = (EditText)findViewById(R.id.editText_name);
        editType = (EditText)findViewById(R.id.editText_type);
        editTime = (EditText)findViewById(R.id.editText_time);
        editDay = (EditText)findViewById(R.id.editText_day);
        editId = (EditText)findViewById(R.id.editText_id);
        btnAddData = (Button)findViewById(R.id.button_add);
        btnViewAll = (Button)findViewById(R.id.button_view);
        btnViewUpdate = (Button)findViewById(R.id.button_update);
        btnDelete = (Button)findViewById(R.id.button_delete);
        AddData();
        viewAll();
        UpdateData();
        DeleteData();
    }

    public void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRow = myDb.deleteData(editId.getText().toString());
                        if(deletedRow > 0)
                            Toast.makeText(MyWorkout.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(MyWorkout.this, "Data not Deleted", Toast.LENGTH_SHORT).show();
                    }
                }
        );

    }

    public void UpdateData() {
        btnViewUpdate.setOnClickListener(
                (v) -> {
                    boolean isUpdate = myDb.updateData(editId.getText().toString() ,
                            editName.getText().toString() ,
                            editType.getText().toString() ,
                            editTime.getText().toString() ,
                            editDay.getText().toString());
                    if(isUpdate == true)
                        Toast.makeText(MyWorkout.this, "Data Updated", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(MyWorkout.this, "Data not Updated", Toast.LENGTH_SHORT).show();

                }


        );
    }

    public void AddData() {
        btnAddData.setOnClickListener(
                (v)  -> {
                    boolean isInserted = myDb.insertData(editName.getText() . toString(),
                            editType.getText() . toString(),
                            editTime.getText() . toString(),
                            editDay.getText() . toString());
                    if(isInserted == true)
                        Toast.makeText(MyWorkout.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(MyWorkout.this, "Data not Inserted", Toast.LENGTH_SHORT).show();
                }
        );
    }

    public void viewAll() {
        btnViewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDb.getAllData();
                        Cursor res =myDb.getAllData();
                        if(res.getCount() == 0) {
                            //show message
                            showMessage("Error" ,"Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("ID :" + res.getString(0)+"\n");
                            buffer.append("Workout Name :" + res.getString(1)+"\n");
                            buffer.append("Workout Type :" + res.getString(2)+"\n");
                            buffer.append("Time Duration :" + res.getString(3)+"\n");
                            buffer.append("Day :" + res.getString(4)+"\n\n");

                        }

                        //show all data
                        showMessage("My WorkOut List" , buffer.toString());

                    }
                }
        );
    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}


