import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import com.opencsv.CSVReader;

/*
This class is responsible to change ArrayList of strings to and from a file
1. contains the columns number of the file
2. changes the file data to array of strings
3. write an array of list to a file
 */

public class FileM{

    // attributes
    public int number_rows = 0;
    public int number_col=0;
    ArrayList<String> all= new ArrayList<>(); // stores the file content in an array of list


    public void Read(String file) {
        /*
        1. Opens the file
        2. Read data and store into an array
        3. Updates the number of columns and rows
         */
        try {

            // Create an object of filereader
            // class with CSV file as a parameter.
            FileReader filereader = new FileReader(file);

            // create csvReader object passing
            // file reader as a parameter
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;

            // we are going to read data line by line
            while ((nextRecord = csvReader.readNext()) != null) {
                number_col = nextRecord.length;
                Collections.addAll(all, nextRecord);
                number_rows++;
            }

            csvReader.close();

        }
        catch (Exception e) {
            e.printStackTrace();

        }

        

    }

    public void write(String filename, ArrayList<String>x)  {
        // write the array of strings into the file
    try {
        //open the file
        File file = new File(filename);
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);


        // start writing row by row
        for (int i = 0; i < x.size(); i = i + number_col) {

            // start writing col col
            for (int j = 0; j < number_col; j++) {
                if (j == number_col - 1) bw.write(x.get(i + j));
                else bw.write(x.get(i + j) + ",");
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

    }


}


