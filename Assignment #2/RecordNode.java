
public class RecordNode {
    private RecordNode next;
    private Record element;

    // Creates an empty node
    public RecordNode() {
        next = null;
        element = null;
    }

    // Creates a node storing a Record object
    public RecordNode(Record elem) {
        next = null;
        element = elem;
    }

    // Returns the next node
    public RecordNode getNext() {
        return next;
    }

    // Sets the next node
    public void setNext(RecordNode node) {
        next = node;
    }

    // Returns the Record object stored in the node
    public Record getElement() {
        return element;
    }

    // Sets the Record object inside this node
    public void setElement(Record elem) {
        element = elem;
    }
}
