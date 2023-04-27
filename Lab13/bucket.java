package algo_lab13;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Vector;

public class bucket
{
	public static void main(String [] args)
	{
		
		//part 1(c) I make a for loop to change input size otomatically
	//	for(int j=10;j<=100000000;j=j*10) {
			//int array_size = j;
		//part 1(d)
			int array_size = 10;
			float array_size1 = 10;
			int [] array = new int[array_size];
			int [] array2 = new int[array_size];
			float [] array3 = new float[array_size];
			long start_time, end_time, elapsed_time, elapsed_time_insertion, elapsed_time_merge;

			Random rand = new Random();
			rand.setSeed(System.currentTimeMillis());

			for (int i = 0; i < array_size; i++)
				array[i] = rand.nextInt(100);
			
			for (int i = 0; i < array_size; i++)
				array2[i] = rand.nextInt(100);
			
			for (int i = 0; i < array_size; i++)
				array3[i] = rand.nextInt(100);
			print_array1(array3);
			bucketSort(array3, array_size);
			print_array1(array3);
			//I make 100 to 999 random 
			// to print array sizes in part 1(c)
			//System.out.println("array size=" + j);
			
//			start_time = System.nanoTime();
//			
//			radix_sort(array, array_size);
//			
//			end_time = System.nanoTime();
//			elapsed_time = end_time - start_time;
//	        System.out.printf("Elapsed time in nanoseconds for radix sort: %d\n", elapsed_time);
	        
//	        start_time = System.nanoTime();
//	        
//			countsort(array2, array_size, array_size);
//			
//			end_time = System.nanoTime();
//			elapsed_time = end_time - start_time;
//	                System.out.printf("Elapsed time in nanoseconds for counting sort: %d\n", elapsed_time);
//
//			start_time = System.nanoTime();
//			heap_sort(array);
//			end_time = System.nanoTime();
//			elapsed_time = end_time - start_time;
//	                System.out.printf("Elapsed time in nanoseconds for heap sort: %d\n", elapsed_time);
//
//	                start_time = System.nanoTime();
//	                merge_sort(array, 0, array_size-1);
//	                end_time = System.nanoTime();
//	                elapsed_time = end_time - start_time;
//	                System.out.printf("Elapsed time in nanoseconds for merge sort: %d\n", elapsed_time);
		//}
			
		
	
	}

	//Implement radix sort algorithm below
	public static void radix_sort(int[]A, int d)
	{
		
		//finding max
		int max = A[0];
		  for (int i = 1; i < A.length; i++) {
		    if (max < A[i])
		      max = A[i];
		  } 
		  //System.out.println(max);
		
		
		for(int s = 1; max / s > 0; s *= 10 ) {
			countsort(A, d, s);
		}

	}
	static void bucketSort(float arr[], int n)
    {
        if (n <= 0)
            return;
 
        // 1) Create n empty buckets
        @SuppressWarnings("unchecked")
        Vector<Float>[] buckets = new Vector[n];
 
        for (int i = 0; i < n; i++) {
            buckets[i] = new Vector<Float>();
        }
 
        // 2) Put array elements in different buckets
        for (int i = 0; i < n; i++) {
            float idx = arr[i] * n;
            buckets[(int)idx].add(arr[i]);
        }
 
        // 3) Sort individual buckets
        for (int i = 0; i < n; i++) {
            Collections.sort(buckets[i]);
        }
 
        // 4) Concatenate all buckets into arr[]
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                arr[index++] = buckets[i].get(j);
            }
        }
    }
 
	
	 public static void countsort(int A[], int n, int exp)
	    {
	        int output[] = new int[n]; // output array
	        int i;
	        int count[] = new int[10];
	        Arrays.fill(count, 0);
	  
	        // Store count of occurrences in count[]
	        for (i = 0; i < n; i++)
	            count[(A[i] / exp) % 10]++;
	  
	        // Change count[i] so that count[i] now contains
	        // actual position of this digit in output[]
	        for (i = 1; i < 10; i++)
	            count[i] += count[i - 1];
	  
	        // Build the output array
	        for (i = n - 1; i >= 0; i--) {
	            output[count[(A[i] / exp) % 10] - 1] = A[i];
	            count[(A[i] / exp) % 10]--;
	        }
	  
	        // Copy the output array to arr[], so that arr[] now
	        // contains sorted numbers according to current
	        // digit
	        for (i = 0; i < n; i++)
	            A[i] = output[i];
	    }
	 public static void counting_sort(int []A,int []B,int k)
		{
			int []C = new int[k];
			for(int i=0;i<k;i++) {
				C[i]=0;
			}
			for(int j=0;j<A.length;j++) {
				C[A[j]]++;
			}
			for(int i=1;i<k;i++) {
				C[i]=C[i]+C[i-1];
			}
			for(int j=A.length-1;-1<j;j--) {
				B[C[A[j]]-1]= A[j];
				C[A[j]]= C[A[j]]-1;
			}
			
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
        public static void print_array1(float [] A)
        {       
                System.out.printf("[");
                for (int i = 0; i < A.length-1; i++)
                {       
                        System.out.printf("%f, ", A[i]);
                }
                
                System.out.printf("%f]\n", A[A.length-1]);
        
        }
}

