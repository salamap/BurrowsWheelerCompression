public class CircularSuffixArray {
	private static final int CUTOFF = 15;
	private final int N;
	private int[] index;

	public CircularSuffixArray(String s) {
		N = s.length();
		index = new int[N];
		for (int i = 0; i < N; i++)
			index[i] = i;
		sort(s, 0, N-1, 0);
	}

	public int length() { return N; }

	public int index(int i) { 
		if (i < 0 || i >= N) throw new IndexOutOfBoundsException();
		return index[i]; 
	}

	private char charAt(String s, int suffix, int d) {
		return s.charAt((suffix + d) % N);
	}

	// 3-way Radix Quicksort
	private void sort(String s, int lo, int hi, int d) {
		if (hi - lo <= CUTOFF) {
			insertion(s, lo, hi, d);
			return;
		}
		int lt = lo, gt = hi;
		char v = charAt(s, index[lo], d);
		int i = lo + 1;
		while (i <= gt) {
			int t = charAt(s, index[i], d);
			if      (t < v) exch(lt++, i++);
			else if (t > v) exch(i, gt--);
			else              i++;
		}
		sort(s, lo, lt - 1, d);
		if (d < N) sort(s, lt, gt, d + 1);
		sort(s, gt + 1, hi, d);
	}

    private void exch(int i, int j) {
        int swap = index[i];
        index[i] = index[j];
        index[j] = swap;
    }

	// Insertion sort starting at index d
	private void insertion(String s, int lo, int hi, int d) {
		for (int i = lo; i <= hi; i++)
			for (int j = i; j > lo && less(s, j, j - 1, d); j--)
				exch(j, j - 1);
	}

    // is text[i+d..N) < text[j+d..N) ?
	private boolean less(String s, int i, int j, int d) {
		for (; d < N; d++) {
			int curr = charAt(s, index[i], d), prev = charAt(s, index[j], d);
			if (curr < prev) return true;
			if (curr > prev) return false;
		}
		return false;
	}
}