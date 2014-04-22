package partA;
/**
 * Alice¡¯s public key pair is ( e = 3, n = 391). Write a Java program that
 * behaves as follows (EncodeString.java): 
 * java EncodeString 
 * Enter the encoding exponent e : 3 
 * Enter the modulus n : 391 
 * Enter the string to encode : Hello Alice 
 * 234 16 301 301 304 315 143 301 265 228 16
 * 
 * @author SephyZhou
 * 
 */
public class EncodeString {
	public static void main(String args[]) {
		long e;
		long n;
		System.out.println( "Please enter a public key pair (e, n).");
		System.out.print( "Enter \"e\":");
		e = StdIn.readLong();
		System.out.print( "Enter \"n\":");
		n = StdIn.readLong();
		System.out.print( "Please enter the individual character to be encoded:");
		//StdIn.readString();
		StdIn.readLine();
		String string = StdIn.readLine();
		String out ="";
		for(int i = 0; i<string.length(); i++){
			char m = string.charAt(i);
			long c = expomod(m, e, n);
			out += c+" ";
		}
		System.out.println( "Transmitting encoded "+string
				+ " as "+out);
		
	}

	static long expomod(long a, long n, long z) {
		long r = a % z;

		for (long i = 1; i < n; i++) {
			r = (a * r) % z;
		}
		return r;
	}
}
