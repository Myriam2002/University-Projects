import java.util.ArrayList;

public class Librarians {

    // attributes
    private String librarian_name;
    private String librarian_id;
    private String librarian_password;

    // zero argument constructor
    public Librarians(){ }
    // constructor
    public Librarians(String librarian_name, String librarian_id, String librarian_password) {
        this.librarian_name = librarian_name;
        this.librarian_id = librarian_id;
        this.librarian_password = librarian_password;
    }

    // setters and getters
    public String getLibrarian_name() {
        return this.librarian_name;
    }

    public void setLibrarian_name(String librarian_name) {
        this.librarian_name = librarian_name;
    }

    public String getLibrarian_id() {
        return this.librarian_id;
    }

    public void setLibrarian_id(String librarian_id) {
        this.librarian_id = librarian_id;
    }

    public String getLibrarian_password() {
        return this.librarian_password;
    }

    public void setLibrarian_password(String librarian_password) {
        this.librarian_password = librarian_password;
    }



    public void create(ArrayList<String> f , int start )
    {
        // used to parse the array given to extract the attributes of an object
        setLibrarian_id(f.get(start));
        setLibrarian_name(f.get(++start));
        setLibrarian_password(f.get(++start));
    }

    public static ArrayList<String>  objects_to_string(ArrayList<Librarians> x)
    {
        /*
        Static function as we don't want it to work with individual objects

        1. takes the file headers into the array of string
        2. extracts the private attributes from the objects one by one
        3. adds them to the array of strings
        => to be used in the FileM write as it takes an arraylist of strings
        */
        ArrayList<String> s = new ArrayList<>();
        s.add("Librarian ID");
        s.add("Librarian name");
        s.add("Librarian Password");
        for(int i=0; i<x.size(); i++)
        {
            s.add(x.get(i).getLibrarian_id());
            s.add(x.get(i).getLibrarian_name());
            s.add(x.get(i).getLibrarian_password());
        }
        return s;
    }


    @Override
    public String toString() {
        return "{" +
                " librarian_name='" + getLibrarian_name() + "'" +
                ", librarian_id='" + getLibrarian_id() + "'" +
                ", librarian_password='" + getLibrarian_password() + "'" +
                "}";
    }




}



