package com.example.fliprapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends ArrayAdapter<PersonData> {
    public CategoryAdapter(@NonNull Context context, List<PersonData> arrayList){
        super(context,R.layout.activity_list,arrayList);


    }
    
    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent){
        PersonData PersonData=getItem(position);
        
        if(view==null){
            view= LayoutInflater.from(getContext()).inflate(R.layout.activity_list,parent,false);
        }

        TextView textCategory=view.findViewById(R.id.textCategory);
        TextView textYear=view.findViewById(R.id.textYear);
        TextView textName=view.findViewById(R.id.textName);
        TextView textId=view.findViewById(R.id.textId);
        TextView textMotivation=view.findViewById(R.id.textMotivation);

        textName.setText("Name : "+PersonData.name);
        textId.setText("Id :\n"+PersonData.id);
        textCategory.setText("Category :\n"+PersonData.category);
        textYear.setText("Year :\n"+PersonData.year);
        textMotivation.setText("Motivation : "+PersonData.motivation);


        return view;
    }
}
