import java.io.FileReader;
import java.io.BufferedReader;
import java.util.*;
import java.util.stream.Collectors;

public class Euler67 {

    // I think this can be solved with dynamic programming.
    // The best route of all routes, is the best route out of the bottom row.
    // The best route of a given node, is the best route of the two above it. 
    // So if we calculate the best route to each of the bottom nodes, we can
    // pick the one with the biggest value


    public static void main(String... args) {
        String file = readInput("input.txt");

        List<String> lines = Arrays.asList(file.split("\n"));
        Pyramid pyramid = new Pyramid(lines);

        System.out.println(pyramid);
        System.out.println(pyramid.findBestRouteToNode(14, 0));
        System.out.println(pyramid.findBestRouteToNode(14, 14));
        System.out.println(pyramid.findBestRouteToNode(14, 7));

        System.out.println("\n-- Now, the best routes to the bottom rows! --\n");

        int lastBestValue = 0;
        List<Node> bestRoute = null;
        for(int i = 0; i < pyramid.getNumberOfRows(); i++) {
            List<Node> route = pyramid.findBestRouteToNode(pyramid.getNumberOfRows() - 1, i);
            int sum = pyramid.sumOfValuesInRoute(route);

            System.out.println("Best Route for Value " + i + " on row " + pyramid.getNumberOfRows() + ": " + route);
            System.out.println("Value: " + sum + "\n");

            if (sum > lastBestValue) {
                lastBestValue = sum;
                bestRoute = route;
            }
        }

        System.out.println("\n--- And the best route of all is... ---\n");
        System.out.println("Best Route: " + bestRoute);
        System.out.println("Value: " + lastBestValue + "\n");

    }


    private static String readInput(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }

            return sb.toString();
        } catch (Exception e) {
            System.out.println("It went bang: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private static class Pyramid {

        private final List<List<Node>> nodes;
        private final Map<Node, List<Node>> bestRoutes;

        public Pyramid(List<String> lines) {
            this.nodes = new ArrayList<>();
            this.bestRoutes = new HashMap<>();

            for (int rowNumber = 0; rowNumber < lines.size(); rowNumber++) {
                String line = lines.get(rowNumber);

                List<String> valuesAsStrings = Arrays.asList(line.split(" "));
                List<Integer> nodeValues = valuesAsStrings.stream().map(Integer::parseInt).toList();

                List<Node> nodesInRow = new ArrayList<>();
                for (int indexInRow = 0; indexInRow < nodeValues.size(); indexInRow++) {
                    Node upperLeft;
                    Node upperRight;

                    if (indexInRow == 0) {
                        upperLeft = null;
                    } else {
                        upperLeft = nodes.get(rowNumber - 1).get(indexInRow - 1);
                    }

                    if (indexInRow == nodeValues.size() - 1) {
                        upperRight = null;
                    } else {
                        upperRight = nodes.get(rowNumber - 1).get(indexInRow);
                    }

                    Node node = new Node(upperLeft, upperRight, nodeValues.get(indexInRow));
                    nodesInRow.add(node);
                }

                nodes.add(nodesInRow);
                if (rowNumber == 0) {
                    bestRoutes.put(nodesInRow.getFirst(), nodesInRow);
                }
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            final int maxDigitLength = 2;
            String gap = " ".repeat(maxDigitLength);

            for (int i = 0; i < nodes.size(); i++) {
                int pyramidBaseWidth = (nodes.size() + (nodes.size() - 1)) * maxDigitLength;
                String unpaddedLine = nodes.get(i).stream().map(String::valueOf).collect(Collectors.joining(gap));
                String padding = gap.repeat((pyramidBaseWidth - unpaddedLine.length()) / (2 * maxDigitLength));
                sb.append(padding);
                sb.append(unpaddedLine);
                sb.append(padding);
                sb.append("\n");
            }

            return sb.toString();
        }

        public List<Node> findBestRouteToNode(int row, int indexInRow) {
            Node selectedNode = nodes.get(row).get(indexInRow);
            List<Node> cachedRoute = bestRoutes.get(selectedNode);

            if (cachedRoute != null) {
                return cachedRoute;
            } else {
                if (indexInRow == 0) {
                    List<Node> newBestRoute = new ArrayList<>(findBestRouteToNode(row - 1, indexInRow));
                    newBestRoute.add(selectedNode);
                    bestRoutes.put(selectedNode, newBestRoute);
                    return newBestRoute;
                } else if (indexInRow == nodes.get(row).size() - 1) {
                    List<Node> newBestRoute = new ArrayList<>(findBestRouteToNode(row - 1, indexInRow - 1));
                    newBestRoute.add(selectedNode);
                    bestRoutes.put(selectedNode, newBestRoute);
                    return newBestRoute;
                } else {
                    int bestUpperLeft = sumOfValuesInRoute(findBestRouteToNode(row - 1, indexInRow - 1)) + selectedNode.getValue();
                    int bestUpperRight = sumOfValuesInRoute(findBestRouteToNode(row - 1, indexInRow)) + selectedNode.getValue();

                    if(bestUpperLeft >= bestUpperRight) {
                        List<Node> newBestRoute = new ArrayList<>(findBestRouteToNode(row - 1, indexInRow - 1));
                        newBestRoute.add(selectedNode);
                        bestRoutes.put(selectedNode, newBestRoute);
                        return newBestRoute;
                    } else {
                        List<Node> newBestRoute = new ArrayList<>(findBestRouteToNode(row - 1, indexInRow));
                        newBestRoute.add(selectedNode);
                        bestRoutes.put(selectedNode, newBestRoute);
                        return newBestRoute;
                    }
                }
            }
        }

        public int getNumberOfRows() {
            return nodes.size();
        }

        public int sumOfValuesInRoute(List<Node> route) {
            int sum = 0;

            for (Node node : route) {
                sum += node.value;
            }

            return sum;
        }
    }


    private static class Node {

        private static int numberProduced = 0;
        private final String id;
        private final Node upperLeft;
        private final Node upperRight;
        private final int value;

        public Node(Node upperLeft, Node upperRight, int value) {
            this.id = "Node-" + numberProduced++;
            this.upperLeft = upperLeft;
            this.upperRight = upperRight;
            this.value = value;
        }

        public String getId() {
            return id;
        }

        public Node getUpperLeft() {
            return upperLeft;
        }

        public Node getUpperRight() {
            return upperRight;
        }

        public int getValue() {
            return value;
        }

        @Override
        public String toString() {
            if (value < 10) {
                return "0" + value;
            }

            return value + "";
        }
    }
}