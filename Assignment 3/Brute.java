import java.util.Arrays;


public class Brute {
	private static final int MIN_POINTS_IN_LINE_SEGMENT = 4;
	
	public static void main(String[] args)
	{
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		
        In in = new In(args[0]);      // input file
        int N = in.readInt();
        int x = 0;
        int y = 0;
        Point[] pointArray = new Point[N];
        Point[] lineSegmentPoints = new Point[MIN_POINTS_IN_LINE_SEGMENT];
        Point currPoint;
        //Populate points array from point coordinates in input file
        for(int i = 0;i < pointArray.length; i++)
        {
            x = in.readInt();
            y = in.readInt();
            currPoint = new Point(x,y);
            pointArray[i] = currPoint;
            currPoint.draw();     
        }
        for(int i = 0; i < pointArray.length-3; i++)
        {
        	for(int j = i+1; j < pointArray.length-2; j++)
        	{
        		for(int k = j+1; k < pointArray.length-1; k++)
        		{
        			for(int l = k+1; l < pointArray.length; l++)
        			{
        				if( (pointArray[i].slopeTo(pointArray[j]) == pointArray[i].slopeTo(pointArray[k])) && 
        					(pointArray[i].slopeTo(pointArray[j]) == pointArray[i].slopeTo(pointArray[l])) )
        				{
        					lineSegmentPoints[0] = pointArray[i];
        					lineSegmentPoints[1] = pointArray[j];
        					lineSegmentPoints[2] = pointArray[k];
        					lineSegmentPoints[3] = pointArray[l];
        					Arrays.sort(lineSegmentPoints);
        					lineSegmentPoints[0].drawTo(lineSegmentPoints[3]);
        					StdOut.println(lineSegmentPoints[0].toString() + " -> " + 
        								   lineSegmentPoints[1].toString() + " -> " + 	
        								   lineSegmentPoints[2].toString() + " -> " + 
        								   lineSegmentPoints[3].toString());
        				}
        			}
        		}
        	}
        }
        
	}
}
