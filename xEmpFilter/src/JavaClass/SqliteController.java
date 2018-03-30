package JavaClass;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteController
{

    //Sqlite Path And Table Name
    private final String sqlite_table = "Employee";
    private final String sqlite_path = "jdbc:sqlite:src/DataBase/Employees.sqlite";
    private final String sqlite_incert = "INSERT INTO Employee(id,name,address) VALUES(?,?,?)";
    private Connection connection;

    //Constructor
    public SqliteController()
    {
        //return Connection Object and put it in connection
        connection = sqliteConnect();
    }

    //make Connection
    private Connection sqliteConnect(){
        //Try To Make Connection
        try
        {
            // load the sqlite-JDBC driver using the current class loader
            Class.forName("org.sqlite.JDBC");
            // create a database connection
            connection = DriverManager.getConnection(sqlite_path);
        }
        //if cant find Library JDBC
        catch (ClassNotFoundException ex)
        {
            System.out.println("Cant Find JDBC");
        }
        //if Can't connection with database
        catch (SQLException ex)
        {
            System.out.println("Can't Make Connection");
        }
        //return object of Connection
        return connection;
    }


    //Take Result Set and get Data
    public List<Employee> getEmployee(String [] employee_id){
        connection = sqliteConnect();
        //return list of employee
        List<Employee> list = new ArrayList<>();
        try
        {
            //it time to reading :D
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            //Add Every Employee with id in Employee_id
            for(int i = 0 ; i < employee_id.length ; i++)
            {
                //Get Every Item with same id in Array of String employee_id using SQL Code
                ResultSet rs = statement.executeQuery("select * from Employee WHERE id = " + String.valueOf(employee_id[i]));
                //While More Employee In Sqlite
                while(rs.next())
                {
                    // read the result set
                    list.add(new Employee(
                            //set id
                            rs.getString("id"),
                            //set name
                            rs.getString("name"),
                            //set address
                            rs.getString("address")
                    ));
                }
            }
            //End Reading so close DataBase
            connection.close();
        }
        catch (SQLException e)
        {
            //Print Error Message If Cant Resing
            System.out.println("Reading Employee From DataBase");
        }
        //return object of List to using it in ListView
        return list;
    }

    //Add Employee To Sqlite
    //Using id , name , address
    public boolean setEmplyee(String id , String name , String address){
        //create connection
        connection = sqliteConnect();
        try
        (
            //Put This Employee in Sqlite
            PreparedStatement pstmt = connection.prepareStatement(sqlite_incert)) {
            //insert id
            pstmt.setString(1, id);
            //insert name
            pstmt.setString(2, name);
            //insert address
            pstmt.setString(3, address);
            //update Data
            pstmt.executeUpdate();
            //Close Connection
            connection.close();
            //return true to use this data and show done message if i want
            return true;
        }
        catch (SQLException e)
        {
            //return false if cant put this empouyee
            return false;
        }
    }



}
