package stone_collection_boardgame.model;

import java.util.Comparator;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Integer getDiffOfXandY(){
        return this.y - this.x;
    }

     public static class sortByDiffOfXandY implements Comparator<Point> {

        @Override
        public int compare(Point o1, Point o2) {
            if (o1.getX().compareTo(o2.getX()) == 0) {
                return o1.getY().compareTo(o2.getY());
            }
            return o1.getX().compareTo(o2.getX());
        }
    }
}
