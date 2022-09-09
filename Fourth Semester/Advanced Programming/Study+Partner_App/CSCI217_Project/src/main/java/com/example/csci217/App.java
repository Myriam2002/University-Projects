package com.example.csci217;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
public class App extends Application {
    public static void main(String[] args) throws Exception {launch();}
    private static Scene scene;
    public static Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("Login_UI.fxml"));
            scene = new Scene(root);
            Image icon = new Image("C:/Users/Mariam Barakat/Documents/university/advanced programming/CSCI217_Project_final_v4/CSCI217_Project/icon.png");
            stage.getIcons().add(icon);
            stage.setTitle("Study Partner");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }
        catch (Exception e) {e.printStackTrace();}
    }
    static void setRoot(String fxml) throws IOException {scene.setRoot(loadFXML(fxml));}
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
}

