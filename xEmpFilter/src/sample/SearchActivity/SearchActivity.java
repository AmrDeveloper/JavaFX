package sample.SearchActivity;

import JavaClass.EmpReader;
import JavaClass.Employee;
import JavaClass.SqliteController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import java.io.File;
import java.util.List;

public class SearchActivity extends Window {

    private String path;
    private String one_id;
    private EmpReader reader;
    //Number of Result
    @FXML private Label result_num;
    @FXML private TextField id_one;
    @FXML private TextField id_list;
    //Table
    @FXML private TableView<Employee> employees_list;
    @FXML private TableColumn<Employee,String> empl_id;
    @FXML private TableColumn<Employee,String> empl_name;
    @FXML private TableColumn<Employee,String> empl_address;
    private List<Employee> employeeList;
    private SqliteController controller;
    //To make List To use it in table
    ObservableList<Employee> data = FXCollections.observableArrayList();


    @FXML
    private void getIdOne(){
        this.one_id = id_one.getText();
    }

    @FXML
    private void getPath(){
        this.path = id_list.getText();
    }

    @FXML
    //get This Employee With this id
    private void singleGet() {
        //Clear data and refresh to protect TableView from duplication
        if(data.size() != 0){
            //if data size not equal zero that mean this is not first search
            //so clear all old search and start from scratch
            data.clear();
            //Refresh TableView to put the new Search Result
            employees_list.refresh();
        }
        controller = new SqliteController();
        employeeList = controller.getEmployee(new String[]{one_id});
        //take every employee in list
        for (Employee e : employeeList) {
             //put this Employee Object in data (ObservableList)
             data.add(e);
        }
        //Connection Between Column and Employees
        //empl_id with id
        empl_id.setCellValueFactory(new PropertyValueFactory<Employee,String>("id"));
        //emp_name with name
        empl_name.setCellValueFactory(new PropertyValueFactory<Employee,String>("name"));
        //empl_address with address
        empl_address.setCellValueFactory(new PropertyValueFactory<Employee,String>("address"));
        //Show All in Table
        employees_list.setItems(data);
        //Show Number of result in Table
        result_num.setText("Number Of Result : 1");
    }


    @FXML
    //reading every id in this list
    //then take get all employees with id in arr[i]
    private void listGet(){
        //Clear data and refresh to protect TableView from duplication
        if(data.size() != 0){
            //if data size not equal zero that mean this is not first search
            //so clear all old search and start from scratch
            data.clear();
            //Refresh TableView to put the new Search Result
            employees_list.refresh();
        }
        //Assert This is Valid Text File Path
        if((path != null) && (!path.equals("")) && path.contains(".txt")){
            reader = new EmpReader(path.trim());
            String [] ids = reader.reading();
            controller = new SqliteController();
            employeeList = controller.getEmployee(ids);
            //take every employee in list
            for (Employee e : employeeList) {
                //put this Employee Object in data (ObservableList)
                data.add(e);
            }
            //Connection Between Column and Employees
            empl_id.setCellValueFactory(new PropertyValueFactory<Employee,String>("id"));
            empl_name.setCellValueFactory(new PropertyValueFactory<Employee,String>("name"));
            empl_address.setCellValueFactory(new PropertyValueFactory<Employee,String>("address"));
            //Show All in Table
            employees_list.setItems(data);
            //Show Number of result in Table
            result_num.setText("Number Of Result : " + employeeList.size());
        }
        else
        {
            //invalid Path
            id_list.setText("Invalid Path");
        }
    }

    @FXML
    //choose Text folder to open
    private void getEmployeeList(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Text File");
        //File to resive the chooser
        File file = fileChooser.showOpenDialog(this);
        //Catch Null Pointer Exception when iam open file
        try
        {
            this.path = file.toURI().toString();
            //delete file: on the path and using trip to delete space
            this.path = path.replaceAll("file:","").trim();
            this.id_list.setText(path);
        }
        catch(NullPointerException e)
        {
            this.id_list.setText(" ");
        }
    }







}
