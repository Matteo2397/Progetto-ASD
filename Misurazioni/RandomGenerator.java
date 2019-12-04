package Misurazioni;

public class RandomGenerator {

    private double seed;
    // costruttore della classe, genera un'istanza di RandomGenerator,
    // fissando il seme iniziale a s.
    public RandomGenerator(double s)
    {
        seed = s;
    }
    // restituisce un numero compreso tra 0 e 1 (e aggiorna il seme)
	public double rnd()
	{
		final long a = 16807;
		final long m = 2147483647;
		final long q = 127773;
		final long r = 2836;
		
			
		double hi = (int)(seed / q);
		double lo = seed - q * hi;
		double test = a * lo - r * hi;
		if (test < 0) {
		   seed = test + m;
		} else {
		   seed = test;
		}
		return seed / m;
	}
	private char randomChar(){				//Algoritmo Policriti
        long n = (long) Math.round(rnd()*26+96.5);
        return (char) n;
    }
    public String randomString(double length){
        StringBuilder str = new StringBuilder();
        for(int i=0;i<length;i++){
            str.append(randomChar());
        }
        return str.toString();
    }
}
