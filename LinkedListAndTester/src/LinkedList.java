/**
 * Created on 9/20/2016, 2:18 PM
 *
 * @author Noah Morton
 *         Tully 7th period
 *         Part of project LinkedListAndTester
 */

@SuppressWarnings("WeakerAccess")
public class LinkedList<E> {

    private LLNode<E> first, last;

    /**
     * Sets up the list to be empty.
     */
    public LinkedList() {
        this.first = null;
        this.last = null;
    }

    /**
     * Sets up the link list setting both first and last to a new node.
     *
     * @param data data to create the linked list with
     */
    public LinkedList(E data) {
        LLNode<E> newNode = new LLNode<>(data);
        first = newNode;
        last = newNode;
    }

    //getters
    public E getFirst() {
        return first.getData();
    }

    public E getLast() {
        return last.getData();
    }

    /**
     * Gets the first node in the list
     *
     * @return the first node in the list.
     */
    public LLNode<E> getFirstNode() {
        return first;
    }

    /**
     * Gets the last node in the list
     *
     * @return returns the last node in the list
     */
    public LLNode<E> getLastNode() {
        return last;
    }

    //manipulation methods -------------------

    /**
     * Removes the first node, returns it's old value
     *
     * @return the old value of first.
     */
    public LLNode<E> removeFirst() {
        LLNode<E> oldFirst = first;
        first = first.getNext();
        return oldFirst;
    }

    /**
     * Removes the last node, returns it's old value
     *
     * @return the old value of last.
     */
    public LLNode<E> removeLast() {
        LLNode<E> oldLast = first;
        last = null;
        return oldLast;
    }

    /**
     * Adds a new node to the list at the first spot, sets the old first to be second to the new node.
     *
     * @param data data to create a new node with.
     */
    public void addFirst(E data) {
        if (first == null) {
            first = last = new LLNode<>(data);

        } else {
            LLNode<E> temp = new LLNode<>(data);
            temp.setNext(first);
            first = temp;
        }
    }

    /**
     * Adds a new node to the list at the last spot, sets the old last to be second-to-last to the new node.
     *
     * @param data data to create a new node with.
     */
    public void addLast(E data) {
        if (last == null) {
            first = last = new LLNode<>(data);
        } else {
            LLNode<E> temp = new LLNode<>(data);
            last.setNext(temp);
            last = temp;
        }
    }

    /**
     * Sets the first and last node to null.
     */
    public void clear() {
        first = null;
        last = null;
    }

    /**
     * Returns the size of the linked list
     *
     * @return int the size of the linked list
     */
    public int size() {
        LLNode<E> item = first;
        int finalSize = 0;

        while (item != null) {
            finalSize++;
            item = item.getNext();
        }

        return finalSize;
    }

    /**
     * Returns the empty status of the list.
     *
     * @return True if first and last are null.
     */
    public boolean empty() {
        return (first == null && last == null);
    }

    public E get(int x) {
        return null;
    }

    public E remove(int x) {
        return null;
    }

    /**
     * Adds a new node at a certain position
     *
     * @param x    index to add at.
     * @param data data to make a new node with.
     */
    public void add(int x, E data) { //fixme not quite working yet
        if (x == 0) addFirst(data); //if adding at the first spot, just call addFirst
        else {
            LLNode<E> newNode = first;
            LLNode<E> previousNode = first;
            
            for (int i = 1; i < x; i++) {
                previousNode = newNode;
                newNode = newNode.getNext();
            }

            previousNode.setNext(newNode);
            newNode.setNext(newNode.getNext());
        }
    }

    public E set(int x, E data) {
        return null;
    }

    public E next() {
        return null;
    }

    @Override
    public String toString() {
        LLNode<E> item = first;
        String finalString = "";

        while (item != null) {
            finalString += item.toString() + "\n";
            item = item.getNext();
        }
        return finalString;
    }
}