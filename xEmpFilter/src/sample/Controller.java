package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Controller {


    @FXML
    //Add Button to go to Add Activity
    private Button add_button;

    @FXML
    //Search Button to go to Search Activity
    private Button search_button;

    @FXML
    //Add for button on click to go to add activity
    private void add_layout(){
        //Go to add_lay
        Stage stage = new Stage();
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("AddActivity/add_lay.fxml"));
            Scene scene = new Scene(root,800,600);
            scene.getStylesheets().add("Stylesheet/AddStyle.css");
            stage.setScene(scene);
            root.minHeight(600);
            root.minWidth(800);
            stage.show();
        }
        catch (IOException e)
        {
            System.out.println("Cant Find This Add fxml");
        }
     }

    @FXML
    //search for button on click to go to search activity
    private void search_layout(){
        //Go to search_lay
        Stage stage = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("SearchActivity/search_lay.fxml"));
            Scene scene = new Scene(root,800,600);
            scene.getStylesheets().add("Stylesheet/SearchStyle.css");
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e)
        {
            System.out.println("Cant Find This Search fxml");
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
