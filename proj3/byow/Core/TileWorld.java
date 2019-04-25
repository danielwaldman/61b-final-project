package byow.Core;

import java.util.ArrayList;
import byow.TileEngine.TETile;
import java.util.Random;
import byow.TileEngine.TERenderer;
import byow.TileEngine.Tileset;

public class TileWorld {
    private ArrayList<Room> rooms; //might not be necessary
    private ArrayList<Hallway> halls;
    private int width;
    private int height;
    private TETile[][] world;
    private Random random;
    private TERenderer teRenderer;

    public TileWorld(long seed) {
        random = new Random(seed);
        width = RandomUtils.uniform(random, 100, 150);
        height = RandomUtils.uniform(random, 80, 120);
        world = new TETile[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }
        rooms = new ArrayList<>();
        halls = new ArrayList<>();
        Point start = new Point(RandomUtils.uniform(random, height),
                RandomUtils.uniform(random, width));
        createAreas(10, start);
    }

    public TileWorld(long seed, TERenderer te) {
        random = new Random(seed);
        width = RandomUtils.uniform(random, 80, 100);
        height = RandomUtils.uniform(random, 80, 100);
        world = new TETile[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }
        teRenderer = te;
        rooms = new ArrayList<>();
        halls = new ArrayList<>();
        Point start = new Point(RandomUtils.uniform(random, height),
                RandomUtils.uniform(random, width));
        createAreas(10, start);
    }

    public void renderWorld() {
        teRenderer.initialize(width, height);
        teRenderer.renderFrame(getTiles());
    }

    public Random getRandom() {
        return random;
    }

    public void add(Point p, TETile tile) {
        if (!isOutofIndex(p.getX(), p.getY())) {
            world[p.getX()][p.getY()] = tile;
        }
    }

    public void createAreas(int numAreas, Point beginning) {
        //this is where we make a new indoor area
        //this is where we add that indoor area to indoorAreas
        Point nextStartingPoint = beginning;
        for (int i = 0; i < numAreas; i++) {
            if (i % 2 == 0) {
                Room temp = new Room(nextStartingPoint, RandomUtils.poisson(random, 5),
                        RandomUtils.poisson(random, 5), this);
                //System.out.print(temp.getDirection());
                //System.out.println(Arrays.toString(temp.getParams()));
                rooms.add(temp);
                int ran = RandomUtils.uniform(random, rooms.size());
                nextStartingPoint = adjustStart(rooms.get(ran).getExitPoint());
            } else {
                Hallway temp = null;
                if (i % 4 == 1) {
                    temp = new Hallway(nextStartingPoint, 1, RandomUtils.poisson(random, 5), this);
                } else {
                    temp = new Hallway(nextStartingPoint, RandomUtils.poisson(random, 5), 1, this);
                }
                //System.out.print(temp.getDirection());
                //System.out.println(Arrays.toString(temp.getParams()));
                halls.add(temp);
                int ran = RandomUtils.uniform(random, halls.size());
                nextStartingPoint = adjustStart(halls.get(ran).getExitPoint());
            }
        }
    }

    public DirectedPoint adjustStart(Point dp) {
        switch (dp.getDirec()) {
            case "top":
                return new DirectedPoint(dp.getX(), dp.getY() + 1, dp.getDirec());
            case "bottom":
                return new DirectedPoint(dp.getX(), dp.getY() - 1, dp.getDirec());
            case "left":
                return new DirectedPoint(dp.getX() - 1, dp.getY(), dp.getDirec());
            case "right":
                return new DirectedPoint(dp.getX() + 1, dp.getY(), dp.getDirec());
            default:
                break;
        }
        return new DirectedPoint(dp.getX(), dp.getY(), "top");
    }

    public boolean isTaken(int x, int y) {
        if (isOutofIndex(x, y)) {
            return true;
        }
        if (!(world[x][y].equals(Tileset.NOTHING))) {
            return true;
        }
        return false;
    }

    public boolean isOutofIndex(int x, int y) {
        if (x >= height || x < 0 || y >= width || y < 0) {
            return true;
        }
        return false;
    }

    public boolean canNotPlace(Point topRight, Point bottomLeft) {
        for (int i = bottomLeft.getX(); i < topRight.getX(); i++) {
            for (int j = bottomLeft.getY(); j < topRight.getY(); j++) {
                if (isOutofIndex(i, j) || isTaken(i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public TETile get(Point p) {
        return world[p.getX()][p.getY()];
    }

    public TETile[][] getTiles() {
        return world;
    }
    /*public static void main(String[] args) {
        TileWorld tw = new TileWorld();
        tw.createAreas(3, new Point(5, 5));
        for (int i = 0; i < 50; i++) {
            for (int g = 0; g < 50; g++) {
                System.out.print(tw.get(new Point(i, g)));
            }
            System.out.println();
        }
    }*/
}
