package algo_lab13;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Vector;

public class linear_2 {
	public static void main(String[] args) {

		// for part 1(c)
//	 for(int j=10;j<=10000000;j=j*10) {
//		 int array_size = j;
//		//int array_size = 100000000;
//		float[] A = new float[array_size];
//		long start_time, end_time, elapsed_time, elapsed_time_insertion, elapsed_time_merge;
//
//		// an array of linked lists
//		SinglyLinkedList<Float>[] B = new SinglyLinkedList[array_size];
//
//		for (int i = 0; i < A.length; i++)
//			B[i] = new SinglyLinkedList<>(); // each element of B array is a linked list object
//
//		Float value = new Float(1.0);
//		Float value_2 = new Float(2.0);
//		Float value_3 = new Float(3.0);
//
//		B[0].addFirst(value);
//		B[0].addFirst(value_2);
//		B[0].addFirst(value_3);
//		B[0].insertion_sort();
//
//		// System.out.printf("%s\n", B[0]);
//
//		Random rand = new Random();
//		rand.setSeed(System.currentTimeMillis());
//
//		for (int i = 0; i < array_size; i++)
//			A[i] = rand.nextFloat();
//
//		float[] A2 = new float[array_size];
//		float[] A3 = new float[array_size];
//		for (int i = 0; i < array_size; i++) {
//			A[i] = rand.nextFloat();
//			A2[i] = A[i];
//			A3[i] = A2[i];
//		}
//		// print_float_array(A);
//		// for part 1(c)
//		System.out.println("size= "+j);
//		start_time = System.nanoTime();
//		bucket_sort(A, array_size);
//		end_time = System.nanoTime();
//		elapsed_time = end_time - start_time;
//		System.out.printf("Elapsed time in nanoseconds for bucket sort: %d\n", elapsed_time);
//
//		// print_float_array(A);
//
//			start_time = System.nanoTime();
//			heap_sort(A);
//			end_time = System.nanoTime();
//			elapsed_time = end_time - start_time;
//	                System.out.printf("Elapsed time in nanoseconds for heap sort: %d\n", elapsed_time);
//
//	                //print_float_array(A);
//
//	                start_time = System.nanoTime();
//	                merge_sort(A, 0, array_size-1);
//	                end_time = System.nanoTime();
//	                elapsed_time = end_time - start_time;
//	                System.out.printf("Elapsed time in nanoseconds for merge sort: %d\n", elapsed_time);
//
//	              //  print_float_array(A);
//		 }
		
		// part 1(d)

		int array_size = 10000000;
		float[] A = new float[array_size];
		long start_time, end_time, elapsed_time, elapsed_time_insertion, elapsed_time_merge;

		// an array of linked lists
		SinglyLinkedList<Float>[] B = new SinglyLinkedList[array_size];

		for (int i = 0; i < A.length; i++)
			B[i] = new SinglyLinkedList<>(); // each element of B array is a linked list object

		Float value = new Float(1.0);
		Float value_2 = new Float(2.0);
		Float value_3 = new Float(3.0);

		B[0].addFirst(value);
		B[0].addFirst(value_2);
		B[0].addFirst(value_3);
		B[0].insertion_sort();

		// System.out.printf("%s\n", B[0]);

		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());

		for (int i = 0; i < array_size; i++)
			A[i] = rand.nextFloat();

		float[] A2 = new float[array_size];
		float[] A3 = new float[array_size];
		for (int i = 0; i < array_size; i++) {
			A[i] = rand.nextFloat();
			A2[i] = A[i];
			A3[i] = A2[i];
		}
		// print_float_array(A);
		
//		start_time = System.nanoTime();
//		bucket_sort(A, array_size);
//		end_time = System.nanoTime();
//		elapsed_time = end_time - start_time;
//		System.out.printf("Elapsed time in nanoseconds for bucket sort: %d\n", elapsed_time);

		// print_float_array(A);

			start_time = System.nanoTime();
			heap_sort(A);
			end_time = System.nanoTime();
			elapsed_time = end_time - start_time;
	                System.out.printf("Elapsed time in nanoseconds for heap sort: %d\n", elapsed_time);

	                //print_float_array(A);
//
//	                start_time = System.nanoTime();
//	                merge_sort(A, 0, array_size-1);
//	                end_time = System.nanoTime();
//	                elapsed_time = end_time - start_time;
//	                System.out.printf("Elapsed time in nanoseconds for merge sort: %d\n", elapsed_time);

	              //  print_float_array(A);
	}

	// Implement bucket sort algorithm below
	public static void bucket_sort(float[] arr, int n) {
		if (n <= 0)
			return;

		// empty buckets
		@SuppressWarnings("unchecked")
		Vector<Float>[] buckets = new Vector[n];

		for (int i = 0; i < n; i++) {
			buckets[i] = new Vector<Float>();
		}

		// I put the elements to different buckets
		for (int i = 0; i < n; i++) {
			float idx = arr[i] * n;
			buckets[(int) idx].add(arr[i]);
		}

		// sorting
		for (int i = 0; i < n; i++) {
			Collections.sort(buckets[i]);
		}

		// concatenate all buckets
		int index = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < buckets[i].size(); j++) {
				arr[index++] = buckets[i].get(j);
			}
		}

	}

	// assumes that index i starts from 1
	public static int parent(int i) {
		return (int) Math.floor(i / 2);
	}

	// assumes that index i starts from 1
	public static int left(int i) {
		return 2 * i;
	}

	// assumes that index i starts from 1
	public static int right(int i) {
		return (2 * i + 1);
	}

	// assumes that index i starts from 1
	public static void max_heapify(float[] A, int array_size, int i) {
		int left_index, right_index, index_of_largest;
		float temp;

		left_index = left(i);
		right_index = right(i);

		if ((left_index <= array_size) && (A[left_index - 1] > A[i - 1]))
			index_of_largest = left_index;
		else
			index_of_largest = i;

		if ((right_index <= array_size) && (A[right_index - 1] > A[index_of_largest - 1]))
			index_of_largest = right_index;

		if (index_of_largest != i) {
			temp = A[i - 1];
			A[i - 1] = A[index_of_largest - 1];
			A[index_of_largest - 1] = temp;
			max_heapify(A, array_size, index_of_largest);
		}
	}

	public static void build_max_heap(float[] A) {
		int middle_index = (int) Math.floor(A.length / 2);
		int array_size = A.length;

		for (int i = middle_index; i >= 1; i--)
			max_heapify(A, array_size, i);
	}

	public static void heap_sort(float[] A) {
		float temp;
		int array_size = A.length;
		build_max_heap(A);

		for (int i = A.length; i >= 2; i--) {
			temp = A[0];
			A[0] = A[i - 1];
			A[i - 1] = temp;
			array_size--;
			max_heapify(A, array_size, 1);
		}
	}

	// indices p and r can start from 0
	public static void merge_sort(float[] A, int p, int r) {
		int q;

		if (p < r) {
			q = (int) Math.floor((p + r) / 2);
			merge_sort(A, p, q);
			merge_sort(A, q + 1, r);
			merge(A, p, q, r);
		}
	}

	public static void merge(float[] A, int p, int q, int r) {
		int n1, n2;
		int i, j;

		n1 = q - p + 1;
		n2 = r - q;

		float[] L = new float[n1];
		float[] R = new float[n2];

		for (i = 0; i < n1; i++)
			L[i] = A[p + i];

		for (i = 0; i < n2; i++)
			R[i] = A[q + i + 1];

		i = 0;
		j = 0;

		for (int k = p; k <= r; k++) {
			if (i >= n1) // the left array finished, copy from right array
			{
				A[k] = R[j];
				j++;
				continue;
			}

			if (j >= n2) // the right array finished, copy from left array
			{
				A[k] = L[i];
				i++;
				continue;
			}

			if (L[i] <= R[j]) {
				A[k] = L[i];
				i++;
			} else {
				A[k] = R[j];
				j++;
			}
		}
	}

	// prints the elements of the array A on the screen
	public static void print_float_array(float[] A) {
		System.out.printf("[");
		for (int i = 0; i < A.length - 1; i++) {
			System.out.printf("%f, ", A[i]);
		}

		System.out.printf("%f]\n", A[A.length - 1]);

	}

	private static class Node<E> {

		/** The element stored at this node */
		private E element; // reference to the element stored at this node

		/** A reference to the subsequent node in the list */
		private Node<E> next; // reference to the subsequent node in the list

		/**
		 * Creates a node with the given element and next node.
		 *
		 * @param e the element to be stored
		 * @param n reference to a node that should follow the new node
		 */
		public Node(E e, Node<E> n) {
			element = e;
			next = n;
		}

		// Accessor methods
		/**
		 * Returns the element stored at the node.
		 * 
		 * @return the element stored at the node
		 */
		public E getElement() {
			return element;
		}

		public void setElement(E e) {
			element = e;
		}

		/**
		 * Returns the node that follows this one (or null if no such node).
		 * 
		 * @return the following node
		 */
		public Node<E> getNext() {
			return next;
		}

		// Modifier methods
		/**
		 * Sets the node's next reference to point to Node n.
		 * 
		 * @param n the node that should follow this one
		 */
		public void setNext(Node<E> n) {
			next = n;
		}
	} // ----------- end of nested Node class -----------

}
