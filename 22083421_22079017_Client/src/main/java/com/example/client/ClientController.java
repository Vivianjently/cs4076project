package com.example.client;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;

import java.io.IOException;

public class ClientController {
    @FXML
    private Button addClassButton;

    @FXML
    private TextField course;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button displayScheduleButton;

    @FXML
    private Label label;

    @FXML
    private Label label2;

    @FXML
    private TextField moduleCode;

    @FXML
    private Button removeClassButton;

    @FXML
    private TextField room;

    @FXML
    private Button stopButton;

    @FXML
    private Button submit1;
    @FXML
    private Button submit2;
    @FXML
    private Button submit3;


    @FXML
    private Separator tableSeperator;


    @FXML
    private TextField time;

    @FXML
    private Label titleLabel;
    TCPCommunication connection = new TCPCommunication();



    @FXML
    void addClassEventAction(ActionEvent event) {
        course.setVisible(true);
        moduleCode.setVisible(true);
        time.setVisible(true);
        room.setVisible(true);
        datePicker.setVisible(true);
        submit1.setVisible(true);

    }

    @FXML
    void displayEventAAction(ActionEvent event) {
        course.setVisible(true);
        submit3.setVisible(true);

    }



    @FXML
    void removeClassEventAction(ActionEvent event) {
        course.setVisible(true);
        moduleCode.setVisible(true);
        time.setVisible(true);
        room.setVisible(true);
        datePicker.setVisible(true);
        submit2.setVisible(true);



    }



    @FXML
    void stopEventAction(ActionEvent event) {
        time.setVisible(false);
        room.setVisible(false);
        moduleCode.setVisible(false);
        datePicker.setVisible(false);

        try {
            connection.sendMessage("TERMINATE");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }




    @FXML
    void submitAction1(ActionEvent event) throws IOException {
        String startTime = time.getText().split("-")[0];
        String endTime = time.getText().split("-")[1];
        connection.sendMessage("ADD CLASS = "+datePicker.getAccessibleText()+","+course.getText()+","+moduleCode.getText()+","+startTime+endTime+","+room.getText());
    }
    void submit2(ActionEvent event) throws IOException {
        String startTime = time.getText().split("-")[0];
        String endTime = time.getText().split("-")[1];
        connection.sendMessage("REMOVE CLASS = "+datePicker.getAccessibleText()+","+course.getText()+","+moduleCode.getText()+","+startTime+endTime+","+room.getText());
    }
    void submit3(ActionEvent event)throws IOException{

        connection.sendMessage("DISPLAY = "+course.getText());

    }



}