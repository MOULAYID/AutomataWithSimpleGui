package sample;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.MalformedURLException;

public class Controller {
    @FXML
    AnchorPane pane;
    @FXML
    ListView<String> listView;


    public static ObservableList<String> ll = FXCollections.observableArrayList();

    public void initialize() {
        listView.getItems().addAll("0", "1", "a", "b");
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void GetIt() throws MalformedURLException {

        if (listView.getSelectionModel().getSelectedItems().size() != 2) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("erreur");
            alert.setContentText("tu dois selectionner exactement 2 symboles\nutilise le ctrl");
            alert.setHeaderText(null);
            alert.show();
        } else {
            ll = listView.getSelectionModel().getSelectedItems();
            makeFadeOut("..\\table\\sample.fxml");
        }

    }

    private void makeFadeOut(String l) throws MalformedURLException {

        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(pane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);

        fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LoadScene(l);
            }
        });
        fadeTransition.play();
    }

    private void LoadScene(String l) {

        Parent SecondView = null;
        try {
            SecondView = (AnchorPane) FXMLLoader.load(getClass().getResource(l));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene newScene = new Scene(SecondView);
        Stage curStage = (Stage) pane.getScene().getWindow();
        curStage.setTitle("tester");
        curStage.setScene(newScene);
    }

}
