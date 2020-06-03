package com.example.datacollection;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewSavedData extends AppCompatActivity {

    DatabaseBackend db_object;
    ListView l1;
    ArrayList<User> userList;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("View saved data");
        setContentView(R.layout.activity_view_saved_data);

        db_object = new DatabaseBackend(this);
        l1 = findViewById(R.id.data_view);
        userList = new ArrayList<>();
        Cursor res = db_object.getData();    //Calling getData() defined in DatabaseBackend class to retrieve all the stored data.
        l1.setEmptyView(findViewById(R.id.empty_message));

        //When the listview is empty, the textview is made visible which displays an empty message.
        //So when there is nothing in the database, nothing is displayed on the list view and only this message is seen.

        //But if the database is non-empty, the below if{} block is executed to proceed with displaying data on the list view.
        if(res.getCount()!=0){
            while(res.moveToNext()){
                user = new User(Integer.parseInt(res.getString(0)),res.getString(1),Integer.parseInt(res.getString(2)));
                userList.add(user);
            }
        }
        else
            return;

        // Since we need to display 3 columns in the list view, we need to make a custom list adapter according to our needs. The 3 column layout is defined in list_adapter_layout.
        CustomListAdapter adapter = new CustomListAdapter(this,R.layout.list_adapter_layout,userList);
        l1.setAdapter(adapter);
    }
}
