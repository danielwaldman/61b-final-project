package byow.Core;
import byow.TileEngine.Tileset;


public class Room extends IndoorArea {
    public Room(Point startingPoint, int length, int width, TileWorld tw) {
        super(startingPoint, length, width, tw);
        build(startingPoint);
    }
}
