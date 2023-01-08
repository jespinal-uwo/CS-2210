// this class implements an ordered dictionary using a binary search tree
// each node of the tree will store a Pel object; the attribute 
// Location of the Pel ojbect stored in a node will be its key attribute
public class BinarySearchTree implements BinarySearchTreeADT {

    private BNode root;

    // Constructor that creates a tree whose root is a leaf node
    public BinarySearchTree() {
        this.root = new BNode();
    }

    // returns the Pel object storing the given key, if the key is stored in the
    // tree
    // returns null otherwise

    public Pel get(BNode r, Location key) {
        if (r.isLeaf()) // if the algorithm reaches a leaf node, then the Pel Object storing the key
                        // doesn't exist
            return null;
        else {
            if ((r.getData().getLocus().compareTo(key)) == 0) // found the Pel Object
                return r.getData();
            // if the key is less than the key stored in the node, the algorithm invokes
            // itself on
            // the left child of the node, othewise it invokes itself on the right child of
            // the node
            else if ((key.compareTo(r.getData().getLocus())) == -1)
                return get(r.leftChild(), key);
            else
                return get(r.rightChild(), key);

        }
    }

    // inserts newData in the tree if no data item with the same key is already
    // there;
    // if a node alrady stores the same key, the algorithm throws a
    // DuplicatedKeyException
    public void put(BNode r, Pel newData) throws DuplicatedKeyException {
        BNode pointer, newNode1, newNode2;

        // pointer to the leaf node where the new data item is supposed to go
        pointer = getNode(r, newData.getLocus());

        // if the pointer points to an internal node, then the key alrady exists in the
        // tree
        if (!pointer.isLeaf())
            throw new DuplicatedKeyException("Key is already in the tree");
        else {

            // creates the new leaf nodes that will be the left child and the right child
            newNode1 = new BNode();
            newNode2 = new BNode();

            pointer.setContent(newData); // stores the new data item into the new node
            pointer.setLeftChild(newNode1); // sets the children of the new node
            pointer.setRightChild(newNode2);
            pointer.leftChild().setParent(pointer); // sets the parent of the children to be the new node
            pointer.rightChild().setParent(pointer);
        }

    }

    // if the key is stores in the tree, the algorithm removes the data item with
    // the given key;
    // throws an InexistentKeyException otherwise
    public void remove(BNode r, Location key) throws InexistentKeyException {
        BNode pointer, pointerPrime;

        // finds the node to remove
        pointer = getNode(r, key);

        // if the node to remove is a leaf node, then the data item with the given key
        // does not exist in the tree
        if (pointer.isLeaf())
            throw new InexistentKeyException("Inexistent Key");

        else {

            // if one of the children of the node to be removed is a leaf, then make
            // the parent point to the other child, therbey deleting the node with the given
            // key
            if (pointer.leftChild().isLeaf() || pointer.rightChild().isLeaf()) {

                BNode otherChild;

                // Finds the child that is not the leaf node
                if (pointer.leftChild().isLeaf())
                    otherChild = pointer.rightChild();
                else
                    otherChild = pointer.leftChild();

                // pointer to the parent of the node to be removed
                pointerPrime = pointer.parent();

                // Makes the parent of the node to be removed point to the other child
                if (pointerPrime != null) {

                    if (pointerPrime.leftChild().getData().getLocus().compareTo(pointer.getData().getLocus()) == 0)
                        pointerPrime.setLeftChild(otherChild);
                    else
                        pointerPrime.setRightChild(otherChild);
                }

                // if the node to be removed is the root node, then make the other child the
                // root of the tree
                else {
                    this.root = otherChild;
                }

            }

            // if both children are internal nodes, then replace the node with the successor
            // and delete the
            // node which was the successor
            else {

                BNode smallestNodePointer;

                try {
                    // finds the successor node
                    smallestNodePointer = smallestNode(pointer.rightChild());
                    // replaces the node with the contents of the succesor node
                    pointer.setContent(smallestNodePointer.getData());

                    // removes the succesor node
                    remove(smallestNodePointer, smallestNodePointer.getData().getLocus());
                } catch (EmptyTreeException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

        }
    }

    // Returns the Pel object with the smallest key larger than the given one.
    // Returns null if the given key has no successor
    public Pel successor(BNode r, Location key) {

        // if the tree is empty then the tree has no successsor
        if (r.isLeaf())
            return null;
        else {

            // finds the node storing the given key
            BNode pointer = getNode(r, key);

            // if the node storing the given key is internal, then the successor
            // is the smallest node from the right-subtree
            if ((!pointer.isLeaf()) && (!pointer.rightChild().isLeaf())) {
                try {
                    return smallest(pointer.rightChild());
                } catch (EmptyTreeException e) {
                    e.printStackTrace();
                    return r.getData();
                }
            }

            // if the node storing the given key is not in the tree, then the algorithm
            // traverses up the tree until it finds the successor
            else {
                pointer = pointer.parent();

                // traverses up the tree until it finds the successor
                while (pointer != null && pointer.getData().getLocus().compareTo(key) == -1) {
                    pointer = pointer.parent();
                }

                // if the algorithm cannot find the successor then the key is the largest in the
                // tree
                if (pointer == null)
                    return null;
                else
                    return pointer.getData();

            }

        }

    }

    // Returns the Pel object with the largesst key smaller than the given one.
    // Returns null if the given key has no successor
    public Pel predecessor(BNode r, Location key) {

        // if the tree is empty then the tree has no predecessor
        if (r.isLeaf())
            return null;
        else {
            // finds the node storing the given key
            BNode pointer = getNode(r, key);

            // if the node storing the given key is internal, then the predecessor
            // is the largest node from the left-subtree
            if ((!pointer.isLeaf()) && (!pointer.leftChild().isLeaf())) {
                try {
                    return largest(pointer.leftChild());
                } catch (EmptyTreeException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return r.getData(); // error, don't need this
                }
            }

            // if the node storing the given key is not in the tree, then the algorithm
            // traverses up the tree until it finds the predecessor
            else {
                pointer = pointer.parent();

                // traverses up the tree until it finds the predecessor
                while (pointer != null && pointer.getData().getLocus().compareTo(key) == 1) {
                    pointer = pointer.parent();
                }

                // if the algorithm cannot find the successor then the key is the smallest in
                // the tree
                if (pointer == null)
                    return null;
                else
                    return pointer.getData();

            }

        }

    }

    // returns the root of the tree

    public BNode getRoot() {
        return this.root;
    }

    // Returns the Pel object in the tree with the smallest key. Throws an
    // EmptyTreeException if the tree
    // does not contain any data
    public Pel smallest(BNode r) throws EmptyTreeException {

        BNode pointer;

        // if the root is a leaf then the tree is empty
        if (r.isLeaf())
            throw new EmptyTreeException("Empty tree");
        else {
            pointer = r;

            // traverses the left subtree until the algorithm reaches the last level of
            // internal nodes,
            // the node is the node with the smallest key
            while (!pointer.isLeaf()) {
                pointer = pointer.leftChild();
            }

            // returns the node with the smallest key
            return pointer.parent().getData();

        }
    }

    // Returns the Pel object in the tree with the largest key. Throws an
    // EmptyTreeException if the tree
    // does not contain any data
    public Pel largest(BNode r) throws EmptyTreeException {
        BNode pointer;

        // if the root is a leaf then the tree is empty
        if (r.isLeaf())
            throw new EmptyTreeException("Empty tree");
        else {
            pointer = r;

            // traverses the right subtree until the algorithm reaches the last level of
            // internal nodes,
            // the node is the node with the largest key
            while (!pointer.isLeaf()) {
                pointer = pointer.rightChild();
            }

            // returns the node with the largest key
            return pointer.parent().getData();

        }
    }

    // private helper method that behaves exactly the same as smallest method,
    // except it returns the node
    // storing the Pel object instead of returning the Pel object itself
    private BNode smallestNode(BNode r) throws EmptyTreeException {

        BNode pointer;

        if (r.isLeaf())
            throw new EmptyTreeException("Empty tree");
        else {
            pointer = r;

            while (!pointer.isLeaf()) {
                pointer = pointer.leftChild();
            }

            return pointer.parent();

        }
    }

    // private helper method that behaves exactly the same as get method, except it
    // returns the node
    // storing the Pel object instead of returning the Pel object itself
    private BNode getNode(BNode r, Location key) {
        if (r.isLeaf())
            return r;
        else {
            if ((r.getData().getLocus().compareTo(key)) == 0)
                return r;
            else if ((key.compareTo(r.getData().getLocus())) == -1)
                return getNode(r.leftChild(), key);
            else
                return getNode(r.rightChild(), key);

        }
    }
}
