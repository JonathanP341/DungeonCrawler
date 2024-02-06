/**
 * DLinkedNode
 * Creates the nodes that will be used to implement the priority queue
 *
 * @author Jonathan Peters
 * @version 1.0, 21/03/23
 */
public class DLinkedNode<T> {
    private T dataItem;
    private double priority;
    private DLinkedNode<T> next;
    private DLinkedNode<T> prev;

    /**
     * Constructor for doubly linked node given a data item and priority
     *
     * @param data
     * @param prio
     */
    public DLinkedNode(T data, double prio) {
        dataItem = data;
        priority = prio;
        next = null;
        prev = null;
    }

    /**
     * Creating a doubly linked node with no prio or dataItem
     */
    public DLinkedNode() {
        dataItem = null;
        priority = 0;
        next = null;
        prev = null;
    }
    
    //Getters

    /**
     * Getting priority
     *
     * @return double - the priority
     */
    public double getPriority() {
        return priority;
    }

    /**
     * Getting the data item
     * @return T - the dataItem
     */
    public T getDataItem() {
        return dataItem;
    }

    /**
     * Getting the next node in the linked list
     * @return DLinkedNode<T>
     */
    public DLinkedNode<T> getNext() {
        return next;
    }

    /**
     * Getting the previous node
     * @return DLinkedNode
     */
    public DLinkedNode<T> getPrev() {
        return prev;
    }
    
    //Setters

    /**
     * Setting the priority
     * @param prio
     * @return void
     */
    public void setPriority(double prio) {
        priority = prio;
    }

    /**
     * Setting the data item
     * @param elem
     */
    public void setDataItem(T elem) {
        dataItem = elem;
    }

    /**
     * Setting the new node
     * @param newNode - the node to set as the next one
     */
    public void setNext(DLinkedNode<T> newNode) {
        next = newNode;
    }

    /**
     * Setting the previous node
     * @param newNode - the node to set as previous
     */
    public void setPrev(DLinkedNode<T> newNode) {
        prev = newNode;
    }
}
