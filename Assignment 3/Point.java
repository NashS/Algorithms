import java.util.Comparator;


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
	
    }
	
}