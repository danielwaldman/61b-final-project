package byow.Core;

public class DirectedPoint extends Point {
    private String direction;

    public DirectedPoint(int startx, int starty, String d) {
        super(startx, starty);
        direction = d;
    }

    public DirectedPoint(Point p, String d) {
        super(p.getX(), p.getY());
        direction = d;
    }

    @Override
    public String getDirec() {
        return direction;
    }
}
