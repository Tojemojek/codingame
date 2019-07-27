
import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
// https://www.codingame.com/ide/puzzle/mayan-calculation
class Solution {
    private static final Logger log = new Logger();
    private static final Map<String, Mayan> intToMayan = new HashMap<>();
    private static final Map<Mayan, Integer> mayanToInt = new HashMap<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numberLenght = in.nextInt();
        int numberHeight = in.nextInt();

        log.log(numberLenght);
        log.log(numberHeight);

        String[] description = readMayanNumber(in, numberHeight);

        createMayanMaps(description, numberLenght, numberHeight);


        int fistNumberHeight = in.nextInt();
        log.log(fistNumberHeight);

        String[] firstNumber = readMayanNumber(in, fistNumberHeight);

        long first = readMayanValue(firstNumber, numberHeight, fistNumberHeight);

        int secondNumberHeight = in.nextInt();
        log.log(secondNumberHeight);

        String[] secondNumber = readMayanNumber(in, secondNumberHeight);

        long second = readMayanValue(secondNumber, numberHeight, secondNumberHeight);

        String operation = in.next();
        log.log(operation);

        long result = calculateValue(first,second, operation);
        log.log(result);
        String mayanResult = decodeMayanNumber(result);


        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(mayanResult);
    }

    private static String decodeMayanNumber(long result) {
        String s = Long.toString(result, 20);
        List<Mayan> mayans = new LinkedList<>();

        for (int i = 0; i < s.length(); i++){
            mayans.add(intToMayan.get(s.substring(i,i+1)));
        }

        StringBuilder sb = new StringBuilder();

        for (Mayan mayan : mayans) {
            sb.append(mayan);
        }

        return sb.toString();
    }

    private static long calculateValue(long first, long second, String operation) {
        switch( operation){
            case "+":
                return first + second;
            case "-":
                return Math.abs(first - second);
            case "*":
                return first * second;
            case "/":
                return first / second;
        }

        return 0;
    }

    private static long readMayanValue(String[] number, int mayanNumberHeight, int currentNumberHeigth) {

        List<Mayan> mayans = new LinkedList<>();

        for (int j = 0; j < currentNumberHeigth;) {
            ArrayList<String> strings = new ArrayList<>();
            for (int i = 0; i < mayanNumberHeight; i++){
                strings.add(number[j++]);
            }
            mayans.add(new Mayan(strings));
        }

        long pow = mayans.size()-1;
        long value = 0L;
        for (Mayan mayan : mayans) {
            value += mayanToInt.get(mayan) * Math.pow(20,pow);
            pow--;
        }

        return value;

    }

    private static void createMayanMaps(String[] description, int numberLenght, int numberHeight) {
        for (int i = 0; i < 20; i++) {
            Mayan tmpM = new Mayan();
            ArrayList<String> str = new ArrayList<>();
            for (int lineNumber = 0; lineNumber < numberHeight; lineNumber++) {
                str.add(description[lineNumber].substring(i * numberLenght, (i + 1) * numberLenght));
            }
            tmpM.setRepresentation(str);
            intToMayan.put(Integer.toString(i,20), tmpM);
            mayanToInt.put(tmpM, i);
        }
    }


    private static String[] readMayanNumber(Scanner in, int numberHeight) {
        String[] mayanNumber = new String[numberHeight];
        for (int i = 0; i < numberHeight; i++) {
            mayanNumber[i] = in.next();
        }
        log.log(mayanNumber);
        return mayanNumber;
    }
}

class Mayan {

    private ArrayList<String> representation;

    public Mayan(ArrayList<String> representation) {
        this.representation = representation;
    }

    public Mayan() {
        representation = new ArrayList<>();
    }

    public ArrayList<String> getRepresentation() {
        return representation;
    }

    public void setRepresentation(ArrayList<String> representation) {
        this.representation = representation;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();


        for (String string : representation) {
            sb.append(string).append("\n");
        }

        return sb.toString().substring(0, sb.length());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mayan mayan = (Mayan) o;
        ArrayList<String> representation = mayan.getRepresentation();

        if (this.representation.size() != representation.size()) {
            return false;
        }

        for (int i = 0; i < this.representation.size(); i++) {
            if (!this.representation.get(i).equals(representation.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(representation);
    }
}


class Logger {
    public void log(Object o) {
        System.err.println(o);
    }

    public void log(String[] strings) {
        for (String string : strings) {
            System.err.println(string);
        }

    }
}