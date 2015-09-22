package lesson1;

import java.util.Random;

public class LSD {

	private static final int SIZE = 1000000;
	private static Random random = new Random();

	public static void main(String[] args)
	{
		int[] data = generate();
		int [] data2 = data.clone(); 
		
		long start = System.nanoTime();
		java.util.Arrays.sort(data);
		long stop = System.nanoTime();

		System.out.println("Elapsed = " + (stop - start));

		start = System.nanoTime();
		LSDsort(data2);
		stop = System.nanoTime();

		System.out.println("Elapsed = " + (stop - start));

}

	private static void LSDsort(int[] data) {
	
	}

	private static int[] generate() {
		
		int[] data = new int[SIZE];
				
		for (int k = 0; k < data.length; k++) {
			data[k] = random.nextInt(SIZE);
		}
		
		return data;
	}
	
}