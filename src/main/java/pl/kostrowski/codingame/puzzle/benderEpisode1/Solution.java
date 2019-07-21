package pl.kostrowski.codingame.puzzle.benderEpisode1;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

//https://www.codingame.com/ide/puzzle/bender-episode-1

class Solution {

    private static final boolean LOG_ON = false;
    private static String[][] worldMap;
    private static List<Teleport> teleports = new LinkedList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int L = in.nextInt();
        int C = in.nextInt();

        if (in.hasNextLine()) {
            in.nextLine();
        }

        worldMap = new String[L][C];
        Robot myRobot = null;

        for (int i = 0; i < L; i++) {
            char[] chars = in.nextLine().toCharArray();
            int j = 0;
            for (char aChar : chars) {
                if (Character.toString(aChar).equals("T")) {
                    teleports.add(new Teleport(i, j));
                } else if (Character.toString(aChar).equals("@")) {
                    myRobot = new Robot(i, j);
                }
                worldMap[i][j++] = Character.toString(aChar);

            }
        }

        if (LOG_ON) {
            System.err.println(L + " " + C);
            printMap(worldMap, myRobot);
        }

        int loopNo = 0;
        StringBuilder sb = new StringBuilder();
        do {
            String s = robotGo(myRobot);
            if (s.length() > 0) {
                sb.append(s).append("\n");
            }
            loopNo++;
            printMap(worldMap, myRobot);
        } while (!worldMap[myRobot.getPosX()][myRobot.getPosY()].equals("$") && loopNo < 1000);

        if (loopNo >= 1000) {
            System.out.println("LOOP");
        } else {
            System.out.println(sb.toString());
        }

    }

    private static void printMap(String[][] worldMap, Robot myRobot) {
        if (LOG_ON) {
            StringBuilder sb = new StringBuilder();

            for (int x = 0; x < worldMap.length; x++) {
                for (int y = 0; y < worldMap[x].length; y++) {
                    if (myRobot.getPosX() == x && myRobot.getPosY() == y) {
                        sb.append("R");
                    } else {
                        sb.append(worldMap[x][y]);
                    }
                }
                sb.append("\n");
            }
            sb.append("\n");
            System.err.print(sb.toString());
        }
    }

    private static String robotGo(Robot myRobot) {
        String direction = myRobot.getDirection();

        int nextX = myRobot.getPosX();
        int nextY = myRobot.getPosY();

        switch (direction) {
            case "S":
                nextX++;
                break;
            case "N":
                nextX--;
                break;
            case "E":
                nextY++;
                break;
            case "W":
                nextY--;
                break;
        }

        String ret = "";
        if (mayPass(myRobot, nextX, nextY)) {
            ret = moveRobot(myRobot);
            checkModifiers(myRobot, nextX, nextY);
            myRobot.setTryDirection(0);
        } else {
            myRobot.setDirection(myRobot.getDirectionOrder()[myRobot.getTryDirection()]);
            myRobot.changeTryDirection();
        }

        return ret;

    }

    private static String moveRobot(Robot myRobot) {
        String direction = myRobot.getDirection();
        switch (direction) {
            case "S":
                myRobot.setPosX(myRobot.getPosX() + 1);
                return "SOUTH";
            case "N":
                myRobot.setPosX(myRobot.getPosX() - 1);
                return "NORTH";
            case "E":
                myRobot.setPosY(myRobot.getPosY() + 1);
                return "EAST";
            case "W":
                myRobot.setPosY(myRobot.getPosY() - 1);
                return "WEST";
        }
        return null;
    }

    private static void checkModifiers(Robot myRobot, int nextX, int nextY) {
        String modifier = worldMap[myRobot.getPosX()][myRobot.getPosY()];

        switch (modifier) {
            case "N":
                myRobot.setDirection("N");
                break;
            case "S":
                myRobot.setDirection("S");
                break;
            case "E":
                myRobot.setDirection("E");
                break;
            case "W":
                myRobot.setDirection("W");
                break;
            case "B":
                myRobot.modifyBenderMode();
                break;
            case "T":
                teleport(myRobot, nextX, nextY);
            default:
                break;
        }
    }


    private static boolean mayPass(Robot myRobot, int nextX, int nextY) {
        String nextField = worldMap[nextX][nextY];

        switch (nextField) {
            case "X":
                if (myRobot.isBenderMode()) {
                    worldMap[nextX][nextY] = " ";
                    return true;
                } else {
                    return false;
                }
            case "#":
                return false;
            case "I":
                myRobot.inverter();
                return true;
            default:
                return true;
        }
    }

    private static void teleport(Robot myRobot, int nextX, int nextY) {
        for (Teleport teleport : teleports) {
            if (teleport.getPosX() == nextX && teleport.getPosY() == nextY) {
                //do nothing
            } else {
                myRobot.setPosX(teleport.getPosX());
                myRobot.setPosY(teleport.getPosY());
                return;
            }
        }

    }
}

class Robot {

    private int posX;
    private int posY;
    private String direction = "S";
    private boolean normalOrder = true;
    private boolean benderMode = false;
    private int tryDirection = 0;
    private String[] directionOrder = {"S", "E", "N", "W"};

    public Robot(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public boolean isNormalOrder() {
        return normalOrder;
    }

    public void inverter() {
        this.normalOrder = !normalOrder;
    }

    public boolean isBenderMode() {
        return benderMode;
    }

    public void modifyBenderMode() {
        this.benderMode = !this.benderMode;
    }

    public int getTryDirection() {
        return tryDirection;
    }

    public void setTryDirection(int tryDirection) {
        if (normalOrder) {
            this.tryDirection = tryDirection;
        } else {
            this.tryDirection = 3 - tryDirection;
        }
    }

    public String[] getDirectionOrder() {
        return directionOrder;
    }

    public void setDirectionOrder(String[] directionOrder) {
        this.directionOrder = directionOrder;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void changeTryDirection() {
        if (normalOrder) {
            tryDirection++;
        } else {
            tryDirection--;
        }
    }

    @Override
    public String toString() {
        return "pl.kostrowski.codingame.puzzle.benderEpisode1.Robot{" +
                "posX=" + posX +
                ", posY=" + posY +
                '}';
    }

}

class Teleport {
    private int posX;
    private int posY;

    public Teleport(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}

