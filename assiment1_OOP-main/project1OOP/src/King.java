public class King extends ConcretePiece {
private Position kingPosition;

public King (){

}
    public King(Player owner,Position kingPosition, String numPiece){
       super(owner,"♔",kingPosition,numPiece);
     this.kingPosition = kingPosition;
    }

    public Position getKingPosition(){
        return this.kingPosition;
    }

    public int setNumKills(){
        return 0;
    }
}
