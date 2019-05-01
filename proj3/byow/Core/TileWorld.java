package byow.Core;

import java.util.ArrayList;
import byow.TileEngine.TETile;
import java.util.Random;
//import byow.TileEngine.TERenderer;
import byow.TileEngine.Tileset;

public class TileWorld {
    private ArrayList<Room> rooms; //might not be necessary
    private ArrayList<Hallway> halls;
    private int width;
    private int height;
    private TETile[][] world;
    private Random random;
    //private TERenderer teRenderer;
    private Avatar person;
    private boolean notfirst;

    public class Avatar {
        Point location;
        TETile appearance;
        TETile prevTile;

        public Avatar(Point p) {
            prevTile = get(p);
            appearance = Tileset.MOUNTAIN;
            location = p;
            remove(location);
            add(location, appearance);
        }

        public void move(Point z) {
            if (isOutofIndex(z.getX(), z.getY()) || get(z).equals(Tileset.WALL) || get(z).equals(Tileset.NOTHING)) {
                return;
            } else {
                remove(location);
                add(location, prevTile);
                person = new Avatar(z);
            }
        }

        public Point getPoint() {
            return location;
        }

        public String getStringPoint() {
            return "" + location.getX() + " " + location.getY();
        }

    }
    public TileWorld(long seed) {
        random = new Random(seed);
        width = RandomUtils.uniform(random, 60, 80);
        height = RandomUtils.uniform(random, 100, 120);
        world = new TETile[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }
        notfirst = false;
        rooms = new ArrayList<>();
        halls = new ArrayList<>();
        Point start = new Point(RandomUtils.uniform(random, height),
                RandomUtils.uniform(random, width));
        createAreas(RandomUtils.uniform(random, 12, 24), start);
    }


    /*public TileWorld(TETile[][] temp, long seed, TERenderer te) {
        random = new Random(seed);
        world = temp;
        width = temp[0].length;
        height = temp.length;
        teRenderer = te;
        notfirst = false;
        rooms = new ArrayList<>();
        halls = new ArrayList<>();
    }


    public TileWorld(long seed, TERenderer te) {
        random = new Random(seed);
        width = RandomUtils.uniform(random, 60, 80);
        height = RandomUtils.uniform(random, 100, 120);
        System.out.println("width" + width);
        System.out.println("height" + height);
        world = new TETile[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }
        teRenderer = te;
        notfirst = false;
        rooms = new ArrayList<>();
        halls = new ArrayList<>();
        Point start = new Point(RandomUtils.uniform(random, height),
                RandomUtils.uniform(random, width));
        createAreas(RandomUtils.uniform(random, 12, 24), start);
    }*/

    public void addAvatar(Point p) {
        person = new Avatar(p);
    }

    public void removeAvatar() {
        remove(person.location);
        add(person.location, person.prevTile);
    }
    public Avatar getAvatar() {
        return person;
    }

    /*public void renderWorld() {
        if (!notfirst) {
            teRenderer.initialize(height, width);
            notfirst = true;
        }
        teRenderer.renderFrame(getTiles());
    }*/

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void closeWindow() {
        System.exit(0);
    }

    public Random getRandom() {
        return random;
    }

    public void add(Point p, TETile tile) {
        if (!isOutofIndex(p.getX(), p.getY()) && !isTaken(p.getX(), p.getY())) {
            world[p.getX()][p.getY()] = tile;
        }
    }

    public void remove(Point p) {
        if (!isOutofIndex(p.getX(), p.getY()) && isTaken(p.getX(), p.getY())) {
            world[p.getX()][p.getY()] = Tileset.NOTHING;
        }
    }


    public void createAreas(int numAreas, Point beginning) {
        //this is where we make a new indoor area
        //this is where we add that indoor area to indoorAreas
        Point nextStartingPoint = beginning;
        for (int i = 0; i < numAreas; i++) {
            if (i % 2 == 0) {
                Room temp;
                if (i == 0) {
                    temp = new Room(nextStartingPoint, RandomUtils.uniform(random, 2, 8),
                            RandomUtils.uniform(random, 2, 8), this, true);
                } else {
                    temp = new Room(nextStartingPoint, RandomUtils.uniform(random, 2, 8),
                            RandomUtils.uniform(random, 2, 8), this);
                }
                //System.out.print(temp.getDirection());
                //System.out.println(Arrays.toString(temp.getParams()));
                rooms.add(temp);
                int ran = RandomUtils.uniform(random, rooms.size());
                nextStartingPoint = adjustStart(rooms.get(ran).getExitPoint());
            } else {
                Hallway temp = null;
                if (nextStartingPoint.getDirec().equals("top") || nextStartingPoint.getDirec().equals("bottom")) {
                    temp = new Hallway(nextStartingPoint, 1, RandomUtils.uniform(random, 5, 12), this);
                } else {
                    temp = new Hallway(nextStartingPoint, RandomUtils.uniform(random, 5, 12), 1, this);
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
        if (world[x][y].equals(Tileset.WALL)) {
            return false;
        } else if (!(world[x][y].equals(Tileset.NOTHING))) {
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

    public String getType(TETile t) {
        if (t.equals(Tileset.WALL)) {
            return "Wall";
        } else if (t.equals(Tileset.LOCKED_DOOR)) {
            return "Starting Point";
        } else if (t.equals(Tileset.FLOOR)) {
            return "Floor";
        } else if (t.equals(Tileset.MOUNTAIN)) {
            return "Character";
        } else if (t.equals(Tileset.TREE)) {
            return "Portal";
        } else {
            return "Nothing";
        }
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
