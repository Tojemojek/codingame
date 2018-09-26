package pl.kostrowski.codingame.puzzle.skynetRevolution;

import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    private static List<Integer> gateways = new LinkedList<>();
    private static Map<Integer, Node> nodes = new HashMap<>();
    private static Node toBlock = null;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // the total number of nodes in the level, including the gateways
        int L = in.nextInt(); // the number of links
        int E = in.nextInt(); // the number of exit gateways

        for (int i = 0; i < N; i++) {
            nodes.put(i, new Node(i));
        }

        for (int i = 0; i < L; i++) {
            int N1 = in.nextInt(); // N1 and N2 defines a link between these nodes
            int N2 = in.nextInt();

            nodes.get(N1).connections.add(N2);
            nodes.get(N2).connections.add(N1);

        }

        for (int i = 0; i < E; i++) {
            int EI = in.nextInt(); // the index of a gateway node
            gateways.add(EI);
        }


        // game loop
        while (true) {
            int SI = in.nextInt(); // The index of the node on which the Skynet agent is positioned this turn
            toBlock = nodes.get(gateways.get(0));

            if (nodeHasConnectionWithGateway(SI)) {
                System.out.println(SI + " " + toBlock.index);
                Integer remove = SI;
                toBlock.connections.remove(remove);
                nodes.get(SI).connections.remove(toBlock.index);
            } else {
                Iterator<Integer> iterator = toBlock.connections.iterator();
                Integer next = iterator.next();
                iterator.remove();
                System.out.println(next + " " + toBlock.index);
                toBlock.connections.remove(next);
                nodes.get(next).connections.remove(toBlock.index);
            }

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");


            // Example: 0 1 are the indices of the nodes you wish to sever the link between
            //System.out.println("0 1");
        }
    }

    private static boolean nodeHasConnectionWithGateway(int node) {
        Set<Integer> connections = nodes.get(node).connections;
        for (Integer connection : connections) {
            if (gateways.contains(connection)) {
                toBlock = nodes.get(connection);
                return true;
            }
        }
        return false;
    }

    static class Node {
        int index;
        Set<Integer> connections;

        public Node(int index) {
            this.index = index;
            this.connections = new HashSet<>();
        }
    }


}