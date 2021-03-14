package textgen;

import java.util.AbstractList;


/**
 * A class that implements a doubly linked list
 *
 * @param <E> The type of the elements stored in the list
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class MyLinkedList<E> extends AbstractList<E> {
    LLNode<E> head;
    LLNode<E> tail;
    int size;

    /**
     * Create a new empty LinkedList
     */
    public MyLinkedList() {
        this.size = 0;
        this.head = new LLNode<>(null);
        this.tail = new LLNode<>(null);

        head.next = tail;
        tail.prev = head;
    }

    /**
     * Appends an element to the end of the list
     *
     * @param element The element to add
     */
    public boolean add(E element) {
        if (element == null) {
            throw new NullPointerException();
        }
        LLNode<E> node = new LLNode<>(element);
        LLNode<E> lastNode = tail.prev;

        lastNode.next = node;
        node.prev = lastNode;

        node.next = tail;
        tail.prev = node;

        size = size + 1;

        return true;
    }

    /**
     * Get the element at position index
     *
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public E get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        LLNode<E> currentNode = head.next;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode.data;
    }

    /**
     * Add an element to the list at the specified index
     *
     * @param index   where the element should be added
     * @param element The element to add
     */
    public void add(int index, E element) {
        if (element == null) {
            throw new NullPointerException();
        }
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        if (index == size) {
            add(element);
            return;
        }

        LLNode<E> currentNode = head.next;

        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }

        LLNode<E> newElement = new LLNode<>(element);

        LLNode<E> nextNode = currentNode.next;
        LLNode<E> preNode = currentNode.prev;

        nextNode.prev = newElement;
        newElement.next = nextNode;

        newElement.prev = preNode;
        preNode.next = newElement;

        size = size + 1;
    }


    /**
     * Return the size of the list
     */
    public int size() {
        return size;
    }

    /**
     * Remove a node at the specified index and return its data element.
     *
     * @param index The index of the element to remove
     * @return The data element removed
     * @throws IndexOutOfBoundsException If index is outside the bounds of the list
     */
    public E remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        LLNode<E> indexedNode = head.next;
        for (int i = 0; i < index; i++) {
            indexedNode = indexedNode.next;
        }

        LLNode<E> preNode = indexedNode.prev;
        LLNode<E> nextNode = indexedNode.next;

        preNode.next = nextNode;
        nextNode.prev = preNode;

        size = size - 1;

        return indexedNode.data;
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
            throw new NullPointerException();
        }

        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        LLNode<E> replacedNode = head.next;
        for (int i = 0; i < index; i++) {
            replacedNode = replacedNode.next;
        }
        E oldData = replacedNode.data;
        replacedNode.data = element;
        return oldData;
    }
}

class LLNode<E> {
    LLNode<E> prev;
    LLNode<E> next;
    E data;

    // TODO: Add any other methods you think are useful here
    // E.g. you might want to add another constructor

    public LLNode(E e) {
        this.data = e;
        this.prev = null;
        this.next = null;
    }

}
