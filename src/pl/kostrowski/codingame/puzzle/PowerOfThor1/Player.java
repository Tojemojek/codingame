package pl.kostrowski.codingame.puzzle.PowerOfThor1;

import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 * ---
 * Hint: You can use the debug stream to print initialTX and initialTY, if Thor seems not follow your orders.
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int lightX = in.nextInt(); // the X position of the light of power
        int lightY = in.nextInt(); // the Y position of the light of power
        int initialTX = in.nextInt(); // Thor's starting X position
        int initialTY = in.nextInt(); // Thor's starting Y position

        Point thorLoc = new Point(initialTX, initialTY);
        Point light = new Point(lightX, lightY);

        // game loop
        while (true) {
            int remainingTurns = in.nextInt(); // The remaining amount of turns Thor can move. Do not remove this line.

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");


            // A single line providing the move to be made: N NE E SE S SW W or NW
            String s = chooseDirection(thorLoc, light);

            System.out.println(s);
        }
    }

    static String chooseDirection(Point thor, Point light) {

        String result = "";

        if (thor.y > light.y) {
            result = result + "N";
            thor.y = thor.y - 1;
        } else if (thor.y < light.y) {
            result = result + "S";
            thor.y = thor.y + 1;
        }

        if (thor.x > light.x) {
            result = result + "W";
            thor.x = thor.x - 1;
        } else if (thor.x < light.x) {
            result = result + "E";
            thor.x = thor.x + 1;
        }
        return result;
    }


    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}