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

	// ----------------------------------------**Main Method
	// Class**-----------------------------------------------------

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		mainMenu();
	}

	// ----------------------------------------**Menu Print
	// Methods**-----------------------------------------------------
	// Menu principal
	public static void mainMenuPrint() {
		System.out.println("*********************** Bienvenido a ADA Solutions *****************************");
		System.out.println("**************** Sistema de gestión y análisis de ADN **************************");
		System.out.println("// Seleccione una opción (1-4) o 0 para salir:");
		System.out.println("1.Comparación de secuencias de ADN - porcentaje de Similaridad.");
		System.out.println("2.SubString(s) más frecuente en una secuencia.");
		System.out.println("3.Ocurrencia de un SubString en una secuencia.");
		System.out.println("4.Complemento reverso de una secuencia.");
		System.out.println("0: Salir.");
		System.out.println("*******************************************************************************");
	}

	// Menú comparación de secuencias
	public static void compMenuPrint() {
		System.out.println("*********************** Bienvenido a ADA Solutions *****************************");
		System.out.println("**************** Sistema de gestión y análisis de ADN **************************");
		System.out.println("---------------- Sección de comparación de Secuencias -------------------------");
		System.out.println("** Seleccione el par de secuencias a comparar: ");
		System.out.println("1.Short sequences                              ---- Orden: mil bases");
		System.out.println("2.Beta Globin Locus (Human and mouse genomes)  ---- Orden: 10 mil bases");
		System.out.println("3.Mnd2 Locus (Human and mouse genomes)         ---- Orden: 100 mil bases");
		System.out.println("4.Vibrio Cholerae & E-Coli                     ---- Orden: 1-4 millones de bases");
		System.out.println("0: Volver.");
		System.out.println("********************************************************************************");
	}

	// Menú Substring más frecuente
	public static void subFreqMenuPrint() {
		System.out.println("*********************** Bienvenido a ADA Solutions *****************************");
		System.out.println("**************** Sistema de gestión y análisis de ADN **************************");
		System.out.println("---------------- Sección de Substring más frecuente  --------------------------");
		System.out.println("** Seleccione la secuencia que desea evaluar: ");
		System.out.println("1.Short sequence                               ---- Orden: mil bases");
		System.out.println("2.Beta Globin Locus                            ---- Orden: 10 mil bases");
		System.out.println("3.Mnd2 Locus                                   ---- Orden: 100 mil bases");
		System.out.println("4.Vibrio Cholerae                              ---- Orden: 1 millón de bases");
		System.out.println("5.E-Coli                                       ---- Orden: 4 millones de bases");
		System.out.println("6.Lynx Canadiensis                             ---- Orden: 6 millones de bases");
		System.out.println("0: Volver.");
		System.out.println("**********************************************************************************");
	}

	// Menú ocurrencia de SubString
	public static void occSubMenuPrint() {
		System.out.println("*********************** Bienvenido a ADA Solutions *****************************");
		System.out.println("****************** Sistema de gestión y análisis de ADN ************************");
		System.out.println("----------- Sección de ocurrencia de Substring en secuencia  --------------------");
		System.out.println("** Seleccione la secuencia que desea evaluar: ");
		System.out.println("1.Short sequence                               ---- Orden: mil bases");
		System.out.println("2.Beta Globin Locus                            ---- Orden: 10 mil bases");
		System.out.println("3.Mnd2 Locus                                   ---- Orden: 100 mil bases");
		System.out.println("4.Vibrio Cholerae                              ---- Orden: 1 millón de bases");
		System.out.println("5.E-Coli                                       ---- Orden: 4 millones de bases");
		System.out.println("6.Lynx Canadiensis                             ---- Orden: 6 millones de bases");
		System.out.println("0: Volver.");
		System.out.println("****************************************************************************");
	}

	// Menú Reverse Compliment
	public static void revSecMenuPrint() {
		System.out.println("*********************** Bienvenido a ADA Solutions *****************************");
		System.out.println("******************* Sistema de gestión y análisis de ADN ***********************");
		System.out.println("---------------- Sección Complemento Reverso de secuencia ---------------------");
		System.out.println("** Seleccione la secuencia que desea evaluar: ");
		System.out.println("1.Short sequence                               ---- Orden: mil bases");
		System.out.println("2.Beta Globin Locus                            ---- Orden: 10 mil bases");
		System.out.println("3.Mnd2 Locus                                   ---- Orden: 100 mil bases");
		System.out.println("4.Vibrio Cholerae                              ---- Orden: 1 millón de bases");
		System.out.println("5.E-Coli                                       ---- Orden: 4 millones de bases");
		System.out.println("6.Lynx Canadiensis                             ---- Orden: 6 millones de bases");
		System.out.println("0: Volver.");
		System.out.println("****************************************************************************");
	}

	// *************************************** Menu Paths Methods
	// *******************************************************
	public static void mainMenu() {
		boolean q = true;
		Scanner sc = new Scanner(System.in);
		mainMenuPrint();
		int option = sc.nextInt();
		while (q) {
			if (option == 1) {
				compMenu();
				mainMenu();
			} else if (option == 2) {
				subFreqMenu();
				mainMenu();
			} else if (option == 3) {
				occSubMenu();
				mainMenu();
			} else if (option == 4) {
				revSeqMenu();
				mainMenu();
			} else if (option == 0) {
				q = false;
				System.exit(0);
			} else {
				System.out.println("Ingrese una opción válida");
				mainMenuPrint();
			}
		}

	}

	public static void compMenu() {
		Scanner sc = new Scanner(System.in);
		compMenuPrint();
		int testNum = sc.nextInt();
		if ((testNum < 0) || (testNum > 4)) {
			System.out.println("Por favor ingrese 1, 2, 3, 4 o 0\n");
			compMenu();
		} else if (testNum == 0) {
			mainMenu();
		}
		// Llamado a la función que lee las secuencias.
		String s1 = readSeq("data/test_data_comp/" + "case" + testNum + "-s1.txt");
		String s2 = readSeq("data/test_data_comp/" + "case" + testNum + "-s2.txt");

		// Se imprime el número de bases(caracteres) que tiene cada secuencia.
		if (s1 != null && s2 != null) {
			System.out.println("S-1: " + s1.length() + " bases");
			System.out.println("S-2: " + s2.length() + " bases");
		}
		// Se obtiene la longitud de emparejamiento para hacer la comparación.
		System.out.print("Por favor ingrese la longitud de emparejamiento: ");
		int matchLength = sc.nextInt();
		// Se crea Stack de referencia que almacenará los resultados.
		StackRefGeneric<String> commonSubs;
		long startTime, estimatedTime;

		// Verificación no nulidad de cadenas.
		if (s1 == null || s2 == null) {
			throw new AssertionError();
		} else {
			// Inicia la medición de tiempo.
			startTime = System.nanoTime();
			// Llamado a función principal de comparación
			commonSubs = SubStringMatch(s1, s2, matchLength);
			// Finaliza medición de tiempo.
			estimatedTime = System.nanoTime() - startTime;
		}
		// Contador de substrings comunes.
		int countCommon = 0;

		while (!commonSubs.isEmpty()) {
			// Pop del Stack de las substrings comunes para imprimirlas una a una.
			System.out.print(commonSubs.pop() + " ");
			countCommon++;
		}
		System.out.print("\nSe encontraron " + countCommon + " subsecuencias comunes.\n");
		// System.out.println(
		// "s1 tiene una similitud del " + countCommon * 100 / (s1.length() -
		// matchLength + 1) + "% con s2.\n");
		System.out.println("Elapsed Time:" + estimatedTime + "\n\n");
	}

	public static void subFreqMenu() {
		Scanner sc = new Scanner(System.in);
		subFreqMenuPrint();
		int testNum = sc.nextInt();
		if ((testNum < 0) || (testNum > 6)) {
			System.out.println("Por favor ingrese 1, 2, 3, 4, 5, 6 o 0");
			subFreqMenu();
		}
		if (testNum == 0) {
			mainMenu();
		}
		StackRefGeneric<String> freqSubs;
		String s1 = readSeq("data/test_data_sec/" + "case" + testNum + ".txt");
		if (s1 != null)
			System.out.println("S-1: " + s1.length() + " bases");
		System.out.print("Por favor ingrese la longitud de subsecuencia deseada: ");
		int subLength = sc.nextInt();
		if (s1 == null) {
			throw new AssertionError();
		} else {
			freqSubs = FindingFrequentSubsBySorting(s1, subLength);
		}
		while (!freqSubs.isEmpty())
			// Pop del Stack de las substrings comunes para imprimirlas una a una.
			System.out.print(freqSubs.pop() + "  ");
		System.out.println("\n");
	}

	public static void occSubMenu() {
		Scanner sc = new Scanner(System.in);
		occSubMenuPrint();
		int testNum = sc.nextInt();
		if ((testNum < 0) || (testNum > 6)) {
			System.out.println("Por favor ingrese 1, 2, 3, 4, 5, 6 o 0");
			occSubMenu();
		}
		if (testNum == 0) {
			mainMenu();
		}
		QueueRefGeneric<Integer> occSubs;
		String s1 = readSeq("data/test_data_sec/" + "case" + testNum + ".txt");
		if (s1 != null)
			System.out.println("S-1: " + s1.length() + " bases");
		System.out.print("Por favor ingrese la subsecuencia que desea buscar: ");
		String occ = sc.next();
		if (s1 == null) {
			throw new AssertionError();
		} else {
			occSubs = PatternMatching(s1, occ.toUpperCase());
		}
		if (!occSubs.isEmpty()) {
			System.out.println("La subsecuencia se encontr� en las posiciones:");
			while (!occSubs.isEmpty())
				System.out.print(occSubs.dequeue() + " ");
		} else
			System.out.println("No se encuentra la subsecuencia.");
		System.out.println("\n");
	}

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
		System.out.println("\nEl complemeto inverso de la secuencia es:");
		System.out.println(rev);
	}

	// *************************** Finding Frequent Subs Method
	// *******************************************************

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
		int maxCount = maxArr(count);
		System.out.println("\nMAX COUNT " + maxCount);
		for (int i = 0; i < text.length() - k; i++) {
			if (count[i] == maxCount && index.get(i) != null) {
				String pattern = index.get(i);
				freqPatterns.push(pattern);
			}
		}
		if (!freqPatterns.isEmpty())
			System.out.println("\nLas subsecuencias más frecuentes de la cadena aparecen " + maxCount + " veces:");
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
	// ***************************Pattern Matching
	// Method*********************************************************

	public static QueueRefGeneric<Integer> PatternMatching(String genome, String pattern) {
		QueueRefGeneric<Integer> indexes = new QueueRefGeneric<>();
		for (int i = 0; i < genome.length() - pattern.length(); i++) {
			if (genome.substring(i, i + pattern.length()).equals(pattern))
				indexes.enqueue(i);
		}
		return indexes;
	}

	// ***************************Reverse Compliment
	// Method*********************************************************

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
		ListArrayGeneric<String> substring_arr1 = new ListArrayGeneric<String>(substring_num1);
		ListArrayGeneric<String> substring_arr2 = new ListArrayGeneric<String>(substring_num2);
		// Se ingresa cada substring en el respectivo arreglo

		// S2
		for (int i = 0; i < substring_num2; i++) {
			String substring2 = s2.substring(i, i + matchLength);
			substring_arr2.insert(substring2);
		}

		// Se imprime la cantidad de substrings en cada arreglo
		System.out.print("\nExisten " + substring_num1 + " subcadenas de longitud " + matchLength + " en s1.");
		System.out
				.println("\nExisten " + substring_arr2.count + " subcadenas de longitud " + matchLength + " en s2.\n");

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

	public static void convertToCSV(StackRefGeneric<String> commonSubs, int num) throws IOException {
		try (PrintWriter writer = new PrintWriter(new File("results/comp/data"+num+".csv"))) {
			StringBuilder sb = new StringBuilder();
			while (!commonSubs.isEmpty()) {
				// Pop del Stack de las substrings comunes para imprimirlas una a una.
				sb.append(commonSubs.pop());
			}
			writer.write(sb.toString());
			System.out.println("done!");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
}
