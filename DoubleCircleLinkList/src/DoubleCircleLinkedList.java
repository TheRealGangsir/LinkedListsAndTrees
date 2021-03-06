/**
 * Created on 9/29/2016, 2:26 PM
 *
 * @author Noah Morton
 *         Tully 7th period
 *         Part of project DoubleCircleLinkList
 */

@SuppressWarnings("WeakerAccess")
public class DoubleCircleLinkedList<E> {

    private LLNode<E> first, last;

    /**
     * Sets up the list to be empty.
     */
    public DoubleCircleLinkedList() {
        this.first = null;
        this.last = null;
    }

    /**
     * Sets up the link list setting both first and last to a new node.
     *
     * @param data data to create the linked list with
     */
    public DoubleCircleLinkedList(E data) {
        first = last = new LLNode<>(data);
        first.setNext(first); //the list is now a small circle that points to itself
        first.setPrevious(first);

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
    public E removeFirst() {
        try {
            LLNode<E> oldFirst = first;
            first.getNext().setPrevious(last);
            first = first.getNext();
            last.setNext(first);
            return oldFirst.getData();
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * Removes the last node, returns it's old value
     *
     * @return the old value of last.
     */
    public E removeLast() {
        try {
            LLNode<E> oldLast = last;

            last.getPrevious().setNext(first);
            first.setPrevious(last.getPrevious());

            last = last.getPrevious();
            return oldLast.getData();
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * Adds a new node to the list at the first spot, sets the old first to be second to the new node.
     *
     * @param data data to create a new node with.
     */
    public void addFirst(E data) {
        if (first == null) {
            first = last = new LLNode<>(data);
            first.setNext(first);
            first.setPrevious(first);
        } else {
            LLNode<E> temp = new LLNode<>(data);
            temp.setNext(first);
            temp.setPrevious(last);
            last.setNext(temp);
            first.setPrevious(temp);
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
            first.setPrevious(temp);
            temp.setNext(first);
            temp.setPrevious(last);

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
        if (empty()) return 0; //empty

        do {
            finalSize++;
            item = item.getNext();
        } while (item != first);
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

    /**
     * Gets the node's data at a location
     *
     * @param x int The index to get from
     * @return E The node data at x's position
     */
    public E get(int x) {
        int index = 0;
        LLNode<E> item = first;

        while (item != null && index < x) {
            index++;
            item = item.getNext();
        }
        if (item == null) return null; //if not found
        else return item.getData();
    }

    /**
     * Gets the node at a location
     *
     * @param x int The index to get from
     * @return E The node at x's position
     */
    public LLNode<E> getNodeFromIndex(int x) {
        int index = 0;
        LLNode<E> item = first;

        while (index < x) {
            index++;
            item = item.getNext();
        }
        if (item == null) return null; //if not found
        else return item;
    }

    /**
     * Removes a node at a position.
     *
     * @param x int The index to remove at.
     * @return E the data of the node removed
     */
    public E remove(int x) {

        E removedData = getNodeFromIndex(x).getData();

        if (x == 0) return removeFirst(); //if provided index is the first or end of the list
        if (x == size() - 1) return removeLast();

        int index = 0;
        LLNode<E> item = first;

        while (index < x) {

            item = item.getNext();
            index++;
        }
        LLNode<E> previous = item.getPrevious();
        LLNode<E> next = item.getNext();
        previous.setNext(next);
        next.setPrevious(previous);

        return removedData;
    }

    /**
     * Adds a new node at a certain position
     *
     * @param x    index to add at.
     * @param data data to make a new node with.
     */
    public void add(int x, E data) {
        if (x == 0) addFirst(data); //if adding at the first spot, just call addFirst
        else if (x == size()) addLast(data); //if adding at the last spot or beyond, just call addLast
        else {
            LLNode<E> newNode = new LLNode<>(data);
            LLNode<E> previousNode = first;

            for (int i = 1; i < x; i++) { //while i < index provided
                previousNode = newNode;
                newNode = newNode.getNext();
            }
            newNode.setNext(previousNode.getNext());
            previousNode.setNext(newNode);
        }
    }

    /**
     * Sets a node's data at a certain index.
     *
     * @param x    int Index to set at
     * @param data E Data to set to
     * @return E The replaced data, if any, or null
     */
    public E set(int x, E data) {
        if (getNodeFromIndex(x).getData() == null) {
            getNodeFromIndex(x).setData(data); //set the data to provided, since it's blank.
            return null;
        } else {
            E oldData = getNodeFromIndex(x).getData();
            getNodeFromIndex(x).setData(data);
            return oldData;
        }
    }

    /**
     * Prints the list forwards, for a certain number of prints.
     *
     * @param numToPrint Number of values to print, >size will print in a loop.
     * @return String to print list
     */
    public String printForwards(int numToPrint) {
        LLNode<E> item = first;
        String finalString = "";
        if (size() == 0) return finalString; //don't print if list empty

        for (int i = 0; i < numToPrint; i++) {
            finalString += item.toString() + "\n";
            item = item.getNext();
        }

        return finalString;
    }

    /**
     * Prints the list backwards, for a certain number of prints.
     *
     * @param numToPrint Number of values to print, >size will print in a loop.
     * @return String to print list
     */
    public String printBackwards(int numToPrint) {
        LLNode<E> item = last;
        String finalString = "";
        if (size() == 0) return finalString; //don't print if list empty

        for (int i = 0; i < numToPrint; i++) {
            finalString += item.toString() + "\n";
            item = item.getPrevious();
        }
        return finalString;
    }

    @Override
    public String toString() {
        return printForwards(size());
    }
}