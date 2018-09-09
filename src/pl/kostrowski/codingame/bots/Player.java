package pl.kostrowski.codingame.bots;

import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        boolean thrustOn = true;
        boolean changeDirection = false;
        boolean initRound = true;

        // game loop
        while (true) {
            int x = in.nextInt();
            int y = in.nextInt();
            int nextCheckpointX = in.nextInt(); // x position of the next check point
            int nextCheckpointY = in.nextInt(); // y position of the next check point
            int nextCheckpointDist = in.nextInt(); // distance to the next checkpoint
            int nextCheckpointAngle = in.nextInt(); // angle between your pod orientation and the direction of the next checkpoint
            int opponentX = in.nextInt();
            int opponentY = in.nextInt();

            double thrust = 0;

            int abs = Math.abs(nextCheckpointAngle);

            if (abs < 10) {
                thrust = 100;
            } else if (abs < 20 && nextCheckpointDist > 1000) {
                thrust = 100;
            } else if (abs < 75) {
                thrust = 100 - abs / 0.75;
            } else {
                thrust = 0;
            }

            Point closest = closest(x, y, nextCheckpointX, nextCheckpointY, nextCheckpointDist);


            System.err.println("My pos " + x + ":" + y);
            System.err.println("Next checkpoint " + nextCheckpointX + ":" + nextCheckpointY);
            System.err.println("Next checkpoint dist " + nextCheckpointDist + ", angle " + nextCheckpointAngle);

            if (nextCheckpointDist > 3000 && abs < 15 && thrustOn == true) {
                System.out.println(nextCheckpointX + " " + nextCheckpointY + " " + "BOOST");
                thrustOn = false;
            }

            System.out.println(closest.x + " " + closest.y + " " + (int) thrust);
        }
    }

    private static Point closest(int x1, int y1, int x2, int y2, int distance) {
        int diameter = 400;


        double a = (y2 * 1.0 - y1 * 1.0) / (x2 * 1.0 - x1 * 1.0);
        double b = (y1 * 1.0 - a * x1 * 1.0);

        System.err.println("TgKat " + a);

        double alfa = Math.atan(a);

        System.err.println("Kat " + alfa);

        double x3 = x1 + Math.cos(alfa) * (distance - diameter);
        double y3 = y1 + Math.sin(alfa) * (distance - diameter);

        return new Point((int) x3, (int) y3);
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


