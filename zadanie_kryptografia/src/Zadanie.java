import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;
import java.util.logging.MemoryHandler;


public class Zadanie {

	/**
	 * @param args
	 * @throws IOException 
	 */
	
	// Funkcja obliczaj¹ca NWD dla dwóch liczb
	//----------------------------------------
	public BigInteger NWD( BigInteger a, BigInteger b)
	{
		BigInteger t;
		  while(b.compareTo(BigInteger.valueOf(0)) != 0)
		  {
		    t = b;
		    b = a.mod(b);
		    a = t;
		  }
		  return a;
	}
	
	// Funkcja obliczania odwrotnoœci modulo n
	//----------------------------------------
	public BigInteger modulo(BigInteger a, BigInteger n)
	{

		BigInteger a0,n0,p0,p1,q,r,t;
		  p0 = BigInteger.valueOf(0); p1 = BigInteger.valueOf(1); a0 = a; n0 = n;
		  q  = n0.divide(a0);
		  r  = n0.mod(a0);
		  while(r.compareTo(BigInteger.valueOf(0)) > 0)
		  {
		    t = p0.subtract(q.multiply(p1));
		    if(t.compareTo(BigInteger.valueOf(0)) >= 0)
		    {
		    	t = t.mod(n);
		    }
		    else
		    {
		    	t = n.subtract(((t.negate()).mod(n)));
		    }
		    p0 = p1; p1 = t;
		    n0 = a0; a0 = r;
		    q  = n0.divide(a0);
		    r  = n0.mod(a0);
		  }
		  return p1;
	}	
	
	// Procedura generowania kluczy RSA
	//---------------------------------
	public BigInteger klucze()
	{
		BigInteger p,q,phi,n,e,d;
		System.out.println("Generowanie klucza RSA");
		// generujemy dwie ró¿ne, losowe liczby pierwsze
		Random rnd = new Random();
		p = new BigInteger(512, rnd);if (p.isProbablePrime(20)) ;
		q = new BigInteger(512, rnd);if (q.isProbablePrime(20)) ;
		System.out.println(p);
		System.out.println(q);
		BigInteger bi = BigInteger.valueOf(1);

		  phi = (p.subtract(bi)).multiply((q.subtract(bi)));
		  //System.out.println(phi);
		  n   = p.multiply(q);
		 // System.out.println(n);
		  
		// wyznaczamy wyk³adniki e i d

		  for(e = BigInteger.valueOf(3); NWD(e,phi).compareTo(BigInteger.valueOf(1)) != 0; e = e.add(BigInteger.valueOf(2)));
		  //System.out.println(e);
		  d = modulo(e,phi);
			//System.out.println(e);
			//System.out.println(n);
			//System.out.println(d);
		// gotowe, wypisujemy klucze
			return d;
		  
	}

	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//Random rnd = new Random();
		//BigInteger bi = new BigInteger(128, rnd);if (bi.isProbablePrime(20)) ;
		//System.out.println(bi);
		BigInteger big;
		FileOutputStream stream = new FileOutputStream("plik.txt");
		Zadanie zadanie = new Zadanie();
		for (int i = 0; i < 100; i++)
		{
			//zadanie.klucze();//System.out.println(i);
			stream.write(zadanie.klucze().toByteArray());
		}
		System.out.println("Koniec!!!!!!");
		//ByteArrayOutputStream bos = new ByteArrayOutputStream(zadanie.klucze());


		//zadanie.klucze();
		//System.out.println(big);
		System.in.read();
	}

}
