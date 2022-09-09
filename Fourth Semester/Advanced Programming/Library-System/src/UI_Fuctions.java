import java.util.ArrayList;
import java.util.Scanner;

public class UI_Fuctions {


    static void Reading_all_files(FileM Admins_file, FileM Books_file, FileM Issued_books_file, FileM Librarians_file, FileM Students_file) {
        // this function opens all files simultaneously and used the method "read", in the FileM class
        // read also finds the numbe rof columns and rows and stores it in the attributes of FileM objects
        Admins_file.Read("Data\\Admins.csv");
        Books_file.Read("Data\\Books.csv");
        Issued_books_file.Read("Data\\Issued Books.csv");
        Librarians_file.Read("Data\\Librarians.csv");
        Students_file.Read("Data\\Students.csv");
    }
    static void creating_objects(FileM Admins_file, FileM Books_file, FileM Issued_books_file, FileM Librarians_file, FileM Students_file,ArrayList<Admins>Admins_objects,ArrayList<Books> Books_objects,ArrayList<Issued_Books> Issued_books_objects,ArrayList<Librarians> Librarians_objects,ArrayList<Students> Students_objects)
    {
        // this function converts the array of strings to array of objects
        /*
        1. loop according to the number of rows (stored in the FileM objects)
        2. create a new object
        3. use the method create (found in the class students , etc.), this parses the correct part of the array and puts them in the objects
        4. add this new object that was intialized in step 1-2 to a list
         */
        for (int i = 1; i < Admins_file.number_rows; i++) {
            Admins y = new Admins();
            y.create(Admins_file.all, i * Admins_file.number_col);
            Admins_objects.add(y);
        }
        for (int i = 1; i < Books_file.number_rows; i++) {
            Books y = new Books();
            y.create(Books_file.all, i * Books_file.number_col);
            Books_objects.add(y);
        }
        for (int i = 1; i < Issued_books_file.number_rows; i++) {
            Issued_Books y = new Issued_Books();
            y.create(Issued_books_file.all, i * Issued_books_file.number_col);
            Issued_books_objects.add(y);
        }
        for (int i = 1; i < Librarians_file.number_rows; i++) {
            Librarians y = new Librarians();
            y.create(Librarians_file.all, i * Librarians_file.number_col);
            Librarians_objects.add(y);
        }
        for (int i = 1; i < Students_file.number_rows; i++) {
            Students y = new Students();
            y.create(Students_file.all, i * Students_file.number_col);
            Students_objects.add(y);
        }
    }
    static void writing_to_files(FileM Admins_file, FileM Books_file, FileM Issued_books_file,FileM Librarians_file, FileM Students_file,ArrayList<Admins>Admins_objects, ArrayList<Books> Books_objects,ArrayList<Issued_Books> Issued_books_objects,ArrayList<Librarians> Librarians_objects,ArrayList<Students> Students_objects )
    {
        /*
        1. create a string array and use the static function "objects_to_string" to change the array of objects to array of strings
        2. use the static function "write" to write the array of strings to the file
         */
        ArrayList<String> admins_string = Admins.objects_to_string(Admins_objects);
        Admins_file.write("Data\\Admins.csv",admins_string);

        ArrayList<String> books_strings = Books.objects_to_string(Books_objects);
        Books_file.write("Data\\Books.csv", books_strings);

        ArrayList<String> issued_strings = Issued_Books.objects_to_string(Issued_books_objects);
        Issued_books_file.write("Data\\Issued Books.csv", issued_strings);

        ArrayList<String> librarian_strings = Librarians.objects_to_string(Librarians_objects);
        Librarians_file.write("Data\\Librarians.csv", librarian_strings);

        ArrayList<String> students_strings = Students.objects_to_string(Students_objects);
        Students_file.write("Data\\Students.csv",students_strings);
    }

    static int login(FileM Admins_file,FileM Librarians_file){
        // interface
        System.out.println("===================================== Welcome to the library system =====================================");
        System.out.println("Please choose which user you are:");
        System.out.println("1. Admin");
        System.out.println("2. Librarian");
        Scanner myObj = new Scanner(System.in); // to be able to make inputs
        System.out.print("Your choice: ");
        int a= myObj.nextInt();
        while(a != 1 && a != 2)
        {
            System.out.print("Your choice must be either 1 or 2 : ");
            a= myObj.nextInt();
        }
        // starting to verify The user ID and password
        System.out.print("Please input your ID: ");
        if (a == 1)
        {
            //make user enter their username and check for the password
            Scanner myObj1 = new Scanner(System.in);
            String username  = myObj1.nextLine();
            int index = Admins_file.all.indexOf(username);  // return -1 if not in the file
            while(index == -1)
            {
                System.out.print("No such user, please try again: ");
                username  = myObj1.nextLine();
                index = Admins_file.all.indexOf(username);
                // must store the index of this ID to access the corresponding password
            }
            System.out.print("Please input your password: ");
            Scanner myObj2 = new Scanner(System.in);
            String password  = myObj2.nextLine();
            if(password.equals(Admins_file.all.get(index + 2)))
            {
                System.out.println("Welcome "+ Admins_file.all.get(index+1)+"!");
                return 1;
            }
            else
            {
                System.out.println("wrong password :(");
                return login(Admins_file,Librarians_file);
            }
        }
        else
        {
            //make user enter their username
            Scanner myObj1 = new Scanner(System.in);
            String username  = myObj1.nextLine();
            int index = Librarians_file.all.indexOf(username); // returns -1 if couldn't find this ID

            while(index == -1)
            {
                System.out.print("No such user, please try again: ");
                Scanner myObj2 = new Scanner(System.in);
                username  = myObj2.nextLine();
                index = Librarians_file.all.indexOf(username);
                //we need to store the index, because we will use it to find the *corresponding* password
            }

            System.out.print("Please input your password: ");
            Scanner myObj3 = new Scanner(System.in);
            String password  = myObj3.nextLine();
            if(password.equals(Librarians_file.all.get(index + 2)))
            {
                System.out.println("Welcome "+ Librarians_file.all.get(index+1)+"!");
                return 2;
            }
            else
            {
                System.out.println("wrong password :(");
                return login(Admins_file,Librarians_file);
            }

        }
    }
    static int UI_admin_view()
    {
        // user interface
        System.out.println("=========Admins View=========");
        System.out.println("1. Add student");
        System.out.println("2. Delete student");
        System.out.println("3. Add librarian");
        System.out.println("4. Delete student");
        System.out.println("5. Add admins");
        System.out.println("6. Delete admins");
        System.out.println("7. Log out");
        System.out.print("Please input your choice: ");
        // make sure the user entered a valid option
        Scanner myobj = new Scanner(System.in);
        int input  = myobj.nextInt();
        while(input < 1 || input > 7 )
        {
            System.out.print("Please input a correct choice: ");
            Scanner myobj1 = new Scanner(System.in);
            input  = myobj1.nextInt();
        }

        return input;
    }

    static void Admin_view(  ArrayList<Admins>Admins_objects,ArrayList<Librarians> Librarian_objects,ArrayList<Students> Students_objects) {
        int input = UI_admin_view();
        while (input != 7) { // so that when user enters 7 it will exit
        if (input == 1)//====================add student
        {
            // validate input
            Scanner sc = new Scanner(System.in);

            System.out.print("student name: ");
            String student_name = sc.nextLine();
            while (!checkAlphabetic(student_name)) {  //make sure the name is all characters
                System.out.print("Name must be only made of characters \n student name: ");
                student_name = sc.nextLine();
            }

            System.out.print("student ID: ");
            String student_id = sc.nextLine();
            while (findingstudent(student_id, Students_objects) != -1) { //make sure the ID isnt there
                System.out.print("This id is already present...\n New ID: ");
                student_id = sc.nextLine();
            }

            System.out.print("Moblie phone: ");
            String mobile = sc.nextLine();
            while (!isInt(mobile)) { // make sure the mobile phone is all numeric
                System.out.print("Mobile phone should only contain numbers...\n New mobile number: ");
                mobile = sc.nextLine();
            }
            System.out.print("Email: ");
            String email = sc.nextLine();
            System.out.print("Birthday: ");
            String birthday = sc.nextLine();
            // use add to put the object inside the arraylist
            Students stud = new Students(student_name, student_id, mobile, email, birthday); // use a constructor to add a new object
            Students_objects.add(stud);
            input = UI_admin_view();
        } else if (input == 2) //=============================== delete student
        {
            // search for the item to delete
            Scanner sc = new Scanner(System.in);
            System.out.print("student ID: ");
            String id = sc.nextLine();
            int index = findingstudent(id, Students_objects);
            if (index == -1) {  // make sure that the id we want to delete is found
                System.out.println("No such ID to delete...");
            } else {
                Students_objects.remove(index);
            }
            input = UI_admin_view();

        } else if (input == 3) // ==================================add librarian
        {
            // validate input
            Scanner sc = new Scanner(System.in);
            System.out.print("Librarian Name: ");
            String librarian_name = sc.nextLine();
            while (!checkAlphabetic(librarian_name)) {  // make sure the name is all characters
                System.out.print("Librarian name must be all characters.... ");
                librarian_name = sc.nextLine();
            }
            System.out.print("Librarian ID: ");
            String librarian_id = sc.nextLine();
            while (findinglibrarian(librarian_id, Librarian_objects) != -1) {  // make sure the id is not in the csv
                System.out.print("This ID already exists, please try another ID\nNew ID: ");
                librarian_id = sc.nextLine();
            }
            System.out.print("Password: ");

            String librarian_password = sc.nextLine();

            Librarians lib = new Librarians(librarian_name, librarian_id, librarian_password); // construct the object with the inputs
            Librarian_objects.add(lib);
            input = UI_admin_view();


        } else if (input == 4) //===================================== delete librarian
        {
            // search for the item to delete
            Scanner sc = new Scanner(System.in);
            System.out.print("Librarain ID to delete: ");
            String id = sc.nextLine();
            int index = findinglibrarian(id, Librarian_objects);
            if (index == -1) { // make sure the id is in the file
                System.out.println("No such ID to delete...");
            } else {
                Librarian_objects.remove(index);
            }
            input = UI_admin_view();

        } else if (input == 5) //============================================= add admins
        {
            // validate the input
            Scanner sc = new Scanner(System.in);
            System.out.print("Admin Name: ");
            String admins_name = sc.nextLine();
            while (!checkAlphabetic(admins_name)) {
                System.out.print("Admin name must be all characters....\nAdmin name: ");
                admins_name = sc.nextLine();
            }
            System.out.print("Admin ID: ");
            String admins_id = sc.nextLine();
            while (findingadmin(admins_id, Admins_objects) != -1) {
                System.out.print("This ID already exists, please try another ID: ");
                admins_id = sc.nextLine();
            }
            System.out.print("Password: ");
            String admins_password = sc.nextLine();

            Admins ad = new Admins(admins_name, admins_id, admins_password);
            Admins_objects.add(ad);
            input = UI_admin_view();

        } else // ========================================================delete admins
        {
            // search for the item to delete
            Scanner sc = new Scanner(System.in);
            System.out.print("Admins ID to delete: ");
            String id = sc.nextLine();
            int index = findingadmin(id, Admins_objects);
            if (index == -1) {
                System.out.println("No such ID to delete...");
            } else {
                Admins_objects.remove(index);
            }
            input = UI_admin_view();

        }
    }
        System.out.println("Logged out");


    }

    static int UI_Librarian_view()
    {

        System.out.println("========= Librarian View =========");
        System.out.println("1. Add Books");
        System.out.println("2. Delete Books");
        System.out.println("3. Search Books by name");
        System.out.println("4. Search Books by ID");
        System.out.println("5. Issue Books");
        System.out.println("6. Return Books");
        System.out.println("7. View Books");
        System.out.println("8. View Issued Books");
        System.out.println("9. Log out");
        System.out.print("Please input your choice: ");

        Scanner myobj = new Scanner(System.in);
        int input  = myobj.nextInt();  // make sure the user inputs the correct input
        while(input < 1 || input > 9 )
        {
            System.out.print("Please input a correct choice: ");
            Scanner myobj1 = new Scanner(System.in);
            input  = myobj1.nextInt();
        }
        return input;

    }

    static void librarian_view(ArrayList<Books> Books_objects,ArrayList<Issued_Books> Issued_books_objects,ArrayList<Students> Students_objects)
    {
        int input = UI_Librarian_view();
        while (input != 9) {   // so that when 9 is input we can log out
        if (input == 1)//====================Add Book
        {
            // validate input
            Scanner sc = new Scanner(System.in);

            System.out.print("Book ID: ");
            String book_id = sc.nextLine();
            while (findingbooks_id(book_id, Books_objects) != -1) {
                System.out.print("This id is already present...\n New ID: ");
                book_id = sc.nextLine();
            }
            while (!isInt(book_id)) {
                System.out.print("ID should only contatin numbers...\n New ID: ");
                book_id = sc.nextLine();
            }

            System.out.print("Book name: ");
            String bookname = sc.nextLine();
            while (!checkAlphabetic(bookname)) {
                System.out.print("Name must be only made of characters \n Book name: ");
                bookname = sc.nextLine();
            }


            System.out.print("Author Name: ");
            String Authour_name = sc.nextLine();
            while (!checkAlphabetic(bookname)) {
                System.out.print("Author Name must be only made of characters \n Author name: ");
                bookname = sc.nextLine();
            }

            boolean type=true;
            int Avilable_Quantity=0;
            while (type) {  //make sure the input is int

                try {
                    System.out.print("Avilable Quantity: ");
                    Avilable_Quantity = sc.nextInt();
                    
                    type=false;
                } catch (Exception e) {

                    System.out.println("Error!\n");
                    System.out.print("Avilable Quantity Should only contain numbers...\n New Quantity: ");
                    Avilable_Quantity = sc.nextInt();
                    
                }
                
            }

            boolean type2=true;
            int Issued_Quantity=0;
            while (type2) {    // make sure the input is all numeric

                try {
                    System.out.print("Issued Quantity: ");
                    Issued_Quantity = sc.nextInt();
                    
                    type2=false;
                } catch (Exception e) {

                    System.out.println("Error!\n");
                    System.out.print("Issued Quantity Should only contain numbers...\n New Quantity: ");
                    Issued_Quantity = sc.nextInt();
                    
                }
                
            }

            // use add to put the object inside the arraylist
            Books bk = new Books(book_id, bookname, Authour_name, Avilable_Quantity, Issued_Quantity);
            
            Books_objects.add(bk);
            input = UI_Librarian_view();

        }

        else if(input==2) //=========== delete books
        {

            Scanner sc = new Scanner(System.in);
            System.out.print("Book ID: ");
            String id = sc.nextLine();
            int index = findingbooks_id(id, Books_objects);
            if (index == -1) {  // make sure the id is there in the file
                System.out.println("No such Book to delete...");
            } else {
                Books_objects.remove(index);
            }
            input = UI_Librarian_view();
        }    

            else if(input==3)  //========search for the book by name
        {

            Scanner sc = new Scanner(System.in);
            System.out.print("Book Name: ");
            String name = sc.nextLine();
            ArrayList<Integer> index = finding_multible_books_name(name, Books_objects);//stores the index of all similar names
            if (index.size() == 0) {
                System.out.println("No such Book in data...");
            } else {

                for(int i=0;i<index.size();i++)  // print all the books with similar name

                System.out.println(Books_objects.get(index.get(i)).toString());

            }
            input = UI_Librarian_view();


        }    

            else if(input==4) //===============search books by ID
        {

            Scanner sc = new Scanner(System.in);
            System.out.print("Enter Book ID: ");
            String name = sc.nextLine();
            int index = findingbooks_id(name, Books_objects);

            if (index == -1) { // make sure the id is already their
                System.out.println("No such Book in data...");
            } else {
               System.out.println(Books_objects.get(index).toString());
            }
            input = UI_Librarian_view();
        }
        else if(input==5) //===========Issue books
        {
            // validating the input
            Scanner sc = new Scanner(System.in);
            System.out.print("Input Student ID to Issue The Book: ");
            String student_id = sc.nextLine();

            while (!isInt(student_id)) {
                System.out.print("ID should only contatin numbers...\n Add valid ID: ");
                student_id = sc.nextLine();
            }

            while (findingstudent(student_id, Students_objects) == -1) {
                System.out.print("No Such Student in the Database\n Add valid ID: ");
                student_id = sc.nextLine();
            }

            System.out.print("Input Book Name to Issue: ");
            String book_name = sc.nextLine();
            while (findingbooks_name(book_name, Books_objects) == -1) {
                System.out.print("No Such Book in the Database\n Search another Book Name: ");
                book_name = sc.nextLine();
            }
            
            int index=findingbooks_name(book_name, Books_objects);
            if (Books_objects.get(index).getAvailable_quantity()<=0){ // can't issue the book

                System.out.println("No Books Available Currently :( ");
            }
            else{
            // change the available quantity and issued quantity
            Books_objects.get(index).setAvailable_quantity(Books_objects.get(index).getAvailable_quantity()-1);
            Books_objects.get(index).setIssued_quantitiy(Books_objects.get(index).getIssued_quantitiy()+1);
            // add a new record to issued books
            String p_id = Integer.toString(Issued_books_objects.size()+1); // to calculate the procedure ID
            Issued_Books newly_issued = new Issued_Books(p_id,Books_objects.get(index).get_Book_ID(),student_id,false );
            Issued_books_objects.add(newly_issued); //add this to the list
            }
            input = UI_Librarian_view();

        } 

        else if(input==6) //================ return book
        {
            Scanner sc = new Scanner(System.in);
            System.out.print("Input Student ID to Return The Book: ");
            String student_id = sc.nextLine();

            while (!isInt(student_id)) {
                System.out.print("ID should only contatin numbers...\n Add valid ID: ");
                student_id = sc.nextLine();
            }

            System.out.print("Input Book Name to Return: ");
            String book_name = sc.nextLine();
          
            while (findingbooks_name(book_name, Books_objects) == -1) {
                System.out.print("No Such Book in the Database\n Search another Book Name: ");
                book_name = sc.nextLine();
            }


            int index_book= findingbooks_name(book_name, Books_objects); //find the book in the books.csv
            //check if the book and id are simultaneously in one record in the issued books file
            if(findingissued_data(student_id,Books_objects.get(index_book).get_Book_ID(), Issued_books_objects) == -1) {
                System.out.print(" Sorry, No Such Student or Book in the Issued Database\n ");
            }
            else{
                //use the index of the book in the books.csv to update the available and issued quantity
            Books_objects.get(index_book).setAvailable_quantity(Books_objects.get(index_book).getAvailable_quantity()+1);
            Books_objects.get(index_book).setIssued_quantitiy(Books_objects.get(index_book).getIssued_quantitiy()-1);
            // this index is used to find the record that we need to change the returned from false to true
            int index_return=findingissued_data(student_id,Books_objects.get(index_book).get_Book_ID(), Issued_books_objects);
            Issued_books_objects.get(index_return).setReturned(true); //changed the returend
            }

            input = UI_Librarian_view();

        } 
        else if(input==7) //============== view books
        {
            // view_books loops through the entire arraylist of books, and uses toString
            view_books(Books_objects);
            input = UI_Librarian_view();
        } 
        else if(input==8)  //================= view issued books
        {
            // view_issued_books loops through the entire arraylist of issued_books, and uses toString
            view_issued_books(Issued_books_objects);
            input = UI_Librarian_view();

        }
        }
        System.out.println("logout");
    
        
    }


    static void view_books(ArrayList<Books> x)
    {
       // prints the object.tostring
        for(int i=0; i<x.size(); i++)
        {
            System.out.println(x.get(i).toString());
        }
        
    }
    
    static void view_issued_books(ArrayList<Issued_Books> x)
    {
        // prints the object.tostring
        for(int i=0; i<x.size(); i++)
        {
            System.out.println(x.get(i).toString());
        }
    }
    public static boolean checkAlphabetic(String input) {
        //used to check if a string is entirely characters
        for (int i = 0; i != input.length(); ++i) {
            if (!Character.isLetter(input.charAt(i)) && input.charAt(i) !=' ') {
                return false;
            }
        }
        return true;
    }
    public static boolean isInt(String str) {
        // used to make sure a string is entirely numerical
        try {
            int x = Integer.parseInt(str);
            return true; //String is an Integer
        } catch (NumberFormatException e) {
            return false; //String is not an Integer
        }
    }
    static int findingstudent(String ID, ArrayList<Students> l)
    {
        // function that loops the array of students to find the student with the same ID in input
        // it will return its index and -1 if not found
        for(int i=0; i<l.size(); i++)
        {
            if(ID.equals((l.get(i).getStudent_id())))
                return i;
        }

        return -1;
    }

    static int findingissued_data(String ID,String book_id, ArrayList<Issued_Books>l)
    {
        // function that loops the array of students to find the student with the same ID in input
        // it will return its index and -1 if not found
        for(int i=0; i<l.size(); i++)
        {
            if(ID.equals((l.get(i).getStudent_id())) && book_id.equals((l.get(i).getBook_id())))
                return i;
        }

        return -1;
    }

    static int findingbooks_id(String ID, ArrayList<Books> l)
    {
        // function that loops the array of students to find the student with the same ID in input
        // it will return its index and -1 if not found
        for(int i=0; i<l.size(); i++)
        {
            if(ID.equals((l.get(i).get_Book_ID())))
                return i;
        }

        return -1;
    }

    static int findingbooks_name(String name, ArrayList<Books> l)
    {
        // function that loops the array of students to find the student with the same ID in input
        // it will return its index and -1 if not found
        for(int i=0; i<l.size(); i++)
        {
            if(name.equals((l.get(i).getBook_name())))
                return i;
        }

        return -1;
    }

    static ArrayList<Integer> finding_multible_books_name(String name, ArrayList<Books> l)
    {
        // finds all books with similar name and stores them in an array
        ArrayList<Integer> finder= new ArrayList<>();
        for(int i=0; i<l.size(); i++)
        {
            if(name.equals((l.get(i).getBook_name())))
                finder.add(i);
        }
        return finder;
    }

    static int findinglibrarian(String ID, ArrayList<Librarians> l)
    {
        //search for the librarian with the given IDm returns its index and -1 if not found
        for(int i=0; i<l.size(); i++)
        {
            if(ID.equals((l.get(i).getLibrarian_id())))
                return i;
        }
        return -1;
    }

    static int findingadmin(String ID, ArrayList<Admins> l)
    {
        // search for the admin accroding to his ID and if found returns his ID, otherwise returns -1
        for(int i=0; i<l.size(); i++)
        {
            if(ID.equals((l.get(i).getAdmin_id())))
                return i;
        }
        return -1;
    }


}
