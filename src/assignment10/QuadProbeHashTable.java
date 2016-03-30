<<<<<<< HEAD
package assignment10;

import java.util.Arrays;
import java.util.Collection;

public class QuadProbeHashTable implements Set<String> {

	String[] table;
	HashFunctor functor;
	int size;
	int capacity;

	/**
	 * Creates a QuadProbeHashTable with the given capacity constraint and a
	 * HashFunctor used for insertion
	 * 
	 * @param capacity
	 * @param functor
	 */
	public QuadProbeHashTable(int capacity, HashFunctor functor) {
		if (isPrime(capacity)) {
			table = new String[capacity];
		} else {
			table = new String[nextPrime(capacity)];
		}
		this.functor = functor;
		size = 0;
		this.capacity = capacity;

	}

	/**
	 * Ensures that this set contains the specified item.
	 * 
	 * @param item
	 *            - the item whose presence is ensured in this set
	 * @return true if this set changed as a result of this method call (that
	 *         is, if the input item was actually inserted); otherwise, returns
	 *         false
	 */
	@Override
	public boolean add(String item) {
		// array has room for another item
		if (size < capacity) {
			// resizes the array if needed
			if (table.length / 2 <= size) {
				String[] copy = table.clone();
				table = new String[nextPrime(table.length)];
				// rehashes all values from the old array and adds them to the
				// resized array
				for (int i = 0; i < copy.length; i++) {
					if (copy[i] != null) {
						table[quadProbe(copy[i])] = copy[i];
					}
				}
			}
			// finds the correct index for insertion
			int index = quadProbe(item);
			// empty index found, inserts the item
			if (table[index] == null) {
				size++;
				table[index] = item;
				return true;
				// item was already contained inside the array
			} else {
				return false;
			}
		}
		// array does not have room for another item
		else {
			return false;
		}

	}

	/**
	 * Ensures that this set contains all items in the specified collection.
	 * 
	 * @param items
	 *            - the collection of items whose presence is ensured in this
	 *            set
	 * @return true if this set changed as a result of this method call (that
	 *         is, if any item in the input collection was actually inserted);
	 *         otherwise, returns false
	 */
	@Override
	public boolean addAll(Collection<? extends String> items) {
		int oldSize = size;
		for (String string : items) {
			add(string);
		}
		return size != oldSize;
	}

	/**
	 * Removes all items from this set. The set will be empty after this method
	 * call.
	 */
	@Override
	public void clear() {
		if (isPrime(capacity)) {
			table = new String[capacity];
		} else {
			table = new String[nextPrime(capacity)];
		}
		size = 0;

	}

	/**
	 * Determines if there is an item in this set that is equal to the specified
	 * item.
	 * 
	 * @param item
	 *            - the item sought in this set
	 * @return true if there is an item in this set that is equal to the input
	 *         item; otherwise, returns false
	 */
	@Override
	public boolean contains(String item) {
		int index = quadProbe(item);
		if (table[index] == item) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Determines if for each item in the specified collection, there is an item
	 * in this set that is equal to it.
	 * 
	 * @param items
	 *            - the collection of items sought in this set
	 * @return true if for each item in the specified collection, there is an
	 *         item in this set that is equal to it; otherwise, returns false
	 */
	@Override
	public boolean containsAll(Collection<? extends String> items) {
		for (String string : items) {
			if (!contains(string)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns true if this set contains no items.
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns the number of items in this set.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Helper method finds the next greatest prime number after size
	 * 
	 * @param size
	 * @return The next prime number that is greater than size
	 */
	private int nextPrime(int size) {
		boolean isPrime = false;
		while (!isPrime) {
			size++;
			isPrime = isPrime(size);
		}
		return size;
	}

	/**
	 * Helper method returns whether or not n is a prime number
	 * 
	 * @param n
	 * @return True if n is prime, otherwise returns false
	 */
	private boolean isPrime(int n) {
		boolean result = true;
		for (int i = 2; i < n; i++) {
			if (n % i == 0) {
				result = false;
				break;
			}
		}
		return result;
	}

	/**
	 * Helper method that returns the first available index that the item could
	 * be inserted into, or if the item is already contained in the array
	 * returns it's index
	 * 
	 * @param item
	 * @return the index of the item, or where the item could be inserted
	 */
	private int quadProbe(String item) {
		int index = functor.hash(item) % table.length;
		if (table[index] == null || table[index].equals(item)) {
			return index;
		} else {
			int quadratic = 1;
			while (table[index] != null && !table[index].equals(item)) {
				index += Math.pow(quadratic, 2);
				if (index > table.length - 1) {
					index = index % table.length;
				}
				quadratic++;
			}
			return index;
		}
	}

	@Override
	public String toString() {
		return Arrays.toString(table);
	}

}
=======
package assignment10;

import java.util.Arrays;
import java.util.Collection;

public class QuadProbeHashTable implements Set<String> {

	String[] table;
	HashFunctor functor;
	int size;
	int capacity;

	/**
	 * Creates a QuadProbeHashTable with the given capacity constraint and a
	 * HashFunctor used for insertion
	 * 
	 * @param capacity
	 * @param functor
	 */
	public QuadProbeHashTable(int capacity, HashFunctor functor) {
		if (isPrime(capacity)) {
			table = new String[capacity];
		} else {
			table = new String[nextPrime(capacity)];
		}
		this.functor = functor;
		size = 0;
		this.capacity = capacity;

	}

	/**
	 * Ensures that this set contains the specified item.
	 * 
	 * @param item
	 *            - the item whose presence is ensured in this set
	 * @return true if this set changed as a result of this method call (that
	 *         is, if the input item was actually inserted); otherwise, returns
	 *         false
	 */
	@Override
	public boolean add(String item) {
		// array has room for another item
		if (size < capacity) {
			// resizes the array if needed
			if (table.length / 2 <= size) {
				String[] copy = table.clone();
				table = new String[nextPrime(table.length)];
				// rehashes all values from the old array and adds them to the
				// resized array
				for (int i = 0; i < copy.length; i++) {
					if (copy[i] != null) {
						table[quadProbe(copy[i])] = copy[i];
					}
				}
			}
			// finds the correct index for insertion
			int index = quadProbe(item);
			// empty index found, inserts the item
			if (table[index] == null) {
				size++;
				table[index] = item;
				return true;
				// item was already contained inside the array
			} else {
				return false;
			}
		}
		// array does not have room for another item
		else {
			return false;
		}

	}

	/**
	 * Ensures that this set contains all items in the specified collection.
	 * 
	 * @param items
	 *            - the collection of items whose presence is ensured in this
	 *            set
	 * @return true if this set changed as a result of this method call (that
	 *         is, if any item in the input collection was actually inserted);
	 *         otherwise, returns false
	 */
	@Override
	public boolean addAll(Collection<? extends String> items) {
		int oldSize = size;
		for (String string : items) {
			add(string);
		}
		return size != oldSize;
	}

	/**
	 * Removes all items from this set. The set will be empty after this method
	 * call.
	 */
	@Override
	public void clear() {
		if (isPrime(capacity)) {
			table = new String[capacity];
		} else {
			table = new String[nextPrime(capacity)];
		}
		size = 0;

	}

	/**
	 * Determines if there is an item in this set that is equal to the specified
	 * item.
	 * 
	 * @param item
	 *            - the item sought in this set
	 * @return true if there is an item in this set that is equal to the input
	 *         item; otherwise, returns false
	 */
	@Override
	public boolean contains(String item) {
		int index = quadProbe(item);
		if (table[index] == item) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Determines if for each item in the specified collection, there is an item
	 * in this set that is equal to it.
	 * 
	 * @param items
	 *            - the collection of items sought in this set
	 * @return true if for each item in the specified collection, there is an
	 *         item in this set that is equal to it; otherwise, returns false
	 */
	@Override
	public boolean containsAll(Collection<? extends String> items) {
		for (String string : items) {
			if (!contains(string)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns true if this set contains no items.
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns the number of items in this set.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Helper method finds the next greatest prime number after size
	 * 
	 * @param size
	 * @return The next prime number that is greater than size
	 */
	private int nextPrime(int size) {
		boolean isPrime = false;
		while (!isPrime) {
			size++;
			isPrime = isPrime(size);
		}
		return size;
	}

	/**
	 * Helper method returns whether or not n is a prime number
	 * 
	 * @param n
	 * @return True if n is prime, otherwise returns false
	 */
	private boolean isPrime(int n) {
		boolean result = true;
		for (int i = 2; i < n; i++) {
			if (n % i == 0) {
				result = false;
				break;
			}
		}
		return result;
	}

	/**
	 * Helper method that returns the first available index that the item could
	 * be inserted into, or if the item is already contained in the array
	 * returns it's index
	 * 
	 * @param item
	 * @return the index of the item, or where the item could be inserted
	 */
	private int quadProbe(String item) {
		int index = functor.hash(item) % table.length;
		if (table[index] == null || table[index].equals(item)) {
			return index;
		} else {
			int quadratic = 1;
			while (table[index] != null && !table[index].equals(item)) {
				index += Math.pow(quadratic, 2);
				if (index > table.length - 1) {
					index = index % table.length;
				}
				quadratic++;
			}
			return index;
		}
	}

	@Override
	public String toString() {
		return Arrays.toString(table);
	}

}
>>>>>>> origin/master
