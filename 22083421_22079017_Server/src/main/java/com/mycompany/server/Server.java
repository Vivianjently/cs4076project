package com.mycompany.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private Course course;
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
    private static String processedMessage(String message){
        String code;
        String data;
        String date;
        String course;
        String module;
        String timeSlot;


        code = message.split("=")[0];
        data = message.split("=")[1];



        switch(code){
            case"ADD_CLASS":
                return ;
            case "REMOVE_CLASS":
                return;
            case "DISPLAY_SCHEDULE":
                return ;
            case "TERMINATE_CONNECTION":
                return ;

        }
    }

    private static void ADD_CLASS (String data){
        String processedData = processedMessage(data);
        String date = processedData.split(",")[0];
        String course = processedData.split(",")[1];
        String module = processedData.split(",")[2];
        String time = processedData.split(",")[3];
    }

    private static void REMOVE_CLASS (+)

    private class IncorrectActionException extends Exception{
        private IncorrectActionException(){
            super();
            }
        private IncorrectActionException(String errorMessage){
            super(errorMessage);
        }
    }

    public static void main (String[]args){
        try {
            servsock = new ServerSocket(PORT);
            } catch (IOException e) {
                System.out.println("Unable to attach to port!");
                System.exit(1);
            }
            run();
        }



}