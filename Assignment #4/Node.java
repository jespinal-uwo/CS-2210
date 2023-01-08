
// This class represents a node of the graph 
// each node contains a unique i.d and a boolean instance variable
// to indiciate if it's marked or not - this is useful to indicate that each
// node won't be processed twice in the graph
public class Node {

    private int num;
    private boolean marked;

    // constructor that initializes the i.d of the node
    public Node(int id) {
        this.num = id;
    }

    // setter method
    public void markNode(boolean mark) {
        this.marked = mark;
    }

    // getter methods that return the id of the node and whether the node is marked
    // or not
    public boolean getMark() {
        return this.marked;

    }

    public int getId() {
        return this.num;
    }

}