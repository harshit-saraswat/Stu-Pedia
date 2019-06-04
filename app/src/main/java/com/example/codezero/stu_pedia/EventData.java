package com.example.codezero.stu_pedia;

/**
 * Created by Harshit Saraswat on 18-03-2018.
 */

public class EventData {
    String event_name,name,venue,date,time,details;

    public EventData(String event_name,String name,String venue,String date,String time,String details){

        this.event_name=event_name;
        this.name=name;
        this.venue=venue;
        this.date=date;
        this.time=time;
        this.details=details;
    }

    String getEvent_name(){return event_name;}
    String getName(){return name;}
    String getVenue(){return venue;}
    String getDate(){return date;}
    String getTime(){return time;}
    String getDetails(){return details;}
}
