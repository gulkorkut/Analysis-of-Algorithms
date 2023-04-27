package algo_lab8;



import java.util.Arrays;
import java.util.Random;

public class heap
{
	static int heap_size;
	public static void main(String [] args)
	{
		int array_size = 10;//16
		int [] array = new int[array_size];
		long start_time, end_time, elapsed_time, elapsed_time_insertion, elapsed_time_merge;

		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());

		for (int i = 0; i < array_size; i++)
			array[i] = rand.nextInt(100);
		
		//part b 
		// I tested my array with heap sort for size 10
		heap_sort(array);
		
		//part c
		// I created for loop to try different size
		
		
//		for (int i=4; i<=67108864; i*=4) {
//			
//			System.out.println();
//			System.out.println("Size "+i);
//			
//			int array_1[] =new int[i];
//			int array_2[] =new int[i];
//			
//			
//			
//			for (int j = 0; j < i; j++)
//			{
//	            array_1[j] = rand.nextInt(100);
//				array_2[j] = array_1[j];
//			}
//			
//			
//			start_time = System.nanoTime();
//			heap_sort(array_1);
//			end_time = System.nanoTime();
//			elapsed_time = end_time - start_time;
//			System.out.println("Elapsed time in nanoseconds for heap " + elapsed_time);
//			
//			
//			start_time = System.nanoTime();
//			merge_sort(array_2, 0, array_2.length-1);
//			end_time = System.nanoTime();
//			elapsed_time = end_time - start_time;
//			System.out.println("Elapsed time in nanoseconds for merge " + elapsed_time);
//			
//			
//	
//			
//			
//		}
		
		
		int array_3[] =new int[67108864];
		int array_4[] =new int[67108864];
		
		
		
		for (int j = 0; j < 67108864; j++)
		{
            array_3[j] = rand.nextInt(100);
			array_4[j] = array_3[j];
		}
		
		//part d
		// part d ram usage for heap sort
		 	
//		start_time = System.nanoTime();
//		heap_sort(arr3);
//		end_time = System.nanoTime();
//		elapsed_time = end_time - start_time;
//		System.out.println("Elapsed time in nanoseconds for heap " + elapsed_time);
//		
//		
//		//part e
		// part e  ram usage for merge sort
		
		start_time = System.nanoTime();
		merge_sort(array_4, 0, array_4.length-1);
		end_time = System.nanoTime();
		elapsed_time = end_time - start_time;
		System.out.println("Elapsed time in nanoseconds for merge " + elapsed_time);
		
		
		
	
	}

	//Implement heap sort algorithm below
	public static void heap_sort(int[] A )
	{
		build_max_heap(A);
		for(int i=A.length-1 ; 1<=i;i--) {
			int temp = A[0];
			A[0] = A[i];
			A[i] = temp;
			heap_size -= 1;
			max_heapify(A,0);
		}
		
	}
	
	
	
	public static int left(int i) {
		int k = 2*i  +1;
		return k;
	}
	
	public static int right(int i) {
		int k = (2*i)+2; 
		return k;
	}
	public static int parent(int i) {
		if (i%2 == 1) {
			return i/2;
		}
		else {
		 return	i/2 -1 ;
		}
	}
	public static void max_heapify(int[]A,int i) {
		int l = left(i);
		int r = right(i);
		int largest;
		if(l<=heap_size && A[l]>A[i]) {
			largest = l;
		}else {
			largest = i;
		}
		if(r<=heap_size && A[r]>A[largest]) {
			largest = r;
		}
		if(largest != i) {
			int temp = A[i];
			A[i] = A[largest];
			A[largest] = temp;
			max_heapify(A,largest);
		}
	}
	
	public static void build_max_heap(int[] A){
		heap_size = A.length-1;
		int length = (int) Math.floor(A.length/2);
		for (int i=length;0<=i;i--) {
			max_heapify(A,i);
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

	//Part 2(a)
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