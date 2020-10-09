import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("¿Cuál data set desea elegir (1,2, or 3)? ");
        int testNum = sc.nextInt();
        if ((testNum < 1) || (testNum > 3)) {
            System.out.println("Por favor ingrese 1,2, o 3");
            return;
        }
        String s1 = readSeq("test_data/"+"case" + testNum + "-s1");
        String s2 = readSeq("test_data/"+"case" + testNum + "-s2");
        if (s1 != null && s2 != null){
            System.out.println("S-1: " + s1.length() + " bases");
            System.out.println("S-2: " + s2.length() + " bases");
        }

        //Se obtiene la longitud de emparejamiento para hacer la comparación
        //
        System.out.print("Por favor ingrese la longitud de emparejamiento: ");
        int matchLength = sc.nextInt();
        long startTime = System.nanoTime();
        StackRef res;
        if (s1 == null || s2 == null) {
            throw new AssertionError();
        } else {
            res = SubStringMatch(s1, s2, matchLength);
        }
        long estimatedTime = System.nanoTime() - startTime;
        int countCommon = 0;
        while (!res.isEmpty()){
            System.out.print(res.pop()+" ");
            countCommon++;
        }
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
        sortArrayUsingStacks(substring_arr2);
        //Algoritmo de comparación... por implementar
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

    static void sortArrayUsingStacks(String []arr)
    {
        // Se hace push de los elementos del array al stack
        int n = arr.length;
        StackRef input = new StackRef();
        for (String s : arr) input.push(s);

        //Se ordena el stack utilizando la función sortStack
        StackRef sortedStack = sortStack(input);

        // Se asignan los elementos del Stack ordenado al array
        for (int i = 0; i < n; i++)
        {
            arr[i] = sortedStack.peek();
            sortedStack.pop();
        }
    }

    static StackRef sortStack(StackRef input)
    {
        StackRef tmpStack = new StackRef();

        while (!input.isEmpty())
        {
            // Se almacena el elemento top del input stack en una variable temporal
            // Luego se le hace pop al input
            String tmp = input.peek();
            input.pop();

            // Mientras tmpStack no esté vacío y su top sea menor a temp
            while (!tmpStack.isEmpty() && tmpStack.peek().compareTo(tmp) < 0)
            {
                // se hace pop del tempStack y se empuja(push) al input stack
                input.push(tmpStack.peek());
                tmpStack.pop();
            }
            tmpStack.push(tmp);
        }
        return tmpStack;
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

}
