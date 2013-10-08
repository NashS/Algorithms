
public class Subset {
	private RandomizedQueue<String> randQueue;
	private int k;
	
	public Subset()
	{
		randQueue = new RandomizedQueue<String>();
		k = Integer.parseInt(StdIn.readString());
		
		String s;
		while(!StdIn.isEmpty())
			randQueue.enqueue(StdIn.readString());
		
		for(int i=0;i<k;i++)
			StdOut.println(randQueue.dequeue());
	}
	
	public static void main(String[] args)
	{
		Subset subset = new Subset();
	}
}
