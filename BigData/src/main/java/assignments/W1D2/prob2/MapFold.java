package assignments.W1D2.prob2;

public class MapFold {

	public static int f(int x) {
		return (x == 4 || x == 3) ? x : 0;
	}

	public static int g(int x, int y) {
		if (x == 0 || y == 3)
			return y;
		return x;
	}

	public static void main(String[] args) {
		int[] a = { 13, 4, 12, 16, 0, 21 };
		int[] b = new int[a.length];
		int[] c = new int[a.length];

		for (int i = 0; i < a.length; i++) {
			b[i] = f(a[i]);
		}

		int x = 0;
		for (int i = 0; i < a.length; i++) {
			x = g(x, b[i]);
			c[i] = x;
		}
		for (int i = 0; i < a.length; i++)
			System.out.print("\t" + a[i] + " ");
		System.out.println();
		for (int i = 0; i < a.length; i++)
			System.out.print("\t" + b[i] + " ");
		System.out.println();
		for (int i = 0; i < a.length; i++)
			System.out.print("\t" + c[i] + " ");
		System.out.println();
	}
}
