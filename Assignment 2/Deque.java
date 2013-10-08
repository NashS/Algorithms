import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> 
{
	private class Node
	{
		Item item;
		Node next, prev;
	}
	
	private class DequeIterator implements Iterator<Item>
	{
		private Node current = first; 
		
		public boolean hasNext() { return current != null; }
		public Item next() 
		{ 
			Item item = current.item;
			current = current.next; 
			return item;
		}
	
		 public void remove() 
		 {
			 throw new java.lang.UnsupportedOperationException();
		 }
	}
	
	private Node first, last;
	private int size; 
	
	// return an iterator over items in order from front to end
	public Iterator<Item> iterator() { return new DequeIterator(); }
	
	
	public Deque()                     // construct an empty deque
	{
		first = null;
		last = null;
		size = 0;
	}
	public boolean isEmpty()           // is the deque empty?
	{
		return size == 0;
	}
	public int size()                  // return the number of items on the deque
	{
		return size; 
	}
	public void addFirst(Item item)    // insert the item at the front
	{
		validateData(item);
		
		if(isEmpty())
		{
			first = new Node();
			first.item = item;
			first.next = null;
			first.prev = null;
			last = first; 
		}
		else
		{
			Node oldFirst = first; 
			first = new Node();
			first.item = item;
			first.next = oldFirst;
			first.prev = null;
			oldFirst.prev = first;
		}
		size++;
	}
	public void addLast(Item item)     // insert the item at the end
	{
		validateData(item);
		if(isEmpty())
		{
			first = new Node();
			first.item = item;
			first.next = null;
			first.prev = null;
			last = first; 
		}
		else
		{
			Node oldLast = last;
			last = new Node();
			last.item = item;
			last.next = null;
			last.prev = oldLast;
			oldLast.next = last; 
		}
		size++;
		
	}
	public Item removeFirst()          // delete and return the item at the front
	{
		if(isEmpty()) throw new java.util.NoSuchElementException();
		
		Item item = null;
		if( size() == 1)
		{
			item = first.item;
			first = null;
			last = null;
		}
		else
		{
			//Node oldFirst = first;
			item = first.item;
			first = first.next;
			//oldFirst = null;
		}
		size--;
		return item;
		
	}
	public Item removeLast()           // delete and return the item at the end
	{
		if(isEmpty()) throw new java.util.NoSuchElementException();
		
		Item item = null;
		if( size() == 1)
		{
			item = first.item;
			first = null;
			last = null;
		}
		else
		{
			item = last.item;
			last = last.prev;
			last.next = null;
		}
		size--;
		return item;
	}
	
	private void validateData(Item item)
	{
		if(item == null)
			throw new java.lang.NullPointerException();
	}
	/*
	public static void main(String[] args) 
	{
		Deque<String> test = new Deque<String>();
		test.addFirst("hello");
		test.addFirst(" ");
		test.addFirst("world");
		String lastStr = test.removeLast();
		for (String s:test)
			StdOut.println(s);
		StdOut.println("last string was: " + lastStr);
	}
	*/
	
}