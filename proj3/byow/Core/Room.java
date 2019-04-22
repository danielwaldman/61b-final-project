package byow.Core;
import byow.TileEngine.Tileset;


public class Room extends IndoorArea {
    public Room(Point startingPoint, int length, int width, TileWorld tw) {
        super(startingPoint, length, width, tw);
        for (int i = 0; i < length; i++) {
            for(int j = 0; j < width; j++) {
                tw.add(new Point(startingPoint.getX() + i, startingPoint.getY() + j), Tileset.FLOOR);
            }
        }
    }
}
