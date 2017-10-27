package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileStore;

public class Controller extends Window {

    //JavaFx Variables
    //TextField for get input path
    @FXML private TextField input_path;
    //TextField for get output path
    @FXML private TextField output_path;
    //TextField for get image name to save with this name
    @FXML private TextField photo_name;
    //TextField For status
    @FXML private Label status;

    //Java Variables
    //String for save image input path
    private String img_input;
    //String for save image output path
    private String img_output;


    //Resizer Object
    private Resizer resizer;


    @FXML
    //Image Input Path getter
    private void get_Input_path() {
        this.img_input = input_path.getText();
    }
    @FXML
    //Image ouput Path getter
    private void get_output_path() {
        this.img_output = output_path.getText();
    }


    @FXML
    //Button Method
    private void start_resize(){
        //Assert all TextField not equal null
        if(img_input == null || img_output == null){
            status.setText("Status : Fail ~_~");
        }
        else{
            try {
                long start = System.currentTimeMillis();
                resizer = new Resizer(this.img_input, this.img_output, null);
                resizer.start_resize();
                //Show Dialog When resizer is done
                status.setText("Status : Done :D");
                start = (System.currentTimeMillis() - start) / 1000;
                System.out.println("Time : " + start + " s");
            }
            catch (Exception e){
                status.setText("Status : Fail ~_~");
            }
        }

    }

    //File Choose open and save
    @FXML
    //choose image folder to open
    private void open_img(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");
        //File to resive the chooser
        File file = fileChooser.showOpenDialog(this);
        //Catch Null Pointer Exception when iam open file
        try
        {
            this.img_input = file.toURI().toString();
            //delete file: on the path and using trip to delete space
            this.img_input = img_input.replaceAll("file:","").trim();
            this.input_path.setText(img_input);
        }
        catch(NullPointerException e)
        {
            this.input_path.setText(" ");
        }

    }

    @FXML
    //choose image to save here
    private void save_img(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");
        //File to resive the chooser
        FileChooser myFile = new FileChooser();
        myFile.setInitialFileName("");
        File file = myFile.showSaveDialog(this);
        //catch Null Pointer Exception when iam save file
        try
        {
            this.img_output = file.toURI().toString();
            //delete file: on the path and using trip to delete space
            this.img_output = img_output.replaceAll("file:","").trim();
            this.output_path.setText(img_output);
        }
        catch(NullPointerException e)
        {
            this.output_path.setText(" ");
        }
    }


    //Social Media ImageView Open Profile
    //Open facebook when click on image view
    @FXML
    private void openFacebook(){
        //My Facebook Profile url
        String facebook = "https://www.facebook.com/AmrDeveloper";
        try
        {
            new ProcessBuilder("x-www-browser",facebook).start();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    //Open github when click on image view
    @FXML
    private void openGithub(){
        //My github Profile url
        String github = "https://github.com/AmrDeveloper";
        try
        {
            new ProcessBuilder("x-www-browser", github).start();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    //Open Profile when click on image view
    @FXML
    private void openLinkedin(){
        //My linkedin Profile url
        String linkedin = "https://www.linkedin.com/in/amrdeveloper/";
        try
        {
            new ProcessBuilder("x-www-browser",linkedin).start();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    //Open askfm when click on image view
    @FXML
    private void openAskfm(){
        //My ask.fm Profile url
        String askfm = "https://ask.fm/AmrDeveloper";
        try
        {
            new ProcessBuilder("x-www-browser",askfm).start();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }




}
