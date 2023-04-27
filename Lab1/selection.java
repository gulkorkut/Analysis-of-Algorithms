package lab1;

import java.util.Arrays;
import java.util.Random;

public class question2
{
	public static void main(String [] args)
	{
		int array_size = 10;
		int n_repetitions = 10;

		int array [] = new int[array_size];

		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());

		//part(b) choosing one integer array and showing that the sort method works

		//initialize elements of array with random integers
		for (int i = 0; i < array.length; i++)
			array[i] = rand.nextInt(100);

		print_array(array);
		selection_sort(array);
		print_array(array);

		//part(c) min, average, max running times

		statistics(1000, 1000);
                statistics(10000, 1000);
	}

	//part(a) implementing selection sort algorithm as a method below
	public static void selection_sort(int [] A)
	{
			
			for(int i=0;i<A.length;i++){
				for(int j=i ; j <A.length;j++){
					if(A[i]>A[j]){
						int t = A[i];
						A[i] = A[j];
						A[j] = t;
					}
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

	//part (c) compute min, average, max times for repeatedly sorting random arrays
	public static void statistics(int array_size, int n_repetitions)
	{
                long min_time, max_time, sum_time;
                double average_time;

                int array [] = new int[array_size];

                Random rand = new Random();
                rand.setSeed(System.currentTimeMillis());

                min_time = 0;
                max_time = 0;
                average_time = 0.0;
                sum_time = 0;

                for (int k = 0; k < n_repetitions; k++)
                {       
                	for (int i = 0; i < array.length; i++) 
                    	array[i] = rand.nextInt(100); 

                	
                	long startTime = System.currentTimeMillis();
                	selection_sort(array);
                	long endTime = System.currentTimeMillis();
                	long elapsed = endTime - startTime;
                	
                	if(min_time==0){ 
                    	min_time = elapsed;	
                    	}
                	if(max_time==0){ 
                    	max_time = elapsed;	
                    	}
                	if(elapsed<min_time){ 
                	min_time = elapsed;	
                	}
                	if(elapsed>max_time){ 
                	max_time = elapsed;
                	}
                	sum_time += elapsed;
                        //initialize elements of array with random integers
                        
                        //call selection sort method

			//compute min_time, max_time, sum_time

                }

                average_time = (double) sum_time / (double) n_repetitions;

                System.out.printf("Min time: %d\n", min_time);
                System.out.printf("Average time: %.2f\n", average_time);
                System.out.printf("Max time: %d\n", max_time);

	}

}