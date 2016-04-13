package chapter11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FibFrog {
	/*
	 * The Fibonacci sequence is defined using the following recursive formula:
	 * 
	 * F(0) = 0 F(1) = 1 F(M) = F(M - 1) + F(M - 2) if M >= 2 A small frog wants
	 * to get to the other side of a river. The frog is initially located at one
	 * bank of the river (position −1) and wants to get to the other bank
	 * (position N). The frog can jump over any distance F(K), where F(K) is the
	 * K-th Fibonacci number. Luckily, there are many leaves on the river, and
	 * the frog can jump between the leaves, but only in the direction of the
	 * bank at position N.
	 * 
	 * The leaves on the river are represented in a zero-indexed array A
	 * consisting of N integers. Consecutive elements of array A represent
	 * consecutive positions from 0 to N − 1 on the river. Array A contains only
	 * 0s and/or 1s:
	 * 
	 * 0 represents a position without a leaf; 1 represents a position
	 * containing a leaf. The goal is to count the minimum number of jumps in
	 * which the frog can get to the other side of the river (from position −1
	 * to position N). The frog can jump between positions −1 and N (the banks
	 * of the river) and every position containing a leaf.
	 * 
	 * For example, consider array A such that:
	 * 
	 * A[0] = 0 A[1] = 0 A[2] = 0 A[3] = 1 A[4] = 1 A[5] = 0 A[6] = 1 A[7] = 0
	 * A[8] = 0 A[9] = 0 A[10] = 0 The frog can make three jumps of length F(5)
	 * = 5, F(3) = 2 and F(5) = 5.
	 * 
	 * Write a function:
	 * 
	 * class Solution { public int solution(int[] A); }
	 * 
	 * that, given a zero-indexed array A consisting of N integers, returns the
	 * minimum number of jumps by which the frog can get to the other side of
	 * the river. If the frog cannot reach the other side of the river, the
	 * function should return −1.
	 * 
	 * For example, given:
	 * 
	 * A[0] = 0 A[1] = 0 A[2] = 0 A[3] = 1 A[4] = 1 A[5] = 0 A[6] = 1 A[7] = 0
	 * A[8] = 0 A[9] = 0 A[10] = 0 the function should return 3, as explained
	 * above.
	 * 
	 * Assume that:
	 * 
	 * N is an integer within the range [0..100,000]; each element of array A is
	 * an integer that can have one of the following values: 0, 1. Complexity:
	 * 
	 * expected worst-case time complexity is O(N*log(N)); expected worst-case
	 * space complexity is O(N), beyond input storage (not counting the storage
	 * required for input arguments). Elements of input arrays can be modified.
	 */
	public int solution(int[] A) {
		if (A.length == 0) {
			return 1;
		}

		// Get fib
		List<Integer> fibs = new ArrayList<Integer>();
		fibs.add(1);
		fibs.add(1);

		int idx = 2;
		int f = fibs.get(idx - 2) + fibs.get(idx - 1);
		while (f <= A.length + 1) {
			fibs.add(f);
			idx++;
			f = fibs.get(idx - 2) + fibs.get(idx - 1);
		}

		// Create steps array, fill with -1 (unreachable)
		// 0: availability 1: min steps
		int[][] steps = new int[A.length + 1][2];
		for (idx = 0; idx < steps.length; idx++) {
			if (idx < A.length) {
				steps[idx][0] = A[idx];
			} else {
				steps[idx][0] = 1;
			}

			steps[idx][1] = -1;
		}

		// Mark steps accessible
		for (int fib : fibs) {
			if ((fib - 1) < steps.length && steps[fib - 1][0] == 1) {
				steps[fib - 1][1] = 1;
			}
		}

		for (int i = 0; i < steps.length; i++) {
			if (steps[i][0] == 0) {
				continue;
			}

			int minIndex = -1;
			int minStep = Integer.MAX_VALUE;
			for (int fib : fibs) {
				int j = i - fib;
				if (j < 0 || steps[j][1] < 0)
					continue;

				if (minStep > steps[j][1]) {
					minIndex = j;
					minStep = steps[j][1];
				}
			}

			if (minIndex != -1) {
				if (steps[i][1] == -1) {
					steps[i][1] = minStep + 1;
				} else {
					steps[i][1] = Math.min(steps[i][1], minStep + 1);
				}
			}
		}

		for (int s[] : steps) {
			System.out.println(Arrays.toString(s));
		}

		return steps[steps.length - 1][1];
	}

	public static void main(String[] args) {
		int[] A = { 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0 };

		System.out.println((new FibFrog()).solution(A));
	}
}
