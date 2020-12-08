/* *Authors: Natalia Monroy, Santiago Rodríguez.
 * Date of Creation: Oct 10th 2020
 * Version: v1 - ListArray implementation
 * Program Name: ADA Solutions
 * Institution: Universidad Nacional de Colombia
 * Description: ADA es un sistema de gestión y análisis de ADN. Cuenta con 4 funcionalidades que arrojan información
 * detallada sobre cadenas de ADN que facilitan a bioquímicos y genetistas su análisis y comparación.
 * */

/*Import statements*/
import java.util.*;
import java.io.*;
import java.lang.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.CheckedOutputStream;

public class Main {

	public static int MAX_COUNT;

	// *************************** Finding Frequent Subs *************************************

	public static ArrayList<String> FindingFrequentSubsBySorting(String genome, int k) {
		ArrayList<String> freqPatterns = new ArrayList<>();

		HashMapCoded<String, Integer> map = new HashMapCoded<>(genome.length());
		int substring_gen = genome.length() - k + 1;

		for (int i = 0; i < substring_gen; i++) {
			String key = genome.substring(i, i + k);
			map.put(key, i);
		}
		for (int i = 0; i < substring_gen; i++) {
			int count = 0;
			String sub = genome.substring(i, i + k);
			System.out.println();
			Iterator loc = map.get(sub);
			if (loc != null){
				while (loc.hasNext()) {
					count++;
					loc.next();
				}
			}
			if (count > MAX_COUNT)
				MAX_COUNT = count;
		}

		for (int i = 0; i < substring_gen; i++) {
			int count = 0;
			String sub = genome.substring(i, i + k);
			Iterator loc = map.get(sub);
			while (loc.hasNext()) {
				count++;
				loc.next();
			}
			if (count == MAX_COUNT)
				freqPatterns.add(sub);
		}

		freqPatterns = removeDuplicates(freqPatterns);
		return freqPatterns;
	}

	public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list) {
		ArrayList<T> newList = new ArrayList<T>();
		for (T element : list) {
			if (!newList.contains(element))
				newList.add(element);
		}
		return newList;
	}

	// *******************************Pattern Matching Method********************************

	public static ArrayList<String> PatternMatching(String genome, String pattern) {
		ArrayList<String> indexes = new ArrayList<>();
		HashMapCoded<String, Integer> map = new HashMapCoded<>(genome.length());
		int substring_gen = genome.length() - pattern.length() + 1;

		// S2
		for (int i = 0; i < substring_gen; i++) {
			String key = genome.substring(i, i + pattern.length());
			map.put(key, i);
		}

		Iterator loc = map.get(pattern);
		if (loc != null){
			while (loc.hasNext())
				indexes.add(String.valueOf(loc.next()));
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

	public static ArrayList<String> SubStringMatch(String s1, String s2, int matchLength) {

		// Hashmap
		HashMapCoded<String, Integer> map = new HashMapCoded<>(s2.length());
		// Num de posibles substrings de longitud matchLength que existen en cada cadena
		int substring_num1 = s1.length() - matchLength + 1;
		int substring_num2 = s2.length() - matchLength + 1;

		// S2
		for (int i = 0; i < substring_num2; i++) {
			String key = s2.substring(i, i + matchLength);
			map.put(key, i);
		}

		ArrayList<String> res = new ArrayList<>();

		for (int j = 0; j < substring_num1; j++) {
			String tag = s1.substring(j, j + matchLength);
			Iterator loc = map.get(tag);
			if (loc != null){
				while (loc.hasNext()){
					res.add(j + "," + loc.next() + "," + tag);
				}
			}
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

	public static void convertToCSV(ArrayList<String> commonSubs, int num, String folder) throws IOException {
		try (PrintWriter writer = new PrintWriter(new File("results/" +folder +"/data"+num+".csv"))) {
			for (int i = 0; i < commonSubs.size(); i++) {
				// Pop del Stack de las substrings comunes para imprimirlas una a una.
                writer.write(commonSubs.get(i));
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
