package com.mycompany.server;

import java.time.LocalDate;
import java.time.LocalTime;

public class Module {
   private String id;
   private LocalDate date;
   private LocalTime time;
   private String room;

    public void setRoom(String room) {
        this.room = room;
    }

    public void setTime(LocalTime time) {
        this.time = time;
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

    public LocalTime getTime() {
        return time;
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

}
