package ProgettoASD;

public class Tree {

    private int[] count = new int[26];
    private Node[] leaf = new Node[26];
    private Node root;

    public Tree(){
        root=null;                                  //creo un nuovo albero vuoto
    }

    public void add(char value){
    	if(root==null){                             //se l'albero e' vuoto creo il nodo root
            root=new Node(value);
            count[root.index]++;
            leaf[root.index] = root;                //aggiungo il nodo al vettore delle foglie
            return;
        }
    	Node nodoNew = new Node(value);             //altrimenti procedo con l'inserimento del nodo
        Node n = root;
        Node parent=null;
        while (n != null){                          //scendo nell' albero finche' non trovo un nodo null
            if (nodoNew.value <= n.value){
                parent = n;
                n = n.left;
            }
            else {
                parent = n;
                n = n.right;
            }
        }
        nodoNew.parent = parent;                        //imposto il parent del nuovo nodo
        if (nodoNew.value <= parent.value)              //metto il nodo a sinistra o a destra in base al carattere
            parent.left = nodoNew;
        else
            parent.right = nodoNew;
        nodoNew.depth = parent.depth+1;                 //aggiorno la profondita del nodo
        count[nodoNew.index]++;                         //incremento contatore occorrenze
        if(parent.equals(leaf[parent.index]))           //se il genitore e' una foglia
            leaf[parent.index] = null;                  //lo rimuovo dal vettore delle foglie
        leaf[nodoNew.index] = nodoNew;                  //e aggiungo il figlio alle foglie
    }
        
    public  int getMax() {                       //ottengo il numero di occorrenze del carattere piu' frequente
        int max = 0;
        for(int i=0;i<26;i++){
            if(count[i]>max)
                max=count[i];
        }
        return max;
    }
    
	public  int getMin() {						//ottengo il numero di occorrenze del carattere meno frequente
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++) {
            if (count[i] < min)
                min = count[i];
        }
        return min;
    }

    public Node[] getLeaf(){                    //restituisco le foglie dell'albero
        return leaf.clone();
    }

    public int pathLength(Node u,Node v){       //calcola la lunghezza del cammino da u a v con u.depth<v.depth
        int length=0;
        if(u.depth>v.depth)                     //se u e' piu' profondo di v richiamo pathLength scambiando u e v
            return pathLength(v,u);
        while (u.depth!=v.depth){           //fino a quando non arrivo alla stessa altezza dell'altro nodo
            v=v.parent;
            length++;
        }
        while (u!=v){                           //quando u e v hanno la stessa profondita'
        	u=u.parent;                         //risalgo entrambi i rami fino a trovare lo stesso nodo
            v=v.parent;                         //ovvero il primo nodo in comune
            length+=2;                          //incremento la lunghezza del cammino di 2 poiche' salgo da entrambi i rami
        }
        return length;
    }
}