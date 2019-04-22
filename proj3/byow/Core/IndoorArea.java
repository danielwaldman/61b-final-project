package byow.Core;

public class IndoorArea {
    private int xStart;
    private int yStart;
    private int xlength;
    private int ywidth;
    private Wall top;
    private Wall bottom;
    private Wall left;
    private Wall right;
    private TileWorld tileWorld;
    private Point startPoint;
    private Point exitPoint;

    public IndoorArea(Point p, int length, int width, TileWorld tw) {
        startPoint = p;
        xStart = startPoint.getX();
        yStart = startPoint.getY();
        xlength = length;
        ywidth = width;
        tileWorld = tw;
        top = new Wall(false, xlength + 2, new Point(xStart - 1, yStart + ywidth), tileWorld);
        bottom = new Wall(false, xlength + 2, new Point(xStart - 1, yStart - 1), tileWorld);
        left = new Wall(true, ywidth, new Point(xStart - 1, yStart - 1 + ywidth), tileWorld);
        right = new Wall(true, ywidth, new Point(xStart + xlength, yStart - 1 + ywidth), tileWorld);
    }

    private void breakWall() {
        int i = RandomUtils.uniform(tileWorld.getRandom(), 3);
        Point removedIndex;
        switch(i)
        {
            case 0:
                removedIndex = top.removeWallRandom();
                break;
            case 1:
                removedIndex = bottom.removeWallRandom();
                break;
            case 2:
                removedIndex = left.removeWallRandom();
                break;
            case 3:
                removedIndex = right.removeWallRandom();
                break;
            default:
                removedIndex = null;
        }
        exitPoint = removedIndex;
    }

    public Point getExitPoint() {
        breakWall();
        return exitPoint;
    }
}
