// class that represents an object on screen
public class MyObject implements MyObjectADT {

    // instance variables of the object. Each object has an unique identifier, and a
    // width + height
    // of its enclosing rectangle. Then locus of the object is the upper left most
    // coordinate
    // of this enclosing rectangle. Each object belongs to a type.

    // Each object contains a set of Pel objects which will be stored in a binary
    // search tree
    private int uniqueIdentifier, widthRectangle, heightRectangle;
    private String type_of_object;
    private Location locus;
    private BinarySearchTree binarySearchTree;

    // constructor that initializes attributes of an object
    public MyObject(int id, int width, int height, String type, Location pos) {
        this.uniqueIdentifier = id;
        this.widthRectangle = width;
        this.heightRectangle = height;
        this.type_of_object = type;
        this.locus = pos;

        binarySearchTree = new BinarySearchTree(); // creates an empty binary search tree
    }

    // setter method that sets the type of the object
    public void setType(String type) {
        this.type_of_object = type;
    }

    // getter methods that return the attributes of the object

    public int getWidth() {
        return this.widthRectangle;
    }

    public int getHeight() {
        return this.heightRectangle;
    }

    public String getType() {
        return this.type_of_object;
    }

    public int getId() {
        return this.uniqueIdentifier;
    }

    public Location getLocus() {
        return this.locus;
    }

    // setter method that sets the locus of the object
    public void setLocus(Location value) {
        this.locus = value;
    }

    // inserts Pel object into the binary search tree of this object.
    // throws DuplicatedKeyException if an error occurs when inserting the Pel
    // object into the tree
    public void addPel(Pel pix) throws DuplicatedKeyException {

        BNode root = this.binarySearchTree.getRoot();

        this.binarySearchTree.put(root, pix);
    }

    // Returns true if this object intersects with the one specified in the
    // parameter.
    // Returns false otherwise
    public boolean intersects(MyObject otherObject) {

        try {

            Pel pointer;

            int xPrime, yPrime;
            Location x_y_prime;

            int locus_this_object_x = this.locus.getx();
            int locus_this_object_y = this.locus.gety();

            int locus_other_object_x = otherObject.locus.getx();
            int locus_other_object_y = otherObject.locus.gety();

            BNode root_other_object = otherObject.getBinarySearchTree().getRoot();
            BNode root_this_object = this.getBinarySearchTree().getRoot();

            // pointer that initially points to the smallest node in the binary search tree
            pointer = this.getBinarySearchTree().smallest(root_this_object);

            // traverses through the binary search tree
            while (pointer != null) {
                int data_this_object_x = pointer.getLocus().getx();
                int data_this_object_y = pointer.getLocus().gety();

                // Formula that computes a new key, if the there is a node in both binary search
                // trees
                // of both objects that contain this new key, then the object intersects
                xPrime = data_this_object_x + locus_this_object_x - locus_other_object_x;
                yPrime = data_this_object_y + locus_this_object_y - locus_other_object_y;

                x_y_prime = new Location(xPrime, yPrime);

                // checks to see if there is a node in both binary search trees
                // of both objects that contain this new key, returning true if it is found
                if (otherObject.getBinarySearchTree().get(root_other_object, x_y_prime) != null)
                    return true;

                // traverses through the tree by repeadetly calling the successor
                pointer = this.getBinarySearchTree().successor(root_this_object, pointer.getLocus());

            }

            return false;

        } catch (Exception e) {
            return false;
        }
    }

    // private helper method that returns the binary search tree which contains all
    // the Pel objects of this object.
    private BinarySearchTree getBinarySearchTree() {
        return this.binarySearchTree;
    }

}
