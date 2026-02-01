package gus.gui;

import java.io.IOException;

import gus.Gus;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * Main JavaFX application for Gus
 */
public class Main extends Application {

    private Gus gus = new Gus("./data/gus.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setGus(gus);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
