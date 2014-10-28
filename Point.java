/*************************************************************************
 * Name: Nobel
 * Email: jwilove0049@163.com
 *
 * Compilation:  javac Point.java
 * Execution: java Point
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    // YOUR DEFINITION HERE
    public final Comparator<Point> SLOPE_ORDER = new SLOPEORDER();       

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    private class SLOPEORDER implements Comparator<Point> {
        public int compare(Point p, Point q) {
            double slope1 = slopeTo(p);
            double slope2 = slopeTo(q);
            if (slope1 < slope2) return -1;
            if (slope1 > slope2) return +1;
            return 0;
        }
    }
    
    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
        double slope;
        
        if ((that.x == this.x) && (that.y == this.y))
            return Double.NEGATIVE_INFINITY;
        else if (that.x == this.x)
            return Double.POSITIVE_INFINITY;
        else if (that.y == this.y)
            return 0.0;
        else
            slope = (double) ((that.y - this.y)/(double) (that.x - this.x));
        
        return slope;
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
        int compareValue = 0;
        if (this.y < that.y) 
            compareValue = -1;
        else if (this.y > that.y) 
            compareValue = 1;
        else if (this.y == that.y) {
            if (this.x < that.x)
                compareValue = -1;
            else if(this.x > that.x)
                compareValue = 1;
            else
                compareValue = 0;
        }
        
        return compareValue;            
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}