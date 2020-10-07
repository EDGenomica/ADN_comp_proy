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
        List<String> res = SubStringMatch(s1, s2, matchLength);

        System.out.println("Se encontraron "+ res.size() + " subsecuencias comunes:");
        System.out.println(res);

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


    private static List<String> SubStringMatch(String s1, String s2, int matchLength) {
        //ArrayList donde se almacenan las substrings que coinciden
        List<String> res = new ArrayList<>();
        //Num de posibles substrings de longitud matchLength que existen en cada cadena
        int substring_num1 = s1.length() - matchLength + 1;
        int substring_num2 = s1.length() - matchLength + 1;
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
            substring_arr1[i] = substring2;
        }
        //Se imprime la cantidad de substrings en cada arreglo
        System.out.println("\nExisten "
                + substring_arr1.length + " subcadenas de longitud " + matchLength +" en s1.");
        System.out.println("\nExisten "
                + substring_arr2.length + " subcadenas de longitud " + matchLength +" en s1.");
        //Llamado a función de ordenamiento SortArray(Stack o Cola) que retorne el arreglo ordenado
        //Solo se ordena el arreglo 2
        //String[] ordered_arr2 = SortArray(substring_arr2);

        //Algoritmo binario de comparación... por implementar
        return res;
    }
}
