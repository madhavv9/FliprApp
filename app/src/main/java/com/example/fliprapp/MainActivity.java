package com.example.fliprapp;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.fliprapp.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{


    ActivityMainBinding binding;
    CategoryAdapter categoryAdapter;
    PersonData personData;
    Button moreThanOneWinner;
    ListView listview,moreThanOneListView;
    String prize_category, prize_year;
    String first_name, surname, name, person_id, person_share, person_motivation;
    List<PersonData> person = new ArrayList<>();
    MoreThanOne moreThanOneData;
    List<MoreThanOne> moreThanOneList = new ArrayList<>();
    HashMap<String,Integer> moreThanOneMapId = new HashMap<String, Integer>();
    HashMap<String,String> IdAndNames = new HashMap<String, String>();
    HashMap<String,Boolean> visited=new HashMap<>();
    Spinner spinnerFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);

        //Filter Category dropdown
        spinnerFilter = findViewById(R.id.spinnerCategory);
        ArrayAdapter<CharSequence> filterAdapter = ArrayAdapter.createFromResource(this, R.array.filterCategory, R.layout.spinner_category);
        filterAdapter.setDropDownViewResource(R.layout.spinner_category);
        spinnerFilter.setAdapter(filterAdapter);
        spinnerFilter.setOnItemSelectedListener(this);

        //filter more than 1 time nobel prize winner
        moreThanOneWinner=(Button) findViewById(R.id.moreThanOneWinner);

        // listview
        listview = findViewById(R.id.listview);

        // Data fetch
        String url = "https://api.nobelprize.org/v1/prize.json";
        AndroidNetworking.initialize(MainActivity.this);
        AndroidNetworking.get(url)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {


                            JSONArray jsonArrayPrizes = response.getJSONArray("prizes");
                            for (int i = 0; i < jsonArrayPrizes.length(); i++) {
                                JSONObject jsonObject = jsonArrayPrizes.getJSONObject(i);

                                prize_category = jsonObject.getString("category");
                                prize_year = jsonObject.getString("year");

                                if (Integer.parseInt(prize_year) < 1900 || Integer.parseInt(prize_year) > 2018)
                                    continue;

                                if (!jsonObject.has("laureates")) continue;
                                JSONArray laureates = jsonObject.getJSONArray("laureates");


                                for (int j = 0; j < laureates.length(); j++) {
                                    JSONObject laureatesObj = laureates.getJSONObject(j);
                                    first_name = laureatesObj.getString("firstname");

                                    if (laureatesObj.has("surname")) {
                                        surname = laureatesObj.getString("surname");
                                    } else surname = "";

                                    name = first_name + " " + surname;

                                    person_id = laureatesObj.getString("id");
                                    person_motivation = laureatesObj.getString("motivation");
                                    person_share = laureatesObj.getString("share");

                                    personData = new PersonData(person_id, name, person_motivation, person_share, prize_category, prize_year);
                                    person.add(personData);

                                    if(moreThanOneMapId.containsKey(person_id)){
                                        int val=moreThanOneMapId.get(person_id);
                                        moreThanOneMapId.put(person_id,val+1);
                                        if(moreThanOneMapId.get(person_id)>1 && !IdAndNames.containsKey(person_id)){
                                            IdAndNames.put(person_id,name);
                                        }
                                    }
                                    else moreThanOneMapId.put(person_id,1);

                               }
                                Collections.sort(person,PersonData::compareTo);
                                categoryAdapter = new CategoryAdapter(MainActivity.this, person);
                                listview.setAdapter(categoryAdapter);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("ERROR", anError.toString());
                    }
                });



        moreThanOneWinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(HashMap.Entry<String,String>entry:IdAndNames.entrySet()){
                    int id=Integer.parseInt(entry.getKey());

                    if(!visited.containsKey(entry.getKey())) {
                        moreThanOneData = new MoreThanOne(id,entry.getValue(),moreThanOneMapId.get(entry.getKey()));
                        moreThanOneList.add(moreThanOneData);
                        Log.d("TAG", entry.getKey()+" "+IdAndNames.get(entry.getKey())+" "+entry.getValue());
                        visited.put(entry.getKey(), true);
                    }
                }
                Collections.sort(moreThanOneList,MoreThanOne::compareTo);
                Intent intent = new Intent(MainActivity.this, NewActivity.class);
                intent.putExtra("LIST",(Serializable)moreThanOneList);
                startActivity(intent);

            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        listview.setAdapter(null);
        List<PersonData>data=new ArrayList<>();
        String text=parent.getItemAtPosition(position).toString();
        if(text.equals("chemistry")) {
            for(int i=0;i<person.size();i++){
                if(person.get(i).getCategory().equals("chemistry")){
                    data.add(person.get(i));
                }
            }
        }
        else if(text.equals("economics")) {
            for(int i=0;i<person.size();i++){
                if(person.get(i).getCategory().equals("economics")){
                    data.add(person.get(i));
                }
            }
        }
        else if(text.equals("literature")) {
            for(int i=0;i<person.size();i++){
                if(person.get(i).getCategory().equals("literature")){
                    data.add(person.get(i));
                }
            }
        }
        else if(text.equals("medicine")) {
            for(int i=0;i<person.size();i++){
                if(person.get(i).getCategory().equals("medicine")){
                    data.add(person.get(i));
                }
            }
        }
        else if(text.equals("peace")) {
            for(int i=0;i<person.size();i++){
                if(person.get(i).getCategory().equals("peace")){
                    data.add(person.get(i));
                }
            }
        }
        else if(text.equals("physics")) {
            for(int i=0;i<person.size();i++){
                if(person.get(i).getCategory().equals("physics")){
                    data.add(person.get(i));
                }
            }
        }
        else{
            data=person;
        }

        Collections.sort(data,PersonData::compareTo);
        categoryAdapter = new CategoryAdapter(MainActivity.this, data);
        listview.setAdapter(categoryAdapter);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        Collections.sort(person,PersonData::compareTo);
        categoryAdapter = new CategoryAdapter(MainActivity.this, person);
        listview.setAdapter(categoryAdapter);
    }
}