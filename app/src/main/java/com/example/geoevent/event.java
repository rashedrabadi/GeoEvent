package com.example.geoevent;

import java.util.List;

public class event {
    String id;
    String name;
    String time;
    String date;
    String description;
    String sponsers;
    String place;

    event(){}

    public event(String id,String name, String time, String date, String description, String sponsers, String place ) {
        this.id=id;
        this.name = name;
        this.time = time;
        this.date = date;
        this.description = description;
        this.sponsers = sponsers;
        this.place = place;

    }
public  String getId(){
        return id;
}
    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getSponsers() {
        return sponsers;
    }

    public String getPlace() {
        return place;
    }

}
