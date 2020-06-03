package com.example.datacollection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

// As the name suggests this class is to create a custom list adapter for the listview which displays the saved data
public class CustomListAdapter extends ArrayAdapter<User> {
    private LayoutInflater mInflater;
    private ArrayList<User> users;
    private int mViewResourceId;


    public CustomListAdapter(Context context, int textViewResourceId, ArrayList<User> users) {
        super(context, textViewResourceId, users);
        this.users = users;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    // list_adapter_layout defines a custom layout that we want as an individual row of data in the listview. So that layout is inflated as a View object here.
    //By default the ArrayAdapter class uses textview. This function overrides the original getView and returns a custom made view.
    public View getView(int position, View conView, ViewGroup parent) {
        conView = mInflater.inflate(mViewResourceId, null);        //mViewResourceId contains the id of list_adapter_layout passed from ViewSavedData class.

        User user = users.get(position);

        if (user != null) {
            TextView id =  conView.findViewById(R.id.col_id);
            TextView name =  conView.findViewById(R.id.col_name);
            TextView age =  conView.findViewById(R.id.col_age);
            if (id != null) {
                id.setText("" + user.getId() + ".");
            }
            if (name != null) {
                name.setText((user.getName()));
            }
            if (age != null) {
                age.setText(("" + user.getAge()));
            }
        }

        return conView;
    }
}

