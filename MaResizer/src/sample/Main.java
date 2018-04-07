package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javafx.application.Application;
import sample.Constants.Constants;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        Scene scene = new Scene(root, Constants.WIDTH, Constants.HEIGHT);
        scene.getStylesheets().add("sample/StyleSheet/MainStyle.css");

        primaryStage.getIcons().add(new Image("sample/Assets/resizeIcon.png"));
        primaryStage.setTitle(Constants.NAME);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
