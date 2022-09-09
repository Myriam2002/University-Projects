package com.example.csci217;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class TodoController {

    @FXML
    private CheckBox checkbox_1_1;

    @FXML
    private CheckBox checkbox_1_2;

    @FXML
    private CheckBox checkbox_1_3;

    @FXML
    private CheckBox checkbox_1_4;

    @FXML
    private CheckBox checkbox_1_5;

    @FXML
    private CheckBox checkbox_2_1;

    @FXML
    private CheckBox checkbox_2_2;

    @FXML
    private CheckBox checkbox_2_3;

    @FXML
    private CheckBox checkbox_2_4;

    @FXML
    private CheckBox checkbox_2_5;

    @FXML
    private CheckBox checkbox_3_1;

    @FXML
    private CheckBox checkbox_3_2;

    @FXML
    private CheckBox checkbox_3_3;

    @FXML
    private CheckBox checkbox_3_4;

    @FXML
    private CheckBox checkbox_3_5;

    @FXML
    private Button reload_todo;

    @FXML
    private Button save_todo;

    @FXML
    private Button todo_return_btn;

    @FXML
    private TextField tx1_1;

    @FXML
    private TextField tx1_2;

    @FXML
    private TextField tx1_3;

    @FXML
    private TextField tx1_4;

    @FXML
    private TextField tx1_5;

    @FXML
    private TextField tx2_1;

    @FXML
    private TextField tx2_2;

    @FXML
    private TextField tx2_3;

    @FXML
    private TextField tx2_4;

    @FXML
    private TextField tx2_5;

    @FXML
    private TextField tx3_1;

    @FXML
    private TextField tx3_2;

    @FXML
    private TextField tx3_3;

    @FXML
    private TextField tx3_4;

    @FXML
    private TextField tx3_5;



    @FXML
    void reloading_todo(ActionEvent event) {
        ArrayList<String> our_saved_todo = list_retrival();
        for(int i =0; i<our_saved_todo.size(); i++)
        {
            switch (i) {
                case 0:
                tx1_1.setText(our_saved_todo.get(i));
                  break;
                case 1:
                tx2_1.setText(our_saved_todo.get(i));
                  break;
                case 2:
                tx3_1.setText(our_saved_todo.get(i));
                  break;
                case 3:
                tx1_2.setText(our_saved_todo.get(i));
                  break;
                case 4:
                tx2_2.setText(our_saved_todo.get(i));
                  break;
                case 5:
                tx3_2.setText(our_saved_todo.get(i));
                  break;
                case 6:
                tx1_3.setText(our_saved_todo.get(i));
                  break;
                case 7:
                tx2_3.setText(our_saved_todo.get(i));
                  break;
                case 8:
                tx3_3.setText(our_saved_todo.get(i));
                  break;
                case 9:
                tx1_4.setText(our_saved_todo.get(i));
                  break;
                case 10:
                tx2_4.setText(our_saved_todo.get(i));
                  break;
                case 11:
                tx3_4.setText(our_saved_todo.get(i));
                  break;
                case 12:
                tx1_5.setText(our_saved_todo.get(i));
                  break;
                case 13:
                tx2_5.setText(our_saved_todo.get(i));
                  break;
                case 14:
                tx3_5.setText(our_saved_todo.get(i));
                  break;
                default:
                  break;
              }

        }


    }

    @FXML
    void saving_todo(ActionEvent event) {
        ArrayList<String> ourlist = take_todo_from_GUI();
        try {
            //open the file
            File file = new File("src/todo_list_saver.txt");
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
    
    
            // start writing row by row
            for (int i = 0; i < ourlist.size(); i = i + 3) {
    
                // start writing col col
                for (int j = 0; j < 3; j++) {
                    if (j == 2) bw.write(ourlist.get(i + j));
                    else bw.write(ourlist.get(i + j) + ",");
                }
                bw.newLine(); // start a new row
            }
            bw.close();
            fw.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        System.out.println(ourlist);

    }

    @FXML
    void todo_return_to_timer(ActionEvent event) throws IOException {
        App.setRoot("Timer_UI");

    }

    ArrayList<String> list_retrival()
    {
        ArrayList<String> A = new ArrayList<>();

        // open the file 
        try {
            File myObj = new File("CSCI217_Project/to_do_list_saver.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              String[] parts = data.split(",");
              for(int i=0; i<3 ; i++)
              {
                  A.add(parts[i]);
              }
            }
            myReader.close();
          }
          catch (FileNotFoundException e) {
            System.out.println("An error occurred, file not found");
          }


        return A;
    } 
    ArrayList<String> take_todo_from_GUI()
    {
        ArrayList<String> s = new ArrayList<>();

        s.add(adding(tx1_1));
        s.add(adding(tx2_1));
        s.add(adding(tx3_1));
        s.add(adding(tx1_2));
        s.add(adding(tx2_2));
        s.add(adding(tx3_2));
        s.add(adding(tx1_3));
        s.add(adding(tx2_3));
        s.add(adding(tx3_3));
        s.add(adding(tx1_4));
        s.add(adding(tx2_4));
        s.add(adding(tx3_4));
        s.add(adding(tx1_5));
        s.add(adding(tx2_5));
        s.add(adding(tx3_5));

        return s;
    }
    String adding(TextField t)
    {
        String s = " ";
        if(t.getText().isEmpty() == true ) 
        {
            return s;
        }
        else 
        {
            s = t.getText();
            return s;
        }

    }
}