package Backend;

public class GameTile {
    private final Unit unit;
    private Position2D position;

    public GameTile(Unit unit, Position2D position) {
        this.unit = unit;
        this.position = position;
    }

    public Unit getUnit() {
        return unit;
    }

    public Position2D getPosition() {
        return position;
    }

    public void setPosition(Position2D pos) {
        position = pos;
    }

    public String toString(){
        return String.valueOf(unit.getTile());
    }
}
