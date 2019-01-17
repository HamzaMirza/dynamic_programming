package dynamic_programming;

import java.util.HashMap;

public class Longest_Common_Sequence {

	public static void main(String[] args) {
		HashMap<Integer, Integer> hm = new HashMap<>(); // HashMap that holds the index of common characters.

		String s1 = "PgOLGGH";
		String s2 = "gAMVGHRP";
		char[] data = s2.toCharArray();	//Converting the string to char array

		quickSort(data, 0, data.length - 1); //Sorting the array in O(nlog(n))
		lcs(s1, data, hm);	//Calling the longest common sequence function.

		printSequence(data, hm); //Printing the sequence.

	}

	public static void printSequence(char[] data, HashMap<Integer, Integer> hm) { // Printing Function
		System.out.println("Longest Common Sequence");
		System.out.println("Length is : " + hm.size());
		for (int i = 0; i < hm.size(); i++) {
			System.out.print(data[hm.get(i)] + " ");
		}
		System.out.println();
	}

	private static void lcs(String s1, char[] s2, HashMap<Integer, Integer> hm) {
		int lengthOfS2 = s2.length;	//Length of character array of string 2.
		char temp = '-';
		for (int i = 0; i < s1.length(); i++) {
			temp = s1.charAt(i);
			if (hm.size() == lengthOfS2) { // If all characters of string 2 has been traversed.
				break;
			} else if (temp > s2[lengthOfS2 - 1] || temp < s2[0]) {	//If the current character of String 1 is less than 
																	//or greater than the minimum or the maximum character of string 2 respectively 
																	//then continue with the next character. 
				continue;
			} else {
				binarySearchAndPlaceValue(temp, s2, hm);	// Probability that it must exist in string 2 
															//and to check we binary search the string 2 character array.
			}
		}
	}

	private static void binarySearchAndPlaceValue(char value, char[] data, HashMap<Integer, Integer> hm) {
		int f = 0, l = data.length - 1; // F== head pointer and l == tail pointer.

		if (f > l) // if head is greater than the tail return as array is empty.
			return;

		int mid = (f + l) / 2; // Calculating the mean index of first and last index.

		while (f < l) { //Iterate till head pointer is not equal than the tail pointer
			if (data[mid] < value) {
				f = mid + 1;
			} else if (data[mid] > value) {
				l = mid - 1;
			} else {
				if (hm.containsValue(mid)) { // If the hashmap has already matched this character with the previous character
					f = mid + 1;	//replace the head pointer's value to mid+1 index.
				} else {
					hm.put(hm.size(), mid); // This character is a match.
					break; // break out of the loop.
				}
				 
			}
			mid = (f + l) / 2; // resetting the middle index.
		}
		if(f==l){ // If head and tail pointer are same,
			if(data[f]==value && !hm.containsValue(f)){ // then check if the characters match 
														//and are not inserted into the hash map.
				hm.put(hm.size(), f); // Insert the character index to hash map.
			}
		}
	}

	private static void quickSort(char[] data, int start, int end) {
		if (start >= end)
			return;
		int pIndex = partition(data, start, end);
		quickSort(data, start, pIndex - 1);
		quickSort(data, pIndex + 1, end);
	}

	private static int partition(char[] data, int start, int end) {
		int pIndex = start;
		int pValue = data[end];

		for (int i = start; i < end; i++) {
			if (data[i] <= pValue) {
				swap(data, i, pIndex++);
			}
		}
		swap(data, end, pIndex);
		return pIndex;
	}

	private static void swap(char[] data, int index1, int index2) {
		char tempValue = data[index1];
		data[index1] = data[index2];
		data[index2] = tempValue;
	}
}
