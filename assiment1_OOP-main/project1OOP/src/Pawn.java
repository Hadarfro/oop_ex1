public class Pawn extends ConcretePiece{
private Position pawnPosition;
private int numKill;


   public Pawn(Player owner, Position pawnPosition,String numPiece){
       super(owner,"♙",pawnPosition,numPiece);
     this.pawnPosition = pawnPosition;

    }
    public Position getPawnPosition(){
       return this.pawnPosition;
    }

    public int setNumKills(){
       int ans = this.getNumKills() + 1;
        return ans;
    }

}
