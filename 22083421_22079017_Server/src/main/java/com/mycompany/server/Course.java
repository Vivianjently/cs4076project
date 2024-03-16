package com.mycompany.server;

public class Course {
   private String id;
   private final Module[] modules = new Module[5];

   private int moduleCount;

    public String getId() {
        return id;
    }

    public int getModuleCount() {
        return moduleCount;
    }

    public void setModuleCount(int moduleCount) {
        this.moduleCount = moduleCount;
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
