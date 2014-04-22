package partA;
/**
 * The program Encode.java will ask the user for a public key pair (e,n) and a
 * single character to be encoded. The character (m) will be entered from the
 * keyboard and it will be used to compute c = me mod n. This encoded value (c)
 * will then be displayed to the user as an integer.
 * "$ java Encode
        Please enter a public key pair (e, n).
        Enter "e": (then I type some valid 'e')
        Enter "n": (then I type some valid 'n')
        Please enter the individual character to be encoded: (then I type some valid character)
         "
         
 * @author SephyZhou
 * 
 */
public class Encode {
	public static void main(String args[]) {
		long e;
		long n;
		System.out.println( "Please enter a public key pair (e, n).");
		System.out.print( "Enter \"e\":");
		e = StdIn.readLong();
		System.out.print( "Enter \"n\":");
		n = StdIn.readLong();
		System.out.print( "Please enter the individual character to be encoded:");
		StdIn.readLine();
		
		char m = StdIn.readString().charAt(0);
		long c = expomod(m, e, n);
		System.out.println( "Transmitting encoded "+m
				+ " as "+c);
		
	}

	static long expomod(long a, long n, long z) {
		long r = a % z;

		for (long i = 1; i < n; i++) {
			r = (a * r) % z;
		}
		return r;
	}

}
