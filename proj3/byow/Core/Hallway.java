package byow.Core;

public class Hallway extends IndoorArea {
    //make sure length or width one has to be = to one
    public Hallway(Point startingPoint, int length, int width, TileWorld tw) {
        super(startingPoint, length, width, tw);
        build(startingPoint);
    }
}
