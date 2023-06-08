package Backend;


public class GameBoard {
    private char[][] currentBoard;
    private char[][] nextBoard;
    /*
    GameBoard[x][y] = null => Empty Space
    GameBoard[x][y].tile = null => Wall
    */
    public double getRange(Position2D pos1, Position2D pos2){
        assert pos1 != null && pos2 != null : "Cannot calculate range with a null: " + pos1 + ", " + pos2;
        return Math.sqrt(Math.pow(pos1.x - pos2.x, 2) + Math.pow(pos1.y - pos2.y, 2));
    }
}
