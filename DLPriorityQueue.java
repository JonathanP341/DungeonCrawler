/**
 * DLPriorityQueue
 * Implements the interface PriorityQueueADT to make a queue using a doubly linked list
 *
 * @author Jonathan Peters
 * @version 1.0, 21/03/23
 */
public class DLPriorityQueue<T> implements PriorityQueueADT<T> {

    private DLinkedNode<T> front;
    private DLinkedNode<T> rear;
    private int count;

    /**
     * The constructor for the priority queue
     */
    public DLPriorityQueue() {
        front = null;
        rear = null;
        count = 0;
    }

    /**
     * Add
     * Adding a new node into the priority by comparing its priority to that of the other nodes in the queue then adding in the proper spot
     *
     * @param dataItem item to be added onto the priority queue
     * @param priority
     */
    public void add(T dataItem, double priority) {
        DLinkedNode<T> curr = front;
        DLinkedNode<T> newNode = new DLinkedNode<T>(dataItem, priority);
        boolean found = false;
        while (curr != null && found == false) { //Looping until we finish the loop or find the right node
            if (curr.getPriority() > priority) {
                found = true;
            } else {
                curr = curr.getNext();
            }
        }
        if (front == null) { //If the list is empty
            front = newNode;
            rear = newNode;
        } else { //If not empty
            if (curr == front) { //If it has the smallest prio in the queue
                newNode.setNext(front);
                front.setPrev(newNode);
                front = newNode;
            } else if (curr == null) { //Highest priority in the queue
                newNode.setPrev(rear);
                rear.setNext(newNode);
                rear = newNode;
            } else { //If its in the middle of the queue
                newNode.setNext(curr);
                newNode.setPrev(curr.getPrev());
                curr.getPrev().setNext(newNode);
                curr.setPrev(newNode);
            }
        }
        count++; //Increasing count
    }

    /**
     * Remove
     * Private helper method remove to remove a given node based off 4 possible situations
     *
     * @param curr
     */
    private void remove(DLinkedNode<T> curr) {
        if (curr == front && curr == rear) { //If its the only element in the queue
            front = null;
            curr = null;
        }
        else if (curr == front) { //If its front
            front = front.getNext();
            front.setPrev(null);
        }
        else if (curr == rear) { //If its rear
            rear = rear.getPrev();
            rear.setNext(null);
        } else { //If its any element in the middle
            curr.getPrev().setNext(curr.getNext());
            curr.getNext().setPrev(curr.getPrev());
        }
    }

    /**
     * <b>Update Priority</b>
     * Updating the priority of a node by removing the node with the same data item then adding another node
     *
     * @param dataItem item whose priority is to be changed
     * @param newPriority  value of the new priority for this data item
     * @throws InvalidElementException
     */
    public void updatePriority(T dataItem, double newPriority) throws InvalidElementException {
        DLinkedNode<T> curr = front; //Variable to loop through the list
        boolean found = false;
        while (curr != null && found == false) {
            if (curr.getDataItem().equals(dataItem)) {
                found = true;
            } else {
                curr = curr.getNext();
            }
        }
        if (found == true) {
            remove(curr); //Removing the element
            add(dataItem, newPriority); //Adding a new element with a different priority but same item
        } else {
            throw new InvalidElementException("DataItem not present in queue");
        }
    }

    /**
     * Remove Min
     * Removing the smallest element which is at the front of the queue
     *
     * @return T - the data item found at the node being removed
     * @throws EmptyPriorityQueueException
     */
    public T removeMin() throws EmptyPriorityQueueException {
        T dataItem;
        if (isEmpty()) {
            throw new EmptyPriorityQueueException("Empty queue");
        } else if (front == null) {
            throw new EmptyPriorityQueueException("Front is null");
        } else {
            dataItem = front.getDataItem();
            if (front.equals(rear)) {
                front = null;
                rear = null;
            } else {
                front = front.getNext();
                front.setPrev(null);
            }

        }
        count--;
        return dataItem;
    }

    /**
     * Is empty
     * Returning if its empty or not
     *
     * @return boolean - is it empty
     */
    public boolean isEmpty() {
        return (front == null);
    }

    /**
     * Size
     * Finds the size of the queue
     *
     * @return int - size of the queue
     */
    public int size() {
        return count;
    }

    /**
     * To String
     * Returns a visual representation of the queue
     *
     * @return string - visual representation of the queue
     */
    public String toString() {
        String s = "";
        DLinkedNode<T> curr = front;
        while (curr != null) {
            s += curr.getDataItem();
            curr = curr.getNext();
        }
        return s;
    }

    /**
     * Get Rear
     * Gets the last element in the queue or the one with the smallest priority
     *
     * @return DLinkedNode
     */
    public DLinkedNode<T> getRear() {
        return rear;
    }

}
