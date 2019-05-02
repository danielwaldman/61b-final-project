package byow.Core;
import java.util.ArrayList;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

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
                if (!tileWorld.isTaken(startingx, startingy - i)) {
                    tw.add(new Point(startingx, startingy - i), Tileset.WALL);
                }
            }
        } else {
            for (int i = 0; i < len; i++) {
                if (!tileWorld.isTaken(startingx + i, startingy)) {
                    tw.add(new Point(startingx + i, startingy), Tileset.WALL);
                }
            }
        }
    }

    public Point removeWallRandom() {
        int ran;
        if (length > 1) {
            ran = RandomUtils.uniform(tileWorld.getRandom(), length - 1);
        } else {
            ran = 0;
        }
        Point temp;
        if (isVert) {
            temp = new Point(startingx, startingy - ran - 1);
        } else {
            temp = new Point(startingx + ran + 1, startingy);
        }
        removeWallSeg(temp);
        return temp;
    }

    public void removeWallSeg(Point index) {
        tileWorld.remove(index);
        //System.out.println("x-coord: " + index.getX() + "y-coord: " + index.getY());
        tileWorld.add(index, Tileset.FLOOR);
    }
}
