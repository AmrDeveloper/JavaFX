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

        //Make Scene object to add css thems
        Scene scene = new Scene(root, 800, 600);

        //Add CSS Frile
        scene.getStylesheets().add("Stylesheet/dracula_theme.css");

        //Program Title
        primaryStage.setTitle("xResizer");

        //Set Application Icon
        primaryStage.getIcons().add(new Image("/icons/resizer_icon.png"));

        //set height and width
        primaryStage.setScene(scene);

        //show this program
        primaryStage.show();
    }


    public static void main(String[] args) {

        launch(args);
    }
}
