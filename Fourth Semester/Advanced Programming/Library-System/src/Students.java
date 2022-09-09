import java.util.ArrayList;

public class Students {

    // attributes
    private String student_name;
    private String student_id;
    private String mobile;
    private String email;
    private String birthday;

    //zero constructor
    public Students(){ }
    // argument constructor
    public Students(String name, String id, String mob, String em, String birth) {
        student_name=name;
        student_id=id;
        mobile=mob;
        email=em;
        birthday=birth;

    }
    //setters and getters
    public String getStudent_name() {
        return this.student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_id() {
        return this.student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public void create(ArrayList<String> f , int start )
    {
        // used to parse the array given to extract the attributes of an object
        setStudent_id(f.get(start));
        setStudent_name(f.get(++start));
        setBirthday(f.get(++start));
        setEmail(f.get(++start));
        setMobile(f.get(++start));
    }

    public static ArrayList<String>  objects_to_string(ArrayList<Students> x)
    {
        /*
        Static function as we don't want it to work with individual objects

        1. takes the file headers into the array of string
        2. extracts the private attributes from the objects one by one
        3. adds them to the array of strings
        => to be used in the FileM write as it takes an arraylist of strings
        */
        ArrayList<String> s = new ArrayList<>();
        s.add("Student ID");
        s.add("Student Name");
        s.add("Student Birthday");
        s.add("Email");
        s.add("Mobile number");
        for(int i=0; i<x.size(); i++)
        {
            s.add(x.get(i).getStudent_id());
            s.add(x.get(i).getStudent_name());
            s.add(x.get(i).getBirthday());
            s.add(x.get(i).getEmail());
            s.add(x.get(i).getMobile());
        }
        return s;
    }
  

    @Override
    public String toString() {
        return "{" +
            " student_name='" + getStudent_name() + "'" +
            ", student_id='" + getStudent_id() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", email='" + getEmail() + "'" +
            ", birthday='" + getBirthday() + "'" +
            "}";
    }

    
}
