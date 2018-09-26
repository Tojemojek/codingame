package pl.kostrowski.codingame.puzzle.aneo;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int speed = in.nextInt();
        int lightCount = in.nextInt();
        boolean czerwone = true;
        List<Data> listOfPoint = new LinkedList<>();

        for (int i = 0; i < lightCount; i++) {
            int distance = in.nextInt();
            int duration = in.nextInt();

            Data point = new Data(distance, duration);
            listOfPoint.add(point);

        }
        for (Data data : listOfPoint) {
            System.err.println(data);
        }

        speed = findMaxSpeeds(listOfPoint, speed);

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(speed);
    }

    static boolean isInGreenWindow(int duration, double time) {

        return (int) (time / duration * 1.0) % 2 == 0;
    }

    static int findMaxSpeeds(List<Data> input, int maxSpeed) {
        boolean different = true;
        int speed = maxSpeed;

        while (different) {
            different = false;
            for (Data data : input) {
                double minTime = data.distance * 1.0 / speed * 3.6;
                if (isInGreenWindow((data.duration), minTime)) {
                } else {
                    int intervals = (int) (minTime / data.duration) + 1;
                    double time = intervals * data.duration;
                    speed = (int) (data.distance / time * 3.6);
                    System.err.println("Duration: " + data.duration + ", min time: " + minTime);
                    System.err.println("Time: " + time + ", speed: " + speed);
                    different = true;
                }
            }
        }
        return speed;
    }

    static class Data {
        int distance;
        int duration;

        public Data(int distance, int duration) {
            this.distance = distance;
            this.duration = duration;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "distance=" + distance +
                    ", duration=" + duration +
                    '}';
        }
    }
}


//Input
//Line 1: An integer speed for the maximum speed allowed on the portion of the road (in km / h).
//
//Line 2: An integer lightCount for the number of traffic lights on the road.
//
//lightCount next lines:
//- An integer distance representing the distance of the traffic light from the starting point (in meters).
//- An integer duration representing the duration of the traffic light on each color.
//
//A traffic light alternates a period of duration seconds in green and then duration seconds in red.
//All traffic lights turn green at the same time as you enter the area.