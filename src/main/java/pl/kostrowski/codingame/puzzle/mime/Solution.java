package pl.kostrowski.codingame.puzzle.mime;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {
    private static Map<String, String> mimeType = new HashMap<>();

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // Number of elements which make up the association table.
        int Q = in.nextInt(); // Number Q of file names to be analyzed.
        for (int i = 0; i < N; i++) {
            String EXT = in.next(); // file extension
            String MT = in.next(); // MIME type.
            mimeType.put(EXT.toUpperCase(), MT);
        }
        in.nextLine();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            String FNAME = in.nextLine(); // One file name per line.
            System.err.println("FileName: " + FNAME);
            String[] split = FNAME.split("\\.");
//            System.err.println("Split Lenght: " + split.length);
            if (FNAME.substring(FNAME.length() - 1).equals(".")) {
                System.err.println("koniec na kropke");
                sb.append("UNKNOWN");
            } else if (split.length == 1) {
                sb.append("UNKNOWN");
            } else {
                String fileType = split[split.length - 1].toUpperCase();
                System.err.println("Filetype: " + fileType);
                if (mimeType.containsKey(fileType)) {
                    sb.append(mimeType.get(fileType));
                } else {
                    sb.append("UNKNOWN");
                }
            }
            if (i < Q - 1) {
                sb.append("\n");
            }
        }

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        // For each of the Q filenames, display on a line the corresponding MIME type. If there is no corresponding type, then display UNKNOWN.
        System.out.println(sb.toString());
    }
}