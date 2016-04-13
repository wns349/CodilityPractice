package chapter9;

import java.util.Arrays;

public class CountNonDivisible {
	/*
	 * You are given a non-empty zero-indexed array A consisting of N integers.
	 * 
	 * For each number A[i] such that 0 ≤ i < N, we want to count the number of
	 * elements of the array that are not the divisors of A[i]. We say that
	 * these elements are non-divisors.
	 * 
	 * For example, consider integer N = 5 and array A such that:
	 * 
	 * A[0] = 3 A[1] = 1 A[2] = 2 A[3] = 3 A[4] = 6 For the following elements:
	 * 
	 * A[0] = 3, the non-divisors are: 2, 6, A[1] = 1, the non-divisors are: 3,
	 * 2, 3, 6, A[2] = 2, the non-divisors are: 3, 3, 6, A[3] = 3, the
	 * non-divisors are: 2, 6, A[6] = 6, there aren't any non-divisors. Write a
	 * function:
	 * 
	 * class Solution { public int[] solution(int[] A); }
	 * 
	 * that, given a non-empty zero-indexed array A consisting of N integers,
	 * returns a sequence of integers representing the amount of non-divisors.
	 * 
	 * The sequence should be returned as:
	 * 
	 * a structure Results (in C), or a vector of integers (in C++), or a record
	 * Results (in Pascal), or an array of integers (in any other programming
	 * language). For example, given:
	 * 
	 * A[0] = 3 A[1] = 1 A[2] = 2 A[3] = 3 A[4] = 6 the function should return
	 * [2, 4, 3, 2, 0], as explained above.
	 * 
	 * Assume that:
	 * 
	 * N is an integer within the range [1..50,000]; each element of array A is
	 * an integer within the range [1..2 * N]. Complexity:
	 * 
	 * expected worst-case time complexity is O(N*log(N)); expected worst-case
	 * space complexity is O(N), beyond input storage (not counting the storage
	 * required for input arguments). Elements of input arrays can be modified.
	 */

	public int[] solution(int[] A) {
		int max = -1;
		for (int i = 0; i < A.length; i++) {
			max = A[i] > max ? A[i] : max;
		}

		int[] elemCount = new int[max+1];
		for (int i = 0; i < A.length; i++) {
			elemCount[A[i]]++;
		}

		// System.out.println(Arrays.toString(elemCount));

		int[] ans = new int[A.length];
		for (int i = 0; i < A.length; i++) {
			int j = 1, num = A[i];
			int sum = 0;

			while (j * j <= num) {
				if (num % j == 0) {
					if (j >= 0 && j < elemCount.length) {
						sum += elemCount[j];
					}
					if (j != num / j && num / j >= 0 && num / j < elemCount.length) {
						sum += elemCount[num / j];
					}
				}

				j++;
			}

			ans[i] = A.length - sum;
		}

		return ans;
	}

	public static void main(String[] args) {
		int[] A = { 3, 1, 2, 3, 6 };
		System.out.println(Arrays.toString((new CountNonDivisible()).solution(A)));
	}
}
