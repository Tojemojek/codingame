package pl.kostrowski.codingame.puzzle.LastCrusade1;

import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int W = in.nextInt(); // number of columns.
        int H = in.nextInt(); // number of rows.
        int[][][] rooms = new int[W][H][1];

        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < H; i++) {
            String LINE = in.nextLine(); // represents a line in the grid and contains W integers. Each integer represents one room of a given type.
            String[] s = LINE.split(" ");

            int j = 0;

            for (String s1 : s) {
                rooms[j][i][0] = Integer.valueOf(s1);
                j++;
            }
        }
        int EX = in.nextInt(); // the coordinate along the X axis of the exit (not useful for this first mission, but must be read).

        // game loop
        while (true) {
            int XI = in.nextInt();
            int YI = in.nextInt();
            String POS = in.next();


            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");
            String s = determineOutput(XI, YI, POS, rooms);

            // One line containing the X Y coordinates of the room in which you believe Indy will be on the next turn.
            System.out.println(s);
        }
    }

    private static String determineOutput(int x, int y, String pos, int[][][] rooms) {
        int i = rooms[x][y][0];


        switch (i) {
            case 1:
            case 3:
            case 7:
            case 8:
            case 9:
                return x + " " + (y + 1);
            case 2:
            case 6:
                if (pos.equalsIgnoreCase("LEFT")) {
                    return (x + 1) + " " + y;
                } else if (pos.equalsIgnoreCase("RIGHT")) {
                    return (x - 1) + " " + y;
                }
            case 4:
            case 10:
            case 12:
                if (pos.equalsIgnoreCase("TOP")) {
                    return (x - 1) + " " + y;
                } else {
                    return x + " " + (y + 1);
                }
            case 5:
            case 11:
            case 13:
                if (pos.equalsIgnoreCase("TOP")) {
                    return (x + 1) + " " + y;
                } else {
                    return x + " " + (y + 1);
                }

        }
        return null;
    }
}