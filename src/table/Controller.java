package table;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MOULID.
 */
public class Controller {
    @FXML
    AnchorPane pane;
    @FXML
    TableView<elem> tableView;
    @FXML
    TableColumn<elem, String> etatCol;
    @FXML
    TableColumn<elem, String> col1Col;
    @FXML
    TableColumn<elem, String> col2Col;
    @FXML
    Label label1, label2, result;
    @FXML
    TextField etatTF, col1TF, col2TF, motTF;
    @FXML
    ListView finalLV, initialLV;

    ObservableList<String> lista = FXCollections.observableArrayList();
    ObservableList<elem> etatList = FXCollections.observableArrayList();

    public void initialize() {

        etatCol.setCellValueFactory(new PropertyValueFactory<>("etat"));
        col1Col.setCellValueFactory(new PropertyValueFactory<>("a"));
        col2Col.setCellValueFactory(new PropertyValueFactory<>("b"));
        etatList.clear();
        lista = sample.Controller.ll;
        col1Col.setText(lista.get(0));
        col2Col.setText(lista.get(1));
        label1.setText(lista.get(0));
        label2.setText(lista.get(1));

        for (elem s : etatList) {
            System.out.println(s.getA());
        }
        tableView.getItems().setAll(etatList);


        finalLV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }

    public void ajouter() {
        String etate = etatTF.getText();
        String coll1 = col1TF.getText();
        String coll2 = col2TF.getText();

        elem e = new elem(etate, coll1, coll2);

        if (!Check(e) && !(etate.equals(""))) {
            etatList.add(e);
            tableView.getItems().setAll(etatList);
            initialLV.getItems().add(e.getEtat());
            finalLV.getItems().add(e.getEtat());
        }


    }

    public boolean Check(elem e) {
        boolean b = false;

        for (elem el : etatList) {
            if (el.getEtat().equals(e.getEtat())) {
                b = true;
                break;
            }
        }
        return b;
    }

    public void dodo() {
        List<elem> notreList = new ArrayList<>();
        elem eee = new elem();
        String ini = "";
        List<String> fin = new ArrayList<>();
        String table[][] = new String[tableView.getItems().size() + 1][3];
        notreList = tableView.getItems();

        table[0][0] = "/";
        table[0][1] = lista.get(0);
        table[0][2] = lista.get(1);
        for (int i = 1; i < table.length; i++) {
            eee = notreList.get(i - 1);

            table[i][0] = eee.getEtat();
            table[i][1] = eee.getA();
            table[i][2] = eee.getB();
        }


        ObservableList<String> lll = FXCollections.observableArrayList();
        ObservableList<String> lla = FXCollections.observableArrayList();
        lll = initialLV.getSelectionModel().getSelectedItems();
        for (String ss : lll)
            ini = ini + ss;


        lla = finalLV.getSelectionModel().getSelectedItems();
        for (String ss : lla) {
            fin.add(ss);
        }

        String mot = motTF.getText();
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                System.out.print(table[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("mot =" + mot + "\ninitial =" + ini + "\nfinal= " + fin);
        Check(table, mot, ini, fin);

    }

    public static int getCol(String table[][], String symbol) {
        int y = 0;
        for (int i = 1; i < table[0].length; i++) {
            if (table[0][i].equals(symbol)) {
                y = i;
                break;
            }
        }
        return y;
    }

    public static int getEtatLig(String table[][], String etat) {
        int y = 0;
        for (int i = 0; i < table.length; i++) {
            if (table[i][0].equals(etat)) {
                y = i;
                break;
            }
        }
        return y;
    }

    public void Check(String table[][], String mot, String initial, List<String> finale) {
        boolean b = true;
        String symbol = "";
        int numEtat = getEtatLig(table, initial);
        String next = "";
        for (int i = 0; i < mot.length(); i++) {
            symbol = String.valueOf(mot.charAt(i));
            next = table[numEtat][getCol(table, symbol)];
            System.out.println(next);
            if (!next.equals("/")) {
                numEtat = getEtatLig(table, next);
            } else {
                b = false;
                break;
            }

        }
        if (finale.contains(next) && b) {
            System.out.println("this is a ");
            result.setText("cet mot apartient au language");
        } else {
            System.out.println("this is not");
            result.setText("cet mot n'apartient pas au language");
        }
    }

    public void retour() throws MalformedURLException {
        makeFadeOut("..\\sample\\sample.fxml");
    }

    public void showit() {
        Stage stage = new Stage();
        File file = new File("..\\instructions.jpg");
        ImageView iv1 = new ImageView();
        iv1.setImage(new Image(new File(file.getAbsolutePath().replace("..", "src")).toURI().toString()));
        Group root = new Group();
        Scene scene = new Scene(root);
        scene.setFill(Color.BLACK);
        HBox box = new HBox();
        box.getChildren().add(iv1);
        root.getChildren().add(box);

        stage.setTitle("instructions");
        stage.setWidth(603);
        stage.setHeight(543);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    public void close() {
        System.exit(0);
    }

    public void supp() {

        elem e = tableView.getSelectionModel().getSelectedItem();

        System.out.println(e);
        if (e == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("erreur");
            alert.setContentText("tu doit d'abord selectionner l'etat que tu veut supprimer\nde la table de transition");
            alert.setHeaderText(null);
            alert.show();
        } else {
            tableView.getItems().remove(e);
            etatList.remove(e);

            finalLV.getItems().remove(e.getEtat());
            initialLV.getItems().remove(e.getEtat());
            finalLV.refresh();
            initialLV.refresh();
        }
    }

    public void clear() {
        initialize();
        finalLV.getItems().removeAll(finalLV.getItems());
        initialLV.getItems().removeAll(initialLV.getItems());
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
