package com.mycompany.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Server {
    private static ArrayList<Course> programmes = new ArrayList<>();

    private static Socket link;
    private static ServerSocket servsock;
    private static final int PORT = 1234;

    private static void run() {
        try {
            while (true) {
                Socket link = servsock.accept();
            }
        } catch (IOException e) {
            System.out.println("Connection failed");
            System.exit(1);
        }
    }
    private static void socketHandler(Socket link) {
        while (true) {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(link.getInputStream()));
                PrintWriter out = new PrintWriter(link.getOutputStream(), true);

                String message = in.readLine();
                System.out.println("Message received from client:" + " " + message);
                out.println(processedMessage(message));

            } catch (IOException e) {
                System.out.println("Input or Output not detected");
            }
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
                    TERMINATE();
                    return ("Connection Terminated!");

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
        for (int i = 0; i < programmes.size(); i++) {
            for (int k = 0; k < programmes.get(i).getModuleCount(); k++) {
                if (programmes.get(k).getModules().get(k).getDate() == LocalDate.parse(date) && programmes.get(k).getModules().get(k).getStartTime() == LocalTime.parse(startTime)) {
                    throw new IncorrectActionException("There is a clash in timing schedule!");
                } else if (programmes.get(k).getModules().get(k).getDate() == LocalDate.parse(date) && programmes.get(k).getModules().get(k).getStartTime().isBefore(LocalTime.parse(endTime))) {
                    throw new IncorrectActionException("There is a clash in timing schedule!");
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
            isFound = true;
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static String DISPLAY_SCHEDULE(String data) {
        String course;
        course = data;
       for(int i = 0 ; i < programmes.size(); i++){
           if(programmes.get(i).getId() == course ){
               return programmes.get(i).toString();
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

