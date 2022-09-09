import java.util.ArrayList;

public class Issued_Books {

    //attributes
    private String procedure_iD;
    private String Book_id;
    private String student_id;
    private boolean returned;


    public Issued_Books(){}
    public Issued_Books(String procedure_iD, String book_id, String student_id, boolean returned) {
        this.procedure_iD = procedure_iD;
        Book_id = book_id;
        this.student_id = student_id;
        this.returned = returned;
    }

    // setters and getters
    public String getProcedure_iD() {
        return this.procedure_iD;
    }

    public void setProcedure_iD(String procedure_iD) {
        this.procedure_iD = procedure_iD;
    }

    public String getBook_id() {
        return this.Book_id;
    }

    public void setBook_id(String Book_id) {
        this.Book_id = Book_id;
    }

    public String getStudent_id() {
        return this.student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public boolean isReturned() {
        return this.returned;
    }

    public boolean getReturned() {
        return this.returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }


    public void create(ArrayList<String> f , int start )
    {
        // parse the array of strings and store the attributes to create an object
        setProcedure_iD(f.get(start));
        setBook_id(f.get(++start));
        setStudent_id(f.get(++start));
        setReturned( Boolean.parseBoolean(f.get(++start)));
    }

    public static ArrayList<String>  objects_to_string(ArrayList<Issued_Books> x)
    {
        /*
        Static function as we don't want it to work with individual objects

        1. takes the file headers into the array of string
        2. extracts the private attributes from the objects one by one
        3. adds them to the array of strings
        => to be used in the FileM write as it takes an arraylist of strings
        */
        ArrayList<String> s = new ArrayList<>();
        s.add("Procedure ID");
        s.add("Book ID");
        s.add("Student ID");
        s.add("Returned");
        for(int i=0; i<x.size(); i++)
        {
            s.add(x.get(i).getProcedure_iD());
            s.add(x.get(i).getBook_id());
            s.add(x.get(i).getStudent_id());
            s.add(Boolean.toString(x.get(i).getReturned()));
        }
        return s;
    }

    @Override
    public String toString() {
        return "{" +
                " procedure_iD='" + getProcedure_iD() + "'" +
                ", Book_id='" + getBook_id() + "'" +
                ", student_id='" + getStudent_id() + "'" +
                ", returned='" + isReturned() + "'" +
                "}";
    }



}
