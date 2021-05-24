package stone_collection_boardgame.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.io.IOException;

/**
*opening game class of the application
*/
public class OpeningGameController {

    @FXML
    private TextField Player1NameTextField;

    @FXML
    private TextField Player2NameTextField;


    @FXML
    private Label errorLabel;

/**
 *  start the game when start button is pressed
*/
    @FXML
    public void startGame(ActionEvent actionEvent) throws IOException {
        System.out.println(Player1NameTextField.getText());
        if (Player1NameTextField.getText().isEmpty() || Player2NameTextField.getText().isEmpty()) {
            errorLabel.setText("Please enter your name!");
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(OpeningGameController.class.getResource("/fxml/gameboard_ui.fxml"));
            Parent root = fxmlLoader.load();
            fxmlLoader.<StoneCollectionGameController>getController().setPlayerName(Player1NameTextField.getText(), Player2NameTextField.getText());
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            Logger.info("The user's names are set to {} and {}, loading game scene", Player1NameTextField.getText(), Player2NameTextField.getText()); // TODO
        }
    }
}
