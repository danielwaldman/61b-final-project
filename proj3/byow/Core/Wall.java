package byow.Core;
import java.util.ArrayList;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import byow.Core.RandomUtils;

public class Wall {
    private boolean isVert;
    private ArrayList<TETile> tiles;
    private int startingx;
    private int startingy;
    private int length;
    private TileWorld tileWorld;
    private Point startPoint;


    public Wall(boolean vert, int len, Point p, TileWorld tw) {
        isVert = vert;
        tiles = new ArrayList<>();
        length = len;
        startPoint = p;
        startingx = startPoint.getX();
        startingy = startPoint.getY();
        tileWorld = tw;
        for (int i = 0; i < length; i++) {
            tiles.add(Tileset.WALL);
        }
        if (isVert) {
            for (int i = 0; i < len; i++) {
                tw.add(new Point(startingx, startingy - i), Tileset.WALL);
            }
        } else {
            for (int i = 0; i < len; i++) {
                tw.add(new Point(startingx + i, startingy), Tileset.WALL);
            }
        }
    }

    public Point removeWallRandom() {
        int ran = RandomUtils.uniform(tileWorld.getRandom(), length);
        Point temp;
        if (isVert) {
            temp = new Point(startingx, startingy - ran);
        } else {
            temp = new Point(startingx + ran, startingy);
        }
        removeWallSeg(ran);
        return temp;
    }

    public void removeWallSeg(int index) {
        tiles.set(index, Tileset.FLOOR);
    }
}
