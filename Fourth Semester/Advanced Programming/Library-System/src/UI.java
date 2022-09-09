import java.util.ArrayList;

public class UI {
    public static void main(String[] args)  {
        /* Creating FileM objects for each file,
        those objects are used to read, write and hold the data in the csv file in a string array
         */
        //extract functions  that we will use in User Interface
        FileM Admins_file = new FileM();
        FileM Books_file = new FileM();
        FileM Issued_books_file = new FileM();
        FileM Librarians_file = new FileM();
        FileM Students_file = new FileM();
     
        // Opening all the csv files and storing all data in an array of strings
        UI_Fuctions.Reading_all_files(Admins_file, Books_file, Issued_books_file, Librarians_file, Students_file);

        // Creating arraylists of all our classes, to create an array of objects
        ArrayList<Admins> Admins_objects = new ArrayList<>();
        ArrayList<Books> Books_objects = new ArrayList<>();
        ArrayList<Issued_Books> Issued_books_objects = new ArrayList<>();
        ArrayList<Librarians> Librarians_objects = new ArrayList<>();
        ArrayList<Students> Students_objects = new ArrayList<>();

        // fill the objects, this function changes the array of strings, to  array of objects, this is to make it easier to edit and delete
        // afterwards we will be working on the array of objects
        UI_Fuctions.creating_objects(Admins_file, Books_file, Issued_books_file, Librarians_file, Students_file, Admins_objects, Books_objects,Issued_books_objects,Librarians_objects,Students_objects);

        // starting the interface, make the user choose whether he is admin or librarian
        // then will start to ask for ID and checks the password
        // which interface variable is used to let us know afterwords which interface to open, admin or librarian
        int which_interface = UI_Fuctions.login(Admins_file, Librarians_file);

        //this "if" statement is to diverge the code into the two possible interfaces
        // admin or librarian
        if(which_interface == 1)
        {
            // this function contains the admin interface along with all the functions an admin can do
            UI_Fuctions.Admin_view( Admins_objects,Librarians_objects,Students_objects );
        }
        else
        {
            // this function contains the interface of the librarian and all the functions a librarian can do
            UI_Fuctions.librarian_view(Books_objects,Issued_books_objects,Students_objects);
        }


        // Writing all the updates
        UI_Fuctions.writing_to_files(Admins_file, Books_file, Issued_books_file, Librarians_file,Students_file, Admins_objects, Books_objects,Issued_books_objects,Librarians_objects,Students_objects );


    
    }


}


