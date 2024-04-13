package com.mycompany.server;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Module {
   private String id;
    private String room;
    private LocalDate date;
    private String startTime;
    private String endTime;

    public String getRoom() {
        return room;
    }


    public String getStartTime(){
        return startTime;

    }
    public String getEndTime(){
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
    @Override
    public String toString(){
        return "Module code - "+this.id+" , Module room - "+this.room+" , Module date - "+this.date+" , Module time slot - "+this.startTime+"-"+this.endTime;

    }
    public int getStartTimeAsInt(){
        return Integer.parseInt(startTime.replace(":",""));
    }
    public int getEndTimeAsInt(){
        return Integer.parseInt(endTime.replace(":",""));
    }
    public boolean equals(Module module){
        return this.id.equals(module.id) && this.room.equals(module.room) && this.date.equals(module.date) && this.startTime.equals(module.startTime) && this.endTime.equals(module.endTime);
    }
    public boolean clashes(Module module){

        boolean aClashesB= this.date.equals(module.date) && (this.getStartTimeAsInt()<=module.getEndTimeAsInt() && this.getStartTimeAsInt()>=module.getStartTimeAsInt()) || (this.getEndTimeAsInt()>=module.getStartTimeAsInt() && this.getEndTimeAsInt()<=module.getEndTimeAsInt());
        boolean bClashesA= (module.getStartTimeAsInt()<=this.getEndTimeAsInt() && module.getStartTimeAsInt()>=this.getStartTimeAsInt()) || (module.getEndTimeAsInt()>=this.getStartTimeAsInt() && module.getEndTimeAsInt()<=this.getEndTimeAsInt());
        return aClashesB || bClashesA;
    }


    public Module(String id,String room,String date,String startTime,String endTime){
        this.id = id;
        this.room = room;

        this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.startTime = startTime;
        this.endTime = endTime;
    }


}
