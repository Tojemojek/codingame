package pl.kostrowski.codingame.puzzle.defibrilators;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Double LON = Double.parseDouble(in.next().replaceAll(",","."));
        Double LAT = Double.parseDouble(in.next().replaceAll(",","."));
        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }

        List<Defibrilator> defibrilators = new LinkedList<>();


        for (int i = 0; i < N; i++) {
            String DEFIB = in.nextLine();
            defibrilators.add(new Defibrilator(DEFIB));
        }

        System.err.printf("USER LON=%.4f, LAT=%.4f \n", LON, LAT);
//        for (pl.kostrowski.codingame.puzzle.defibrilators.Defibrilator defibrilator : pl.kostrowski.codingame.puzzle.defibrilators) {
//            System.err.printf("%s in distance %f \n", defibrilator.getName(), defibrilator.distance(LAT, LON));
//            System.err.printf("%s LON=%.4f, LAT=%.4f \n", defibrilator.getName(), defibrilator.getLON(), defibrilator.getLAT());
//        }

        Defibrilator closest = findClosest(defibrilators, LAT, LON);
        System.err.println(closest.getLAT() + " " + closest.getLON());

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(closest.getName());
    }

    private static Defibrilator findClosest(List<Defibrilator> defibrilators, Double lat, Double lon) {
        Defibrilator ret = defibrilators.get(0);
        Double dist = defibrilators.get(0).distance(lat,lon);
        for (Defibrilator defibrilator : defibrilators) {
            if (dist > defibrilator.distance(lat,lon)){
                ret = defibrilator;
                dist = defibrilator.distance(lat,lon);
            }
        }

        return ret;
    }


}

class Defibrilator {

    private int no;
    private String name;
    private String address;
    private Double LON;
    private Double LAT;

    public Defibrilator(String DEFIB) {
        String[] split = DEFIB.split(";");

        this.no = Integer.parseInt(split[0]);
        this.name = split[1];
        this.address = split[2];
        this.LON = Double.parseDouble(split[4].replaceAll(",", "."));
        this.LAT = Double.parseDouble(split[5].replaceAll(",", "."));
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLON() {
        return LON;
    }

    public void setLON(Double LON) {
        this.LON = LON;
    }

    public Double getLAT() {
        return LAT;
    }

    public void setLAT(Double LAT) {
        this.LAT = LAT;
    }

    public Double distance(Double latB, Double lonB){
        double x = (lonB - this.LON) * Math.cos((this.LAT + latB) / 2);
        double y = latB - this.LAT;
        return Math.sqrt(x*x + y*y)*6371;
    }
}