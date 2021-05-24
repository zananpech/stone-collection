package stone_collection_boardgame.model;

import org.tinylog.Logger;

import java.util.Collections;
import java.util.List;

/**
 * Class representing the model of the game.
 */
public class StoneCollectionGameModel {

    /**
     * The size of the game board in 4x4
     */
    public static int BOARD_SIZE = 4;

    private Boolean[][] board = new Boolean[BOARD_SIZE][BOARD_SIZE];

    /**
     * when the game starts, the default player's turn is set to player one
     */
    private Player player = Player.ONE;

    /**
     * steps of player1 and player2 are set to 0
     */
    private int stepsByPlayer1 = 0;
    private int stepsByPlayer2 = 0;

    private String winnerName;


    public StoneCollectionGameModel() {

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = false;
            }
        }
    }

    public Boolean getSquareProperty(int row, int col){
        return board[row][col];
    }

    public void setSquareProperty(int row, int col){
        board[row][col] = Boolean.TRUE;
    }


    /**
     * switch turn between player1 and player2
     */
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

    /**
     * Returns the change in the player turn
     *
     * @return the change in the player turn
     */
    public Player getPlayerTurn(){
        return this.player;
    }

    /**
     * Returns the steps performed by player1
     *
     * @return the steps performed by player1
     */
    public int getStepsByPlayer1(){return this.stepsByPlayer1;}

    /**
     * Returns the steps performed by player2
     *
     * @return the steps performed by player2
     */
    public int getStepsByPlayer2(){return this.stepsByPlayer2;}


    /**
     * Returns true if the chosen stones are adjacent to one another
     * and not from different rows or columns
     *
     * @param moves a list of points clicked by the player
     * @return  true if the chosen stones are adjacent to one another
     * and not from different rows or columns
     */
    public static boolean isValidMove(List<Point> moves) {
        Collections.sort(moves, new Point.sortByXorY());
        for (Point p: moves){
            Logger.debug("({},{})", p.getX(), p.getY());
        }
        int firstRow = moves.get(0).getX();
        int firstColumn = moves.get(0).getY();
        boolean allSameRowsOrColumns = moves.stream().allMatch(
                point ->
                    point.getX().equals(firstRow) || point.getY().equals(firstColumn)
        );
        if (allSameRowsOrColumns) {
            for (int i = 0; i < moves.size() - 1; i++) {
                int rowDiff = Math.abs((Integer) moves.get(i + 1).getX() - (Integer) moves.get(i).getX());
                int colDiff = Math.abs((Integer) moves.get(i + 1).getY() - (Integer) moves.get(i).getY());
                if ((rowDiff + colDiff) > 1) {
                    return false;
                }
            }
        }
        else{return false;}
        return true;
    }


    public void setWinnerName(String name){
        this.winnerName = name;
    }

    /**
     * {@return the name of the winner
     */
    public String getWinnerName(){
        return this.winnerName;
    }


    /**
     * visualize the board on the console
     */
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
