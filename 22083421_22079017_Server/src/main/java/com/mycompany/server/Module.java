package com.mycompany.server;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Module {
   private String id;
    private String room;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    public String getRoom() {
        return room;
    }


    public LocalTime getStartTime(){
        return startTime;

    }
    public LocalTime getEndTime(){
        return  endTime;

    }
    public LocalDate getDate() {
        return date;
    }
    public void setRoom(String room) {
        this.room = room;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }




    public void setId(String id) {
        this.id = id;
    }



    public String getId() {
        return id;
    }
    public String toString(){
        return "Module code - "+this.id+" , Module room - "+this.room+" , Module date - "+this.date+" , Module time slot - "+this.startTime+"-"+this.endTime;

    }

    public Module(String id,String room,String date,String startTime,String endTime){
        this.id = id;
        this.room = room;

        this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.startTime = LocalTime.parse(startTime);
        this.endTime = LocalTime.parse(endTime);
    }


}
