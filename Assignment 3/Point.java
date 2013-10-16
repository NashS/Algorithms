
public class Point implements Comparable<Point> {
	private int x, y;
	
	public    int compareTo(Point that)                // is this point lexicographically smaller than that point?
	{
		if(this.y > that.y)
			return 1;
		if(this.y < that.y);
			return -1;
		if(this.x > that.x)
			return 1;
		if(this.x < that.x)
			return -1;
		if(this.x == that.x && this.y == that.y)
			return 0;
	}
	
	public final Comparator<Point> SLOPE_ORDER = new slopeComperator();        // compare points by slope to this point

	private class slopeComperator implements Comperator<Point>
	{
		public int compare(Point p1, Point p2)
		{
			s1 = slopeTo(p1);
			s2 = slopeTo(p2);
			if(s1 > s2)
				return 1;
			if(s1 < s2)
				return -1;
			if(s1 == s2)
				return 0;
		}
	}
	
	public Point(int x, int y)                         // construct the point (x, y)
	{
		this.x = x;
		this.y = y;
	}
	
	public   void draw()                               // draw this point
	public   void drawTo(Point that)                   // draw the line segment from this point to that point
	public String toString()                           // string representation

	
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
}