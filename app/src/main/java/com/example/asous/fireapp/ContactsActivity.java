package com.example.asous.fireapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.asous.fireapp.adapters.Contact;
import com.example.asous.fireapp.adapters.ListAdapter;

import java.util.ArrayList;

public class ContactsActivity extends Activity {
    ArrayList<Contact> contacts ;
    Contact newContact1,newContact2 ;
    ListAdapter listAdapter ;
    ListView listView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        listView = (ListView)findViewById(R.id.listview) ;
        newContact1 = new Contact("ayoub","ayoubgmail.com") ;
        newContact2 = new Contact("soussi","soussigmail.com") ;
        contacts = new ArrayList<>() ;
        contacts.add(newContact1) ;
        contacts.add(newContact2) ;
        contacts.add(newContact1) ;
        contacts.add(newContact2) ;
        contacts.add(newContact1) ;
        contacts.add(newContact2) ;
        contacts.add(newContact1) ;
        listAdapter = new ListAdapter(ContactsActivity.this,contacts) ;
        listView.setAdapter(listAdapter);
    }
}
