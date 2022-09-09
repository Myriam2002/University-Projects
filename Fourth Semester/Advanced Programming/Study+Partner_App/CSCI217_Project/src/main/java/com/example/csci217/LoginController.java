package com.example.csci217;
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.*;

public class LoginController {
    //**********************************************************************
    private String user_name = "";
    public void setUser_name(String user_name) {this.user_name = user_name;}
    public String getUser_name() {return user_name;}
    //**********************************************************************
    @FXML
    private TextField login_username_field;
    @FXML
    private PasswordField login_password_field;
    @FXML
    private Button login_signup_btn;
    @FXML
    private Button login_login_btn;
    @FXML
    void open_signup_scene(ActionEvent event) throws IOException {
        App.setRoot("Signup_UI");
    }
    @FXML
    void signInOnClick(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        //----------------------------------------------------
        String userName = login_username_field.getText();
        String password = login_password_field.getText();
        jdbSignInUp user = new jdbSignInUp();
        user.signIn(userName,password);
        //----------------------------------------------------
        if(user.signIn(userName,password)){
            JOptionPane.showMessageDialog(null, "Welcome :) ");
            App.setRoot("Timer_UI");
            //************************************************
            //int[] timesArray = user.studyHours(getUser_name());
            //************************************************
        }
        else {
            JOptionPane.showMessageDialog(null, "Incorrect Username or password , Try again :( ");
        }
    }

}
