package com.example.csci217;
import java.sql.*;
public class jdbSignInUp {
    public boolean signIn(String username ,String password) throws ClassNotFoundException, SQLException {
        // ******************************* Sign In *******************************
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        Connection connection = DriverManager.getConnection("jdbc:ucanaccess://studyPartner.accdb");
        // Sign In
        Statement st = connection.createStatement();
        String sql = "Select * from student_info";
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            String firstNameDB = rs.getString(1); String lastNameDB = rs.getString(2); String passwordDB = rs.getString(3);String userNameDB = rs.getString(4);
            if (username.equals(userNameDB) && password.equals(passwordDB)) {
                connection.close(); // close the Connection to let the database know we're done with it
                return true;}}
        connection.close(); // close the Connection to let the database know we're done with it
        return false;}
    // ******************************* Sign Up *******************************
    public void signUp(String userName,String firstName,String lastName,String password) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connection = DriverManager.getConnection("jdbc:ucanaccess://studyPartner.accdb");
            //------------------------------------------------------------------------------------------
            //Sign Up
            PreparedStatement preparedStatement = connection.prepareStatement("insert into student_info(username, firstname, lastname, password) values (?,?,?,?)");
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setString(4, password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();}
    }
    // ******************************* Check user name *******************************
    public boolean checkUserName(String userName){
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connection = DriverManager.getConnection("jdbc:ucanaccess://studyPartner.accdb");
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM student_info WHERE username='"+userName+"'";
            ResultSet rs= stmt.executeQuery(sql);
            if(rs.next()){
                return true;}
            else {return false;}
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();}
        return false;}
    public static void main(String[] args) throws ClassNotFoundException, SQLException {}
}


















//        try {
//            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
//            Connection connection = DriverManager.getConnection("jdbc:ucanaccess://studyPartner.accdb");
//            //------------------------------------------------------------------------------------------
//            // Sign Up
////            PreparedStatement preparedStatement = connection.prepareStatement("insert into student_info(username, firstname, lastname, password) values (?,?,?,?)");
////            preparedStatement.setString(1, "nino31");
////            preparedStatement.setString(2, "Nadine");
////            preparedStatement.setString(3, "Gamal");
////            preparedStatement.setString(4, "5678");
////            preparedStatement.executeUpdate();
//            //------------------------------------------------------------------------------------------
//            // Sign In
////            preparedStatement.execute("select * from student_info"); // select the data from the table
////            ResultSet rs = preparedStatement.getResultSet(); // get the ResultSet that will generate from our query
////            if (rs != null) // if rs == null, then there is no record in ResultSet to show
////                while (rs.next()) // By this line we will step through our data row-by-row
////                {
////                    System.out.println("________________________________________" );
////                    System.out.println("Username: " + rs.getString(1) );
////                    System.out.println("First Name: " + rs.getString(2) );
////                    System.out.println("Last Name: " + rs.getString(3) );
////                    System.out.println("________________________________________" );
////                }
////            //------------------------------------------------------------------------------------------
//            Statement st = connection.createStatement();
//            String sql = "Select * from student_info";
//            ResultSet rs = st.executeQuery(sql);
//            while(rs.next()){
//                System.out.println("\n"+rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4));
//            }
////            preparedStatement.close(); // close the Statement to let the database know we're done with it
//            connection.close(); // close the Connection to let the database know we're done with it
//        }
//        catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            System.out.println("Collllllllllllllllllllllllll");
//            e.printStackTrace();
//        }

