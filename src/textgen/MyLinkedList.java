package textgen;

import java.util.AbstractList;

/**
 * A class that implements a doubly linked list
 * 
 * @param <E> The type of the elements stored in the list
 */
class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// Initialize head and tail
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.next = tail;
		tail.prev = head;
		size = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * 
	 * @param element The element to add
	 */
	public boolean add(E element) {
		if (element == null) {
			throw new NullPointerException("Cannot add null element");
		}
		LLNode<E> newNode = new LLNode<E>(element);
		newNode.prev = tail.prev;
		newNode.next = tail;
		tail.prev.next = newNode;
		tail.prev = newNode;
		size++;
		return true;
	}

	/**
	 * Get the element at position index
	 * 
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		LLNode<E> current = head.next;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * 
	 * @param index   The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element) {
		if (element == null) {
			throw new NullPointerException("Cannot add null element");
		}
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		LLNode<E> current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		LLNode<E> newNode = new LLNode<E>(element);
		newNode.prev = current;
		newNode.next = current.next;
		current.next.prev = newNode;
		current.next = newNode;
		size++;
	}

	/** Return the size of the list */
	public int size() {
		return size;
	}

	/**
	 * Remove a node at the specified index and return its data element.
	 * 
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		LLNode<E> current = head.next;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		current.prev.next = current.next;
		current.next.prev = current.prev;
		size--;
		return current.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * 
	 * @param index   The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) {
		if (element == null) {
			throw new NullPointerException("Cannot set null element");
		}
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		LLNode<E> current = head.next;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		E oldData = current.data;
		current.data = element;
		return oldData;
	}

}

class LLNode<E> {
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	public LLNode(E e) {
		this.data = e;
		this.prev = null;
		this.next = null;
	}

	public String toString() {
		return data.toString();
	}

}