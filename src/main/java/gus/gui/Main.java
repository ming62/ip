package gus.gui;

import java.io.IOException;

import gus.Gus;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
            java.net.URL css = Main.class.getResource("/styles.css");
            if (css != null) {
                scene.getStylesheets().add(css.toExternalForm());
            }
            stage.setTitle("Gus");
            try {
                var iconStream = Main.class.getResourceAsStream("/images/Gus.jpg");
                if (iconStream != null) {
                    stage.getIcons().add(new Image(iconStream));
                }
            } catch (Exception e) {
            }
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setGus(gus);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
