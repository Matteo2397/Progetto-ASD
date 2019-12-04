package ProgettoASD;

public class Node {

	public char value;
	public int index;
	public Node left;
	public Node right;
	public Node parent;
	public int depth;

    public Node(char value){
        this.value=value;                       //carattere contenuto nel nodo
        this.index=value-'a';                   //indice da 0 a 25 per la posizione negli array
        this.left=null;                         //figlio sinistro
        this.right=null;                        //figlio destro
        this.parent=null;                       //genitore
        this.depth=0;                           //profondita'
    }
}