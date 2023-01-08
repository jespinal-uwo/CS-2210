// this class represents the nodes of the binary search tree
// each node will store an object of the class Pel 
public class BNode {

    // instance variables of the node,
    // Pel object is the data item while leftNode, rightNode, and parentNode
    // are the references to the node's left child, right child, and its parent
    private Pel pixel;
    private BNode leftNode, rightNode, parentNode;

    // constructor that initializes instance variables to the specified values
    public BNode(Pel value, BNode left, BNode right, BNode parent) {
        this.pixel = value;
        this.leftNode = left;
        this.rightNode = right;
        this.parentNode = parent;
    }

    // constructor that initializes a leaf node
    public BNode() {
        this.pixel = null;
        this.leftNode = null;
        this.rightNode = null;
        this.parentNode = null;
    }

    // getter method that returns the parent node
    public BNode parent() {
        return this.parentNode;
    }

    // setter method that sets the parent node of this node
    public void setParent(BNode newParent) {
        this.parentNode = newParent;
    }

    // setter method that sets the left child of this node
    public void setLeftChild(BNode p) {
        this.leftNode = p;
    }

    // setter method that sets the right child of this node
    public void setRightChild(BNode p) {
        this.rightNode = p;
    }

    // setter method that sets the data attribute of this node
    public void setContent(Pel value) {
        this.pixel = value;
    }

    // method that checks if this node is a leaf, if so returns true
    // otherwise, returns false
    public Boolean isLeaf() {
        if (this.leftNode == null && this.rightNode == null)
            return true;
        else
            return false;
    }

    // getter method that returns the data attribute
    public Pel getData() {
        return this.pixel;
    }

    // getter method that returns the left child of the node
    public BNode leftChild() {
        return this.leftNode;
    }

    // getter method that returns the right child of the node
    public BNode rightChild() {
        return this.rightNode;
    }

}
