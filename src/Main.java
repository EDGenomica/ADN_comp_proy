import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //El usuario selecciona el conjunto de datos a comparar.
        System.out.print("¿Cuál data set desea elegir (1, 2, 3 o 4)? ");
        int testNum = sc.nextInt();
        //Agregar función de Julian.
        if ((testNum < 1) || (testNum > 4)) {
            System.out.println("Por favor ingrese 1, 2, 3 o 4");
            return;
        }
        //Llamado a la función que lee las secuencias.
        String s1 = readSeq("test_data/"+"case" + testNum + "-s1");
        String s2 = readSeq("test_data/"+"case" + testNum + "-s2");
        //Se imprime el número de bases(caracteres) que tiene cada secuencia.
        if (s1 != null && s2 != null){
            System.out.println("S-1: " + s1.length() + " bases");
            System.out.println("S-2: " + s2.length() + " bases");
        }
        //Se obtiene la longitud de emparejamiento para hacer la comparación.
        System.out.print("Por favor ingrese la longitud de emparejamiento: ");
        int matchLength = sc.nextInt();
        //Inicia la medición de tiempo.
        long startTime = System.nanoTime();
        //Se crea Stack de referencia que almacenará los resultados.
        StackRef res;
        //Verificación no nulidad de cadenas.
        if (s1 == null || s2 == null) {
            throw new AssertionError();
        } else {
            //Llamado a función principal de comparación
            res = SubStringMatch(s1, s2, matchLength);
        }
        //Finaliza medición de tiempo.
        long estimatedTime = System.nanoTime() - startTime;
        //Contador de substrings comunes.
        int countCommon = 0;
        while (!res.isEmpty()){
            //Pop del Stack de las substrings comunes para imprimirlas una a una.
            System.out.print(res.pop()+" ");
            countCommon++;
        }
        //
        System.out.println("\nSe encontraron " + countCommon + " subsecuencias comunes.\n");
        System.out.println("Elapsed Time:"+ estimatedTime);
    }

    public static String readSeq(String fileName) {
        BufferedReader br;
        // Create a reader for the file
        try {
            InputStream in = new FileInputStream(fileName);
            br = new BufferedReader(new InputStreamReader(in));
        }
        catch (IOException e) {
            System.out.println("IOException while opening " +
                    fileName + "\n" + e);
            return null;
        }
        StringWriter buffer = new StringWriter();
        // Accumulate each line of the file (minus surrounding whitespace)
        // sequentially in a string buffer.  Convert to lower case as we read.
        //
        try {
            boolean stop = false;
            while (!stop)
            {
                String nextline = br.readLine();
                if (nextline == null) // end of file
                    stop = true;
                else
                {
                    String seq = nextline.trim();
                    buffer.write(seq.toLowerCase());
                }
            }
        }
        catch (IOException e) {
            System.out.println("IOException while reading sequence from " +
                    fileName + "\n" + e);
            return null;
        }
        return buffer.toString();
    }

    private static int PatternCount(String s, String p){
        int count = 0;
        for(int i = 0; i < s.length()-p.length(); i++){
            String sub = s.substring(i, i+ p.length());
            if(sub.equals(p))
                count ++;
        }
        return count;
    }

    private ListArrayGeneric<String> FreqSubstrings(String s, int k){
        ListArrayGeneric<String> FreqSubs = new ListArrayGeneric();
        int numOfSubs = s.length() - k + 1;
        int[] counts = new int[numOfSubs];
        for (int i = 0; i < s.length()-k; i++){
            String pattern = s.substring(i, i+k);
            counts[i] = PatternCount(s, pattern);
        }
        int maxCount = maxArr(counts);
        for (int i = 0; i < s.length()-k; i++){
            if (counts[i] == maxCount)
                FreqSubs.insert(s.substring(i, i+k));
        }
        return FreqSubs;
    }

    static int maxArr(int[] arr)
    {
        int i;
        // Initialize maximum element
        int max = arr[0];
        // Traverse array elements from second and
        // compare every element with current max
        for (i = 1; i < arr.length; i++)
            if (arr[i] > max)
                max = arr[i];
        return max;
    }

    private static StackRef SubStringMatch(String s1, String s2, int matchLength) {
        //ArrayList donde se almacenan las substrings que coinciden
        StackRef res = new StackRef();
        //Num de posibles substrings de longitud matchLength que existen en cada cadena
        int substring_num1 = s1.length() - matchLength + 1;
        int substring_num2 = s2.length() - matchLength + 1;
        //Se crean los arreglos para almacenar los substrings de cada cadena con las longitudes calculadas anteriormente.
        String[] substring_arr1 = new String[substring_num1];
        String[] substring_arr2 = new String[substring_num2];
        //Se ingresa cada substring en el respectivo arreglo
        //S1
        for (int i = 0; i < s1.length() - matchLength + 1; i++) {
            String substring1 = s1.substring(i, i + matchLength);
            substring_arr1[i] = substring1;
        }
        //S2
        for (int i = 0; i < s2.length() - matchLength + 1; i++) {
            String substring2 = s2.substring(i, i + matchLength);
            substring_arr2[i] = substring2;
        }

        //Se imprime la cantidad de substrings en cada arreglo
        System.out.println("\nExisten " + substring_arr1.length + " subcadenas de longitud " + matchLength +" en s1.");
        System.out.println("\nExisten " + substring_arr2.length + " subcadenas de longitud " + matchLength +" en s2.\n");

        //Llamado a función de ordenamiento
        Quicksort(substring_arr2, 0, substring_num2-1);
        //Algoritmo binario de comparación
        for(int i=0; i < substring_num1; i++){
            int j = binarySearch(substring_arr2,substring_arr1[i]);
            if (j == 1)
                res.push(substring_arr1[i]);
        }
        return res;
    }

    public static int binarySearch(String[] arr, String x)
    {
        int l = 0, r = arr.length - 1, c = 0;
        while (l <= r) {
            int m = l + (r - l) / 2;
            // Check if x is present at mid
            if (arr[m].equals(x))
                return 1;
            // If x greater, ignore left half
            if (arr[m].compareTo(x) < 0)
                l = m + 1;
            // If x is smaller, ignore right half
            else
                r = m - 1;
        }
        // el substring no está presente
        return c;
    }

    //QuickSort algorithm

    static int partition(String arr[], int low, int high)
    {
        String pivot = arr[high];
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++)
        {
            // If current element is smaller than the pivot
            if (arr[j].compareTo(pivot) < 0)
            {
                i++;

                // swap arr[i] and arr[j]
                String temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        String temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;
    }

    /* The main function that implements QuickSort()
      arr[] --> Array to be sorted,
      low  --> Starting index,
      high  --> Ending index */
    static void Quicksort(String arr[], int low, int high)
    {
        if (low < high)
        {
            /* pi is partitioning index, arr[pi] is
              now at right place */
            int pi = partition(arr, low, high);

            // Recursively sort elements before
            // partition and after partition
            Quicksort(arr, low, pi-1);
            Quicksort(arr, pi+1, high);
        }
    }

    //Classes------------------------------------------------------

    public static class NodeGeneric<T> {
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
        public NodeGeneric getNext(){
            return next;
        }
        public void setNext(NodeGeneric<T> next){
            this.next = next;
        }
    }

    public static class StackRef {
        public NodeGeneric top;
        //Constructor
        public StackRef(){
            top = null;
        }
        //Value returning Methods
        public boolean isEmpty(){
            return top == null;
        }
        public String pop() {
            if (isEmpty())
                throw new RuntimeException("Stack is Empty");
            String s = String.valueOf(top.getData());
            top = top.getNext();
            return s;
        }
        //Void methods
        public void push(String sub) {
            NodeGeneric<String> newp = new NodeGeneric<>(sub);
            newp.setNext(top);
            top = newp;
        }
        public String peek(){
            return String.valueOf(top.getData());
        }
    }

    public class ListArrayGeneric<T extends Comparable<T>> {

        private final int N = 5;
        private int position, count;
        private T[] larray;
        T reference;

        //Constructor
        public ListArrayGeneric() {
            count = 0;
            larray = (T[])new Comparable[N];
        }
        //Value returning methods
        private boolean empty() {
            return count <= 0;
        }
        private boolean full() {
            return count >= N;
        }
        public boolean delete(T item) {
            boolean deleted = false;
            if(!empty()) {
                if(search(item)) {
                    for(int j = position; j < count-1; j++)
                        larray[j] = larray[j-1];
                    count--;
                    deleted = true;
                }
            }
            else {
                throw new RuntimeException("List is Empty");
            }
            return deleted;
        }
        public boolean insert(T item){
            boolean inserted = false;
            if(!full()){
                //Does not insert duplicates
                if(!search(item)){
                    for(int j = count; j > position; j--)
                        larray[j] = larray[j+1];
                    larray[position] = item;
                    count++;
                    inserted = true;
                }
            }
            else
                throw new RuntimeException("List is Full");
            return inserted;
        }
        private boolean search(T item) {
            boolean found = false, stop = false;
            int position = 0;
            while (position < count && !stop){
                if(larray[position].compareTo(item) >= 0){
                    stop = true;
                    if(larray[position].compareTo(item) == 0)
                        found = true;
                }
                else
                    position++;
            }
            return found;
        }

        public void output() {
            System.out.print("List: ");
            int j = 0;
            while(j != count) {
                System.out.print(larray[j]+" ");
                j++;
            }
            System.out.println();
        }
        public int CompareTo(T item){
            int result;
            if(reference.compareTo(item) > 0)
                result = 1;
            else if (reference.compareTo(item) < 0)
                result = -1;
            else
                result = 0;
            return result;
        }
    }

}
