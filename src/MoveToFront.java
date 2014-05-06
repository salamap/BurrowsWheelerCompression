public class MoveToFront {
	private static final int N = 256;
	
    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() {
    	char[] characters = new char[N];
    	init(characters);
    	while (!BinaryStdIn.isEmpty()) {
    		char c = BinaryStdIn.readChar();
    		int index = getIndex(c, characters);
    		BinaryStdOut.write((char)index);
    		mtf(index, characters);
    	}
    	BinaryStdOut.close();
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {
    	char[] characters = new char[N];
    	init(characters);
    	while (!BinaryStdIn.isEmpty()) {
    		char c = BinaryStdIn.readChar();
    		BinaryStdOut.write(characters[c]);
    		mtf(c, characters);
    	}
    	BinaryStdOut.close();
    }

    // if args[0] is '-', apply move-to-front encoding
    // if args[0] is '+', apply move-to-front decoding
    public static void main(String[] args) {
        if      (args[0].equals("-")) encode();
        else if (args[0].equals("+")) decode();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
    
    private static void init(char[] a) {
    	for (char c = 0; c < N; c++) {
    		a[c] = c;
    	}
    }
    
    private static int getIndex(char c, char[] a) {
    	int i = 0;
    	for (; i < N; i++)
    		if (a[i] == c) break;
    	return i;
    }
    
    private static void mtf(int index, char[] a) {
    	char c = a[index];
    	for(;index > 0; index--) {
    		a[index] = a[index - 1];
    	}
    	a[0] = c;
    }
}