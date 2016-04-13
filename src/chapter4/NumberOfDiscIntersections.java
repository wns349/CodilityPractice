package chapter4;

import java.util.Arrays;

public class NumberOfDiscIntersections {
	/*
	 * We draw N discs on a plane. The discs are numbered from 0 to N − 1. A
	 * zero-indexed array A of N non-negative integers, specifying the radiuses
	 * of the discs, is given. The J-th disc is drawn with its center at (J, 0)
	 * and radius A[J].
	 * 
	 * We say that the J-th disc and K-th disc intersect if J ≠ K and the J-th
	 * and K-th discs have at least one common point (assuming that the discs
	 * contain their borders).
	 * 
	 * The figure below shows discs drawn for N = 6 and A as follows:
	 * 
	 * A[0] = 1 A[1] = 5 A[2] = 2 A[3] = 1 A[4] = 4 A[5] = 0
	 * 
	 * 
	 * There are eleven (unordered) pairs of discs that intersect, namely:
	 * 
	 * discs 1 and 4 intersect, and both intersect with all the other discs;
	 * disc 2 also intersects with discs 0 and 3. Write a function:
	 * 
	 * class Solution { public int solution(int[] A); }
	 * 
	 * that, given an array A describing N discs as explained above, returns the
	 * number of (unordered) pairs of intersecting discs. The function should
	 * return −1 if the number of intersecting pairs exceeds 10,000,000.
	 * 
	 * Given array A shown above, the function should return 11, as explained
	 * above.
	 * 
	 * Assume that:
	 * 
	 * N is an integer within the range [0..100,000]; each element of array A is
	 * an integer within the range [0..2,147,483,647]. Complexity:
	 * 
	 * expected worst-case time complexity is O(N*log(N)); expected worst-case
	 * space complexity is O(N), beyond input storage (not counting the storage
	 * required for input arguments). Elements of input arrays can be modified.
	 */

	class Pair implements Comparable<Pair> {

		long lowerBound;
		long upperBound;

		Pair(long l, long u) {
			this.lowerBound = l;
			this.upperBound = u;
		}

		@Override
		public int compareTo(Pair other) {
			return Long.compare(lowerBound, other.lowerBound);
		}

		@Override
		public String toString() {
			return "(" + lowerBound + "," + upperBound + ")";
		}
	}

	public int solution(int[] A) {
		Pair[] val = new Pair[A.length];

		// Sort
		for (int i = 0; i < A.length; i++) {
			val[i] = new Pair((long) ((long) i - (long) A[i]), (long) ((long) i + (long) A[i]));
		}
		Arrays.sort(val);

		int ans = 0; // answer, total number of disc intersections
		for (int i = 0; i < A.length; i++) {
			// Search
			int pos = search(val, val[i].upperBound);

			// Exclude anything to left before me and myself (1)
			pos = pos - i - 1;
			ans += pos;

			if (ans > 1E7) {
				return -1;
			}
		}

		return ans;
	}

	public int search(Pair[] range, long target) {
		int low = 0;
		int high = range.length - 1;

		if (range[high].lowerBound == target) {
			return range.length;
		}

		int mid = 0;
		long midVal = 0;

		while (low <= high) {
			mid = (high + low) / 2;
			midVal = range[mid].lowerBound;

			if (midVal < target) {
				low = mid + 1;
			} else if (midVal > target) {
				high = mid - 1;
			} else {
				break;
			}
		}

		if (midVal < target) {
			return mid + 1;
		} else if (midVal > target) {
			return mid;
		} else {
			// Equal, find rightmost
			for (int i = mid; i < range.length; i++) {
				if (range[i].lowerBound > target) {
					return i;
				}
			}

			return range.length;
		}
	}

	public static void main(String[] args) {
		NumberOfDiscIntersections o = new NumberOfDiscIntersections();
		int[] A = { 1, 5, 2, 1, 4, 0 };
		System.out.println(o.solution(A));
	}
}
