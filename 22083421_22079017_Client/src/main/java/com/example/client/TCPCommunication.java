package com.example.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPCommunication {
    static InetAddress host;
    static final int PORT = 1234;
    static PrintWriter out;
    static BufferedReader in;
    static Socket link;

    public TCPCommunication() {
        try {
            host = InetAddress.getLocalHost();
            link = new Socket(host, PORT);
            in = new BufferedReader(new InputStreamReader(link.getInputStream()));
            out = new PrintWriter(link.getOutputStream(), true);

        } catch (UnknownHostException e) {
            System.err.println("Host ID not found!");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error while communicating with the server.");
            e.printStackTrace();
        }
    }
    public   void sendMessage(String message) throws IOException {
        out.println(message);

        String response = in.readLine();

        if(response.equals("Connection Terminated!")){
            closeConnection();
            System.exit(0);
        }

        System.out.println(response);
    }
    public  void closeConnection(){
        try {
            link.close();
            System.out.println("Connection has been closed");
        } catch (IOException e) {
            System.exit(1);
        }
    }
}