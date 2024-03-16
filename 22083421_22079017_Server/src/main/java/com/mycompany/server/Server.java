package com.mycompany.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Server {
    private static ArrayList<Course> programmes = new ArrayList<>();

    private static Socket link;
    private static ServerSocket servsock;
    private static final int PORT = 1234;

    private static void run() {
        try {
            while (true) {
                Socket link = servsock.accept();
                socketHandler(link);
            }
        } catch (IOException e) {
            System.out.println("Connection failed");
            System.exit(1);
        }
    }
    private static void socketHandler(Socket link) {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(link.getInputStream()));
                PrintWriter out = new PrintWriter(link.getOutputStream(), true);

                while(true) {
                    String message = in.readLine();
                    System.out.println("Message received from client: " + message);

                    String response = processedMessage(message);

                    if(response.equals("Connection Terminated!")){
                        out.println(response);
                        TERMINATE();
                    }

                    out.println();
                }

            } catch (IOException e) {
                System.out.println("Input or Output not detected");
            }
    }

    private static String processedMessage(String message) {
        String code;
        String data;

        code = message.split("=")[0];
        data = message.split("=")[1];
        try {
            switch (code) {
                case "ADD_CLASS":
                    ADD_CLASS(data);
                    return ("Class Added");
                case "REMOVE_CLASS":
                    REMOVE_CLASS(data);
                    return ("Class Removed");
                case "DISPLAY_SCHEDULE":
                    DISPLAY_SCHEDULE(data);
                    return("Schedule displayed!");
                case "TERMINATE_CONNECTION":
                    return "Connection Terminated!";

            }
        } catch (IncorrectActionException e) {
            return e.getMessage();
        }
        return "";
    }


    private static void ADD_CLASS(String data) throws IncorrectActionException {
        String date;
        String course;
        String module;
        String startTime;
        String endTime;
        String room;
        date = data.split(",")[0];
        course = data.split(",")[1];
        module = data.split(",")[2];
        startTime = data.split(",")[3];
        endTime = data.split(",")[4];
        room = data.split(",")[5];


        String[] splitData = {date, course, module, startTime, endTime, room};
        if (splitData.length < 6) {
            System.out.println("Please enter data in all the fields");
            throw new IncorrectActionException("Please check if all fields are filled!");
        }

        //Goes through each module in every course and checks if the time overlaps.
        //Then it checks if this module is being added to the course, if so then throw overlap error.
        //It also checks to see if room number is the same because then the module overlaps with a module from another course
        for (int i = 0; i < programmes.size(); i++) {
            for (int k = 0; k < programmes.get(i).getModules().size(); k++) {
                Module curMod = programmes.get(i).getModules().get(k);
                if(curMod.getStartTime().isBefore(LocalTime.parse(startTime)) &&
                   LocalTime.parse(endTime).isBefore(curMod.getEndTime()) &&
                   curMod.getDate().equals(LocalDate.parse(date))) {
                    if (curMod.getRoom().equals(room) || programmes.get(i).getId().equals(splitData[1])){
                        throw new IncorrectActionException("Missing data");
                    }
                }
            }
        }

        Module Class = new Module(module, room, date, startTime, endTime);
        boolean isFound = false;
        for (int z = 0; z < programmes.size(); z++) {

            if (programmes.get(z).getId() == course) {
                programmes.get(z).addModule(Class);
                isFound = true;

            }
        }
        if (!isFound) {
            Course programme = new Course(course);
            programme.addModule(Class);
            programmes.add(programme);
        }
    }

    private static void REMOVE_CLASS(String data) throws IncorrectActionException {
        String date;
        String course;
        String module;
        String startTime;
        String endTime;
        String room;
        date = data.split(",")[0];
        course = data.split(",")[1];
        module = data.split(",")[2];
        startTime = data.split(",")[3];
        endTime = data.split(",")[4];
        room = data.split(",")[5];

        String[] splitData = {date, course, module, startTime, endTime, room};
        if (splitData.length < 6) {
            System.out.println("Please enter data in all the fields");
            throw new IncorrectActionException("Please check if all fields are filled!");
        }

        Module Class = new Module(module, room, date, startTime, endTime);
        boolean isFound = false;
        for (int z = 0; z < programmes.size(); z++) {

            if (programmes.get(z).getId() == course) {
                programmes.get(z).removeModule(Class);
                isFound = true;

            }
        }
        if (!isFound) {
            Course programme = new Course(course);
            programme.removeModule(Class);
            isFound = true;
        } else throw new IncorrectActionException("Module not found");
    }

    private static void TERMINATE() {
        try {
            link.close();
            System.exit(0);
        } catch (IOException e) {
            System.exit(0);
        }

    }

    private static String DISPLAY_SCHEDULE(String data) {
        String course;
        course = data;
       for(int i = 0 ; i < programmes.size(); i++){
           if(programmes.get(i).getId().equals(course )){
               System.out.println(programmes.get(i).toString());
           }
       }
       return "";
    }

    public static void main(String[] args) {
        try {
            servsock = new ServerSocket(PORT);
        } catch (IOException e) {
            System.out.println("Unable to attach to port!");
            System.exit(1);
        }
        run();
    }


    }

