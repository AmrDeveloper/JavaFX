package JavaClass;

//Employee Attribute
public class Employee {

    //Employee id
    private String id;
    //Employee Name
    private String name;
    //Employee Address
    private String address;

    //Constructor
    Employee(String id , String name , String address){
        this.id = id;
        this.name = name;
        this.address = address;
    }

    //Getter And Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
