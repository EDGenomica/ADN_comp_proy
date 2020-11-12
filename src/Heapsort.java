

import java.util.*;

public class Heapsort {

	public Heapsort() {
	}

	private void swapElements(ListArrayGeneric<String> a, int i, int j) {
		String temp;
		temp = a.get(i);
		a.set(i, a.get(j));
		a.set(j, temp);
	}

	private void moveDown(ListArrayGeneric<String> array, int parent, int size) {
		boolean flag = false;
		String largest = "-1";
		int child = 2 * parent + 1;
		String temp = array.get(parent);
		while (child < size && !flag) {
			largest = array.get(child);
			if (child + 1 < size && array.get(child + 1).compareTo(array.get(child)) > 0)
				largest = array.get(++child);
			if (largest.compareTo(temp) > 0) {
				array.set(parent, largest);
				parent = child;
			} else
				flag = true;
			child = 2 * parent + 1;
		}
		array.set(parent, temp);
	}

	private void makeHeap(ListArrayGeneric<String> array, int n) {
		for (int i = n / 2 - 1; i >= 0; i--)
			moveDown(array, i, n);
	}

	public void heapSort(ListArrayGeneric<String> array, int n) {
		makeHeap(array, n);
		for (int i = n - 1; i > 0; i--) {
			swapElements(array, 0, i);
			moveDown(array, 0, i);
		}
	}

}