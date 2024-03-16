package com.mycompany.server;

public class Course {
   private String id;
   private final Module[] modules = new Module[5];

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Module[] getModules() {
        return modules;
    }

    public Course(String id){
     this.id = id;

    }

}
