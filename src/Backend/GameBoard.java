package Backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GameBoard {
    private GameTile[][] currentBoard;
    int currentLevel;

    GameBoard() {
        currentLevel = 1;
        parseLevel(1);

    }

    /*
     * GameBoard[x][y] = null => Empty Space
     * GameBoard[x][y].tile = null => Wall
     */
    public double getRange(Position2D pos1, Position2D pos2) {
        assert pos1 != null && pos2 != null : "Cannot calculate range with a null: " + pos1 + ", " + pos2;
        return Math.sqrt(Math.pow(pos1.x - pos2.x, 2) + Math.pow(pos1.y - pos2.y, 2));
    }

    public void parseLevel(int level) {
        String[] levelString = readMap(level).split("\n");
        int numRows = levelString.length;
        int numCols = levelString[0].length();
        currentBoard = new GameTile[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                char tileChar = levelString[i].charAt(j);
                currentBoard[i][j] = createGameTile(tileChar, i, j);
            }
        }
    }

    private GameTile createGameTile(char tileChar, int i, int j) {
        switch (tileChar) {
            case '.':
                // TODO
            case '#':
                // TODO
            case '@':
                // TODO
            case 'X':
                // TODO
            default:
                throw new UnsupportedOperationException("Unimplemented method 'createGameTile'");
        }
    }

    public void parseNextLevel() {
        parseLevel(++currentLevel);
    }

    public String readMap(int level) {
        StringBuilder text = new StringBuilder();
        String path = System.getProperty("user.dir") + "\\levels_dir\\level" + level + ".txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line).append("\n");
            }
        } catch (IOException e) {
            text.append("An error occurred while uploading the text: " + e.getMessage());
        }
        return text.toString();
    }

}
