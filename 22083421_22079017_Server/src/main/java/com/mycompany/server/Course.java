package com.mycompany.server;

import java.util.ArrayList;

public class Course {
   private String id;
   private final ArrayList<Module> modules = new ArrayList<>();


   private int moduleCount;
   public void addModule(Module module){
       if(moduleCount<5) {
           modules.add(module);
       } else if (moduleCount==5) {
           System.out.println("This course already has 5 modules");

       }
   }
   public void removeModule(Module module){
       modules.remove(module);
   }

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

    public ArrayList<Module> getModules() {
        return modules;
    }

    public Course(String id){
     this.id = id;

    }
    public String toString(){
       String result =  "Course Time Table for - "+this.id+" is \n";
       for(int i=0;i<modules.size();i++ ){

           result+= modules.get(i).toString()+"\n";



       }
       return result;
    }

}
