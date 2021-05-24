package stone_collection.model;

import org.junit.jupiter.api.Test;
import stone_collection_boardgame.model.Point;
import stone_collection_boardgame.model.StoneCollectionGameModel;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
public class GameModelTest {

    @Test
    void test_isValidMove(){
        assertEquals(true, StoneCollectionGameModel.isValidMove(
                Arrays.asList(
                        new Point(1,2),
                        new Point(1,1),
                        new Point (1,3)
                )
        ));
        assertEquals(false, StoneCollectionGameModel.isValidMove(
                Arrays.asList(
                        new Point(1,2),
                        new Point(2,1),
                        new Point (1,3)
                )
        ));
    }

}
