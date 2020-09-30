package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Choisir votre alphabets");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.setResizable(false);
        primaryStage.setIconified(false);
        File file = new File("..\\idea.jpg");
        System.out.println(file.getAbsolutePath().replace("..", "src"));
        primaryStage.getIcons().add(new Image(new File(file.getAbsolutePath().replace("..", "src")).toURI().toString()));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
