package stone_collection.model;

import org.junit.jupiter.api.Test;
import stone_collection_boardgame.model.Point;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PointTest {

    @Test
    void test_point(){
        assertThrows(IllegalArgumentException.class, () ->
        { Point point = new Point(-1,-1);});

    }
}
