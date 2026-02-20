package gus.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        displayPicture.setImage(img);

        this.getStyleClass().add("dialog-box");
        dialog.getStyleClass().add("dialog");

        dialog.setWrapText(true);
        dialog.maxWidthProperty().bind(this.widthProperty().subtract(110));
        HBox.setHgrow(dialog, Priority.ALWAYS);

        Insets zero = new Insets(0);
        HBox.setMargin(displayPicture, zero);
        HBox.setMargin(dialog, zero);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.getStyleClass().add("user");
        return db;
    }

    public static DialogBox getGusDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.getStyleClass().add("gus");
        if (text != null && text.startsWith("Something went wrong.")) {
            db.dialog.getStyleClass().add("error");
        }
        return db;
    }
}
