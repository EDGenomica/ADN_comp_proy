/* *Authors: Julian Quintero, Natalia Monroy, Santiago Rodríguez, Víctor Torres.
 * Date of Creation: Oct 10th 2020
 * Version: v1 - ListArray implementation
 * Program Name: Ada
 * Institution: Universidad Nacional de Colombia
 * Description: hfgfjiodps
 * */

/*Import statements*/
import java.util.*;
import java.io.*;
import java.lang.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

	public static int MAX_COUNT;

	/*
	public static void revSeqMenu() {
		Scanner sc = new Scanner(System.in);
		revSecMenuPrint();
		int testNum = sc.nextInt();
		if ((testNum < 0) || (testNum > 6)) {
			System.out.println("Por favor ingrese 1, 2, 3, 4, 5, 6 o 0");
			revSeqMenu();
		}
		if (testNum == 0) {
			mainMenu();
		}
		String rev;
		String s1 = readSeq("data/test_data_sec/" + "case" + testNum + ".txt");
		if (s1 != null)
			System.out.println("S-1: " + s1.length() + " bases");
		if (s1 == null) {
			throw new AssertionError();
		} else {
			rev = reverseCompliment(s1);
		}
		System.out.println("\nEl complemento inverso de la secuencia es:");
		System.out.println(rev);
	}

	 */

	// *************************** Finding Frequent Subs *************************************

	public static StackRefGeneric<String> FindingFrequentSubsBySorting(String text, int k) {
		StackRefGeneric<String> freqPatterns = new StackRefGeneric<>();
		ListArrayGeneric<String> index = new ListArrayGeneric<>(text.length() - k + 1);
		int[] count = new int[text.length() - k + 1];
		for (int i = 0; i < text.length() - k + 1; i++) {
			String pattern = text.substring(i, i + k);
			index.insert(pattern);
			count[i] = 1;
		}

		// Llamado a función de ordenamiento
		quickSort(index, 0, text.length() - k);

		for (int i = 1; i < text.length() - k; i++) {
			if (index.get(i).equals(index.get(i - 1)))
				count[i] = count[i - 1] + 1;
		}
		MAX_COUNT = maxArr(count);
		for (int i = 0; i < text.length() - k; i++) {
			if (count[i] == MAX_COUNT && index.get(i) != null) {
				String pattern = index.get(i);
				freqPatterns.push(pattern);
			}
		}
		return freqPatterns;
	}

	public static int maxArr(int[] arr) {
		int i;
		int max = arr[0];
		for (i = 1; i < arr.length; i++)
			if (arr[i] > max)
				max = arr[i];
		return max;
	}
	// *******************************Pattern Matching Method********************************

	public static StackRefGeneric<String> PatternMatching(String genome, String pattern) {
		StackRefGeneric<String> indexes = new StackRefGeneric<>();
		for (int i = 0; i < genome.length() - pattern.length(); i++) {
			if (genome.substring(i, i + pattern.length()).equals(pattern))
				indexes.push(String.valueOf(i));
		}
		return indexes;
	}

	// *****************************Reverse Compliment Method*******************************************

	public static String reverseCompliment(String s) {
		Stack comp = new Stack();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == 'A') {
				comp.push('T');
			} else if (s.charAt(i) == 'T') {
				comp.push('A');
			} else if (s.charAt(i) == 'C') {
				comp.push('G');
			} else if (s.charAt(i) == 'G') {
				comp.push('C');
			}
		}
		String rev = "";
		while (!comp.isEmpty()) {
			rev += comp.pop();
		}
		return rev;
	}

	// ***************************SubString Matching
	// Method*********************************************************

	public static StackRefGeneric<String> SubStringMatch(String s1, String s2, int matchLength) {
		// ArrayList donde se almacenan las substrings que coinciden
		StackRefGeneric<String> res = new StackRefGeneric<>();
		// Num de posibles substrings de longitud matchLength que existen en cada cadena
		int substring_num1 = s1.length() - matchLength + 1;
		int substring_num2 = s2.length() - matchLength + 1;
		// Se crean los arreglos para almacenar los substrings de cada cadena con las
		// longitudes calculadas anteriormente.
		ListArrayGeneric<String> substring_arr2 = new ListArrayGeneric<String>(substring_num2);
		// Se ingresa cada substring en el respectivo arreglo

		// S2
		for (int i = 0; i < substring_num2; i++) {
			String substring2 = s2.substring(i, i + matchLength);
			substring_arr2.insert(substring2);
		}

		// Llamado a función de ordenamiento
		quickSort(substring_arr2, 0, substring_arr2.count - 1);

		// Algoritmo binario de comparación
		for (int i = 0; i < substring_num1; i++) {
			String sub = s1.substring(i, i + matchLength);
			boolean j = substring_arr2.binarySearch(sub);
			if (j)
				res.push(sub);
		}

		return res;
	}

	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% Auxiliar Methods
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

	public static String readSeq(String fileName) {
		BufferedReader br;
		// Create a reader for the file
		try {
			InputStream in = new FileInputStream(fileName);
			br = new BufferedReader(new InputStreamReader(in));
		} catch (IOException e) {
			System.out.println("IOException while opening " + fileName + "\n" + e);
			return null;
		}
		StringWriter buffer = new StringWriter();
		// Accumulate each line of the file (minus surrounding whitespace)
		// sequentially in a string buffer. Convert to upper case as we read.
		try {
			boolean stop = false;
			while (!stop) {
				String nextline = br.readLine();
				if (nextline == null)
					stop = true;
				else {
					String seq = nextline.trim();
					buffer.write(seq.toUpperCase());
				}
			}
		} catch (IOException e) {
			System.out.println("IOException while reading sequence from " + fileName + "\n" + e);
			return null;
		}
		return buffer.toString();
	}

	// QuickSort algorithm
	static int partition(ListArrayGeneric<String> arr, int low, int high) {
		String pivot = arr.get(high);
		int i = (low - 1); // index of smaller element
		for (int j = low; j < high; j++) {
			// If current element is smaller than the pivot
			if (arr.get(j).compareTo(pivot) < 0) {
				i++;
				// swap arr[i] and arr[j]
				String temp = arr.get(i);
				arr.set(i, arr.get(j));
				arr.set(j, temp);
			}
		}
		// swap arr[i+1] and arr[high] (or pivot)
		String temp = arr.get(i + 1);
		arr.set(i + 1, arr.get(high));
		arr.set(high, temp);
		return i + 1;
	}

	static void quickSort(ListArrayGeneric<String> arr, int low, int high) {
		if (low < high) {
			// pi es indice de partición
			int pi = partition(arr, low, high);
			// Ordena los elementos antes y después de partición recursivamente
			quickSort(arr, low, pi - 1);
			quickSort(arr, pi + 1, high);
		}
	}

	public static void convertToCSV(StackRefGeneric<String> commonSubs, int num, String folder) throws IOException {
		try (PrintWriter writer = new PrintWriter(new File("results/" +folder +"/data"+num+".csv"))) {
			while (!commonSubs.isEmpty()) {
				// Pop del Stack de las substrings comunes para imprimirlas una a una.
                writer.write(commonSubs.pop());
                writer.write(System.lineSeparator());
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void convertToCSVRev(String Rev, int num) throws IOException {
		try (PrintWriter writer = new PrintWriter(new File("results/rev/data"+num+".txt"))) {
			if (Rev != null) {
				// Pop del Stack de las substrings comunes para imprimirlas una a una.
				writer.write(Rev);
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
}
