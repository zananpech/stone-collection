package stone_collection_boardgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
*first scene class of the application
*/
public class StoneCollectionGameApplication extends Application {

/**
 * load first scene of the game
*/
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/openinggame_ui.fxml"));
        primaryStage.setTitle("Stone Collection");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
