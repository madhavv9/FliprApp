package com.example.fliprapp;

import java.io.Serializable;

public class MoreThanOne implements Comparable<MoreThanOne>, Serializable {
    String name;
    int id,frequency;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getFrequency() {
        return frequency;
    }

    public MoreThanOne(int id, String name, int frequency) {
        this.id = id;
        this.name = name;
        this.frequency = frequency;
    }

    @Override
    public int compareTo(MoreThanOne o) {
        int c= o.getFrequency()-this.getFrequency();
        if(c==0){
            return this.getId()-o.getId();
        }
        return c;
    }
}
