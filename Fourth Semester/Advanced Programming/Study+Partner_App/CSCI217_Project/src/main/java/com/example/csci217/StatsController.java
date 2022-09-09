package com.example.csci217;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class StatsController {

    @FXML
    private BarChart<?, ?> stats_graph;

    @FXML
    private Button stats_return_btn;

    @FXML
    private Label stats_totalwork;

    @FXML
    void stats_return_to_timer(ActionEvent event) throws IOException {
        App.setRoot("Timer_UI");
    }

}