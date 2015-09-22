package lesson1;

import java.util.Random;

public class LSD {

	private static final int SIZE = 1000000;
	private static Random random = new Random();
	private static final int W = 4;								// because int is 4 bytes
	
	public static void main(String[] args)
	{
		int[] data = generate();	
		int [] data2 = data.clone(); 
		
		long start = System.nanoTime();
		java.util.Arrays.sort(data);
		long stop = System.nanoTime();

		System.out.println("Elapsed = " + (stop - start));
		long first = stop - start;
		
		start = System.nanoTime();
		LSDsort(data2);
		stop = System.nanoTime();
		
		System.out.println("Elapsed = " + (stop - start) );
		long second = stop - start;
		
		if(check(data2)){
			System.out.println("LSD sort is fasted in "  + (first * 1.0 / second) + " times");
		}

	}

	private static void LSDsort(int[] data) {
		int n = data.length;
		int r = 256;
		int mask = r - 1;
		
		int[] arr = new int [n];
		
		for(int i = 0; i < W; i++)
		{
			int[] count = new int [r+1];
			for(int j = 0; j < n; j++)
			{
				int c = (data[j] >> 8 * i) & mask;
				count[c + 1]++;
			}
			for(int k = 0; k < r; k++)								//compute frequency counts
			{
				count[k + 1] += count[k];
			}
			if (i == W-1) 
			{
                int shift1 = count[r] - count[r / 2];
                int shift2 = count[r / 2];
                for (int k = 0; k < r / 2; k++)
                {
                	count[k] += shift1;
                }
                for (int k = r / 2; k < r; k++)
                {
                	count[k] -= shift2;
                }
            }
			for(int l = 0; l < n; l++)								//move data
			{
				int c = (data[l] >> 8 * i) & mask;
				arr[count[c]++] = data[i];
			}
			for (int l = 0; l < n; l++)								//copy back
				 data[l] = arr[l];
		}
	}

	private static int[] generate() {
		
		int[] data = new int[SIZE];
				
		for (int k = 0; k < data.length; k++) {
			data[k] = random.nextInt(SIZE);
		}
		
		return data;
	}
	
	private static boolean check( int[] data ){
		
		for(int i = 0; i < data.length - 1; i++)
		{
			if(data[i] > data[i+1])
				return false;
		}
		return true;
	}
	
}