
// Class that represents the location (x,y) of a pel. The location is the key attribute of a pel
public class Location {

    // instance variables that represent the x and y coordinate of a pel
    private int x_coordinate;
    private int y_coordinate;

    // Constructor that initializes instance variables
    public Location(int x, int y) {

        this.x_coordinate = x;
        this.y_coordinate = y;

    }

    // getter method that returns the x-coordinate of a pel
    public int getx() {
        return this.x_coordinate;
    }

    // getter method that returns the y-coordinate of a pel
    public int gety() {
        return this.y_coordinate;
    }

    // method that compares two location objects, this will be used
    // to compare which key attribute is larger or smaller to determine
    // where to put the pel object in the binary search tree
    public int compareTo(Location p) {

        if ((this.gety() > p.gety()) || ((this.gety() == p.gety()) && (this.getx() > p.getx())))
            return 1;
        else if ((this.getx() == p.getx()) && (this.gety() == p.gety()))
            return 0;
        else if ((this.gety() < p.gety()) || ((this.gety() == p.gety()) && (this.getx() < p.getx())))
            return -1;
        else
            return -1;

    }

}