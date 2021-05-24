package stone_collection_boardgame.results;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class WinnerResult {
    private String winnerName;
    private int wins;
}
