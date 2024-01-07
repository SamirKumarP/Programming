package coding;

public class RecursionFib {

	public static void main(String[] args) {
		fib(10);

	}

	private static int fib(int n) {
		if (n <= 0) return 0;
		else if (n == 1) return 1;
		else return fib(n) + fib(n-1);
	}
	

	private static int fibonacci(int n, int[] memo) {
		if (n == 0) return 0;
		else if (n == 1) return 1;
		if (memo[n] == 0) {
			memo[n] = fibonacci(n-1, memo) + fibonacci(n-2, memo);
		}
		return memo[n];
	}

}
