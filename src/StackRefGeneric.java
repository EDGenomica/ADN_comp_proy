public class StackRefGeneric<T> {
    public NodeGeneric<T> top;
    public int size;
    //Constructor
    public StackRefGeneric(){
        top = null;
    }
    //Value returning Methods
    public boolean isEmpty(){
        return top == null;
    }
    public T pop() {
        if (isEmpty())
            throw new RuntimeException("Stack is Empty");
        T data = top.getData();
        top = top.getNext();
        size--;
        return data;
    }
    //Void methods
    public void push(T sub) {
        NodeGeneric<T> newp = new NodeGeneric<T>(sub);
        newp.setNext(top);
        top = newp;
        size++;
    }
    public String peek(){
        return String.valueOf(top.getData());
    }
}