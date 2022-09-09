package com.example.csci217;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;

import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
public class TimerController implements Initializable {
	
	ObservableList<String> timesList = FXCollections.observableArrayList("10 min", "15 min", "20 min", "30 min", "45 min", "1 hour", "2 hours");
	
	String str_time = "00:00:00";
	
	Time time = new Time(str_time);
	
	int num_sec = 1;
	
	double progress = 0;
	
	int study_sec = 0;
	
	Date date;
	
	@FXML
	private Label songLabel;
	
	@FXML
	private Button playButton, pauseButton, resetButton, prevButton, nextButton;
	
	@FXML
	private Slider volumeSilder;
	@FXML
	private Rectangle background;
	
	
	private File directory;
	private File[] files;
	
	
	private ArrayList<File> songs;
	
	private int songNumber;
	
	
	@FXML
	private Media media;
	
	@FXML
	private MediaPlayer mediaPlayer;


    @FXML
    private Label timer_avart_name;

    @FXML
    private Rectangle timer_avatar_picture;

    @FXML
    private Button timer_exit_btn;

    @FXML
    private ChoiceBox<String> timer_music_choice;

    @FXML
    private ProgressBar timer_progress_bar;

    @FXML
    private Button timer_reset_timer;

    @FXML
    private Button timer_stats_btn;

    @FXML
    private Label timer_timer;
	Timeline timeline = new Timeline(
    		new KeyFrame(Duration.seconds(1), e->{
    			time.OneSecondBack();
    			timer_timer.setText(time.getTime());
    			
    		}));

    @FXML
    private ComboBox<String> timer_timer_combobox;

    @FXML
    private Button timer_todolist_btn;
    
    @FXML
    private Label myLabel;
    

    @FXML
    void Exit(ActionEvent event) throws IOException {
        Alert a = 
            new Alert(Alert.AlertType.CONFIRMATION, 
                    "Are you sure you want to logout?", 
                    ButtonType.YES,
                    ButtonType.NO);
      Optional<ButtonType> confirm = a.showAndWait();
      if (confirm.isPresent() && confirm.get() == ButtonType.YES) {
        App.setRoot("Login_UI");;
      }
    }

    @FXML
    void open_stats(ActionEvent event) throws IOException {
        App.setRoot("Stats_UI");
    }

    @FXML
    void open_todo_list(ActionEvent event) throws IOException {

        App.setRoot("Todo_UI");
    }
	Timeline timeline2;
    @FXML
    void timer_activate_timer(ActionEvent event) {
		date = new Date();
		String s = timer_timer_combobox.getSelectionModel().getSelectedItem().toString();
		
		switch(s) {
			case "10 min":
				str_time = "00:10:00";
				num_sec = 10*60;
				break;
			case "15 min":
				str_time = "00:15:00";
				num_sec = 15*60;
				break;
			case "20 min":
				str_time = "00:20:00";
				num_sec = 20 * 60;
				break;
			case "30 min":
				str_time = "00:30:00";
				num_sec = 30*60;
				break;
			case "45 min":
				str_time = "00:45:00";
				num_sec = 45*60;
				break;
			case "1 hour":
				str_time = "01:00:00";
				num_sec = 1 * 60 * 60;
				break;
			case "2 hours":
				str_time = "02:00:00";
				num_sec = 2 * 60 * 60;
				break;
		}
		
		time = new Time(str_time);
		
		timeline.setCycleCount(Timeline.INDEFINITE);
     	timeline.play();
     	
     	
     	
		timeline2 = new Timeline(new KeyFrame(Duration.seconds(1),e->{
			double number = (1.0/num_sec);
			progress += number;
			timer_progress_bar.setProgress(progress);
			study_sec ++;
		}));
     	
     	timeline2.setCycleCount(num_sec);
     	
     	timeline2.play();

    }

    @FXML
    void timer_resettimer(ActionEvent event) {
		time = new Time(0, 0, 0);
    	timer_timer.setText(time.getTime());
    	timeline.stop();
    	timer_progress_bar.setProgress(0.0);
    	timeline2.stop();
    	System.out.println("Today's date :" + date);
    	System.out.println("Seconds you spent studying: " + study_sec);

    }
    
  

@Override
public void initialize(URL arg0, ResourceBundle arg1) {

	// TODO Auto-generated method stub
	timer_timer_combobox.setValue("Set Duration...");
	 timer_timer_combobox.setItems(timesList);
	 timer_timer.setText(str_time); //The initial time before running timer_activate_timer

	 
	 timer_progress_bar.setStyle("-fx-accent: #ceb27c;");
	 // songs
		 songs = new ArrayList<File>();
		 directory = new File("CSCI217_Project/music");
		 files = directory.listFiles();
		 
		 if(files != null) {
			 
			 for(File file: files) {
				 songs.add(file);
			 }
		 }
		 media = new Media(songs.get(songNumber).toURI().toString());
		 mediaPlayer = new MediaPlayer(media);
		 
		 songLabel.setText(songs.get(songNumber).getName());
		 
		 
}


 public void playSound() {
	 
	 mediaPlayer.play();
	 
 }
 
 public void pauseMedia() {
	 mediaPlayer.pause();
	 
 }
 
public void resetMedia() {
	mediaPlayer.seek(Duration.seconds(0));
	 
 }

public void prevMedia() {
	if(songNumber > 0) {
		songNumber--;
		mediaPlayer.stop();
		
		media = new Media(songs.get(songNumber).toURI().toString());
		 mediaPlayer = new MediaPlayer(media);
		 
		 songLabel.setText(songs.get(songNumber).getName());
		 
		 playSound();
	}
	else {
		songNumber = songs.size() - 1;
		mediaPlayer.stop();
		
		media = new Media(songs.get(songNumber).toURI().toString());
		 mediaPlayer = new MediaPlayer(media);
		 
		 songLabel.setText(songs.get(songNumber).getName());
		 
		 playSound();
		
	}
}

public void nextMedia() {
	
	if(songNumber < songs.size() - 1) {
		songNumber++;
		mediaPlayer.stop();
		
		media = new Media(songs.get(songNumber).toURI().toString());
		 mediaPlayer = new MediaPlayer(media);
		 
		 songLabel.setText(songs.get(songNumber).getName());
		 
		 playSound();
	}
	else {
		songNumber = 0;
		mediaPlayer.stop();
		
		media = new Media(songs.get(songNumber).toURI().toString());
		 mediaPlayer = new MediaPlayer(media);
		 
		 songLabel.setText(songs.get(songNumber).getName());
		 
		 playSound();
		
	}
	 
}




}
