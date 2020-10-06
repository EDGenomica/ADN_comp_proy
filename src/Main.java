import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        String s1 = null;
        String s2 = null;
        Scanner sc = new Scanner(System.in);
        int testNum = 1;
        System.out.print("¿Cuál data set desea elegir (1,2, or 3)? ");
        testNum = sc.nextInt();
        if ((testNum < 1) || (testNum > 3)) {
            System.out.println("Por favor ingrese 1,2, o 3");
            return;
        }
        s1 = readSeq("test_data/"+"case" + testNum + "-s1");
        s2 = readSeq("test_data/"+"case" + testNum + "-s2");
        System.out.println("S-1: " + s1.length() + " bases");
        System.out.println("S-2: " + s2.length() + " bases");

        //Se obtiene la longitud de emparejamiento para hacer la comparación
        //
        System.out.print("Por favor ingrese la longitud de emparejamiento: ");
        int matchLength = sc.nextInt();
        List<String> res = solve(s1, s2, matchLength);

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


    private static List<String> solve(String s1, String s2, int matchLength) {
        List<String> res = new ArrayList<>();
        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();
        int l = ch1.length;
        int m = ch2.length;
        int[][] L = new int[l+1][m+1];

        for(int i= 1; i <= l; i++)
        {
            for(int j = 1; j <= m; j++)
            {
                if(ch1[i-1] == ch2[j-1])
                    L[i][j] = L[i-1][j-1] + 1;

                else
                    L[i][j] = 0;

                StringBuilder sb = new StringBuilder();
                if(L[i][j] == matchLength+1) {
                    int ci = i, cj = j;
                    while(L[ci][cj] > 0) {
                        L[ci][cj] = 0;
                        sb.insert(0, ch1[ci-1]);
                        ci--;cj--;
                    }
                    sb.deleteCharAt(sb.length() - 1);
                    res.add(sb.toString());
                }
            }
        }
        /*
        for(int i=L.length-1; i>=0; i--) {
            for(int j=L[0].length-1;j>=0; j--) {
                StringBuilder sb = new StringBuilder();
                if(L[i][j] == matchLength+1) {
                    int ci = i, cj = j;
                    while(L[ci][cj] > 0) {
                        L[ci][cj] = 0;
                        sb.insert(0, ch1[ci-1]);
                        ci--;cj--;
                    }
                    sb.deleteCharAt(sb.length() - 1);
                    res.add(sb.toString());
                }
            }
        }*/
        return res;
    }
}
