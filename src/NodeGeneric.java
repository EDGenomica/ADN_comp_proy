public class NodeGeneric<T> {
    private final T data;
    private NodeGeneric<T> next;
    //Constructors
    public NodeGeneric(T data){
        this.data = data;
        next = null;
    }
    //Getters
    public T getData(){
        return data;
    }
    public NodeGeneric <T> getNext(){
        return next;
    }
    //Setters
    public void setNext(NodeGeneric<T> next){
        this.next = next;
    }
}