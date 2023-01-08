import java.util.Iterator;
import java.util.ArrayList;

// This class represents an undirected graph, it uses an adjacency matrix as the
// data structure to represent the graph
public class Graph implements GraphADT {

    // private instance variables that store the vertices and the edges of the graph
    // in a
    // 1-dimensional and 2-dimensional array, respectively.
    private Node[] VerticesArray;
    private Edge[][] adjacencyMatrix;

    // Creates a graph with n nodes and no edges.
    public Graph(int n) {

        // space complexity of vertices array is O(n)
        // space complexity of adjacency matrix is O(n x n) or O(n^2)
        this.VerticesArray = new Node[n];
        this.adjacencyMatrix = new Edge[n][n];

        // Creates n nodes, ids of the nodes are 0, 1, ..., n-1
        for (int i = 0; i < n; i++) {
            this.VerticesArray[i] = new Node(i);
        }

        // since the graph have no edges, all elements in the adjacency matrix are set
        // to null to siginify
        // than edge between 2 nodes doesn't exist
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.adjacencyMatrix[i][j] = null;
            }
        }

    }

    // Adds an edge of the given ype connecting u and v
    // This method throws a GraphException if either node does not exist or if in
    // the
    // graph there is already an edge connecting the given nodes.
    public void addEdge(Node u, Node v, String edgeType) throws GraphException {

        // Throws exception if nodes u and v don't exist in the graph
        if (!nodesExist(u, v))
            throw new GraphException("Nodes u and v don't exist in the graph!");

        int row = u.getId();
        int col = v.getId();

        int row2 = v.getId();
        int col2 = u.getId();

        // if in the adjacency matrix there is already an entry corresponding to the
        // edge connecting u and v or v and u
        // then an edge already exists connecting u and v or v and u
        if (this.adjacencyMatrix[row][col] != null)
            throw new GraphException("There is already an edge connecting Nodes u and v");

        if (this.adjacencyMatrix[row2][col2] != null)
            throw new GraphException("There is already an edge connecting Nodes u and v");

        // creates edge object connecting u and v & v and u
        Edge edge = new Edge(u, v, edgeType);
        Edge edge2 = new Edge(v, u, edgeType);

        this.adjacencyMatrix[row][col] = edge;
        this.adjacencyMatrix[row2][col2] = edge2;

    }

    // Returns true if nodes u and v are adjacent; it returns false otherwise.
    public boolean areAdjacent(Node u, Node v) throws GraphException {

        // Throws exception if nodes u and v don't exist in the graph
        if (!nodesExist(u, v))
            throw new GraphException("Nodes u and v don't exist in the graph!");

        int row = u.getId();
        int col = v.getId();
        // two nodes are adjacent if there is an edge connecting the two nodes
        // thus if in the adjacency matrix there is an edge object that connects both
        // nodes
        // then nodes u and v are adjacent
        if (this.adjacencyMatrix[row][col] == null)
            return false;
        else
            return true;
    }

    // Returns a Java Iterator storing all the edges incident on node u.
    // It returns null if u does not have any edges incident on it.

    public Iterator incidentEdges(Node u) throws GraphException {

        // Throws exception if node u doesn't exist in the graph
        if (u.getId() >= this.VerticesArray.length)
            throw new GraphException("Node doesn't exist");

        int row = u.getId();

        // flag to check if there is an edge in the row of the adjaceny matrix
        boolean hasIncidentEdges = false;

        ArrayList<Edge> edgesArrayList = new ArrayList<Edge>();

        // Scans entire row of the adjacency matrix corresponding to the node u
        // all edges stored along this row are the edges incident on the node u
        for (int col = 0; col < this.VerticesArray.length; col++) {
            if (this.adjacencyMatrix[row][col] != null) {
                hasIncidentEdges = true;
                edgesArrayList.add(adjacencyMatrix[row][col]); // stores all edges incident on node u in an array list
            }

        }

        if (!hasIncidentEdges)
            return null;

        // creates an iterator storing all the edges incident on u
        Iterator<Edge> it = edgesArrayList.iterator();

        return it;

    }

    // Returns the edge connecting nodes u and v.
    // This method throws a GraphException if there is no edge between u and v.
    public Edge getEdge(Node u, Node v) throws GraphException {

        // Throws exception if nodes u and v don't exist in the graph
        if (!nodesExist(u, v))
            throw new GraphException("Nodes u and v don't exist in the graph!");

        int row = u.getId();
        int col = v.getId();
        // checks the adjacency matrix to see if there is an edge connecting nodes u and
        // v
        if (this.adjacencyMatrix[row][col] == null) {
            throw new GraphException("No edge between u and v");
        } else {
            return this.adjacencyMatrix[row][col];
        }

    }

    // Returns the node with the specified id. If no node with this id
    // the method should throw a GraphException.
    public Node getNode(int id) throws GraphException {

        // throws an exceptions if the node doesn't exist in the graph
        if (id >= this.VerticesArray.length)
            throw new GraphException("Node doesn't exist");

        else {
            return this.VerticesArray[id];
        }

    }

    // private helper method that returns false if either node u or node v don't
    // exist in the graph
    private boolean nodesExist(Node u, Node v) {

        boolean flag1 = true;
        boolean flag2 = true;

        if (u.getId() >= this.VerticesArray.length)
            flag1 = false;

        if (v.getId() >= this.VerticesArray.length)
            flag2 = false;

        if (flag1 && flag2)
            return true;
        else
            return false;

    }

}
