package dynamic_programming;

import java.util.HashMap;

public class fibonacci {

	public static void main(String[] args) {
		int hashmaps = 2, N = 39;
		long start = 0,result = 0;
		float elapsedTime = 0.0f;
		
		HashMap<Integer, Integer> hm[] = new HashMap[hashmaps]; //One hashmap for tabulation and one for memoization
		for (int i = 0; i < hashmaps; i++)
			hm[i] = new HashMap<>();

		//Calling and noting the total time required to call recursion function.
		start = System.currentTimeMillis();
			result = fibonacci_recursion(N);
		elapsedTime = (System.currentTimeMillis() - start) / 1000F;
		System.out.println("Recursion: " + result + ": Takes " + elapsedTime + " seconds");

		//Calling and noting the total time required to call tabulation function.
		start = System.currentTimeMillis();
			result = fibonacci_dp_tabulation(N, hm[0]);
		elapsedTime = (System.currentTimeMillis() - start) / 1000F;
		System.out.println("Tabulation: " + result + ": Takes " + elapsedTime + " seconds");

		//Calling and noting the total time required to call memoization function.
		start = System.currentTimeMillis();
			result = fibonacci_dp_memoization(N, hm[1]);
		elapsedTime = (System.currentTimeMillis() - start) / 1000F;
		System.out.println("Memoization: " + result + ": Takes " + elapsedTime + " seconds");
	}

	private static int fibonacci_recursion(int n) {
		if (n <= 0)
			return 0;
		else if (n == 1)
			return 1;
		return fibonacci_recursion(n - 1) + fibonacci_recursion(n - 2);
	}

	private static int fibonacci_dp_tabulation(int n, HashMap<Integer, Integer> hm) {
		hm.put(0, 0);
		hm.put(1, 1);
		for (int i = 2; i <= n; i++)
			hm.put(i, hm.get(i - 1) + hm.get(i - 2));
		return hm.get(n);

	}

	private static int fibonacci_dp_memoization(int n, HashMap<Integer, Integer> hm) {
		hm.put(0, 0);
		hm.put(1, 1);
		return fibonacci_dp_memoization_helper(n, hm);
	}

	private static int fibonacci_dp_memoization_helper(int n, HashMap<Integer, Integer> hm) {
		if (n < 0)
			return 0;
		if (hm.containsKey(n))
			return hm.get(n);
		int val=fibonacci_dp_memoization_helper(n - 1, hm) + fibonacci_dp_memoization_helper(n - 2, hm);
		hm.put(n,val);
		return val;
	}
}
