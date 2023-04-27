package algo_lab10;

import java.util.Arrays;
import java.util.Random;

public class quick
{
	public static void main(String [] args)
	{
		int array_size = 10;
		int [] array = new int[array_size];
		long start_time, end_time, elapsed_time, elapsed_time_insertion, elapsed_time_merge;

		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());

		for (int i = 0; i < array_size; i++)
			array[i] = rand.nextInt(100);
		print_array(array);
		
		start_time = System.nanoTime();
		randomized_quicksort(array, 0, array_size-1);
		end_time = System.nanoTime();
		elapsed_time = end_time - start_time;
		print_array(array);
                System.out.printf("Elapsed time in nanoseconds for quick sort: %d\n", elapsed_time);
		
		start_time = System.nanoTime();
		heap_sort(array);
		end_time = System.nanoTime();
		elapsed_time = end_time - start_time;
		
                System.out.printf("Elapsed time in nanoseconds for heap sort: %d\n", elapsed_time);
                

		System.exit(1);

                start_time = System.nanoTime();
                merge_sort(array, 0, array_size-1);
                end_time = System.nanoTime();
                elapsed_time = end_time - start_time;
                System.out.printf("Elapsed time in nanoseconds for merge sort: %d\n", elapsed_time);
	
	}

	//Implement partition algorithm below
	public static int partition(int A[], int p, int r)
	{
		int x = A[r];
		int i = p -1;
		for(int j=p; j<=r-1; j++) {//check here
			if(A[j]<=x) {
				i = i+1;
				int temp = A[i];
				A[i] = A[j];
				A[j] = temp;
			}
		}
		int temp = A[i+1];
		A[i+1] = A[r];
		A[r] = A[i+1];
		return i+1;
	}

	//Implement randomized partition algorithm below
	public static int randomized_partition(int A[], int p, int r)
	{
		 Random random = new Random();
		    int i = random.ints(p, r)
		      .findFirst()
		      .getAsInt();
		    // exchange
			int temp = A[i+1];
			A[i+1] = A[r];
			A[r] = A[i+1];
			
			return partition(A, p ,r);
	}
	
	//Implement randomized quick sort algorithm below
	public static int randomized_quicksort(int A[], int p, int r)
	{
		if(p<r) {
			int q = randomized_partition(A,p, r);
			randomized_quicksort(A,p,q-1);
			randomized_quicksort(A, q+1, r);
		}
		return 0;
	}

	//assumes that index i starts from 1
	public static int parent(int i)
	{
		return (int)Math.floor(i/2);
	}

        //assumes that index i starts from 1
	public static int left(int i)
	{
		return 2*i;
	}

        //assumes that index i starts from 1
	public static int right(int i)
	{
		return (2*i+1);
	}

        //assumes that index i starts from 1
	public static void max_heapify(int [] A, int array_size, int i)
	{
		int left_index, right_index, index_of_largest;
		int temp;

		left_index = left(i);
                right_index = right(i);

		if ((left_index <= array_size) && (A[left_index-1] > A[i-1]))
			index_of_largest = left_index;
		else
			index_of_largest = i;

		if ((right_index <= array_size) && (A[right_index-1] > A[index_of_largest-1]))
			index_of_largest = right_index;

		if (index_of_largest != i)
		{
			temp = A[i-1];
			A[i-1] = A[index_of_largest-1];
			A[index_of_largest-1] = temp;
			max_heapify(A, array_size, index_of_largest);
		}
	}

	public static void build_max_heap(int [] A)
	{
		int middle_index = (int)Math.floor(A.length/2);
		int array_size = A.length;

		for (int i = middle_index; i >= 1; i--)
			max_heapify(A, array_size, i);
	}

	public static void heap_sort(int [] A)
	{
		int temp;
		int array_size = A.length;
		build_max_heap(A);
		
		for (int i = A.length; i >= 2; i--)
		{
			temp = A[0];
			A[0] = A[i-1];
			A[i-1] = temp;
			array_size--;
			max_heapify(A, array_size, 1);
		}
	}

	//indices p and r can start from 0
	public static void merge_sort(int [] A, int p, int r)
	{
		int q;

		if (p < r)
		{
			q = (int)Math.floor((p+r)/2);
			merge_sort(A, p, q);
			merge_sort(A, q+1, r);
			merge(A, p, q, r);
		}
	}

	public static void merge(int [] A, int p, int q, int r)
	{
		int n1, n2;
		int i, j;

		n1 = q-p+1;
		n2 = r-q;

		int [] L = new int[n1];
		int [] R = new int[n2];

		for (i = 0; i < n1; i++)
			L[i] = A[p+i];

                for (i = 0; i < n2; i++)
                        R[i] = A[q+i+1];		

		i = 0;
		j = 0;
		
		for (int k=p; k <= r; k++)
		{
			if (i >= n1) //the left array finished, copy from right array
			{
				A[k] = R[j];
				j++;
				continue;
			}
			
			if (j >= n2) //the right array finished, copy from left array
			{
				A[k] = L[i];
				i++;
				continue;
			}
	
			if (L[i] <= R[j])
			{
				A[k] = L[i];
				i++;
			}
			else
			{
				A[k] = R[j];
				j++;
			}
		} 
	}	

        //prints the elements of the array A on the screen
        public static void print_array(int [] A)
        {       
                System.out.printf("[");
                for (int i = 0; i < A.length-1; i++)
                {       
                        System.out.printf("%d, ", A[i]);
                }
                
                System.out.printf("%d]\n", A[A.length-1]);
        
        }
}

