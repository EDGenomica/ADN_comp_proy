public class QueueRefGeneric<T> {
    public NodeGeneric<T> front, rear;
    //Constructor
    public QueueRefGeneric(){
        front = null;
        rear = null;
    }
    //Value returning Methods
    public boolean isEmpty(){
        return (front == null);
    }
    public T dequeue() {
        if (isEmpty())
            throw new RuntimeException("Queue is Empty");
        T data = front.getData();
        front = front.getNext();
        return data;
    }
    //Void methods
    public void enqueue(T item) {
        NodeGeneric<T> newp = new NodeGeneric<>(item);
        if(rear != null)
            rear.setNext(newp);
        else
            front = newp;
        rear = newp;
    }
}

