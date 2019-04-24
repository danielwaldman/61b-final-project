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

    public void buildRight(int shift) {
        yStart = yStart - shift;
        for (int i = 0; i < xlength; i++) {
            for(int j = 0; j < ywidth; j++) {
                if (!tileWorld.isTaken(xStart + i, yStart + j)) {
                    tileWorld.add(new Point(xStart + i, yStart + j), Tileset.FLOOR);
                }
            }
        }
        top = new Wall(false, xlength + 2, new Point(xStart - 1, yStart + ywidth), tileWorld);
        bottom = new Wall(false, xlength + 2, new Point(xStart - 1, yStart - 1), tileWorld);
        left = new Wall(true, ywidth, new Point(xStart - 1, yStart - 1 + ywidth), tileWorld);
        right = new Wall(true, ywidth, new Point(xStart + xlength, yStart - 1 + ywidth), tileWorld);
    }

    public void buildLeft(int shift) {
        yStart = yStart - shift;
        for (int i = 0; i < xlength; i++) {
            for(int j = 0; j < ywidth; j++) {
                if (!tileWorld.isTaken(xStart - i, yStart + j)) {
                    tileWorld.add(new Point(xStart - i, yStart + j), Tileset.FLOOR);
                }
            }
        }
        top = new Wall(false, xlength + 2, new Point(xStart - xlength, yStart + ywidth), tileWorld);
        bottom = new Wall(false, xlength + 2, new Point(xStart - xlength, yStart - 1), tileWorld);
        left = new Wall(true, ywidth, new Point(xStart - xlength, yStart - 1 + ywidth), tileWorld);
        right = new Wall(true, ywidth, new Point(xStart + 1, yStart - 1 + ywidth), tileWorld);
    }

    public void buildUp(int shift) {
        xStart = xStart + shift;
        for (int i = 0; i < xlength; i++) {
            for(int j = 0; j < ywidth; j++) {
                if (!tileWorld.isTaken(xStart + i, yStart + j)) {
                    tileWorld.add(new Point(xStart + i, yStart + j), Tileset.FLOOR);
                }
            }
        }
        top = new Wall(false, xlength + 2, new Point(xStart - 1, yStart + ywidth), tileWorld);
        bottom = new Wall(false, xlength + 2, new Point(xStart - 1, yStart - 1), tileWorld);
        left = new Wall(true, ywidth, new Point(xStart - 1, yStart - 1 + ywidth), tileWorld);
        right = new Wall(true, ywidth, new Point(xStart + xlength, yStart - 1 + ywidth), tileWorld);
    }

    public void buildDown(int shift) {
        xStart = xStart + shift;
        for (int i = 0; i < xlength; i++) {
            for(int j = 0; j < ywidth; j++) {
                if (!tileWorld.isTaken(xStart + i, yStart - j)) {
                    tileWorld.add(new Point(xStart + i, yStart - j), Tileset.FLOOR);
                }
            }
        }
        top = new Wall(false, xlength + 2, new Point(xStart - 1, yStart + 1), tileWorld);
        bottom = new Wall(false, xlength + 2, new Point(xStart - 1, yStart - ywidth), tileWorld);
        left = new Wall(true, ywidth, new Point(xStart - 1, yStart), tileWorld);
        right = new Wall(true, ywidth, new Point(xStart + xlength, yStart), tileWorld);
    }

    public void build(Point dp) {
        String d = dp.getDirec();
        if (d == null) {
            d = "top";
        }
        int buildvert, buildhoriz;
        if (xlength > 1 && ywidth > 1) {
            buildvert = RandomUtils.uniform(tileWorld.getRandom(), xlength - 1);
            buildhoriz = RandomUtils.uniform(tileWorld.getRandom(), ywidth - 1);
        } else {
            buildvert = 0;
            buildhoriz = 0;
        }
        switch(d)
        {
            case "top":
                Point topRight = new Point(dp.getX() + xlength, dp.getY() + ywidth);
                Point bottomLeft = new Point(dp.getX() - 1, dp.getY() - 1);
                /*if (tileWorld.canNotPlace(topRight, bottomLeft)) {
                    build(new DirectedPoint(dp.getX(), dp.getY(), "bottom"));
                } else {*/
                    buildUp(buildvert);
                    break;
                //}
            case "bottom":
                /*Point topRight = new Point(dp.getX() + xlength, dp.getY() + 1);
                Point bottomLeft = new Point(dp.getX() - 1, dp.getY() - ywidth);*/
                buildDown(buildvert);
                break;
            case "left":
                buildLeft(buildhoriz);
                break;
            case "right":
                buildRight(buildhoriz);
                break;
        }
    }
}
