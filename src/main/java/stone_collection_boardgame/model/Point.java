package stone_collection_boardgame.model;

import java.util.Comparator;

/**
*coordinate class of row and column
*/
public class Point {
    private int x;
    private int y;

    /**
     * Returns the direction to which the cube at the specified position is rolled to the empty space.
     *
     * @param x the row of the board
     * @param y the column of the board
     * @throws IllegalArgumentException if row or column is less than 0
     */
    public Point(int x, int y) {
        if (x<0 || y<0) throw new IllegalArgumentException();
        else{
        this.x = x;
        this.y = y;}
    }


    /**
     * Returns the row coordinate.
     *
     * @return the row coordinate
     */
    public Integer getX() {
        return x;
    }

    /**
     * Returns the column coordinate.
     *
     * @return the column coordinate
     */
    public Integer getY() {
        return y;
    }

/**
 *   class sort the points in ascending order
*/
    public static class sortByXorY implements Comparator<Point> {


        /**
         * Returns value of comparison between rows and columns of both objects.
         *
         * @param o1 point of first object
         * @param o2 point of second object
         * @return value of comparison between rows and columns of both objects
         */
        @Override
        public int compare(Point o1, Point o2) {
            if (o1.getX().compareTo(o2.getX()) == 0) {
                return o1.getY().compareTo(o2.getY());
            }
            return o1.getX().compareTo(o2.getX());
        }
    }
}
