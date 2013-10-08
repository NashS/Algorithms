import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> 
{
	/*
	private static final double DEQ_NULLITEMS_RATIO_THRESHOLD = 0.75; 
	*/
	private static final double LOITERING_RATIO_THRESHOLD = 0.25; 
	private static enum resizeOption
	{
		Shrink,Grow
	}
	
	private int sizeRandQueueContainer;
	private int sizeRandQueue;
	//Number of null items due to dequeue operation
	private Item[] randQueue;
	
	private class RandomizedQueueIterator implements Iterator<Item>
	{
		private int current = 0;
		private int[] randIdxArray = new int[sizeRandQueue]; 
		public RandomizedQueueIterator()
		{
			int it = 0;
			for(int i = 0; i < randQueue.length; i++)
			{
				if(randQueue[i] == null)
					continue;
				randIdxArray[it++] = i;
			}
			StdRandom.shuffle(randIdxArray);
		}
		
		public boolean hasNext() { return current != randIdxArray.length; }
		public Item next() 
		{ 
			Item item = randQueue[randIdxArray[current]];
			current++;
			return item;
		}
	
		 public void remove() 
		 {
			 throw new java.lang.UnsupportedOperationException();
		 }
	}
	
	private void resizeRandQueueContainer(resizeOption rszOpt)
	{
		if(rszOpt == resizeOption.Grow)
			sizeRandQueueContainer *= 2;
		else if(rszOpt == resizeOption.Shrink)
			sizeRandQueueContainer /= 2;
			
		Item[] tempRef = (Item[]) new Object[sizeRandQueueContainer];
		int idx = 0;
		for(Item i:randQueue)
		{
			//reached end of randQueue
			if(i == null)
				break;
			tempRef[idx++] = i; 
		}
		randQueue = tempRef;	
	}
	
	// return an independent iterator over items in random order
	public Iterator<Item> iterator(){ return new RandomizedQueueIterator(); }   
	
	public RandomizedQueue()           // construct an empty randomized queue
	{
		sizeRandQueueContainer = 1;
		sizeRandQueue = 0;
		randQueue = (Item[]) new Object[sizeRandQueueContainer];
	}
	
	public boolean isEmpty()           // is the queue empty?
	{
		return sizeRandQueue == 0;
	}
	
	public int size()                  // return the number of items on the queue
	{
		return sizeRandQueue;
	}
	
	public void enqueue(Item item)     // add the item
	{
		if(sizeRandQueue + 1 > sizeRandQueueContainer)
			resizeRandQueueContainer(resizeOption.Grow);
		
		randQueue[sizeRandQueue] = item;
		sizeRandQueue++;
	}
	
	public Item dequeue()              // delete and return a random item
	{
		if(sizeRandQueue - 1 <= LOITERING_RATIO_THRESHOLD * sizeRandQueueContainer)
			resizeRandQueueContainer(resizeOption.Shrink);
		
		int randIdx = StdRandom.uniform(sizeRandQueue);
		Item item = randQueue[randIdx];
		randQueue[randIdx] = randQueue[sizeRandQueue-1];
		randQueue[sizeRandQueue-1] = null;
		sizeRandQueue--;
		return item;
	}
	
	public Item sample()               // return (but do not delete) a random item
	{
		int randIdx = StdRandom.uniform(sizeRandQueue);
		Item item = randQueue[randIdx];
		return item;
	}
	/*
	public void printQueue()
	{
		for(int i= 0; i < sizeRandQueueContainer;i++)
		{
			StdOut.println("randQueue[" + i + "]: " + randQueue[i]);
		}
		
	}
	
	public static void main (String[] args)
	{
		RandomizedQueue<Integer> test = new RandomizedQueue<Integer>();
		for(int i=0; i<9; i++)
			test.enqueue(i);
		test.printQueue();
		StdOut.println("\n\nNow dequeueing 5 elements\n\n");
		for(int i=0; i<5; i++)
			StdOut.println("Dequeue operation: " + i + ": " + test.dequeue());
		test.printQueue();
		
		StdOut.println("\n\nNow dequeueing 2 elements\n\n");
		for(int i=0; i<2; i++)
			StdOut.println("Dequeue operation: " + i + ": " + test.dequeue());
		test.printQueue();
		
		for(int i=3; i<15; i+=3)
			test.enqueue(i);
		StdOut.println("\n\nNow using iterator\n\n");
		for(int q:test)
			StdOut.println("q: " + q);
		test.printQueue();
		
		StdOut.println("\n\nNow using sample\n\n");
		for(int i=0; i<3; i++)
			StdOut.println("Sample operation: " + i + ": " + test.sample());
		test.printQueue();
	}
	*/
	
}