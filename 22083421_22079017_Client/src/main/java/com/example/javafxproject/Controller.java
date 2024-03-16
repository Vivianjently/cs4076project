package com.example.javafxproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;

public class Controller {
        @FXML
        private TextField date;

        @FXML
        private TextField month;

        @FXML
        private TextField textField;

        @FXML
        private Label label;

        @FXML
        private SubScene subScene;

        @FXML
        private Button addClassButton;



        @FXML
        private Button displayScheduleButton;

        @FXML
        private Separator elevenOclockLine;

        @FXML
        private Separator fifteenOclockLine;

        @FXML
        private Separator fourteenOclockLine;

        @FXML
        private Label label2;

        @FXML
        private Separator nineOclockLine;

        @FXML
        private Button removeClassButton;

        @FXML
        private Separator seventeenOclockLine;

        @FXML
        private Separator sixteenOclockLine;

        @FXML
        private Button stopButton;

        @FXML
        private TextField tField1;

        @FXML
        private TextField tField2;

        @FXML
        private TextField tField3;

        @FXML
        private Separator tableSeperator;

        @FXML
        private Separator tenOclockLine;

        @FXML
        private Separator thirteenOclockLine;

        @FXML
        private Separator timeLineSeperator;

        @FXML
        private Label timeSlot1;

        @FXML
        private Label timeSlot2;

        @FXML
        private Label timeSlot3;

        @FXML
        private Label timeSlot4;

        @FXML
        private Label timeSlot5;

        @FXML
        private Label timeSlot6;

        @FXML
        private Label timeSlot7;

        @FXML
        private Label timeSlot8;

        @FXML
        private Label timeSlot9;

        @FXML
        private Label titleLabel;

        @FXML
        private Separator twelveOclockLine;

        @FXML
        void messageEventAction(ActionEvent event) {
          String message = textField.getText();
          TCPCommunication.sendMessage(message);
          String response = TCPCommunication.receiveMessage();
          label.setText(response);
        }
        @FXML
        void addClassEventAction(ActionEvent event) {
          tField1.setVisible(true);
          tField2.setVisible(true);
          tField3.setVisible(true);
          month.setVisible(true);
          date.setVisible(true);
          String message = "ADD_CLASS";
          TCPCommunication.sendMessage(message);
          String response = TCPCommunication.receiveMessage();
          label.setText(response);
        }

        @FXML
        void displayEventAAction(ActionEvent event) {
             String message = "DISPLAY_SCHEDULE";
             TCPCommunication.sendMessage(message);
             String response = TCPCommunication.receiveMessage();
             label.setText(response);
        }

        @FXML
        void nameOfClass(ActionEvent event) {

        }

        @FXML
        void removeClassEventAction(ActionEvent event) {
                tField1.setVisible(true);
                tField2.setVisible(true);
                tField3.setVisible(true);
                month.setVisible(true);
                date.setVisible(true);
                String message = "REMOVE_CLASS";
                TCPCommunication.sendMessage(message);
                String response = TCPCommunication.receiveMessage();
                label.setText(response);

        }

        @FXML
        void roomOfClass(ActionEvent event) {

        }

        @FXML
        void stopEventAction(ActionEvent event) {
                tField1.setVisible(false);
                tField2.setVisible(false);
                tField3.setVisible(false);
                month.setVisible(false);
                date.setVisible(false);
              TCPCommunication.closeConnection();
              label.setText("Connection has been closed!");
        }

        @FXML
        void timeOfClass(ActionEvent event) {

        }

        @FXML
        void monthEntry(ActionEvent event) {

        }

        @FXML
        void dateEntry(ActionEvent event) {

        }

}
