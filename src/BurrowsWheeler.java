public class BurrowsWheeler {
    private static final int R = 256;

	// apply Burrows-Wheeler encoding, reading from standard input and writing to standard output
    public static void encode() {
    	String input = BinaryStdIn.readString();
    	CircularSuffixArray mCSA = new CircularSuffixArray(input);
    	int N = mCSA.length();
    	for (int i = 0; i < N; i++) {
    		if (mCSA.index(i) == 0) {
    			BinaryStdOut.write(i);
    			break;
    		}
    	}
    	for (int i = 0; i < N; i++) {
    		int t = mCSA.index(i) + N - 1;
    		BinaryStdOut.write(input.charAt(t % N));
    	}
    	BinaryStdOut.close();
    }

    // apply Burrows-Wheeler decoding, reading from standard input and writing to standard output
    public static void decode() {
		int first = BinaryStdIn.readInt();
		String last = BinaryStdIn.readString();
		int N = last.length();
		int[] count = new int[R + 1];
		int[] next = new int[N];
		// count frequencies of each letter
		for (int i = 0; i < N; i++)
			count[last.charAt(i) + 1]++;
		// compute frequency cumulates
		for (int r = 0; r < R; r++)
			count[r + 1] += count[r];
		// access cumulates using key as index to move items
		for (int i = 0; i < N; i++)
			next[count[last.charAt(i)]++] = i;
		for (int i = next[first], c = 0; c < N; i = next[i], c++)
			BinaryStdOut.write(last.charAt(i));
		BinaryStdOut.close();
    }

	// if args[0] is '-', apply Burrows-Wheeler encoding
    // if args[0] is '+', apply Burrows-Wheeler decoding
    public static void main(String[] args) {
        if      (args[0].equals("-")) encode();
        else if (args[0].equals("+")) decode();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}
