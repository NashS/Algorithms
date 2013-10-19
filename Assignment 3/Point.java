import java.util.Comparator;
import java.util.Arrays;


public class Point implements Comparable<Point> {

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate	
	
	// compare points by slope
	public final Comparator<Point> SLOPE_ORDER = new slopeComparator();        // compare points by slope to this point

	private class slopeComparator implements Comparator<Point>
	{
		public int compare(Point p1, Point p2)
		{
			double s1 = slopeTo(p1);
			double s2 = slopeTo(p2);
			if(s1 > s2)
				return 1;
			if(s1 < s2)
				return -1;
			//equal
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
    public double slopeTo(Point that)                  // the slope between this point and that point
	{
		double dx = that.x - this.x;
		double dy = that.y - this.y;
		if(dx == 0)
		{
			if(dy == 0)
				return Double.NEGATIVE_INFINITY;
			return Double.POSITIVE_INFINITY;
		}
		else
		{
			if(dy == 0)
				return 0;
		}
		return dy/dx;
	}

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that)                // is this point lexicographically smaller than that point?
	{
		if(this.y > that.y)
			return 1;
		if(this.y < that.y)
			return -1;
		if(this.x > that.x)
			return 1;
		if(this.x < that.x)
			return -1;
		//equal
		return 0;
	}

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    	Point origin = new Point(0,0);
    	Point p1 = new Point(1,2);
    	Point p2 = new Point(3,1);
    	Point neg = new Point(-1,-1);
    	Point degen = new Point(0,0);
    	Point horz = new Point(2,0);
    	Point horz2 = new Point(3,0);
    	Point vert = new Point (0,2);
    	Point vert2 = new Point (0,3);
    	Point[] pointArray = new Point[9];
    	pointArray[0] = origin;
    	pointArray[1] = p1;
    	pointArray[2] = p2;
    	pointArray[3] = neg;
    	pointArray[4] = degen;
    	pointArray[5] = horz;
    	pointArray[6] = vert;
    	pointArray[7] = horz2;
    	pointArray[8] = vert2;
    	
    	Arrays.sort(pointArray);
    	
    	
    	StdOut.println("Origin: " + origin.toString());
    	StdOut.println("p1: " + p1.toString());
    	StdOut.println("p2: " + p2.toString());
    	StdOut.println("neg: " + neg.toString());
    	StdOut.println("degen: " + degen.toString());
    	StdOut.println("horz: " + horz.toString());
    	StdOut.println("vert: " + vert.toString());
    	StdOut.println("horz2: " + horz2.toString());
    	StdOut.println("vert2: " + vert2.toString());
    	StdOut.println("");
    	StdOut.println("Testing compareTo()");
    	StdOut.println("");
    	StdOut.println("Origin.compareTo(p1): "+ origin.compareTo(p1));
    	StdOut.println("Origin.compareTo(degen): "+ origin.compareTo(degen));
    	StdOut.println("Origin.compareTo(neg): "+ origin.compareTo(neg));
    	StdOut.println("");
    	StdOut.println("Testing slopeTo()");
    	StdOut.println("");
    	StdOut.println("Origin.slopeTo(p1): "+ origin.slopeTo(p1));
    	StdOut.println("Origin.slopeTo(horz): "+ origin.slopeTo(horz));
    	StdOut.println("Origin.slopeTo(degen): "+ origin.slopeTo(degen));
    	StdOut.println("Origin.slopeTo(vert): "+ origin.slopeTo(vert));
    	StdOut.println("Origin.slopeTo(neg): "+ origin.slopeTo(neg));
    	StdOut.println("");
    	StdOut.println("unsorted array: ");
    	StdOut.println("");
    	for(int i = 0;i<pointArray.length;i++)
    		StdOut.println("pointArray[" + i + "]: " + pointArray[i]);
    	StdOut.println("");
    	StdOut.println("Testing natural ordering sort: ");
    	StdOut.println("");
    	Arrays.sort(pointArray);
    	for(int i = 0;i<pointArray.length;i++)
    		StdOut.println("pointArray[" + i + "]: " + pointArray[i]);
    	pointArray[0] = origin;
    	pointArray[1] = p1;
    	pointArray[2] = p2;
    	pointArray[3] = neg;
    	pointArray[4] = degen;
    	pointArray[5] = horz;
    	pointArray[6] = vert;
    	pointArray[7] = horz2;
    	pointArray[8] = vert2;
    	StdOut.println("");
    	StdOut.println("Testing slope ordering(w.r.t. origin) sort: ");
    	StdOut.println("");
    	Arrays.sort(pointArray,origin.SLOPE_ORDER);
    	for(int i = 0;i<pointArray.length;i++)
    		StdOut.println("pointArray[" + i + "]: " + pointArray[i]);
    	StdOut.println("");
    	StdOut.println("");
    }
	
}