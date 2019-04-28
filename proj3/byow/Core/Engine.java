package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.TERenderer;
import edu.princeton.cs.introcs.StdDraw;
import byow.Core.TileWorld.Avatar;

public class Engine {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {
        String seed = "";
        boolean seedExists = false;
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char c = StdDraw.nextKeyTyped();
                target:
                while (!seedExists) {
                    switch (c) {
                        case ('n'):
                            break;
                        case ('N'):
                            break;
                        case ('s'):
                            interactWithInputString(seed);
                            seedExists = true;
                            c = StdDraw.nextKeyTyped();
                            break target;
                        case ('S'):
                            interactWithInputString(seed);
                            seedExists = true;
                            c = StdDraw.nextKeyTyped();
                            break target;
                        default:
                            seed = seed + c;
                    }
                }
                switch(c) {
                    case ('w'):
                        break;
                    case ('W'):
                        break;
                    case ('a'):
                        break;
                    case ('A'):
                        break;
                    case ('s'):
                        break;
                    case ('S'):
                        break;
                    case ('d'):
                        break;
                    case ('D'):
                        break;
                }
            }
        }
    }

    /**
     * Method used for autograding and testing your code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The engine should
     * behave exactly as if the user typed these characters into the engine using
     * interactWithKeyboard.
     *
     * Recall that strings ending in ":q" should cause the game to quite save. For example,
     * if we do interactWithInputString("n123sss:q"), we expect the game to run the first
     * 7 commands (n123sss) and then quit and save. If we then do
     * interactWithInputString("l"), we should be back in the exact same state.
     *
     * In other words, both of these calls:
     *   - interactWithInputString("n123sss:q")
     *   - interactWithInputString("lww")
     *
     * should yield the exact same world state as:
     *   - interactWithInputString("n123sssww")
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] interactWithInputString(String input) {
        // passed in as an argument, and return a 2D tile representation of the
        // world that would have been drawn if the same inputs had been given
        // to interactWithKeyboard().
        //
        // See proj3.byow.InputDemo for a demo of how you can make a nice clean interface
        // that works for many different input types.
        String seed = "";
        int loc = 0;
        for (int i = 0; i < input.length(); i++) {
            char temp = input.charAt(i);
            if (temp != ('N') && temp != ('n')) {
                if (temp == ('S') || temp == ('s')) {
                    loc = i + 1;
                    break;
                } else {
                    seed = seed + temp;
                }
            } else if (temp == ('l') || temp == ('L')) {
                //load previous state
                loc = i + 1;
                break;
            }
        }
        long seedInt = Long.parseLong(seed);
        //TileWorld newWorld = new TileWorld(seedInt);
        TileWorld newWorld = new TileWorld(seedInt, ter);
        newWorld.renderWorld();
        for (int x = loc; x < input.length(); x++) {
            char temp = input.charAt(x);
            Avatar tempA = newWorld.getAvatar();
            Point locA = tempA.location;
            switch (temp) {
                case ('w'):
                    tempA.move(new Point(locA.getX(), locA.getY() + 1));
                    newWorld.renderWorld();
                    break;
                case ('W'):
                    tempA.move(new Point(locA.getX(), locA.getY() + 1));
                    newWorld.renderWorld();
                    break;
                case ('a'):
                    tempA.move(new Point(locA.getX() - 1, locA.getY()));
                    newWorld.renderWorld();
                    break;
                case ('A'):
                    tempA.move(new Point(locA.getX() - 1, locA.getY()));
                    newWorld.renderWorld();
                    break;
                case ('s'):
                    tempA.move(new Point(locA.getX(), locA.getY() - 1));
                    newWorld.renderWorld();
                    break;
                case ('S'):
                    tempA.move(new Point(locA.getX(), locA.getY() - 1));
                    newWorld.renderWorld();
                    break;
                case ('d'):
                    tempA.move(new Point(locA.getX() + 1, locA.getY()));
                    newWorld.renderWorld();
                    break;
                case ('D'):
                    tempA.move(new Point(locA.getX() + 1, locA.getY()));
                    newWorld.renderWorld();
                    break;
                case (':'):
                    x += 1;
                    char next = input.charAt(x);
                    if (next == ('Q') || next == ('q')) {
                        // do some save shit and quit
                    }
                    break;
            }
        }
        TETile[][] finalWorldFrame = newWorld.getTiles();
        return finalWorldFrame;
    }
}
