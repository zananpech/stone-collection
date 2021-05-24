package stone_collection_boardgame.results;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RegisterBeanMapper(GameResult.class)
@RegisterBeanMapper(WinnerResult.class)

public interface GameResultDao {
    @SqlUpdate("""
        create table gameresult (
            id int primary key not null,
            player1Name varchar2(50) not null,
            stepsByPlayer1 int not null,
            player2Name varchar2(50) not null,
            stepsByPlayer2 int not null,
            winnerName varchar2(50) not null,
            gameDate DATE not null,
            gameTime TIMESTAMP not null
        )
        """

    )
/**
 *   create table of game result
*/
void createTable();
    /**
     *   insert game result into database
     */
    @SqlUpdate("INSERT INTO gameresult VALUES (:id,:player1Name, :stepsByPlayer1, :player2Name, :stepsByPlayer2, :winnerName, :gameDate, :gameTime)")
    void insertGameResult(@Bind("id") int id, @Bind("player1Name") String player1Name, @Bind("stepsByPlayer1") int stepsByPlayer1, @Bind("player2Name") String player2Name,@Bind("stepsByPlayer2") int stepsByPlayer2,@Bind("winnerName") String winnerName,@Bind("gameDate") LocalDate gameDate, @Bind("gameTime") LocalTime gameTime);

    /**
     *   insert game result into database
     */
    @SqlUpdate("INSERT INTO gameresult VALUES (:id,:player1Name,:stepsByPlayer1,:player2Name,:stepsByPlayer2,:winnerName,:gameDate,:gameTime)")
    void insertGameResult(@BindBean GameResult gameResult);




    /**
     * Return all the game results from the database
     * @return list of all game results
     */
    @SqlQuery("SELECT * FROM gameresult ORDER BY id")
    List<GameResult> listGameResults();

    /**
     * Return top 5 winner's names and number of wins
     * @return top 5 winner's names and number of wins
     */
    @SqlQuery("select * from (select winnerName, count(winnerName) wins from gameresult group by winnerName order by count(winnerName) DESC) where rownum <=5")
    List<WinnerResult> listTop5WinnerResult();

    /**
     * Return the last game's id
     * @return last game's id
     */
    @SqlQuery("SELECT id FROM gameresult WHERE ROWNUM <=1 ORDER BY id DESC")
    int getLastGameID();
}
