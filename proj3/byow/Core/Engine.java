package byow.Core;

import byow.TileEngine.TETile;
//import byow.TileEngine.TERenderer;
import edu.princeton.cs.introcs.StdDraw;
import byow.Core.TileWorld.Avatar;

import java.awt.Font;
import java.awt.Color;

public class Engine {
    //TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    private class Container {
        TETile[][] tiles;
        TileWorld t;
        String s;
        String a;
        boolean sE;
        boolean lF;

        Container(TETile[][] t, String q, boolean b, boolean c) {
            tiles = t;
            s = q;
            sE = b;
            lF = c;
        }

        public String getS() {
            return s;
        }

        public boolean getlF() {
            return lF;
        }
    }
    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {
        String seed = "";
        StdDraw.setCanvasSize(this.WIDTH * 16, this.HEIGHT * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(0, HEIGHT);
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.text(WIDTH / 2, HEIGHT * 2 / 3, "CS61B: THE GAME");
        StdDraw.text(WIDTH / 2, HEIGHT / 2, "New Game (N)");
        StdDraw.text(WIDTH / 2, HEIGHT / 2 - 2, "Load Game (L)");
        StdDraw.text(WIDTH / 2, HEIGHT / 2 - 4, "Quit (Q)");
        StdDraw.enableDoubleBuffering();
        TETile[][] world = null;
        boolean seedExists = false;
        boolean loadfile = false;
        Container tempp = switch1(world, seed, seedExists, loadfile);
        seed = tempp.getS();
        loadfile = tempp.getlF();
        String all = seed;
        while (seed.substring(0, 1).equals("N") || seed.substring(0, 1).equals("n")) {
            seed = seed.substring(1);
        }
        while (seed.substring(seed.length() - 1).equals("s")
                || seed.substring(seed.length() - 1).equals("S")) {
            seed = seed.substring(0, seed.length() - 1);
        }
        System.out.println(seed);
        String seed12 = "";
        for (int i = 0; i < seed.length(); i++) {
            char temp = seed.charAt(i);
            if (temp != ('N') && temp != ('n')) {
                if (temp == ('S') || temp == ('s')) {
                    break;
                } else {
                    seed12 = seed12 + temp;
                }
            }
        }
        long seedInt = Long.parseLong(seed12);
        TileWorld newWorld = new TileWorld(seedInt);
        //TileWorld newWorld = new TileWorld(seedInt, ter);
        if (loadfile) {
            newWorld.removeAvatar();
            String t = Main.loadAvatar();
            String [] points = t.split("\\s+");
            int x = Integer.parseInt(points[0]);
            int y = Integer.parseInt(points[1]);
            newWorld.addAvatar(new Point(x, y));
        }
        //newWorld.renderWorld();
        switch2(newWorld, all, seed);
    }

    private Container switch1(TETile[][] world, String seed, boolean seedExists, boolean loadfile) {
        target:
        while (!seedExists) {
            if (StdDraw.hasNextKeyTyped()) {
                char c = StdDraw.nextKeyTyped();
                switch (c) {
                    case ('l'):
                        world = interactWithInputString(Main.loadStored());
                        seed = Main.loadStored();
                        seedExists = true;
                        loadfile = true;
                        break target;
                    case ('L'):
                        world = interactWithInputString(Main.loadStored());
                        seed = Main.loadStored();
                        seedExists = true;
                        loadfile = true;
                        break target;
                    case ('n'):
                        seed = "n" + seed;
                        show(seed);
                        break;
                    case ('N'):
                        seed = "N" + seed;
                        show(seed);
                        break;
                    case ('s'):
                        interactWithInputString(seed);
                        seedExists = true;
                        seed = seed + "s";
                        show(seed);
                        break;
                    case ('S'):
                        interactWithInputString(seed);
                        seedExists = true;
                        seed = seed + "s";
                        show(seed);
                        break;
                    case ('Q'):
                        //System.exit(0);
                        break;
                    case ('q'):
                        //System.exit(0);
                        break;
                    default:
                        seed = seed + c;
                        show(seed);
                }
            }
        }
        return new Container(world, seed, seedExists, loadfile);
    }

    private void switch2(TileWorld newWorld, String all, String seed) {
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char c = StdDraw.nextKeyTyped();
                Avatar tempA = newWorld.getAvatar();
                Point locA = tempA.location;
                switch (c) {
                    case ('w'):
                        tempA.move(new Point(locA.getX(), locA.getY() + 1));
                        //newWorld.renderWorld();
                        break;
                    case ('W'):
                        tempA.move(new Point(locA.getX(), locA.getY() + 1));
                        //newWorld.renderWorld();
                        break;
                    case ('a'):
                        tempA.move(new Point(locA.getX() - 1, locA.getY()));
                        //newWorld.renderWorld();
                        break;
                    case ('A'):
                        tempA.move(new Point(locA.getX() - 1, locA.getY()));
                        //newWorld.renderWorld();
                        break;
                    case ('s'):
                        tempA.move(new Point(locA.getX(), locA.getY() - 1));
                        //newWorld.renderWorld();
                        break;
                    case ('S'):
                        tempA.move(new Point(locA.getX(), locA.getY() - 1));
                        //newWorld.renderWorld();
                        break;
                    case ('d'):
                        tempA.move(new Point(locA.getX() + 1, locA.getY()));
                        //newWorld.renderWorld();
                        break;
                    case ('D'):
                        tempA.move(new Point(locA.getX() + 1, locA.getY()));
                        //newWorld.renderWorld();
                        break;
                    case (':'):
                        while (true) {
                            if (StdDraw.hasNextKeyTyped()) {
                                char next = StdDraw.nextKeyTyped();
                                if (next == ('Q') || next == ('q')) {
                                    Main.setStored(all);
                                    Main.storeAvatar(newWorld);
                                    //newWorld.closeWindow();
                                }
                                break;
                            }
                        }
                        break;
                    default:
                        break;
                }
                seed = seed + c;
                all = all + c;
            } else if (StdDraw.isMousePressed()) {
                shows(newWorld.getType(newWorld.get(new Point((int) Math.round(StdDraw.mouseX()),
                                (int) Math.round(StdDraw.mouseY())))),
                        newWorld.getWidth(), newWorld.getHeight(),
                        (int) Math.round(StdDraw.mouseX()), (int) Math.round(StdDraw.mouseY()));
                //newWorld.renderWorld();
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
            if (temp == ('L') || temp == ('l')) {
                return interactWithInputString(Main.loadStored() + input.substring(1));
            } else if (temp != ('N') && temp != ('n')) {
                if (temp == ('S') || temp == ('s')) {
                    loc = i + 1;
                    break;
                } else {
                    seed = seed + temp;
                }
            }
        }
        long seedInt = Long.parseLong(seed);
        TileWorld newWorld = new TileWorld(seedInt);
        //TileWorld newWorld = new TileWorld(seedInt, ter);
        //newWorld.renderWorld();
        for (int x = loc; x < input.length(); x++) {
            char temp = input.charAt(x);
            Avatar tempA = newWorld.getAvatar();
            Point locA = tempA.location;
            switch (temp) {
                case ('w'):
                    tempA.move(new Point(locA.getX(), locA.getY() + 1));
                    //newWorld.renderWorld();
                    break;
                case ('W'):
                    tempA.move(new Point(locA.getX(), locA.getY() + 1));
                    //newWorld.renderWorld();
                    break;
                case ('a'):
                    tempA.move(new Point(locA.getX() - 1, locA.getY()));
                    //newWorld.renderWorld();
                    break;
                case ('A'):
                    tempA.move(new Point(locA.getX() - 1, locA.getY()));
                    //newWorld.renderWorld();
                    break;
                case ('s'):
                    tempA.move(new Point(locA.getX(), locA.getY() - 1));
                    //newWorld.renderWorld();
                    break;
                case ('S'):
                    tempA.move(new Point(locA.getX(), locA.getY() - 1));
                    //newWorld.renderWorld();
                    break;
                case ('d'):
                    tempA.move(new Point(locA.getX() + 1, locA.getY()));
                    //newWorld.renderWorld();
                    break;
                case ('D'):
                    tempA.move(new Point(locA.getX() + 1, locA.getY()));
                    //newWorld.renderWorld();
                    break;
                case (':'):
                    x += 1;
                    char next = input.charAt(x);
                    if (next == ('Q') || next == ('q')) {
                        Main.setStored(input.substring(0, input.length() - 2));
                    }
                    //newWorld.closeWindow();
                    break;
                default:
                    break;
            }
        }
        TETile[][] finalWorldFrame = newWorld.getTiles();
        return finalWorldFrame;
    }

    public void shows(String s, int w, int h, int x, int y) {
        Font font = new Font("Monaco", Font.BOLD, 20);
        StdDraw.setFont(font);
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.text(w / 2, h / 2, s);
        StdDraw.text(w / 2, h / 2 - 5, Integer.toString(x));
        StdDraw.text(w / 2, h / 2 - 10, Integer.toString(y));
        StdDraw.show();
    }

    public void show(String s) {
        int midWidth = WIDTH / 2;
        int midHeight = HEIGHT / 2;

        Font font = new Font("Monaco", Font.BOLD, 20);
        StdDraw.setFont(font);
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.text(midWidth, midHeight, s);
        StdDraw.show();
    }
}

