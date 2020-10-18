public class ListArrayGeneric<T extends Comparable<T>> {

    public int count;
    private T[] larray;
    T reference;

    //Constructor
    public ListArrayGeneric(int N) {
        count = 0;
        larray = (T[]) new Comparable[N];
    }

    //Value returning methods
    public boolean binarySearch(T item) {
        int l = 0, r = count - 1;
        boolean found = false;
        while (!empty() && l <= r) {
            int m = l + (r - l) / 2;
            // Verifica si el item está presente en el medio.
            if (larray[m].compareTo(item) == 0){
                //El item se encuentra en el arreglo
                delete(m);
                return true;
            }
            // Si el item es mayor, ignora la mitad a la izquierda.
            else if (larray[m].compareTo(item) < 0)
                l = m + 1;
                // Si el item es menor, ignora la mitad a la derecha.
            else
                r = m - 1;
        }
        return found;
    }

    public int compareTo(T item){
        int result;
        if(reference.compareTo(item) > 0)
            result = 1;
        else if (reference.compareTo(item) < 0)
            result = -1;
        else
            result = 0;
        return result;
    }

    public void delete(int position) {

        if(!empty()) {
            for(int j = position; j<count-1; j++)
                larray[j] = larray[j+1];
            count--;
        }
        else
            throw new RuntimeException("List is Empty");
    }

    private boolean empty() {
        return count <= 0;
    }
    private boolean full() {
        return count >= larray.length;
    }

    public T get(int index){
        return larray[index];
    }

    public void insert(T item){
        if(!full()){
            larray[count] = item;
            count++;
        }
        else
            throw new RuntimeException("List is Full");
    }

    //Void returning methods
    public void output() {
        System.out.print("List: ");
        int j = 0;
        while(j != count) {
            System.out.print(larray[j]+" ");
            j++;
        }
        System.out.println();
    }

    public void set(int index, T item){
        larray[index] = item;
    }
}