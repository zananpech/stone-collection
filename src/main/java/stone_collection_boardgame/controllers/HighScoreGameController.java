package stone_collection_boardgame.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Slf4JSqlLogger;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.tinylog.Logger;
import stone_collection_boardgame.results.GameResultDao;
import stone_collection_boardgame.results.WinnerResult;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;


public class HighScoreGameController {


    @Inject
    private FXMLLoader fxmlLoader;

    @Inject
    private GameResultDao gameResultDao;

    @FXML
    private TableView<WinnerResult> highScoreTable;

    @FXML
    private TableColumn<WinnerResult, String> winnerName;

    @FXML
    private TableColumn<WinnerResult, Integer> wins;



    @FXML
    private void initialize() {
        Logger.debug("Loading high scores...");
        Jdbi jdbi = Jdbi.create("jdbc:oracle:thin:@oracle.inf.unideb.hu:1521:ora19c", "u_Q15ZGK", "kalvinter");
        jdbi.installPlugin(new SqlObjectPlugin());
        jdbi.setSqlLogger(new Slf4JSqlLogger());
        List<WinnerResult> winnerResults = jdbi.withExtension(GameResultDao.class, GameResultDao::listTop5WinnerResult);
        winnerName.setCellValueFactory(new PropertyValueFactory<>("winnerName"));
        wins.setCellValueFactory(new PropertyValueFactory<>("wins"));


        ObservableList<WinnerResult> observableResult = FXCollections.observableArrayList();
        observableResult.addAll(winnerResults);
        highScoreTable.setItems(observableResult);

    }
    public void handleReplayButton(ActionEvent actionEvent) throws IOException {
        Logger.debug("{} is pressed", ((Button) actionEvent.getSource()).getText());
        FXMLLoader fxmlLoader = new FXMLLoader(HighScoreGameController.class.getResource("/fxml/openinggame_ui.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void handleExitButton() {
        Platform.exit();
        System.exit(0);

    }
}
