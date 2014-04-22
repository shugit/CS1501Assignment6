package partA;
/**
 * Alice¡¯s private pair is (235,391). Write a Java program that behaves as follows:
 * java DecodeInts.java
 * Enter the decoding exponent d : 235
 * Enter the modulus n : 391
 * Enter the number of integers to decode : 11
 * 234 16  301   301 304 315 143 301 265 228 16
 * Hello Alice
 * @author SephyZhou
 *
 */
public class DecodeInts {
	public static void main(String args[]) {
		long d;
		long n;
		System.out.println( "Please enter a public key pair (d, n).");
		System.out.print( "Enter \"d\":");
		d = StdIn.readLong();
		System.out.print( "Enter \"n\":");
		n = StdIn.readLong();
		System.out.print( "Enter the number of integers to decode :");
		int count = StdIn.readInt();
		System.out.print( "Please enter the integer to be decoded:");
		String result = "";
		for(int i = 0; i<count; i++){
			long c = StdIn.readLong();
			char m = (char) expomod(c, d, n);
			result+= m+"";
		}
		System.out.println(result);		
		
	}
	static long expomod(long a, long n, long z) {
		long r = a % z;

		for (long i = 1; i < n; i++) {
			r = (a * r) % z;
		}
		return r;
	}
}
