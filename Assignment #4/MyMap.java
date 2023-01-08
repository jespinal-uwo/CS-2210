import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.*;

// This class represents the roadmap.
// A graph will be used to store the map and to try to find a path from the 
// starting point to the destination satisfying the conditions specified by an input file

public class MyMap {

    private int starting_Node_ID, end_Node_ID, numPrivateRoads, numConstructionRoads;
    private Graph graph;
    private Stack<Node> stack; // a stack will be used to store all the nodes that lead from the starting node
                               // to the destination node

    // Constructor for building a graph from the input file specified in the
    // parameter; this graph represents the roadmap.
    // If the input file does not exist, this method should throw a MapException.
    MyMap(String inputFile) throws MapException {

        try {

            // initializes the stack
            this.stack = new Stack<Node>();

            int width, length, numOfNodes, start, tmpStart;

            boolean odd = true;

            // opens the input file so it's ready to be processed
            BufferedReader input = new BufferedReader(new FileReader(inputFile));

            // skips the first line of the input file, which is the scale of the map
            input.readLine();

            // Reads the start id, end id, width, and length from the input file and stores
            // them
            // into their respective variables
            this.starting_Node_ID = Integer.parseInt(input.readLine());
            this.end_Node_ID = Integer.parseInt(input.readLine());
            width = Integer.parseInt(input.readLine());
            length = Integer.parseInt(input.readLine());

            // Reads the number of private roads and the number of construction roads and
            // stores
            // them into their respective instance variables
            this.numPrivateRoads = Integer.parseInt(input.readLine());
            this.numConstructionRoads = Integer.parseInt(input.readLine());

            // The number of nodes in the graph is calculated by the width * length of the
            // roadmap
            numOfNodes = width * length;

            // creates a graph with numOfNodes nodes and no edges between them
            this.graph = new Graph(numOfNodes);

            start = 0;

            String str;

            // algorithm that reads the rest of the input file until there are no more
            // entries left
            // the rest of the input file specifies the connection (edges) between nodes of
            // the graph
            while ((str = input.readLine()) != null) {

                // stops reading if it finds an empty string in the file
                if (str.equals(""))
                    break;

                // algorithm alternates between reading odd entries and even entries of the
                // input file
                if (odd) {

                    tmpStart = start;

                    // for every line that is an odd entry
                    // it reads every second character starting at the 2nd character
                    // that corresponds to the type of road
                    // or edge and connects that edge between 2 endpoints nodes
                    for (int i = 1; i < str.length(); i = i + 2) {

                        if (str.charAt(i) == 'P')
                            graph.addEdge(graph.getNode(tmpStart), graph.getNode(tmpStart + 1), "public");
                        if (str.charAt(i) == 'V')
                            graph.addEdge(graph.getNode(tmpStart), graph.getNode(tmpStart + 1), "private");
                        if (str.charAt(i) == 'C')
                            graph.addEdge(graph.getNode(tmpStart), graph.getNode(tmpStart + 1), "construction");

                        tmpStart++;
                    }

                    odd = false;
                }

                // algorithm alternates between reading odd entries and even entries of the
                // input file
                else if (!odd) {

                    tmpStart = start;

                    // for every line that is an even entry
                    // it reads every second character starting at the 1st character
                    // that corresponds to the type of road
                    // or edge and connects that edge between 2 endpoints nodes
                    for (int i = 0; i < str.length(); i = i + 2) {

                        if (str.charAt(i) == 'P')
                            graph.addEdge(graph.getNode(tmpStart), graph.getNode(tmpStart + width), "public");
                        if (str.charAt(i) == 'V')
                            graph.addEdge(graph.getNode(tmpStart), graph.getNode((tmpStart + width)), "private");
                        if (str.charAt(i) == 'C')
                            graph.addEdge(graph.getNode(tmpStart), graph.getNode((tmpStart + width)), "construction");

                        tmpStart++;
                    }

                    odd = true;

                    start = start + width;
                }

            }

            // safely closes the input file so it is not accidentally read again
            input.close();

            // throws a map exception if the input file doesn't exist
        } catch (IOException e) {
            throw new MapException("Error! Input file doesn't exist");
        } catch (GraphException e) {
            e.printStackTrace();
        }

    }

    // getter methods that return the graph, starting node ID, ending node ID, and
    // maximum private roads, and maximum construction roads respectively
    public Graph getGraph() {
        return this.graph;
    }

    public int getStartingNode() {
        return this.starting_Node_ID;
    }

    public int getDestinationNode() {
        return this.end_Node_ID;
    }

    public int maxPrivateRoads() {
        return this.numPrivateRoads;
    }

    public int maxConstructionRoads() {
        return this.numConstructionRoads;
    }

    // Returns a Java Iterator containing the nodes of a path from the start node to
    // the destination node
    // such that the path uses at most maxPrivate private roads and maxConstruction
    // construction roads
    // if the path does not exist, this method returns the value null.
    public Iterator findPath(int start, int destination, int maxPrivate, int maxConstruction) throws GraphException {

        if (path(start, destination, maxPrivate, maxConstruction)) {
            Iterator<Node> it = this.stack.iterator(); // stack contains the path storing all the nodes that
            return it; // start at the starting node and ends at the
        } // destination node

        else {
            return null;
        }

    }

    // private helper method that returns true if a path exists from the starting
    // node to the destination
    // node that uses at most maxPrivate roads and maxConstruction roads
    private boolean path(int start, int destination, int maxPrivate, int maxConstruction) throws GraphException {

        // Create start and destination nodes
        Node startNode = this.graph.getNode(start);
        Node destinationNode = this.graph.getNode(destination);

        // marks the starting node so that it is not processed again
        startNode.markNode(true);
        this.stack.push(startNode); // pushes the node onto the stack

        // if both starting and destination nodes are equal then a path exists
        if (startNode.getId() == destinationNode.getId())
            return true;

        else {

            // iterator containing all edges incident on the starting node
            Iterator<Edge> edgeIterator = this.graph.incidentEdges(startNode);

            // processes all edges incident on the starting node
            while (edgeIterator.hasNext()) {

                Edge edge = edgeIterator.next();

                // Gets the other endpoint of the edge incident on the starting node
                Node u = edge.secondNode();

                // only processes the nodes which have not been marked in the graph
                if (u.getMark() != true) {

                    // Checks to see if the edge is a construction road
                    if (edge.getType().equals("construction")) {

                        // algorithm recursively calls itself on the other endpoint node of the edge if
                        // the maximum
                        // number of construction roads have NOT been used
                        if (maxConstruction != 0) {
                            if (path(u.getId(), destination, maxPrivate, maxConstruction - 1) == true)
                                return true;
                        }

                    }

                    // Checks to see if the edge is a private road
                    else if (edge.getType().equals("private")) {

                        // algorithm recursively calls itself on the other endpoint node of the edge if
                        // the maximum
                        // number of private roads have NOT been used
                        if (maxPrivate != 0) {
                            if (path(u.getId(), destination, maxPrivate - 1, maxConstruction) == true)
                                return true;
                        }

                    }

                    // the edge then has to be a public raod
                    else {

                        // algorithm recursively calls itself on the other endpoint node of the edge
                        if (path(u.getId(), destination, maxPrivate, maxConstruction) == true)
                            return true;

                    }

                }

            }

            // If the algorithm hasn't returned true at this stage, then the
            // current node is not part of the path to the destination node
            this.stack.pop();
            // unmarks the current node so that it may be processed again
            startNode.markNode(false);
            return false;

        }

    }

}
