package stone_collection_boardgame.results;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor

/**
 *winner result class of the application
 */
public class WinnerResult {
    private String winnerName;
    private int wins;
}
