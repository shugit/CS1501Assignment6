package partB;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Random;

import partA.StdIn;
import partA.StdOut;

public class RSA {
	String publicFile = "public.txt";
	String privateFile = "private.txt";

	public static void main(String args[]) {
		RSA rsa = new RSA();
		if (args.length == 0) {
			rsa.KeyGen();
		} else if (args.length == 2) {
			String command = args[0];
			String filename = args[1];
			if (command.matches("(.*)encrypt(.*)")) {
				StdOut.println("encrypting!");
				rsa.Encode(filename);
			} else if (command.matches("(.*)decrypt(.*)")) {
				StdOut.println("decrypting!");
				rsa.Decode(filename);
			}

		}
	}

	public void KeyGen() {
		// get the number of bits in the primes
		int primeBits = 12;

		// get a random number
		Random rnd = new Random();

		// get two distinct primes of size primeBits
		BigInteger p = new BigInteger(primeBits, 128, rnd);
		BigInteger q;
		do
			q = new BigInteger(primeBits, 128, rnd);
		while (p.compareTo(q) == 0);
		// compute the modulus
		System.out.println("p = " + p + " q = " + q);
		BigInteger n = p.multiply(q);
		System.out.println("The value of n = " + n);
		// compute m = phi(n)
		BigInteger pMinus1 = p.subtract(BigInteger.valueOf(1));
		BigInteger qMinus1 = q.subtract(BigInteger.valueOf(1));
		BigInteger phi = pMinus1.multiply(qMinus1);
		System.out.println("The value of PHI = " + phi);
		// get e relatively prime to m
		BigInteger e = BigInteger.valueOf(3);
		while (e.gcd(phi).compareTo(BigInteger.valueOf(1)) > 0)
			e = e.add(BigInteger.valueOf(2));
		// compute d the decryption exponent
		System.out.println("The public exponent = " + e);
		// BigInteger d = e.modInverse(m);
		BigInteger d = XGCD(e, phi);
		System.out.println("The private key is " + d);
		PrintWriter publicWriter = null;
		try {
			publicWriter = new PrintWriter(publicFile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		publicWriter.print(n + " " + e);
		publicWriter.close();

		PrintWriter privateWriter = null;
		try {
			privateWriter = new PrintWriter(privateFile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		privateWriter.print(n + " " + d);
		privateWriter.close();

	}

	public void Encode(String filename) {
		In in = new In(publicFile);
		String s = in.readAll();
		String[] parts = s.split(" ");
		BigInteger n = new BigInteger(parts[0]);
		BigInteger e = new BigInteger(parts[1]);

		In fileIn = new In(filename);
		String out = "";
		String fileContent = fileIn.readAll();
		for (int i = 0; i < fileContent.length(); i++) {
			char ch = fileContent.charAt(i);
			long chlong = (long) ch;
			BigInteger m = BigInteger.valueOf(chlong);
			BigInteger c = expomod(m, e, n);
			out += c + " ";
		}
		System.out.println(out);
	}

	BigInteger expomod(BigInteger a, BigInteger n, BigInteger z) {
		// BigInteger r = a % z;
		BigInteger r = a.remainder(z);

		// for (BigInteger i = one; i < n; i++) {
		for (BigInteger i = one; i.compareTo(n) < 0; i = i.add(one)) {
			// r = (a * r) % z;
			r = (a.multiply(r)).remainder(z);
		}
		return r;
	}

	public void Decode(String filename) {
		In in = new In(privateFile);
		String s = in.readAll();
		String[] parts = s.split(" ");
		BigInteger n = new BigInteger(parts[0]);
		BigInteger d = new BigInteger(parts[1]);
		In fileIn = new In(filename);
		String out = "";
		String fileContent = fileIn.readAll();
		String[] fileparts = fileContent.split(" ");
		int count = fileparts.length;
		
		System.out.println(fileContent);	
		
		
		String result = "";
		for(int i = 0; i<count; i++){
			long clong = Long.parseLong(fileparts[i]);
			//System.out.println(clong);
			BigInteger c = BigInteger.valueOf(clong);
			BigInteger mbig = expomod(c, d, n);
			char m = (char) mbig.intValue(); 
			result+= m+"";
		}
		System.out.println(result);	
		
		PrintWriter publicWriter = null;
		try {
			publicWriter = new PrintWriter(filename+".cop");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		publicWriter.print(result);
		publicWriter.close();
		
	}

	BigInteger one = new BigInteger("1");

	BigInteger XGCD(BigInteger n, BigInteger phi) {
		BigInteger i = new BigInteger("2");
		while (!(((i.multiply(n)).remainder(phi)).equals(one))) {
			i = one.add(i);
			// StdOut.println(i);
		}
		return i;
		/*
		 * long i = 2; while (((i * n) % phi) != 1) i++; return i;
		 */
	}

}
