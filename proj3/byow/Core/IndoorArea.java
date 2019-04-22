package byow.Core;

public class IndoorArea {
    private int xStart;
    private int yStart;
    private int xlength;
    private int ywidth;
    private IndoorArea entry;
    private IndoorArea exit;

    public IndoorArea(int initx, int inity, int length, int width, IndoorArea entrance) {
        xStart = initx;
        yStart = inity;
        xlength = length;
        ywidth = width;
        entry = entrance;
        exit = null;
    }
}
