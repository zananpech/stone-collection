package stone_collection_boardgame.results;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameResult {
    private int id;
    private String player1Name;
    private int stepsByPlayer1;
    private String player2Name;
    private int stepsByPlayer2;
    private String winnerName;
    private LocalDate gameDate;
    private LocalTime gameTime;

//    public GameResult(int id, String player1Name, int stepsByPlayer1, String player2Name, int stepsByPlayer2, String winnerName, LocalDate date) {
//        this.id = id;
//        this.player1Name = player1Name;
//        this.stepsByPlayer1 = stepsByPlayer1;
//        this.player2Name = player2Name;
//        this.stepsByPlayer2 = stepsByPlayer2;
//        this.winnerName = winnerName;
//        this.gameDate = date;
//    }
}
