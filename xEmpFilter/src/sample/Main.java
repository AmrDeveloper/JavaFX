package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("xEmployeesFilter");
        Scene gui = new Scene(root, 800, 600);
        //Add CSS File
        gui.getStylesheets().add("Stylesheet/MainStyle.css");
        primaryStage.setScene(gui);
        //set Max Height
        //primaryStage.setMaxHeight(600);
        primaryStage.setMinHeight(600);
        //set Max Width
        primaryStage.setMaxWidth(800);
        primaryStage.setMinWidth(800);
        //set Application icon
        primaryStage.getIcons().add(new Image("/Icons/app_icon.png"));
        //Show This Stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }

}
