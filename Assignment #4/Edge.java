// this class represents an edge of the graph, each edge contains two node endpoints which are connected
// by the edge itself
public class Edge {

    // private instance variables
    private Node u_Node;
    private Node v_Node;

    private String road_type;

    // constructor of the class, the first two parameters are the endpoints of the
    // edge
    // the last parameter reprsents the road type of the edge
    // roads can be: public, private, or construction
    public Edge(Node u, Node v, String type) {
        this.u_Node = u;
        this.v_Node = v;

        this.road_type = type;
    }

    // getter methods that return the first node, second node, and road type of the
    // edge respectively
    public Node firstNode() {
        return this.u_Node;
    }

    public Node secondNode() {
        return this.v_Node;
    }

    public String getType() {
        return this.road_type;
    }

}
