// Class that represents the data items to be stored in the nodes of the binary search tree
public class Pel {

    // instance variables of the data item,
    // Location object is the key attribute of the data item
    private Location position;
    private int colour;

    // constructor that initializes instance variables
    public Pel(Location p, int color) {
        this.position = p;
        this.colour = color;
    }

    // getter method that returns the key attribute of the data item
    public Location getLocus() {
        return this.position;
    }

    // getter method that returns the colour of the data item
    public int getColor() {
        return this.colour;
    }

}
