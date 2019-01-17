package dynamic_programming;

import java.util.HashMap;

public class LongestIncreasingSequence {

	public static void main(String[] args) {
		HashMap<Integer, Integer> hm = new HashMap<>(); // Sorted values of
														// indexes
		int arr[] = { 10, 22, 9, 33, 21, 50, 41, 60, 80 }; // Actual array of
															// integers.
		executeAndPrintSequence(arr, hm); // Calls lis function and prints it.
	}

	public static void executeAndPrintSequence(int arr[], HashMap<Integer, Integer> hm) {
		System.out.println("Longest Increasing Sequence");
		System.out.println("Length is :" + lis(arr, hm)); // Call to lis
															// function
		for (int i = 0; i < hm.size(); i++) {
			System.out.print(arr[hm.get(i)] + " ");
		}
		System.out.println();
	}

	public static int lis(int arr[], HashMap<Integer, Integer> hm) { // Actual
																		// Function
		int len = 0; // Pointer to the maximum element in the hashmap.
		hm.put(0, 0); // Placing the initial index 0 as minimum value.
		for (int i = 1; i < arr.length; i++) { // Iterating over the array.
			if (arr[i] > arr[hm.get(len)]) { // If the current element is
												// greater than the maximum
												// element in the hashmap,
				hm.put(++len, i); // Then place the current element in the
									// hashmap and increment the len pointer to
									// that element.
			} else if (arr[i] < arr[hm.get(0)]) { // If the current element is
													// less than the minimum
													// element in the hashmap,
				hm.put(0, i); // Then replace the minimum element with the
								// current value.
			} else { // If the current value is somewhere in the middle of the
						// hashmap,
				if (!hm.containsKey(i)) { // Then check if it is already present
											// in the hashmap in case of
											// duplicates in the array,
					binarySearchAndPutValue(arr[i], i, hm); // Binary Search the
															// minimum value
															// index it should
															// replace and
															// replace it.
				}
			}
		}
		return hm.size(); // Returning the hashmap total elements count as it is
							// the sequence length.
	}

	public static void binarySearchAndPutValue(int val, int index, HashMap<Integer, Integer> hm) { // This
																									// function
																									// searches
																									// for
																									// the
																									// index
																									// to
																									// replace
																									// with
																									// the
																									// new
																									// value.
		int f = 1; // The head pointer.
		int l = hm.size() - 1; // The tail pointer.
		int mid = (f + l) / 2; // Mid value between tail and head pointer.
		while (f < l) { // Iterate over the hashmap till head pointer is less
						// than the tail pointer.
			if (hm.get(mid) > val && hm.get(mid - 1) < val) { // If the value is
																// in middle of
																// mid index and
																// mid-1 index,
				hm.put(mid - 1, val); // Then replace the mid-1 index value as
										// it is less than the value.
				break;
			} else if (hm.get(mid) < val && hm.get(mid + 1) > val) { // If the
																		// value
																		// is in
																		// middle
																		// of
																		// mid
																		// index
																		// and
																		// mid+1
																		// index,
				hm.put(mid, val); // Then replace the mid index value as it is
									// less than the value.
				break;
			} else if (hm.get(mid) < val && hm.get(mid + 1) < val) { // If the
																		// value
																		// is in
																		// greater
																		// than
																		// mid
																		// index
																		// and
																		// mid+1
																		// index,
				f = mid + 1; // set head pointer with mid+1 value as we are
								// shrinking the search space from left side.
			} else { // If the value is less than mid index and mid+1 index,
				l = mid - 1; // set tail pointer with mid-1 value as we are
								// shrinking the search space from right side.
			}
		}

	}
}
