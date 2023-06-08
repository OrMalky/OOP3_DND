package Backend;

public class Position {
    public int x;
    public int y;

    public Position(int _x, int _y){
        x = _x;
        y = _y;
    }

    public boolean equals(Position other) {
        return other != null && x == other.x && y == other.y;
    }

}
