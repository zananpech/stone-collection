package stone_collection.model;

import org.junit.jupiter.api.Test;
import stone_collection_boardgame.model.Point;
import stone_collection_boardgame.model.StoneCollectionGameModel;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class GameModelTest {

    @Test
    void test_isValidMove() {
        assertTrue(StoneCollectionGameModel.isValidMove(
                Arrays.asList(
                        new Point(1, 2),
                        new Point(1, 1),
                        new Point(1, 3)
                )
        ));
        assertFalse(StoneCollectionGameModel.isValidMove(
                Arrays.asList(
                        new Point(1, 2),
                        new Point(2, 1),
                        new Point(1, 3)
                )
        ));

    }

        @Test
        void test_allSameRowsOrColumns(){
        assertTrue(StoneCollectionGameModel.allSameRowsOrColumns(
                Arrays.asList(
                        new Point(1,2),
                        new Point(1,1),
                        new Point (1,3)
                )
        ));
        assertTrue(StoneCollectionGameModel.allSameRowsOrColumns(
                    Arrays.asList(
                            new Point(3,2),
                            new Point(4,2),
                            new Point (1,2)
                    )
            ));
        assertFalse(StoneCollectionGameModel.allSameRowsOrColumns(
                    Arrays.asList(
                            new Point(3,1),
                            new Point(4,2),
                            new Point (1,2)
                    )
            ));

    }

}
