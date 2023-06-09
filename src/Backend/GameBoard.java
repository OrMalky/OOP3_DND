package Backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Backend.GameTile.TileType;

public class GameBoard {

    public enum Direction {
        UP(0, -1),
        DOWN(0, 1),
        LEFT(-1, 0),
        RIGHT(1, 0);

        private final int deltaX;
        private final int deltaY;

        Direction(int horozonial, int vertial) {
            this.deltaX = vertial;
            this.deltaY = horozonial;
        }

        public int getDeltaX() {
            return deltaX;
        }

        public int getDeltaY() {
            return deltaY;
        }
    }

    private GameTile[][] currentBoard;
    int currentLevel;
    CharacterFactory cf;
    Position2D playerPosition;
    Player player;

    GameBoard(Player _player, CharacterFactory _cf, MassageCallBack _massageCallBack) {
        currentLevel = 1;
        cf = _cf;
        player = _player;
    }

    public void initializeBoard() {
        currentLevel = 1;
        parseLevel(1);
    }

    public String getBoardAsString() {
        StringBuilder s = new StringBuilder();
        for (GameTile[] row : currentBoard) {
            for (GameTile tile : row) {
                s.append(tile.getType() == TileType.UNIT ? cf.getTileChar(tile.getUnit().getName())
                        : tile.getType() == TileType.BORDER ? '#' : '.');
            }
            s.append("\n");
        }
        return s.toString();
    }

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

    public void tick() {
        for (GameTile[] row : currentBoard) {
            for (GameTile tile : row) {
                if (tile.getType() == TileType.UNIT) {
                    tile.getUnit().tick();
                }
            }
        }
    }

    private GameTile createGameTile(char tileChar, int i, int j) {
        switch (tileChar) {
            case '.':
                return new GameTile(TileType.EMPTY, new Position2D(i, j));
            case '#':
                return new GameTile(TileType.BORDER, new Position2D(i, j));
            case '@':
                playerPosition = new Position2D(i, j);
                return new GameTile(player, new Position2D(i, j));
            default:
                return new GameTile(cf.createEnemy(tileChar), new Position2D(i, j));
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

    // Swtich places of two tiles on the board
    public void switchPlaces(Position2D pos1, Position2D pos2) {
        GameTile temp = currentBoard[pos1.x][pos1.y];
        currentBoard[pos1.x][pos1.y] = currentBoard[pos2.x][pos2.y];
        currentBoard[pos2.x][pos2.y] = temp;
    }

    public void movePlayer(Direction direction) {
        int newX = playerPosition.x + direction.getDeltaX();
        int newY = playerPosition.y + direction.getDeltaY();
        if (isValidMove(newX, newY)) {
            switchPlaces(playerPosition, new Position2D(newX, newY));
            playerPosition.x = newX;
            playerPosition.y = newY;

        }

    }

    ArrayList<Unit> getAllUnitsInRange(int range) {
        ArrayList<Unit> unitsInRange = new ArrayList<Unit>();
        for (int i = 0; i < currentBoard.length; i++) {
            for (GameTile tile : currentBoard[i]) {
                if (tile.getType() == TileType.UNIT && getRange(tile.getPosition(), playerPosition) <= range)
                    unitsInRange.add(tile.getUnit());
            }
        }
        return unitsInRange;
    }

    public void castSpecialAbility() {
        ArrayList<Unit> unitsInRange = getAllUnitsInRange(player.getAbilityRange());
        player.castAbility(unitsInRange);
    }

    private boolean isValidMove(int x, int y) {
        if (x < 0 || x >= currentBoard.length || y < 0 || y >= currentBoard[0].length) {
            // Out of bounds
            return false;
        }

        return currentBoard[x][y].getType() == TileType.EMPTY;
    }

    public String getPlayerInfo() {
        return player.toString();
    }

}
