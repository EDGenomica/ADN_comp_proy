public class LinkedListGeneric <T extends Comparable<T>> {
    public NodeGeneric<T> head;
    public int size;

    public LinkedListGeneric(){
        this(null);
    }
    public LinkedListGeneric(NodeGeneric<T> h){
        head = h;
    }

    public void insert(T item){
        NodeGeneric<T> ptr, prev;
        ptr = head;
        prev = null;
        while(ptr != null && ptr.getData().compareTo(item)<0){
            prev = ptr;
            ptr = ptr.getNext();
        }
        if(ptr== null || !(ptr.getData().equals(item))){
            NodeGeneric<T> newp = new NodeGeneric<>(item);
            newp.setData(item);
            newp.setNext(ptr);
            if(prev == null)
                head = newp;
            else
                prev.setNext(newp);
        }
        size ++;
    }
}