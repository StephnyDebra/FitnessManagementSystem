package com.example.womenpower;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MySupplement extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editName,editDosage,editDescription,editDay,editId;
    Button btnAddData;
    Button btnViewAll;
    Button btnViewUpdate;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_supplement);
        myDb = new DatabaseHelper(this);

        editName = (EditText)findViewById(R.id.editText_name);
        editDosage = (EditText)findViewById(R.id.editText_type);
        editDescription = (EditText)findViewById(R.id.editText_time);
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
                            Toast.makeText(MySupplement.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(MySupplement.this, "Data not Deleted", Toast.LENGTH_SHORT).show();
                    }
                }
        );

    }

    public void UpdateData() {
        btnViewUpdate.setOnClickListener(
                (v) -> {
                    boolean isUpdate = myDb.updateData(editId.getText().toString() ,
                            editName.getText().toString() ,
                            editDosage.getText().toString() ,
                            editDescription.getText().toString() ,
                            editDay.getText().toString());
                    if(isUpdate == true)
                        Toast.makeText(MySupplement.this, "Data Updated", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(MySupplement.this, "Data not Updated", Toast.LENGTH_SHORT).show();

                }


        );
    }

    public void AddData() {
        btnAddData.setOnClickListener(
                (v)  -> {
                    boolean isInserted = myDb.insertData(editName.getText() . toString(),
                            editDosage.getText() . toString(),
                            editDescription.getText() . toString(),
                            editDay.getText() . toString());
                    if(isInserted == true)
                        Toast.makeText(MySupplement.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(MySupplement.this, "Data not Inserted", Toast.LENGTH_SHORT).show();
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
                            buffer.append("Id :" + res.getString(0)+"\n");
                            buffer.append("Name :" + res.getString(1)+"\n");
                            buffer.append("Dosage :" + res.getString(2)+"\n");
                            buffer.append("Description :" + res.getString(3)+"\n");
                            buffer.append("Day :" + res.getString(4)+"\n\n");

                        }

                        //show all data
                        showMessage("Data" , buffer.toString());

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