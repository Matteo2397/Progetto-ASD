package ProgettoASD;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
    	execute();
    }
    public static void execute() {
    	@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);		
    	String line = input.nextLine();					//aquisisco l'input dallo Standard Input
    	Tree T = new Tree();							//creo un albero vuoto
        if (line.length() != 0) {						//se la stringa di input non e' vuota
            for (int i = 0; i < line.length(); i++)		//aggiungo ogni carattore all'albero
                T.add(line.charAt(i));
        }
        Graph G = new Graph();							//creo un grafo vuoto
        G.addVertices(T);								//aggiungo i vertici del grafo passando l'albero
        G.addEdges(T);									//aggiungo gli archi del grafo passando l'albero
        int Vertex = G.getVCardinality();
        int Edges = G.getECardinality();
        System.out.println(Vertex+" "+Edges);			//stampo in numero di vertici e archi
        G.printDOT();									//stampo il grafo in formato DOT
    }
  
    //esecuzione dell'algoritmo su una stringa passata da argomento e senza stampa dell'output per la misura dei tempi
    public static void execute(String in) {	
    	String line = in;								//aquisisco l'input dallo Standard Input
    	Tree T = new Tree();							//creo un albero vuoto
        if (line.length() != 0) {						//se la stringa di input non e' vuota
            for (int i = 0; i < line.length(); i++)		//aggiungo ogni carattore all'albero
                T.add(line.charAt(i));
        }
        Graph G = new Graph();							//creo un grafo vuoto
        G.addVertices(T);								//aggiungo i vertici del grafo passando l'albero
        G.addEdges(T);									//aggiungo gli archi del grafo passando l'albero
    }
}