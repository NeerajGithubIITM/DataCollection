package com.example.datacollection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DataEntry extends AppCompatActivity {

    DatabaseBackend dbobject;
    EditText ed1, ed2;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Enter new data");
        setContentView(R.layout.activity_data_entry);

        dbobject = new DatabaseBackend(this);
        ed1 = findViewById(R.id.name_entry);
        ed2 = findViewById(R.id.age_entry);
        b1 = findViewById(R.id.btnadd);

        // OnClickListener for the save button
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String name = ed1.getText().toString();
            if(name.length() == 0){
                Toast.makeText(DataEntry.this, "You can't leave a field blank!!", Toast.LENGTH_SHORT).show();
                return;
            }
            int age;
            try{
                age = Integer.parseInt(ed2.getText().toString());
            }
            catch(NumberFormatException nfe)
            {
                Toast.makeText(DataEntry.this, "Invalid age. Please retry", Toast.LENGTH_SHORT).show();
                ed2.setText("");
                return;
            }
            //name,age store the entered values.
            //Passing name,age in insertData() to store into the database.
            boolean is_inserted = dbobject.insertData(name,age);

            //Verifying that the data insertion was successful.
            if(is_inserted)
                Toast.makeText(DataEntry.this, "Data saved successfully", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(DataEntry.this, "Couldn't save data. Please retry", Toast.LENGTH_SHORT).show();
            ed1.setText("");
            ed2.setText("");
            }
        });
    }
}
