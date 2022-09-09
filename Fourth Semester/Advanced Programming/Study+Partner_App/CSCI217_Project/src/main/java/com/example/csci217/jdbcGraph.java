package com.example.csci217;
import java.util.ArrayList;
import java.sql.*;

public class jdbcGraph {
    public int[] studyHours(String userName) {
        int[] timesArray = new int[0];
        try {
            Connection connection = DriverManager.getConnection("jdbc:ucanaccess://studyPartner.accdb");
            PreparedStatement preparedStatement;
            ResultSet resultSet;
            String query = "SELECT * FROM study_times WHERE username='" + userName + "'";
            int saturday = 0, sunday = 0, monday = 0, tuesday = 0, wednesday = 0, thursday = 0, friday = 0;
            //String saturday = null, sunday = null, monday = null, tuesday = null, wednesday = null, thursday = null, friday = null;
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            timesArray = new int[7];
            while (resultSet.next()) {
                saturday = resultSet.getInt("saturday");
                sunday = resultSet.getInt("sunday");
                monday = resultSet.getInt("monday");
                tuesday = resultSet.getInt("tuesday");
                wednesday = resultSet.getInt("wednesday");
                thursday = resultSet.getInt("thursday");
                friday = resultSet.getInt("friday");
                timesArray = new int[]{saturday, sunday, monday, tuesday, wednesday, thursday, friday};
            }
            connection.close(); // close the Connection to let the database know we're done with it
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return timesArray;
    }
    public static void main(String[] args) {}
}
