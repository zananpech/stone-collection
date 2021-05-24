package stone_collection_boardgame.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import lombok.extern.java.Log;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Slf4JSqlLogger;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.tinylog.Logger;
import stone_collection_boardgame.model.Point;
import stone_collection_boardgame.model.StoneCollectionGameModel;
import stone_collection_boardgame.results.GameResult;
import stone_collection_boardgame.results.GameResultDao;
import util.javafx.ControllerHelper;

import javax.inject.Inject;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class StoneCollectionGameController {

    @FXML
    private GridPane board;

    private Image stoneImage;

    private final StoneCollectionGameModel model = new StoneCollectionGameModel();

    private List<Point> moves = new ArrayList<Point>();

    private List<Button> clickedButtons = new ArrayList<>();

    private String player1Name;
    private String player2Name;


    @FXML
    private Label currentPlayerName;

    @Inject
    private FXMLLoader fxmlLoader;

    private LocalDate gamedate = LocalDate.now();
    private LocalTime gameTime = LocalTime.now();

    private int totalStoneCollected = 0;
    private int stoneCollected = 0;

    public void setPlayerName(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        currentPlayerName.setText(player1Name);
    }

    @FXML
    private void initialize() {
        for (int i = 0; i < board.getRowCount(); i++) {
            for (int j = 0; j < board.getColumnCount(); j++) {
                var stoneButton = createButton();
                board.add(stoneButton, j, i);
            }
        }
    }

    private Button createButton() {
        stoneImage = new Image(getClass().getResource("/images/stone2.png").toExternalForm());
        var stoneButton = new Button();
        stoneButton.setGraphic(new ImageView(stoneImage));
        stoneButton.setOnMouseClicked(this::handleCollectStone);
        return stoneButton;
    }


    @FXML
    private void handleCollectStone(MouseEvent event) {
        var square = (Button) event.getSource();
        clickedButtons.add(square);
        var row = GridPane.getRowIndex(square);
        var col = GridPane.getColumnIndex(square);
        moves.add(new Point(row,col));
        Logger.debug("Button ({}, {}) is pressed", row, col);
        stoneCollected++;
        square.setDisable(true);
    }

//    private void handleSolved(ObservableValue<? extends Boolean> observableValue, boolean oldValue, boolean newValue) {
//        if (newValue) {
//            Logger.info("Player {} has solved the game in {} steps", playerName, steps.get());
//            stopwatch.stop();
//            messageLabel.setText(String.format("Congratulations, %s!", playerName));
//            resetButton.setDisable(true);
//            giveUpFinishButton.setText("Finish");
//        }
//    }


    public void handleFinishTurn(javafx.event.ActionEvent event) throws IOException {
        if (model.isValidMove(moves)){
            totalStoneCollected += stoneCollected;
            stoneCollected = 0;
            moves = new ArrayList<>();
            clickedButtons = new ArrayList<>();
            Logger.debug("{} stones have been collected", totalStoneCollected);

            boolean allStonesAreCollected = totalStoneCollected == 16;
            if (allStonesAreCollected) {
                switch (model.getPlayerTurn()){
                    case ONE -> model.setWinnerName(player1Name);
                    case TWO -> model.setWinnerName(player2Name);
                }

                Logger.debug("The winner is {}", model.getWinnerName());
                createGameResult();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/highscore_ui.fxml"));
                stage.setScene(new Scene(root));
                stage.show();
            }
            else { model.switchPlayerTurn();
                switch (model.getPlayerTurn()) {
                    case ONE -> currentPlayerName.setText(player1Name);
                    case TWO -> currentPlayerName.setText(player2Name);
                }
                Logger.debug(moves);
            }
        }
        else{
            Logger.debug("Invalid Move!");
            stoneCollected = 0;
            moves = new ArrayList<>();
            for (Button button : clickedButtons){
                button.setDisable(false);
            }
        }
    }

    public void createGameResult() {
        Jdbi jdbi = Jdbi.create("jdbc:oracle:thin:@oracle.inf.unideb.hu:1521:ora19c", "u_Q15ZGK", "kalvinter");
        jdbi.installPlugin(new SqlObjectPlugin());
        jdbi.setSqlLogger(new Slf4JSqlLogger());
        List<GameResult> gameResults = jdbi.withExtension(GameResultDao.class, dao -> {
            //dao.createTable();
            int lastGameID = dao.getLastGameID();
            dao.insertGameResult(new GameResult(lastGameID + 1, player1Name, model.getStepsByPlayer1(), player2Name, model.getStepsByPlayer2(), model.getWinnerName(), gamedate, gameTime));
            return dao.listGameResults();
        });
        gameResults.forEach(System.out::println);
    }



}


