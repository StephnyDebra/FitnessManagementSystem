package com.example.powerwomen;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class mydiet extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editDate,editBreakfast,editLunch,editDinner,editId;
    Button btnAddData;
    Button btnViewAll;
    Button btnViewUpdate;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydiet);
        myDb = new DatabaseHelper(this);

        editDate = (EditText)findViewById(R.id.editText_name);
        editBreakfast = (EditText)findViewById(R.id.editText_type);
        editLunch = (EditText)findViewById(R.id.editText_time);
        editDinner = (EditText)findViewById(R.id.editText_day);
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

    public void DeleteData() {          //Deleting the data set in the database.
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRow = myDb.deleteData(editId.getText().toString());
                        if(deletedRow > 0)
                            Toast.makeText(mydiet.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(mydiet.this, "Data not Deleted", Toast.LENGTH_SHORT).show();
                    }
                }
        );

    }

    public void UpdateData() {
        btnViewUpdate.setOnClickListener(     //Update data set in the database.
                (v) -> {
                    boolean isUpdate = myDb.updateData(editId.getText().toString() ,
                            editDate.getText().toString() ,
                            editBreakfast.getText().toString() ,
                            editLunch.getText().toString() ,
                            editDinner.getText().toString());
                    if(isUpdate == true)
                        Toast.makeText(mydiet.this, "Data Updated", Toast.LENGTH_SHORT).show(); // toast message is displayed.
                    else
                        Toast.makeText(mydiet.this, "Data not Updated", Toast.LENGTH_SHORT).show(); //toast message is displayed.

                }


        );
    }

    public void AddData() { //Adding data to the database
        btnAddData.setOnClickListener(
                (v)  -> {
                    boolean isInserted = myDb.insertData(editDate.getText() . toString(),
                            editBreakfast.getText() . toString(),
                            editLunch.getText() . toString(),
                            editDinner.getText() . toString());
                    if(isInserted == true)
                        Toast.makeText(mydiet.this, "Data Inserted", Toast.LENGTH_SHORT).show(); // toast message is displayed
                    else
                        Toast.makeText(mydiet.this, "Data not Inserted", Toast.LENGTH_SHORT).show(); //toast message is displayed
                }
        );
    }

    public void viewAll() {         //Viewing all data in the database
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
                            buffer.append("Id :" + res.getString(0)+"\n");
                            buffer.append("Date :" + res.getString(1)+"\n");
                            buffer.append("Breakfast :" + res.getString(2)+"\n");
                            buffer.append("Lunch :" + res.getString(3)+"\n");
                            buffer.append("Dinner :" + res.getString(4)+"\n\n");

                        }

                        //show all data
                        showMessage("DIET LIST" , buffer.toString());

                    }
                }
        );
    }

    public void showMessage(String title, String Message) {      // show message
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}