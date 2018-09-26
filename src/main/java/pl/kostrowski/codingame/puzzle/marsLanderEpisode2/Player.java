package pl.kostrowski.codingame.puzzle.marsLanderEpisode2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

//Refaktor - najpierw decyzje potem wybór mocy

class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int surfaceN = in.nextInt(); // the number of points used to draw the surface of Mars.
        List<Point> powierzchnia = new ArrayList<>();

        for (int i = 0; i < surfaceN; i++) {
            int landX = in.nextInt(); // X coordinate of a surface point. (0 to 6999)
            int landY = in.nextInt(); // Y coordinate of a surface point. By linking all the points together in a sequential fashion, you form the surface of Mars.
            Point kolejny = new Point(landX, landY);
            powierzchnia.add(kolejny);
        }
        Point[] landing = findLanding(powierzchnia);
        System.err.println("Ladowisko jest od " + landing[0] + " do " + landing[1]);

        // game loop
        while (true) {
            int X = in.nextInt();
            int Y = in.nextInt();
            int hSpeed = in.nextInt(); // the horizontal speed (in m/s), can be negative.
            int vSpeed = in.nextInt(); // the vertical speed (in m/s), can be negative.
            int fuel = in.nextInt(); // the quantity of remaining fuel in liters.
            int rotate = in.nextInt(); // the rotation angle in degrees (-90 to 90).
            int power = in.nextInt(); // the thrust power (0 to 4).

            Point lander = new Point(X, Y);
            int tilt = 0;

            Point gorka = findPeak(powierzchnia, lander, landing);


            System.err.println("Górka ma współrzędne: " + gorka);
            System.err.println("Lądowisko ma współrzędne: " + landing[0] + " " + landing[1]);
            System.err.println("V speed " + vSpeed + " hSpeed " + hSpeed);
            System.err.println("Powinien ominąć górkę: " + shouldAvoidPeek(lander, gorka, hSpeed, vSpeed));
            String whereIsLandingSpace = whereIsLandingSpace(lander, landing);
            System.err.println("Gdzie lądowisko " + whereIsLandingSpace);
            boolean willHitLanding = willHitLanding(lander, landing, hSpeed, vSpeed);
            System.err.println("Trafi w lądowisko " + willHitLanding);
            boolean toLowForLanding = isToLowForLanding(lander, landing, hSpeed, vSpeed, whereIsLandingSpace);
            System.err.println("Too low " + toLowForLanding);

            power = 4;

            if (willHitLanding && Math.abs(hSpeed) < 20 && !whereIsLandingSpace.equals("Over") && toLowForLanding) {
                System.err.println("Trafi, utrzymuje");
                switch (whereIsLandingSpace) {
                    case "Left":
                        rotate = +5;
                        break;
                    case "Right":
                        rotate = -5;
                        break;
                }
                if (vSpeed > 0) {
                    power = 0;
                } else {
                    power = 4;
                }
            } else if (willHitLanding && Math.abs(hSpeed) < 20) {
                System.err.println("Trafi, obniżam");
                rotate = 0;
                if (vSpeed > 0) {
                    power = 0;
                } else {
                    power = -vSpeed / 7; //zeby 35 trafiło w moc 4
                }
            } else {
                int correction = 0;
                switch (whereIsLandingSpace) {
                    case "Left":
                        System.err.println("case LEFT, " + "LANDING X " + landing[1].x + " LANDER X " + lander.x + " SPEED " + hSpeed);
                        correction = Math.abs(hSpeed) < 20 ? 0 : hSpeed + 20;
                        rotate = -(landing[1].x - lander.x) / 45 - (correction);
                        power = 4;
                        break;
                    case "Right":
                        System.err.println("case RIGHT, " + "LANDING X " + landing[0].x + " LANDER X " + lander.x + " SPEED " + hSpeed);
                        correction = Math.abs(hSpeed) < 20 ? 0 : hSpeed - 20;
                        rotate = (lander.x - landing[0].x) / 45 + (correction);
                        power = 4;
                        break;
                    case "Over":
                        if (hSpeed > 0) {
                            System.err.println("case OVER, SPEED>0, " + "LANDING X " + landing[0].x + " LANDER X " + lander.x + " SPEED " + hSpeed);
                            rotate = (lander.x - landing[0].x) / 45 + (hSpeed);
                            power = 4;
                        }
                        if (hSpeed < 0) {
                            System.err.println("case OVER, SPEED<0, " + "LANDING X " + landing[1].x + " LANDER X " + lander.x + " SPEED " + hSpeed);
                            rotate = (lander.x - landing[1].x) / 45 + (hSpeed);
                            power = 4;
                        }
                        break;
                }
            }

            if (hSpeed > 40) {
                rotate = hSpeed;
            }
            if (hSpeed < -40) {
                rotate = hSpeed;
            }


            if (shouldAvoidPeek(lander, gorka, hSpeed, vSpeed)) {
                if (rotate > 15) {
                    rotate = 5;
                }
                if (rotate < -15) {
                    rotate = -5;
                }
            }

            if (rotate > 90) {
                rotate = 90;
            }
            if (rotate < -90) {
                rotate = -90;
            }

            if ((lander.y - landing[0].y) < 100) {
                rotate = 0;
                power = -vSpeed / 7;
            }


//            power = -vSpeed/20;
//
//            if (power < 0){
//                power = 0;
//            }
            if (power > 4) {
                power = 4;
            }
            if (vSpeed > 0) {
                power = 0;
            }


            // rotate power. rotate is the desired rotation angle. power is the desired thrust power.
            System.out.println(rotate + " " + power);
        }
    }

    static Point[] findLanding(List<Point> powierzchnia) {

        Point[] ladowisko = new Point[2];
        int size = powierzchnia.size();
        boolean flat = false;

        for (int i = 1; i < size; i++) {
            if (!flat && powierzchnia.get(i).y == powierzchnia.get(i - 1).y) {
                ladowisko[0] = powierzchnia.get(i - 1);
                flat = true;
            }
            if (flat && powierzchnia.get(i).y != powierzchnia.get(i - 1).y) {
                ladowisko[1] = powierzchnia.get(i - 1);
                return ladowisko;
            }
        }
        return null;
    }

    static int gdzieLadowisko(Point[] landing, Point lander) {
        if (landing[0].x > lander.x) {
            return -1; //
        } else if (landing[1].x < lander.x) {
            return 1;
        } else {
            return 0;
        }
    }

    static String whereIsLandingSpace(Point lander, Point[] landing) {
        if (landing[0].x > lander.x) {
            return "Right"; //
        } else if (landing[1].x < lander.x) {
            return "Left";
        } else {
            return "Over";
        }
    }

    static boolean willHitLanding(Point lander, Point[] landing, int hspeed, int vspeed) {
        if (vspeed == 0) {
            vspeed = -1;
        }

        String whereLanding = whereIsLandingSpace(lander, landing);

        int distx1 = Math.abs(lander.x - landing[0].x);
        int distx2 = Math.abs(lander.x - landing[1].x);
        int disty = Math.abs(lander.y - landing[0].y);
        vspeed = Math.abs(vspeed);

        if (whereLanding.equals("Right") && hspeed > 0) {
            if (Math.abs(distx1 / hspeed) <= disty / vspeed && disty / vspeed >= Math.abs(distx2 / hspeed)) {
                return true;
            }
        }
        if (whereLanding.equals("Left") && hspeed < 0) {
            if (Math.abs(distx1 / hspeed) >= disty / vspeed && disty / vspeed <= Math.abs(distx2 / hspeed)) {
                return true;
            }
        }
        if (whereLanding.equals("Over") && hspeed > 0) {
            if (disty / vspeed <= Math.abs(distx2 / hspeed)) {
                return true;
            }
        }
        if (whereLanding.equals("Over") && hspeed < 0) {
            return disty / vspeed <= Math.abs(distx1 / hspeed);
        }

        return false;
    }


    static Point findPeak(List<Point> powierzchnia, Point lander, Point[] landing) {
        Point gorka = new Point(-1, -1);

        int gdzieLadowisko = gdzieLadowisko(landing, lander);
//        System.err.println(gdzieLadowisko);

        if (gdzieLadowisko > 0) {
            for (Point point : powierzchnia) {
//                System.err.println("Punkt " + point + " lawodiwsko " + landing[0] + " do " + landing[1] + " górka " + gorka);
                if (point.x < lander.x && point.x < landing[1].x && point.y > gorka.y) {
                    gorka = point;
                }
            }
        } else {
            for (Point point : powierzchnia) {
//                System.err.println("Punkt " + point + " lawodiwsko " + landing[0] + " do " + landing[1] + " górka " + gorka);
                if (point.x > lander.x && point.x < landing[0].x && point.y > gorka.y) {
                    gorka = point;
                }
            }
        }

        if (landing[0].y > gorka.y) {
            gorka = new Point(-1, -1);
        }

        return gorka;
    }

    private static boolean shouldAvoidPeek(Point lander, Point gorka, int hspeed, int vspeed) {
        if (gorka.x == -1 || hspeed == 0 || vspeed == 0) {
            return false;
        }

        int distx = Math.abs(lander.x - gorka.x);
        int disty = Math.abs(lander.y - gorka.y);

        System.err.println("Dist x " + distx + " Dist y " + disty);

        return Math.abs(disty / vspeed) - 100 < Math.abs(distx / hspeed);
    }

    private static boolean isToLowForLanding(Point lander, Point[] landing, int hspeed, int vspeed, String whereIsLanding) {
        if (vspeed >= 0 || hspeed == 0) {
            return false;
        }

        int disty = Math.abs(lander.y - landing[0].y);
        int distx1 = Math.abs(lander.x - landing[0].x);
        int distx2 = Math.abs(lander.x - landing[1].x);


        if (whereIsLanding.equals("Left") && hspeed < 0) {
            System.err.println("distx2/hspeed " + distx2 / -hspeed + " -disty/vspeed" + -disty / vspeed);
            if (distx2 / -hspeed < -disty / vspeed) {
                return false;
            }
        }
        if (whereIsLanding.equals("Right") && hspeed > 0) {
            System.err.println("distx1/-hspeed " + distx1 / -hspeed + " -disty/vspeed" + -disty / vspeed);
            return distx1 / -hspeed >= -disty / vspeed;
        }
        return true;
    }

    static class Point {

        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}