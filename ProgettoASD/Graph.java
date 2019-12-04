package ProgettoASD;

public class Graph {

    private Node[] V = new Node[26];
    private boolean[][] E = new boolean[26][26];
    private int Vcard;
    private int Ecard;

    public Graph(){             //creo un grafo vuoto
        Vcard = 0;              //inizializzo cardinalita' di V a 0
        Ecard = 0;              //inizializzo cardinalita' di E a 0
    }

    public void addVertices(Tree T){        //aggiungo al grafo i vertici, ovvero le foglie dell'albero T
        V=T.getLeaf();
        for(int i=0;i<26;i++){
            if(V[i]!=null)
                Vcard++;                    //incremento la cardinalita' di V per ogni nuovo vertice
        }
    }

    public void addEdges(Tree T){           //aggiungo al grafo gli archi
        int M=T.getMax();
        int m=T.getMin();
        for(int i=0;i<26;i++){
            for(int j=i;j<26;j++){
                if(V[i]!=null && V[j]!=null && V[i]!=V[j]){
                    int length = T.pathLength(V[i],V[j]);       //calcolo la lunghezza le cammino tra due foglie
                    if(length >= m && length <= M){             //se e' compresa nell' intervallo [m,M] allora c'e' un arco
                        E[i][j]=true;                           //tra i vertici
                        Ecard++;                                //incremento la cardinalia' degli archi
                    }
                }
            }
        }
    }

    public int getVCardinality(){
        return Vcard;
    }

    public int getECardinality(){
        return Ecard;
    }

    public void printDOT(){
        int label = 0;
        System.out.println("graph G {");
        for(int i=0;i<26;i++){
            if(V[i]!=null) {
                System.out.println(V[i].value + " [label=\"" + label + "\"];");         //stampa tutti i vertici
                label++;
            }
        }
        for(int i=0;i<26;i++) {
            for (int j=i;j<26;j++) {
                if(E[i][j]==true){
                    System.out.println(V[i].value + " -- " + V[j].value + ";");       //stampa gli archi
                }
            }
        }
        System.out.println("}");
    }
}