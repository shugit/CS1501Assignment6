package partA;
/**
 * The program KeyGen.java will ask the user for two primes (p and q) with p < q
 * . It will compute n, phi, e, and d and will display these values to the user
 * in a nicely formatted way.
 * Please enter a prime number (p): 419
     Please enter another prime number (q): 541
     Then, produce the following output in the terminal/prompt:
        p = 419 q = 541
        The value of n =   226679
        The value of phi = 225720
        The value of e =   7
        The value of d =   128983
 * @author SephyZhou
 * 
 */
public class KeyGen {
	public static void main(String args[]) {
		long p;
		long q;
		System.out.print( "Please enter a prime number (p):");
		p = StdIn.readInt();
		System.out.print( "Please enter another prime number (q):");
		q = StdIn.readInt();
		System.out.println("p = " + p + " q = " + q);
		long n = p * q;
		System.out.println( "The value of n =\t"+n);
		long phi = (p - 1) * (q - 1);
		System.out.println( "The value of phi =\t"+phi);
		long e = findfirstnocommon(phi);
		System.out.println( "The value of e =\t"+e);
		long d = findinverse(e, phi);
		System.out.println( "The value of d =\t"+d);
		
	}
	static long findinverse(long n, long phi) {
		long i = 2;
		while (((i * n) % phi) != 1)
			i++;
		return i;
	}
	
	static long findfirstnocommon(long n) {

		long j;
		for (j = 2; j < n; j++)
			if (euclid(n, j) == 1)
				return j;
		return 0;
	}
	static long euclid(long m, long n) {

		// pre: m and n are two positive integers (not both 0)
		// post: returns the largest integer that divides both
		// m and n exactly

		while (m > 0) {
			long t = m;
			m = n % m;
			n = t;
		}
		return n;
	}
	
	
}
