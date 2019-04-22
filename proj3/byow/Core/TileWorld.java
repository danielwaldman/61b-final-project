package byow.Core;

import java.util.ArrayList;

public class TileWorld {
    private ArrayList<IndoorArea> indoorAreas;
    private int width;
    private int height;

    public TileWorld(int w, int h) {
        width = w;
        height = h;
    }

    public void createAreas(int numAreas) {
        if (numAreas <= 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        //this is where we make a new indoor area
        //this is where we add that indoor area to indoorAreas
        if (numAreas == 1) {
        } else {
            createAreas(numAreas - 1);
        }
    }

}
