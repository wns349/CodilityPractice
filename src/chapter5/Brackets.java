package chapter5;

public class Brackets {
	/*
	 * A string S consisting of N characters is considered to be properly nested
	 * if any of the following conditions is true:
	 * 
	 * S is empty; S has the form "(U)" or "[U]" or "{U}" where U is a properly
	 * nested string; S has the form "VW" where V and W are properly nested
	 * strings. For example, the string "{[()()]}" is properly nested but
	 * "([)()]" is not.
	 * 
	 * Write a function:
	 * 
	 * class Solution { public int solution(String S); }
	 * 
	 * that, given a string S consisting of N characters, returns 1 if S is
	 * properly nested and 0 otherwise.
	 * 
	 * For example, given S = "{[()()]}", the function should return 1 and given
	 * S = "([)()]", the function should return 0, as explained above.
	 * 
	 * Assume that:
	 * 
	 * N is an integer within the range [0..200,000]; string S consists only of
	 * the following characters: "(", "{", "[", "]", "}" and/or ")". Complexity:
	 * 
	 * expected worst-case time complexity is O(N); expected worst-case space
	 * complexity is O(N) (not counting the storage required for input
	 * arguments).
	 */

	public int solution(String S) {
		if (S.isEmpty()) {
			return 1;
		}

		char[] queue = new char[S.length()];
		int top = 0;

		for (int i = 0; i < S.length(); i++) {
			char ch = S.charAt(i);

			if (ch == '(' || ch == '{' || ch == '[') {
				if (top < S.length()) {
					queue[top++] = ch;
				} else {
					// Queue is full, must be invalid
					return 0;
				}
			} else {
				if (top - 1 >= 0) {
					char pop = queue[--top];
					if (ch == ')' && pop != '(') {
						return 0;
					} else if (ch == '}' && pop != '{') {
						return 0;
					} else if (ch == ']' && pop != '[') {
						return 0;
					}
				} else {
					// Out of index, must be invalid
					return 0;
				}
			}
		}

		return top == 0 ? 1 : 0;
	}

	public static void main(String[] args) {
		Brackets b = new Brackets();
		String S = "{[()()]}";
		System.out.println(b.solution(S));
	}
}
