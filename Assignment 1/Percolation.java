public class Percolation {
	
	private static enum siteStatus
	{
		CLOSED, OPEN
	}
	
	private final int N;
	private final int topSiteIdx;
	private final int bottomSiteIdx;
	
	private siteStatus[][] siteStatusGrid;
	private WeightedQuickUnionUF connectionGrid;
	private WeightedQuickUnionUF backwashGrid;
	
	// create N-by-N grid, with all sites blocked
	public Percolation(int n)             
	{
		N = n;
		topSiteIdx = 0;
		bottomSiteIdx = N*N+1;
		
		if (N < 1) throw new IndexOutOfBoundsException();
		
		// +2 for virtual top and bottom sites
		connectionGrid = new WeightedQuickUnionUF(N * N + 2);
		backwashGrid = new WeightedQuickUnionUF(N * N + 1);
		//connect top and bottom row sites to virtual top and bottom site
		for (int i = 1; i <= N; i++)
		{
			backwashGrid.union(topSiteIdx, topSiteIdx + i);
			connectionGrid.union(topSiteIdx, topSiteIdx + i);
			connectionGrid.union(bottomSiteIdx, bottomSiteIdx - i);
		}
		
		
		siteStatusGrid = new siteStatus[N][N];
		for (int row = 0; row < N; row++)
		{
			for (int col = 0; col < N; col++)
			{
				siteStatusGrid[row][col] = siteStatus.CLOSED;
			}
		}
	}
	
	// open site (row i, column j) if it is not already
	public void open(int i, int j)         
	{
		validateIndex(i, j);
		
		//Check adjacent blocks
		siteStatusGrid[i-1][j-1] = siteStatus.OPEN;
		//check left
		if (j - 1 >= 1 && siteStatusGrid[i-1][j-2] == siteStatus.OPEN)
		{
			connectionGrid.union(xyTo1D(i, j), xyTo1D(i, j-1));
			backwashGrid.union(xyTo1D(i, j), xyTo1D(i, j-1));
		}
		//check right
		if (j + 1 <= N && siteStatusGrid[i-1][j] == siteStatus.OPEN)
		{
			connectionGrid.union(xyTo1D(i, j), xyTo1D(i, j+1));
			backwashGrid.union(xyTo1D(i, j), xyTo1D(i, j+1));
		}
		//check top
		if (i - 1 >= 1 && siteStatusGrid[i-2][j-1] == siteStatus.OPEN)
		{
			connectionGrid.union(xyTo1D(i, j), xyTo1D(i-1, j));
			backwashGrid.union(xyTo1D(i, j), xyTo1D(i-1, j));
		}
		//check bottom
		if (i + 1 <= N && siteStatusGrid[i][j-1] == siteStatus.OPEN)
		{
			connectionGrid.union(xyTo1D(i, j), xyTo1D(i+1, j));
			backwashGrid.union(xyTo1D(i, j), xyTo1D(i+1, j));
		}
	}
	
	// is site (row i, column j) open?
	public boolean isOpen(int i, int j)    
	{
		validateIndex(i, j);
		return siteStatusGrid[i-1][j-1] == siteStatus.OPEN;
	}
	
	 // is site (row i, column j) full?
	public boolean isFull(int i, int j)   
	{
		validateIndex(i, j);
		return isOpen(i, j) && 
			   connectionGrid.connected(xyTo1D(i, j), topSiteIdx) &&
			   !isBackwash(xyTo1D(i, j));
	}
	
	// does the system percolate?
	public boolean percolates()            
	{
		return connectionGrid.connected(topSiteIdx, bottomSiteIdx);
	}
	
	// make sure that index given is within the percolation grid
	private void validateIndex(int i, int j)
	{
		if (i < 1 || i > N || j < 1 || j > N) 
			throw new IndexOutOfBoundsException();
	}
	
	//Convert xy coordinates to 1D index
	private int xyTo1D(int i, int j)
	{
		//+1 due to first element being virtual top site
		return (i - 1) * N + (j - 1) + 1; 
	}
	
	//checks if site is connected to top only through the bottom virtual site
	private boolean isBackwash(int i)
	{
		
		return connectionGrid.connected(i, topSiteIdx) && 
			   !backwashGrid.connected(i, topSiteIdx);
	}
}