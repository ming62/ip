package gus.gui;

import gus.Gus;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Gus gus;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Jesse.jpg"));
    private Image gusImage = new Image(this.getClass().getResourceAsStream("/images/Gus.jpg"));
    private Image gusExceptionImage = new Image(this.getClass().getResourceAsStream("/images/GusException.jpg"));

    /** Initialize the instance */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showWelcomeMessage();
    }

    /** Injects the Duke instance */
    public void setGus(Gus g) {
        gus = g;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Gus's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = gus.getResponseGui(input);

        boolean isException = response.startsWith("Something went wrong.");
        Image replyImage = isException ? gusExceptionImage : gusImage;
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getGusDialog(response, replyImage)
        );
        userInput.clear();
    }

    private void showWelcomeMessage() {
        String welcomeMessage = "Hello, Welcome to Los Pollos Hermanos.\n"
            + "My name is Gustavo Fring, but you may call me Gus.\n"
            + "What can I do for you today?";
        dialogContainer.getChildren().add(DialogBox.getGusDialog(welcomeMessage, gusImage));
    }
}
