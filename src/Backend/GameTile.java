package Backend;

public class GameTile {
    private TileType type;
    private Unit unit;
    private Position2D position;

    public enum TileType {
        BORDER,
        EMPTY,
        UNIT
    }
    public GameTile(TileType type, Position2D position) {
        this.type = type;
        this.position = position;
    }

    public GameTile(Unit unit, Position2D position) {
        this.type = TileType.UNIT;
        this.unit = unit;
        this.position = position;
    }

    public TileType getType() {
        return type;
    }

    public Unit getUnit() {
        return unit;
    }

    public Position2D getPosition() {
        return position;
    }


}
