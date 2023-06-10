package Backend;

public class Position2D {
    public int x;
    public int y;

    public Position2D(int _x, int _y){
        x = _x;
        y = _y;
    }

    public static Position2D add(Position2D pos1, Position2D pos2) {
        return new Position2D(pos1.x + pos2.x, pos1.y + pos2.y);
    }
}