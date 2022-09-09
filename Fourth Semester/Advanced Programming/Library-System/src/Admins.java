import java.util.ArrayList;

public class Admins {

// attributes
    private String admin_name;
    private String admin_id;
    private String admin_password;
    // zero argument constructor
    public Admins(){ }
    // initializing constructor
    public Admins(String admin_name, String admin_id, String admin_password) {
        this.admin_name = admin_name;
        this.admin_id = admin_id;
        this.admin_password = admin_password;
    }
// setters and getters
    public String getAdmin_name() {
        return this.admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getAdmin_id() {
        return this.admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_password() {
        return this.admin_password;
    }

    public void setAdmin_password(String admin_password) {
        this.admin_password = admin_password;
    }

    // used to parse the array given to extract the attributes of an object
    public void create(ArrayList<String> f , int start )
    {
        setAdmin_id(f.get(start));
        setAdmin_name(f.get(++start));
        setAdmin_password(f.get(++start));
    }

    public static ArrayList<String>  objects_to_string(ArrayList<Admins> x)
    {
        /*
        Static function as we don't want it to work with individual objects

        1. takes the file headers into the array of string
        2. extracts the private attributes from the objects one by one
        3. adds them to the array of strings
        => to be used in the FileM write as it takes an arraylist of strings
        */
        ArrayList<String> s = new ArrayList<>();
        s.add("Admin ID");
        s.add("Admin Name");
        s.add("Admin password");
        for(int i=0; i<x.size(); i++)
        {
            s.add(x.get(i).getAdmin_id());
            s.add(x.get(i).getAdmin_name());
            s.add(x.get(i).getAdmin_password());
        }
        return s;
    }


    @Override
    public String toString() {
        return "Admins{" +
                "admin_name='" + admin_name + '\'' +
                ", admin_id='" + admin_id + '\'' +
                ", admin_password='" + admin_password + '\'' +
                '}';
    }
}