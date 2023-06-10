package Backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GameBoard {

    public enum Direction {
        UP(0, -1),
        DOWN(0, 1),
        LEFT(-1, 0),
        RIGHT(1, 0);

        private final int deltaX;
        private final int deltaY;

        Direction(int horozonial, int vertial) {
            this.deltaX = horozonial;
            this.deltaY = vertial;
        }

        public int getDeltaX() {
            return deltaX;
        }

        public int getDeltaY() {
            return deltaY;
        }

        public Position2D getDeltaPosition() {
            return new Position2D(deltaX, deltaY);
        }
    }

    private GameTile[][] currentBoard;
    private Player player;
    private CharacterFactory cf;
    private GameTile playerTile;

    private final char EMPTY = '.';
    private final char WALL = '#';
    private final char PLAYER = '@';

    GameBoard(CharacterFactory _cf, Player _player) {
        cf = _cf;
        player = _player;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (GameTile[] row : currentBoard) {
            for (GameTile tile : row) {
                s.append(tile == null ? "." : tile.getUnit() == null ? '#' : tile.getUnit().getTile());
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
        int rows = levelString.length;
        int cols = levelString[0].length();
        currentBoard = new GameTile[rows][cols];
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                char tileChar = levelString[y].charAt(x);
                Position2D pos = new Position2D(x, y);
                setTileAt(createGameTile(tileChar, pos), pos);
            }
        }
    }

    public void tick() {
        for (GameTile[] row : currentBoard) {
            for (GameTile tile : row) {
                if (tile != null && tile.getUnit() != null){
                    tile.getUnit().tick();
                }
            }
        }
    }

    private GameTile createGameTile(char tileChar, Position2D pos) {
        switch (tileChar) {
            case EMPTY:
                return null;
            case WALL:
                return new GameTile(null, pos);
            case PLAYER:
                playerTile = new GameTile(player, pos);
                return playerTile;
            default:
                return new GameTile(cf.createEnemy(tileChar), pos);
        }
    }

    private String readMap(int level) {
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

    // Player interaction
    public boolean interact(Direction direction) {
        GameTile player = getTileAt(playerTile.getPosition());
        Position2D newPos = Position2D.add(playerTile.getPosition(), direction.getDeltaPosition());
        if(getTileAt(newPos) == null) {
            move(player, newPos);
            return true;
        } else {
            return interact(player, getTileAt(newPos));
        }
    }

    // Tiles interactions
    public boolean interact(GameTile performer, GameTile target) {
        if(target.getUnit() == null)
            return false;
        return true;
    }

    private void move(GameTile tile, Position2D pos){
        Position2D oldPos = tile.getPosition();
        setTileAt(tile, pos);
        setTileAt(null, oldPos);
        tile.setPosition(pos);
    }

    ArrayList<Unit> getAllUnitsInRange(int range) {
        ArrayList<Unit> unitsInRange = new ArrayList<Unit>();
        for (int i = 0; i < currentBoard.length; i++) {
            for (GameTile tile : currentBoard[i]) {
                if (tile != null && tile.getUnit() != null && getRange(tile.getPosition(), playerTile.getPosition()) <= range)
                    unitsInRange.add(tile.getUnit());
            }
        }
        return unitsInRange;
    }

    public void castSpecialAbility() {
        ArrayList<Unit> unitsInRange = getAllUnitsInRange(player.getAbilityRange());
        player.castAbility(unitsInRange);
    }

    private GameTile getTileAt(Position2D pos){
        return currentBoard[pos.y][pos.x];
    }

    private void setTileAt(GameTile tile, Position2D pos){
        currentBoard[pos.y][pos.x] = tile;
    }
}
