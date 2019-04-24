package byow.Core;

import byow.TileEngine.Tileset;

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
    private String direction;

    public IndoorArea(Point p, int length, int width, TileWorld tw) {
        direction = p.getDirec();
        startPoint = p;
        xStart = startPoint.getX();
        yStart = startPoint.getY();
        xlength = length;
        ywidth = width;
        tileWorld = tw;
    }

    public String getDirection() {
        return direction;
    }

    public int[] getParams() {
        int[] temp = {xStart, yStart, xlength, ywidth};
        return temp;
    }

    private void breakWall() {
        int i = RandomUtils.uniform(tileWorld.getRandom(), 3);
        Point removedIndex;
        switch(i)
        {
            case 0:
                removedIndex = new DirectedPoint(top.removeWallRandom(), "top");
                break;
            case 1:
                removedIndex = new DirectedPoint(bottom.removeWallRandom(), "bottom");
                break;
            case 2:
                removedIndex = new DirectedPoint(left.removeWallRandom(), "left");
                break;
            case 3:
                removedIndex = new DirectedPoint(right.removeWallRandom(), "right");
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

    public void buildRight() {
        for (int i = 0; i < xlength; i++) {
            for(int j = 0; j < ywidth; j++) {
                tileWorld.add(new Point(xStart + i, yStart + j), Tileset.FLOOR);
            }
        }
        top = new Wall(false, xlength + 2, new Point(xStart - 1, yStart + ywidth), tileWorld);
        bottom = new Wall(false, xlength + 2, new Point(xStart - 1, yStart - 1), tileWorld);
        left = new Wall(true, ywidth, new Point(xStart - 1, yStart - 1 + ywidth), tileWorld);
        right = new Wall(true, ywidth, new Point(xStart + xlength, yStart - 1 + ywidth), tileWorld);
    }

    public void buildLeft() {
        for (int i = 0; i < xlength; i++) {
            for(int j = 0; j < ywidth; j++) {
                tileWorld.add(new Point(xStart - i, yStart + j), Tileset.FLOOR);
            }
        }
        top = new Wall(false, xlength + 2, new Point(xStart - xlength, yStart + ywidth), tileWorld);
        bottom = new Wall(false, xlength + 2, new Point(xStart - xlength, yStart - 1), tileWorld);
        left = new Wall(true, ywidth, new Point(xStart - xlength, yStart - 1 + ywidth), tileWorld);
        right = new Wall(true, ywidth, new Point(xStart + 1, yStart - 1 + ywidth), tileWorld);
    }

    public void buildUp() {
        for (int i = 0; i < xlength; i++) {
            for(int j = 0; j < ywidth; j++) {
                tileWorld.add(new Point(xStart + i, yStart + j), Tileset.FLOOR);
            }
        }
        top = new Wall(false, xlength + 2, new Point(xStart - 1, yStart + ywidth), tileWorld);
        bottom = new Wall(false, xlength + 2, new Point(xStart - 1, yStart - 1), tileWorld);
        left = new Wall(true, ywidth, new Point(xStart - 1, yStart - 1 + ywidth), tileWorld);
        right = new Wall(true, ywidth, new Point(xStart + xlength, yStart - 1 + ywidth), tileWorld);
    }

    public void buildDown() {
        for (int i = 0; i < xlength; i++) {
            for(int j = 0; j < ywidth; j++) {
                tileWorld.add(new Point(xStart + i, yStart - j), Tileset.FLOOR);
            }
        }
        top = new Wall(false, xlength + 2, new Point(xStart - 1, yStart + 1), tileWorld);
        bottom = new Wall(false, xlength + 2, new Point(xStart - 1, yStart - ywidth), tileWorld);
        left = new Wall(true, ywidth, new Point(xStart - 1, yStart - 1 + ywidth), tileWorld);
        right = new Wall(true, ywidth, new Point(xStart + xlength, yStart - 1 + ywidth), tileWorld);
    }

    public void build(String d) {
        if (d == null) {
            d = "top";
        }
        switch(d)
        {
            case "top":
                buildUp();
                break;
            case "bottom":
                buildDown();
                break;
            case "left":
                buildLeft();
                break;
            case "right":
                buildRight();
                break;
        }
    }
}
