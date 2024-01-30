public class Pawn extends ConcretePiece{
private Position pawnPosition;
//private int numKils;


   public Pawn(Player owner, Position pawnPosition,String numPiece){
       super(owner,"♙",pawnPosition,numPiece,0);
     this.pawnPosition = pawnPosition;

    }
    public Position getPawnPosition(){
       return this.pawnPosition;
    }

    public int setNumKils(){
       int ans = this.getNumKiles() + 1;
        return ans;
    }

}
