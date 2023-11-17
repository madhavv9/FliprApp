package com.example.fliprapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MoreThanOneAdapter extends ArrayAdapter<MoreThanOne> {

    public MoreThanOneAdapter(@NonNull Context context, List<MoreThanOne> arrayList){
        super(context,R.layout.morethanone_list,arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent){
        MoreThanOne PersonData=getItem(position);

        if(view==null){
            view= LayoutInflater.from(getContext()).inflate(R.layout.morethanone_list,parent,false);
        }

        TextView moreThanOne_Id=view.findViewById(R.id.moreThanOne_Id);
        TextView moreThanOne_Name=view.findViewById(R.id.moreThanOne_Name);
        TextView moreThanOne_Freq=view.findViewById(R.id.moreThanOne_Freq);

        moreThanOne_Id.setText("Id : \n"+PersonData.getId());
        moreThanOne_Name.setText("Name :\n"+PersonData.getName());
        moreThanOne_Freq.setText("No. of Times :\n"+PersonData.getFrequency());


        return view;
    }

}