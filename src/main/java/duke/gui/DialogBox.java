package duke.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent
 * the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static final Font dukeFont = Font.font("Arial", 12);
    private static final Font dukeErrorFont = Font.font("Arial", FontWeight.BOLD, 12);
    private static final Font userFont = Font.font("Verdana", 12);

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img, Font font) {
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

        // Set the font for the dialog box
        dialog.setFont(font);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the
     * right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, userFont);
    }

    public static DialogBox getDukeDialog(String text, Image img, boolean isError) {
        DialogBox db;
        if (isError) {
            db = new DialogBox(text, img, dukeErrorFont);
        } else {
            db = new DialogBox(text, img, dukeFont);
        }
        db.flip();
        return db;
    }
}
