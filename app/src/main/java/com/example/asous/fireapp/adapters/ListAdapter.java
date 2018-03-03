package com.example.asous.fireapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.asous.fireapp.R;

import java.util.ArrayList;

/**
 * Created by asous on 02/03/2018.
 */

public class ListAdapter extends ArrayAdapter<Contact> {


    public ListAdapter(Context context, ArrayList<Contact> arrayList) {
        super(context, 0 , arrayList );
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contact contact = getItem(position) ;
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.singleitem, parent, false);
        }
        TextView username = (TextView) convertView.findViewById(R.id.usernameid);
        TextView email = (TextView) convertView.findViewById(R.id.emailid);
        // Populate the data into the template view using the data object
        username.setText(contact.username);
        email.setText(contact.email);
        // Return the completed view to render on screen
        return convertView;

    }
}
