package com.example.fliprapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

public class NewActivity extends AppCompatActivity {

    List<MoreThanOne>list;
    ListView moreThanOneListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        moreThanOneListView = findViewById(R.id.moreThanOneListView);

        Intent i=getIntent();
        list=(List<MoreThanOne>)i.getSerializableExtra("LIST");
        MoreThanOneAdapter moreThanOneAdapter=new MoreThanOneAdapter(NewActivity.this,list);
        moreThanOneListView.setAdapter(moreThanOneAdapter);
    }
}