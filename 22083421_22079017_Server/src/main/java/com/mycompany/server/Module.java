package com.mycompany.server;

import java.time.LocalDate;
import java.time.LocalTime;

public class Module {
   private String id;
   private LocalDate date;
   private LocalTime startTime;
   private LocalTime endTime;
   private String room;

    public void setRoom(String room) {
        this.room = room;
    }



    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoom() {
        return room;
    }


    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    public Module(String id){
        this.id = id;
    }
    public Module(String id,LocalDate date,String room){
        this.id = id;
        this.date = date;
        this.room = room;

    }

}
