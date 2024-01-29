public class Pawn extends ConcretePiece{
private Position pawnPosition;
private int numKils;


   public Pawn(Player owner, Position pawnPosition,String numPiece){
       super(owner,"♙",pawnPosition,numPiece);
     this.pawnPosition = pawnPosition;
       this.numKils = 0;
    }
    public Position getPawnPosition(){
       return this.pawnPosition;
    }

    public int getNumKils(){
       return this.numKils;
    }

    public int setNumKils(){
        return this.numKils++;
    }

}
