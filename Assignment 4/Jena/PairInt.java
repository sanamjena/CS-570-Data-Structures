package Maze;

public class PairInt {

    private int x;
    private int y;

    public PairInt(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //get x coordinate
    public int getX() {
        return x;
    }

    //get y coordinate
    public int getY() {
        return y;
    }

    //set x coordinate
    public void setX(int x) {
        this.x = x;
    }

    //set y coordinate
    public void setY(int y) {
        this.y = y;
    }

    //check to see if PairInt p has same x, y as x y as object
    public boolean equals(PairInt p) {
        if (p.getX() == x && p.getY() == y) {
            return true;
        }
        return false;
    }

    //toString method prints out in form (x, y)
    public String toString() {
        String a = "(" + x + ", " + y + ")";
        return a;
    }

    //makes a copy of PairInt object
    public PairInt copy() {
        PairInt copy = new PairInt(x, y);
        return copy;
    }
}