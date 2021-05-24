package stone_collection_boardgame.model;

import java.util.Collections;
import java.util.List;

public class StoneCollectionGameModel {

    public static int BOARD_SIZE = 4;
    public static int id = 0;

    private Boolean[][] board = new Boolean[BOARD_SIZE][BOARD_SIZE];
    private Player player = Player.ONE;


    private int stepsByPlayer1 = 0;
    private int stepsByPlayer2 = 0;

    private String winnerName;


    public StoneCollectionGameModel() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = false;
            }
        }
        id++;
    }

    public Boolean getSquareProperty(int row, int col){
        return board[row][col];
    }

    public void setSquareProperty(int row, int col){
        board[row][col] = Boolean.TRUE;
    }


    public void switchPlayerTurn() {
        switch (this.player) {
            case ONE -> {
                player = Player.TWO;
                stepsByPlayer1++;
            }
            case TWO -> {
                player = Player.ONE;
                stepsByPlayer2++;
            }
        }
    }

    public Player getPlayerTurn(){
        return this.player;
    }

    public int getStepsByPlayer1(){return this.stepsByPlayer1;}
    public int getStepsByPlayer2(){return this.stepsByPlayer2;}

    public static boolean isValidMove(List<Point> moves) {
        Collections.sort(moves, new Point.sortByXorY());
        for (int i = 0; i < moves.size() - 1; i++) {
            int rowDiff = Math.abs((Integer) moves.get(i+1).getX() - (Integer) moves.get(i).getX());
            int colDiff = Math.abs((Integer) moves.get(i + 1).getY() - (Integer) moves.get(i).getY());

            if ((rowDiff + colDiff) > 1) {
                return false;
            }
        }
        return true;
    }

    public void setWinnerName(String name){
        this.winnerName = name;
    }

    public String getWinnerName(){
        return this.winnerName;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                sb.append(board[i][j].toString()+ " ");
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        var model = new StoneCollectionGameModel();
        System.out.println(model);
    }
}
