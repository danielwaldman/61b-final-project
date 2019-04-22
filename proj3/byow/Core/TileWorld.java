package byow.Core;

import java.util.ArrayList;
import byow.TileEngine.TETile;
import java.util.Random;

public class TileWorld {
    private ArrayList<IndoorArea> indoorAreas;//might not be necessary
    private int width;
    private int height;
    private TETile[][] world;
    private static final Random random = new Random(500);

    public TileWorld(int w, int h) {
        width = w;
        height = h;
        world = new TETile[h][w];
        indoorAreas = new ArrayList<>();
    }

    public Random getRandom() {
        return random;
    }

    public void add(Point p, TETile tile) {
        world[p.getX()][p.getY()] = tile;
    }

    public void createAreas(int numAreas, Point beginning) {
        //this is where we make a new indoor area
        //this is where we add that indoor area to indoorAreas
        Point nextStartingPoint = beginning;
        for (int i = 0; i < numAreas; i++) {
            IndoorArea temp = new Room(nextStartingPoint, 5, 5, this);
            indoorAreas.add(temp);
            nextStartingPoint = temp.getExitPoint();
        }
    }

    public TETile get(Point p) {
        return world[p.getX()][p.getY()];
    }

    public static void main(String[] args) {
        TileWorld tw = new TileWorld(50, 50);
        tw.createAreas(2, new Point(10, 10));
        for (int i = 0; i < 50; i++) {
            for (int g = 0; g < 50; g++) {
                System.out.print(tw.get(new Point(i, g)));
            }
            System.out.println();
        }
    }
}
