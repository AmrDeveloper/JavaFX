package sample.AddActivity;

import JavaClass.SqliteController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import java.io.IOException;

//This Class for Add Layout Activity Controller
public class AddActivity {

    private String id;
    private String name;
    private String address;
    @FXML private Label status;
    @FXML private TextField employee_id;
    @FXML private TextField employee_name;
    @FXML private TextField employee_address;
    @FXML private ImageView back_img;
    private SqliteController controller;


    @FXML
    //Get Text From id TextField
    private void getEmpId(){
        this.id = employee_id.getText();
    }

    @FXML
    //Get Text From name TextField
    private void getEmpName(){
        this.name = employee_name.getText();
    }

    @FXML
    //Get Text From Address TextField
    private void getEmpAddress(){
        this.address = employee_address.getText();
    }

    @FXML
    //After Check Add This Employee To Data
    private void addToSqltie(){
        //check if id , name and address is valid
        if((id != null) || (name != null) || (address != null))
        {
            //then send to sqlite
            controller = new SqliteController();
            controller.setEmplyee(id,name,address);
            //The Class will close connection after setEmployee
            status.setText("Status : Done :D");
            freeField();
        }
        else
        {
            //invalid Data
            status.setText("Status : Error ~_~");
            freeField();

        }
    }


    //Make All TextField with no text
    private void freeField() {
        this.employee_id.setText("");
        this.employee_name.setText("");
        this.employee_address.setText("");
    }


    @FXML
    private void goMain() throws IOException {

    }



}
