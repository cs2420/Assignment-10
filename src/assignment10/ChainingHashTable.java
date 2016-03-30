package assignment10;

import java.util.Collection;
import java.util.LinkedList;

public class ChainingHashTable implements Set<String> {

	private LinkedList<String>[] storage;
	private HashFunctor hashMethod;
	private int size;
	private int capacity;

	/**
	 * Constructs a hash table of the given capacity that uses the hashing
	 * function specified by the given functor.
	 */
	@SuppressWarnings("unchecked")
	public ChainingHashTable(int capacity, HashFunctor functor) {
		this.capacity = makePrime(capacity);
		storage = (LinkedList<String>[]) new LinkedList[capacity];
		
		// create a linked list at each index
		for(int i = 0; i < size; i++){
			storage[i] = new LinkedList<String>();
		}
		
		hashMethod = functor;
		size = 0;
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

		// If the item is null
		if (item == null) {
			throw new NullPointerException("This item is empty");
		}

		// If the item's already in the set
		if (this.contains(item)) {
			return false;
		}

		// Rehash everything if load factor is 1.0 (could be slightly higher)
		if (size >= capacity) {
			// Double the capacity
			this.capacity = capacity * 2;
			// create replacement storage array
			LinkedList<String>[] newStorage = new LinkedList[capacity];

			// Go through each index of the storage array
			for (int i = 0; i < this.storage.length; i++) {
				// take each item in the index and add it to the new storage
				// array
				for (String currentString : storage[i]) {
					newStorage[this.hashMethod.hash(currentString)]
							.add(currentString);
				}
			}
			// replace the hash table's storage with the newly hashed array
			this.storage = newStorage;
		}

		// Insert the new item
		this.size++;
		int hashedValue = this.hashMethod.hash(item);
		return storage[hashedValue].add(item);

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
		boolean somethingAdded = false;

		// Goes through collection of items, ensures each is in set
		for (String currentString : items) {
			if (this.add(currentString)) {
				somethingAdded = true;
			}
		}

		return somethingAdded;
	}

	/**
	 * Removes all items from this set. The set will be empty after this method
	 * call.
	 */
	@Override
	public void clear() {
		size = 0;
		storage = (LinkedList<String>[]) new LinkedList[capacity];
		;
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
		if(storage[this.hashMethod.hash(item)] == null){
			return false;
		}
		return storage[this.hashMethod.hash(item)].contains(item);
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
		// Goes through collection of items, checks if each is in set
		for (String currentString : items) {
			if (!this.contains(currentString)) {
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
		return this.size == 0;
	}

	/**
	 * Returns the number of items in this set.
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * Helper method to ensure that a number is prime. If the number is prime,
	 * it returns the same number. Otherwise it finds the next ascending number
	 * that is prime.
	 * 
	 * @param number
	 * @return
	 */
	private int makePrime(int number) {
		// return the same number if it's already prime
		if (isPrime(number)) {
			return number;
		}

		// Ensure number is odd since prime numbers are odd
		if (number % 2 == 0) {
			number++;
		}

		// Loop through until number is prime
		while (!isPrime(number)) {
			number += 2;
		}

		return number;
	}

	/**
	 * Helper method that returns true if the number is prime, and false if it's
	 * not prime.
	 * 
	 * @param number
	 * @return
	 */
	private boolean isPrime(int number) {
		// if number is divisible by 2, then it's not prime
		if (number % 2 == 0 || number < 2) {
			return false;
		}

		for (int i = 3; i < number; i += 2) {
			if (number % i == 0) {
				return false;
			}
		}

		return true; // number is prime
	}

}
