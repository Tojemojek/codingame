import java.util.Scanner;

/**
 * Don't let the machines win. You are humanity's last hope...
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int width = in.nextInt(); // the number of cells on the X axis
        int height = in.nextInt(); // the number of cells on the Y axis
        boolean[][] isNode = new boolean[width + 1][height + 1];

        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < height; i++) {
            String line = in.nextLine(); // width characters, each either 0 or .
            System.err.println(line);
            for (int j = 0; j < line.length(); j++) {
                isNode[j][i] = line.substring(j, j + 1).equals("0");
                System.err.print("Node: " + j + ":" + i + " " + isNode[j][i] + " ");
            }
            System.err.println(" ");
        }

        boolean right = false;
        boolean bottom = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (isNode[j][i]) {
                    System.err.println("Badam " + j + "," + i);
                    sb.append(j + " " + i + " ");
                    int k = j;
                    while (k < width) {
                        if (isNode[k + 1][i]) {
                            sb.append((k + 1) + " " + i + " ");
                            right = true;
                            break;
                        }
                        k++;
                    }
                    if (!right) {
                        sb.append("-1 -1 ");
                    }
                    right = false;
                    int l = i;
                    while (l < height) {
                        if (isNode[j][l + 1]) {
                            sb.append(j + " " + (l + 1) + "\n");
                            bottom = true;
                            break;
                        }
                        l++;
                    }
                    if (!bottom) {
                        sb.append("-1 -1\n");
                    }
                    bottom = false;
                }
            }
        }

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");


        // Three coordinates: a node, its right neighbor, its bottom neighbor
        System.out.println(sb.toString());
    }
}
