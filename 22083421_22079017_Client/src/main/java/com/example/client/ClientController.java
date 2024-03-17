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
        if(submit2.isVisible()){
            submit2.setVisible(false);
        }
        if(submit3.isVisible()){
            submit3.setVisible(false);
        }
        submit1.setVisible(true);

    }

    @FXML
    void displayEventAAction(ActionEvent event) {
        moduleCode.setVisible(false);
        time.setVisible(false);
        room.setVisible(false);
        datePicker.setVisible(false);

        course.setVisible(true);
        if(submit1.isVisible()){
            submit1.setVisible(false);
        }
        if(submit2.isVisible()) {
            submit2.setVisible(false);
        }
        submit3.setVisible(true);

    }



    @FXML
    void removeClassEventAction(ActionEvent event) {
        course.setVisible(true);
        moduleCode.setVisible(true);
        time.setVisible(true);
        room.setVisible(true);
        datePicker.setVisible(true);
        if(submit1.isVisible()){
            submit1.setVisible(false);
        }
        if(submit3.isVisible()) {
            submit3.setVisible(false);
        }
        submit2.setVisible(true);




    }



    @FXML
    void stopEventAction(ActionEvent event) {
        time.setVisible(false);
        room.setVisible(false);
        moduleCode.setVisible(false);
        datePicker.setVisible(false);
        course.setVisible(false);
        submit1.setVisible(false);
        submit2.setVisible(false);
        submit3.setVisible(false);


        try {
            connection.sendMessage("TERMINATE_CONNECTION=---");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }




    @FXML
    public void submitAction1(ActionEvent event) throws IOException {
        String startTime = time.getText().split("-")[0];
        String endTime = time.getText().split("-")[1];
        connection.sendMessage("ADD_CLASS="+datePicker.getValue()+","+course.getText()+","+moduleCode.getText()+","+startTime+","+endTime+","+room.getText());
    }
    @FXML
    public void submitAction2(ActionEvent event) throws IOException {
        String startTime = time.getText().split("-")[0];
        String endTime = time.getText().split("-")[1];
        connection.sendMessage("REMOVE_CLASS="+datePicker.getValue()+","+course.getText()+","+moduleCode.getText()+","+startTime+","+endTime+","+room.getText());
    }
    @FXML
    public void submitAction3(ActionEvent event)throws IOException{

        connection.sendMessage("DISPLAY_SCHEDULE="+course.getText());

    }



}