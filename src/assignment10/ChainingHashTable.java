package assignment10;

import java.util.Collection;
import java.util.LinkedList;

public class ChainingHashTable implements Set<String>{

	 private LinkedList<String>[] storage;
	
	/** Constructs a hash table of the given capacity that uses the hashing function
     * specified by the given functor.
     */
   @SuppressWarnings("unchecked")
   public ChainingHashTable(int capacity, HashFunctor functor){
	    storage = (LinkedList<String>[]) new LinkedList[capacity];
   }
   
	@Override
	public boolean add(String item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends String> items) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(String item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<? extends String> items) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
