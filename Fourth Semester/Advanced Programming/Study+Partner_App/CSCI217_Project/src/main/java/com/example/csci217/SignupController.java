package com.example.csci217;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.*;
public class SignupController {
    @FXML
    private TextField signup_first_name_field;
    @FXML
    private TextField signup_last_name_field;
    @FXML
    private TextField signup_username_field;
    @FXML
    private PasswordField signup_password;
    @FXML
    private PasswordField signup_confirm_password;
    @FXML
    private Button signup_signup_btn;
    @FXML
    void signupOnClick(ActionEvent event) throws IOException {
        //----------------------------------------------------
        String fName = signup_first_name_field.getText();
        String lName = signup_last_name_field.getText();
        String userName = signup_username_field.getText();
        String password = signup_password.getText();
        String passwordConfirm = signup_confirm_password.getText();
        jdbSignInUp user = new jdbSignInUp();
        boolean repeatedUserName = user.checkUserName(userName);
        boolean fname = fName.matches("[a-zA-Z]+");
        boolean lname = lName.matches("[a-zA-Z]+");
        boolean username = (userName.toUpperCase()).matches("^(?=.*[A-Z])(?=.*[0-9])[A-Z0-9]+$");
        boolean passwordd = (password.toUpperCase()).matches("^(?=.*[A-Z])(?=.*[0-9])[A-Z0-9]+$");
        boolean equalPassword = password.equals(passwordConfirm);
        if(fName==""||lName==""||userName==""||password==""||passwordConfirm==""){JOptionPane.showMessageDialog(null, "Please complete empty information !");}
        else {
            if(!fname || !lname){JOptionPane.showMessageDialog(null, "First and Last Name must be letters only !");}
            else {
                if(repeatedUserName){JOptionPane.showMessageDialog(null, "Username is already exist , please enter another one !");}
                else {
                    if(!username){JOptionPane.showMessageDialog(null, "Username must have letters and numbers !");}
                    else{
                        if(!passwordd){JOptionPane.showMessageDialog(null, "Password must have letters and numbers !");}
                        else {
                            if(!equalPassword){JOptionPane.showMessageDialog(null, "Two Passwords must be the same !");}
                            else {
                                user.signUp(userName,fName,lName,password);
                                App.setRoot("Timer_UI");
                                LoginController userr = new LoginController();
                                userr.setUser_name(userName);
                                JOptionPane.showMessageDialog(null, "Hello " +fName+" Welcome to the program :) ");}}}}}}
        //----------------------------------------------------
    }

}

