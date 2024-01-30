public class King extends ConcretePiece {
private Position kingPosition;

public King (){

}
    public King(Player owner,Position kingPosition, String numPiece){
       super(owner,"♔",kingPosition,numPiece,0);
     this.kingPosition = kingPosition;
    }

    public Position getKingPosition(){
        return this.kingPosition;
    }

    public int setNumKiles(){
        return 0;
    }
}
