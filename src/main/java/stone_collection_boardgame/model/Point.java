package stone_collection_boardgame.model;

import java.util.Comparator;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        if (x<0 || y<0) throw new IllegalArgumentException();
        else{
        this.x = x;
        this.y = y;}
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static class sortByXorY implements Comparator<Point> {

        @Override
        public int compare(Point o1, Point o2) {
            if (o1.getX().compareTo(o2.getX()) == 0) {
                return o1.getY().compareTo(o2.getY());
            }
            return o1.getX().compareTo(o2.getX());
        }
    }
}
