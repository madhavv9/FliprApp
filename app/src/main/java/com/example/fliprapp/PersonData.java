package com.example.fliprapp;

import java.time.Year;
import java.util.Comparator;

public class PersonData implements Comparable<PersonData> {
    String id,name,motivation,share,category,year;

    public PersonData(String id,String name,String motivation,String share,String category,String year){
        this.id=id;
        this.name=name;
        this.motivation=motivation;
        this.share=share;
        this.category=category;
        this.year=year;
    }

    public int getid(){
        return Integer.parseInt(id);
    }
    public String getName(){
        return name;
    }
    public int getYear(){
        return Integer.parseInt(year);
    }
    public String getCategory(){
        return category;
    }


    @Override
    public int compareTo(PersonData o) {
        int c= Integer.parseInt(this.id)-o.getid();
        if(c==0){
            return this.getYear()-o.getYear();
        }
        return c;
    }

    public static Comparator<PersonData> categoryComparator=new Comparator<PersonData>() {
        @Override
        public int compare(PersonData o1, PersonData o2) {

            int c= o1.getCategory().compareTo(o2.getCategory());
            if(c==0){
                return o1.getid()-o2.getid();
            }
            return c;
        }
    };

    public static Comparator<PersonData> yearComparator=new Comparator<PersonData>() {
        @Override
        public int compare(PersonData o1, PersonData o2) {
            int c= o1.getYear()-o2.getYear();
            if(c==0){
                return o1.getid()-o2.getid();
            }
            return c;
        }
    };
}
