import java.util.ArrayList;
import java.awt.Point;
import java.lang.Math;

public class PercolationStats {
	
	private final int T;
	private final int N;
	private int currExperimentCount = 0;
	private int openSites; 
	private double[] fracOpenSites;
	private ArrayList<Point> availRowCol;
	private double mean;
	private double stdDev;
	private double confidenceLo;
	private double confidenceHi;
	
	private Percolation perc;
	
	// perform T independent computational experiments on an N-by-N grid
	public PercolationStats(int n, int t)    
	{
		N = n;
		T = t;
		
		validateIndex(N, T);
		fracOpenSites = new double[T];
		
		while(currExperimentCount < T)
		{
			initRandArray();
			perc = new Percolation(N);
			Point randSite = new Point(-1, -1);
			openSites = 0;
			while(!perc.percolates())
			{
				randSite = availRowCol.remove((int)(Math.random()*availRowCol.size()));
				perc.open(randSite.x, randSite.y);
				openSites++;
			}
			fracOpenSites[currExperimentCount] = ((double)openSites) / (N * N);
			currExperimentCount++;
		}
		//calculate mean
		double sum = 0;
		double sqSum = 0;
		for(int i = 0;i < T; i++)
		{
			sum += fracOpenSites[i];
		}
		mean = sum / T;
		//calculate stddev
		for(int i = 0;i < T; i++)
		{
			sqSum += (fracOpenSites[i] - mean) * (fracOpenSites[i] - mean);
		}
		stdDev = Math.sqrt(sqSum/(T-1));
		confidenceLo = mean - (1.96 * stdDev / Math.sqrt(T));
		confidenceHi = mean + (1.96 * stdDev / Math.sqrt(T));
	}
   
	// sample mean of percolation threshold
	public double mean()                     
	{
		return mean;
	}
	
	// sample standard deviation of percolation threshold
	public double stddev()                   
	{
		return stdDev;
	}
	
	// returns lower bound of the 95% confidence interval
	public double confidenceLo()             
	{
		return confidenceLo;
	}
	
	 // returns upper bound of the 95% confidence interval
	public double confidenceHi()            
	{
		return confidenceHi;
	}
	
	private void validateIndex(int n, int t)
	{
		if (n < 1 || t < 1) 
			throw new IndexOutOfBoundsException();
	}
	
	private void initRandArray()
	{
		if (availRowCol == null)
			availRowCol = new ArrayList<Point>();
		availRowCol.clear();
		for (int row = 1; row <= N; row++)
		{
			for (int col = 1; col <= N; col++)
			{
				Point currPoint = new Point(row,col);
				availRowCol.add(currPoint);
			}
		}
	}
	
	// test client, described below
	public static void main(String[] args)
	{
		PercolationStats percStats = 
		new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		StdOut.println("mean                    = " + percStats.mean());
		StdOut.println("stddev                  = " + percStats.stddev());
		StdOut.println("95% confidence interval = " 
						+ percStats.confidenceLo() + ", " 
						+ percStats.confidenceHi());
		
	}
}