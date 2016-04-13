package chapter4;

import java.util.Arrays;

public class Triangle {
	/*
	 * A zero-indexed array A consisting of N integers is given. A triplet (P,
	 * Q, R) is triangular if 0 ≤ P < Q < R < N and:
	 * 
	 * A[P] + A[Q] > A[R], A[Q] + A[R] > A[P], A[R] + A[P] > A[Q]. For example,
	 * consider array A such that:
	 * 
	 * A[0] = 10 A[1] = 2 A[2] = 5 A[3] = 1 A[4] = 8 A[5] = 20 Triplet (0, 2, 4)
	 * is triangular.
	 * 
	 * Write a function:
	 * 
	 * class Solution { public int solution(int[] A); }
	 * 
	 * that, given a zero-indexed array A consisting of N integers, returns 1 if
	 * there exists a triangular triplet for this array and returns 0 otherwise.
	 * 
	 * For example, given array A such that:
	 * 
	 * A[0] = 10 A[1] = 2 A[2] = 5 A[3] = 1 A[4] = 8 A[5] = 20 the function
	 * should return 1, as explained above. Given array A such that:
	 * 
	 * A[0] = 10 A[1] = 50 A[2] = 5 A[3] = 1 the function should return 0.
	 * 
	 * Assume that:
	 * 
	 * N is an integer within the range [0..100,000]; each element of array A is
	 * an integer within the range [−2,147,483,648..2,147,483,647]. Complexity:
	 * 
	 * expected worst-case time complexity is O(N*log(N)); expected worst-case
	 * space complexity is O(N), beyond input storage (not counting the storage
	 * required for input arguments). Elements of input arrays can be modified.
	 */

	public int solution(int[] A) {
		int[] edges = new int[A.length];
		int edgeIndex = 0;
		for (int i = 0; i < A.length; i++) {
			if (A[i] > 0) {
				// Add only positive
				edges[edgeIndex++] = A[i];
			}
		}

		// No edges
		if (edgeIndex < 3) {
			return 0;
		}

		Arrays.sort(edges);

		for (int i = 0; i < edges.length - 2; i++) {
			int a = edges[i];
			int b = edges[i + 1];
			int c = edges[i + 2];

			if (a != 0 && isAnswer(a, b, c)) {
				return 1;
			}
		}

		// check last two
		int a = edges[edges.length - 2];
		int b = edges[edges.length - 1];
		int c = edges[0];
		if (a != 0 && isAnswer(a, b, c)) {
			return 1;
		}

		a = edges[edges.length - 1];
		b = edges[0];
		c = edges[1];
		if (a != 0 && isAnswer(a, b, c)) {
			return 1;
		}

		return 0;

	}

	private boolean isAnswer(int a, int b, int c) {
		return ((long) a + (long) b > (long) c && (long) a + (long) c > (long) b && (long) b + (long) c > (long) a);
	}
}
