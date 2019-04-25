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
        switch (i) {
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

    public void buildRightUp(int shift) {
        yStart = yStart - shift;
        for (int i = 0; i < xlength; i++) {
            for (int j = 0; j < ywidth; j++) {
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

    public void buildRightDown(int shift) {
        yStart = yStart - shift;
        for (int i = 0; i < xlength; i++) {
            for (int j = 0; j < ywidth; j++) {
                if (!tileWorld.isTaken(xStart + i, yStart - j)) {
                    tileWorld.add(new Point(xStart + i, yStart - j), Tileset.FLOOR);
                }
            }
        }
        top = new Wall(false, xlength + 2, new Point(xStart - 1, yStart + 1), tileWorld);
        bottom = new Wall(false, xlength + 2, new Point(xStart - 1, yStart - ywidth), tileWorld);
        left = new Wall(true, ywidth, new Point(xStart - 1, yStart - 1), tileWorld);
        right = new Wall(true, ywidth, new Point(xStart + xlength, yStart - 1 + ywidth), tileWorld);
    }

    public void buildLeftUp(int shift) {
        yStart = yStart - shift;
        for (int i = 0; i < xlength; i++) {
            for (int j = 0; j < ywidth; j++) {
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

    public void buildLeftDown(int shift) {
        yStart = yStart - shift;
        for (int i = 0; i < xlength; i++) {
            for (int j = 0; j < ywidth; j++) {
                if (!tileWorld.isTaken(xStart - i, yStart - j)) {
                    tileWorld.add(new Point(xStart - i, yStart - j), Tileset.FLOOR);
                }
            }
        }
        top = new Wall(false, xlength + 2, new Point(xStart - xlength, yStart + 1), tileWorld);
        bottom = new Wall(false, xlength + 2, new Point(xStart - xlength, yStart - ywidth),
                tileWorld);
        left = new Wall(true, ywidth, new Point(xStart - xlength, yStart), tileWorld);
        right = new Wall(true, ywidth, new Point(xStart + 1, yStart), tileWorld);
    }

    public void buildUpRight(int shift) {
        xStart = xStart + shift;
        for (int i = 0; i < xlength; i++) {
            for (int j = 0; j < ywidth; j++) {
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

    public void buildUpLeft(int shift) {
        xStart = xStart - shift;
        for (int i = 0; i < xlength; i++) {
            for (int j = 0; j < ywidth; j++) {
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

    public void buildDownRight(int shift) {
        xStart = xStart + shift;
        for (int i = 0; i < xlength; i++) {
            for (int j = 0; j < ywidth; j++) {
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


    public void buildDownLeft(int shift) {
        xStart = xStart - shift;
        for (int i = 0; i < xlength; i++) {
            for (int j = 0; j < ywidth; j++) {
                if (!tileWorld.isTaken(xStart - i, yStart - j)) {
                    tileWorld.add(new Point(xStart - i, yStart - j), Tileset.FLOOR);
                }
            }
        }
        top = new Wall(false, xlength + 2, new Point(xStart - xlength, yStart + 1), tileWorld);
        bottom = new Wall(false, xlength + 2, new Point(xStart - xlength, yStart - ywidth),
                tileWorld);
        left = new Wall(true, ywidth, new Point(xStart - xlength, yStart), tileWorld);
        right = new Wall(true, ywidth, new Point(xStart + 1, yStart), tileWorld);
    }

    void methodTop(int drr, Point dp, int buildvert) {
        if (drr == 1) {
            Point topRight = new Point(dp.getX() + xlength, dp.getY() + ywidth);
            Point bottomLeft = new Point(dp.getX() - 1, dp.getY() - 1);
            if (!tileWorld.canNotPlace(topRight, bottomLeft)) {
                buildUpRight(buildvert);
            }
            buildUpLeft(buildvert);
        } else {
            Point topRight = new Point(dp.getX() + 1, dp.getY() + ywidth);
            Point bottomLeft = new Point(dp.getX() - xlength, dp.getY() - 1);
            if (!tileWorld.canNotPlace(topRight, bottomLeft)) {
                buildUpLeft(buildvert);
            }
            buildUpRight(buildvert);
        }
    }

    void methodBottom(int drr, Point dp, int buildvert) {
        if (drr == 1) {
            Point topRight = new Point(dp.getX() + xlength, dp.getY() + 1);
            Point bottomLeft = new Point(dp.getX() - 1, dp.getY() - ywidth);
            if (!tileWorld.canNotPlace(topRight, bottomLeft)) {
                buildDownRight(buildvert);
            }
            buildDownLeft(buildvert);
        } else {
            Point topRight = new Point(dp.getX() + 1, dp.getY() + 1);
            Point bottomLeft = new Point(dp.getX() - xlength, dp.getY() - ywidth);
            if (!tileWorld.canNotPlace(topRight, bottomLeft)) {
                buildDownLeft(buildvert);
            }
            buildDownRight(buildvert);
        }
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
        int drr = RandomUtils.uniform(tileWorld.getRandom(), 1);
        switch (d) {
            case "top":
                methodTop(drr, dp, buildvert);
                break;
            case "bottom":
                methodBottom(drr, dp, buildvert);
                break;
            case "left":
                if (drr == 1) {
                    Point topRight = new Point(dp.getX() + 1, dp.getY() + ywidth);
                    Point bottomLeft = new Point(dp.getX() - xlength, dp.getY() - 1);
                    if (!tileWorld.canNotPlace(topRight, bottomLeft)) {
                        buildLeftUp(buildhoriz);
                        break;
                    }
                    buildLeftDown(buildhoriz);
                    break;
                } else {
                    Point topRight = new Point(dp.getX() + 1, dp.getY() + 1);
                    Point bottomLeft = new Point(dp.getX() - xlength, dp.getY() - ywidth);
                    if (!tileWorld.canNotPlace(topRight, bottomLeft)) {
                        buildLeftDown(buildhoriz);
                        break;
                    }
                    buildLeftUp(buildhoriz);
                    break;
                }
            case "right":
                if (drr == 1) {
                    Point topRight = new Point(dp.getX() + xlength, dp.getY() + ywidth);
                    Point bottomLeft = new Point(dp.getX() - 1, dp.getY() - 1);
                    if (!tileWorld.canNotPlace(topRight, bottomLeft)) {
                        buildRightUp(buildhoriz);
                        break;
                    }
                    buildRightDown(buildhoriz);
                    break;
                } else {
                    Point topRight = new Point(dp.getX() + xlength, dp.getY() + 1);
                    Point bottomLeft = new Point(dp.getX() - 1, dp.getY() - ywidth);
                    if (!tileWorld.canNotPlace(topRight, bottomLeft)) {
                        buildRightDown(buildhoriz);
                        break;
                    }
                    buildRightUp(buildhoriz);
                    break;
                }
            default:
                break;
        }
    }
}
