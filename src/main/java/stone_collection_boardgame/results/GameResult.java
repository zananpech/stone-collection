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

}
