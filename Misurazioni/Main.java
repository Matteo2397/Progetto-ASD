package Misurazioni;

public class Main {

    public static void main(String[] args) {
    	RandomGenerator r = new RandomGenerator(123456789);
        long inputLength=0;
        double tol=2;
        long gran = granularity();
        long tMin = calcolaTMin(gran, tol);
        System.out.println("Granularità: "+gran+"	Tolleranza: "+tol+"%"+"	tMin: "+tMin);
        double za=2.33;					//0.02->2.33    0.05->1.96    0.10->1.64
        int campioniIniziali=10;
        System.out.println("Lunghezza input	Tempo medio	Delta");
    	for(inputLength=1000;inputLength<150000;inputLength*=1.33) {
            double res[]=Misurazione(inputLength, campioniIniziali, za, tMin, r);
            double tempoMedio=res[0];
            double delta=res[1];
            //System.out.println(inputLength+"	"+tempoMedio+"	"+delta);
            System.out.format("%d\t\t%.8f\t%.8f\n",inputLength,tempoMedio,delta); 
    	}
    }
    
    public static void execute(String str){
    	ProgettoASD.Main.execute(str);
    }  
    
    public static double calcoloDelTempo(String input, double rip) {
        double t0 = System.currentTimeMillis();
        for (int i = 0; i < rip; i++) {
    		execute(input);
        }
        double t1 = System.currentTimeMillis();
        double time = t1 - t0;					//tempo totale di esecuzione
        return time/rip;						//tempo medio
    }
    public static long granularity(){
        long t0 = System.currentTimeMillis();
        long t1 = System.currentTimeMillis();
        while (t0==t1){
            t1=System.currentTimeMillis();
        }
        return (t1-t0);
    }
    public static long calcolaTMin(long gran, double tol) {
        return (long)(gran / (tol/100));
    }
    public static double calcolaRip(double tMin, String input){
        double t0 = 0;
        double t1 = 0;
        double rip = 1;
        while (t1-t0 <= tMin) {
            rip = rip*2;
            t0 = System.currentTimeMillis();
            for (int i=0;i<=rip;i++) {
        		execute(input);
            }
            t1 = System.currentTimeMillis();
        }
        double max = rip;
        double min = rip/2;
        int cicliErrati = 5;
        while (max-min >= cicliErrati) {
            rip = (max+min)/2;
            t0 = System.currentTimeMillis();
            for (int i=0;i<rip;i++) {
        		execute(input);
            }
            t1 = System.currentTimeMillis();
            if (t1-t0 <= tMin) {
                min = rip;
            } else {
                max = rip;
            }
        }
        return max;
    }
    
    public static double[] Misurazione(long length,int c,double za,double tMin,RandomGenerator r) {
    	double DELTA=0;
    	double delta=0;
    	double t= 0;
    	double sum2 = 0;
    	double e=0;
    	double cn = 0;
    	double s=0;
	    do{
    		for (int i=0;i<c;i++) {
    			String input=r.randomString(length);
    			double rip=calcolaRip(tMin, input);
	        	double m = calcoloDelTempo(input, rip);
	        	t = t + m;
	        	sum2 = sum2 + m*m;
	    	}
	    	cn = cn + c;
	    	e = t/cn;
	    	DELTA=e/10;
	    	s = Math.sqrt(sum2/cn-e*e);
	    	delta = (1/Math.sqrt(cn))*za*s;
	    }while(delta>=DELTA);
    	return new double[]{e,delta};
    }
}