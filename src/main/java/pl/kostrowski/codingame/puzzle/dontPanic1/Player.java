package pl.kostrowski.codingame.puzzle.dontPanic1;

import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    private static List<Elevator> elevators = new LinkedList<>();
    private static Map<Integer, List<Elevator>> quickFindElevator = new HashMap<>();

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int nbFloors = in.nextInt(); // number of floors
        int width = in.nextInt(); // width of the area
        int nbRounds = in.nextInt(); // maximum number of rounds
        int exitFloor = in.nextInt(); // floor on which the exit is found
        int exitPos = in.nextInt(); // position of the exit on its floor
        int nbTotalClones = in.nextInt(); // number of generated clones
        int nbAdditionalElevators = in.nextInt(); // ignore (always zero)
        int nbElevators = in.nextInt(); // number of elevators
        for (int i = 0; i < nbElevators; i++) {
            int elevatorFloor = in.nextInt(); // floor on which this elevator is found
            int elevatorPos = in.nextInt(); // position of the elevator on its floor
            elevators.add(new Elevator(elevatorFloor, elevatorPos));
        }

        for (Elevator elevator : elevators) {
            if (quickFindElevator.containsKey(elevator.floor)) {
                quickFindElevator.get(elevator.floor).add(elevator);
            } else {
                List<Elevator> tmp = new LinkedList<>();
                tmp.add(elevator);
                quickFindElevator.put(elevator.floor, tmp);
            }
        }


        int nextTarget = 0;
        String whatToDo = "WAIT";
        int interestingFloor = 0;
        // game loop
        while (true) {
            int cloneFloor = in.nextInt(); // floor of the leading clone
            int clonePos = in.nextInt(); // position of the leading clone on its floor
            String direction = in.next(); // direction of the leading clone: LEFT or RIGHT

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            if (interestingFloor == cloneFloor) {
                if (isOnRightFloor(cloneFloor, exitFloor)) {
                    nextTarget = exitPos;
                } else {
                    nextTarget = findBestElevator(cloneFloor);
                }
                if (isGoingInRightDirection(nextTarget, clonePos, direction)) {
                    whatToDo = "WAIT";
                } else {
                    whatToDo = "BLOCK";
                }
                interestingFloor++;
            } else {
                whatToDo = "WAIT";
            }


            System.out.println(whatToDo); // action: WAIT or BLOCK
        }
    }

    private static boolean isOnRightFloor(int cloneFloor, int exitFloor) {
        return cloneFloor == exitFloor;
    }

    private static boolean isGoingInRightDirection(int nextTarget, int clonePos, String direction) {
        return nextTarget > clonePos && direction.equalsIgnoreCase("RIGHT") ||
                nextTarget < clonePos && direction.equalsIgnoreCase("LEFT");
    }

    private static int findBestElevator(int floor) {
        List<Elevator> elevators = quickFindElevator.get(floor);
        return elevators.get(0).pos;
    }

}

class Elevator {
    int floor;
    int pos;

    public Elevator(int floor, int pos) {
        this.floor = floor;
        this.pos = pos;
    }

}