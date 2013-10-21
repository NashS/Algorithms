import java.util.Arrays;


public class Fast {
	private static final int MIN_POINTS_IN_LINE_SEGMENT = 4;
	
	public static void main(String[] args)
	{
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		
		In in = new In(args[0]);      // input file
        int N = in.readInt();
        int x = 0;
        int y = 0;
        int slopeArrayidx = 0;
        Point[] pointArray = new Point[N];
        Point[] slopeSortedPointArray = new Point[N-1];
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
        for(int i = 0; i < pointArray.length; i++)
        {
        	//Initialize the array to be the same as the pointArray each iteration
        	slopeArrayidx = 0;
        	for(int j = 0; j < pointArray.length; j++)
        	{
        		//Skip reference point which is used for creating slopes
        		if(j==i)continue;
        		slopeSortedPointArray[slopeArrayidx++] = pointArray[j];
        	}
        	Arrays.sort(slopeSortedPointArray,pointArray[i].SLOPE_ORDER);
        	for(int j = 0; j < slopeSortedPointArray.length - 2; j++)
        	{
        		if( (pointArray[i].slopeTo(slopeSortedPointArray[j]) == pointArray[i].slopeTo(slopeSortedPointArray[j+1])) &&
        			(pointArray[i].slopeTo(slopeSortedPointArray[j]) == pointArray[i].slopeTo(slopeSortedPointArray[j+2])) )	
        		{
        			lineSegmentPoints[0] = pointArray[i];
					lineSegmentPoints[1] = slopeSortedPointArray[j];
					lineSegmentPoints[2] = slopeSortedPointArray[j+1];
					lineSegmentPoints[3] = slopeSortedPointArray[j+2];
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
