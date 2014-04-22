package partA;
/**
 * The program Decode.java will ask the user for a private key pair (d,n) and a
 * single integer (c) to be decoded. The program will then compute m = cd mod n
 * and display the result as a character.
 * 
 * @author SephyZhou
 * 
 */
public class Decode {
	public static void main(String args[]) {
		long d;
		long n;
		System.out.println( "Please enter a public key pair (d, n).");
		System.out.print( "Enter \"d\":");
		d = StdIn.readLong();
		System.out.print( "Enter \"n\":");
		n = StdIn.readLong();
		System.out.print( "Please enter the integer to be decoded:");
		//StdIn.readString();
		long c = StdIn.readLong();
		char m = (char) expomod(c, d, n);
		System.out.println("Decoding " + c + " to " + m);		
		
	}
	static long expomod(long a, long n, long z) {
		long r = a % z;

		for (long i = 1; i < n; i++) {
			r = (a * r) % z;
		}
		return r;
	}

}
