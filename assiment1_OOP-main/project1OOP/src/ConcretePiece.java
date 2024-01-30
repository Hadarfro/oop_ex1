public abstract class  ConcretePiece implements Piece{
private Player owner;
private String typePiece; // king or pawn
    private Position piecePosition;
    private String numPiece;
    private int numKile;
   public ConcretePiece(){
        owner = this.getOwner();
        typePiece = this.getType();
        piecePosition = this.getPiecePosition();
       numPiece = this.getNumPiece();
       numKile = this.getNumKiles();
    }
   public ConcretePiece(Player player,String typePiece,Position piecePosition, String numPiece,int numKile){
        this.owner = player;
        this.typePiece = typePiece;
        this.piecePosition = piecePosition;
        this.numPiece = numPiece;
        this.numKile = 0;
    }

    @Override
    public Player getOwner() {
      Player p = this.owner;
        return p;
    }

    @Override
    public String getType() {
        if("♔".equals(this.typePiece)){
            return "♔";
        }
        if ("♙".equals(this.typePiece)){
            return "♙";
        }
        return "";
    }
    public Position getPiecePosition(){
       return this.piecePosition;
    }
    public String getNumPiece(){
       return this.numPiece;
    }
    public int getNumKiles(){
        return this.numKile++;
    }
    public int setNumKiles(){
        return this.numKile++;
    }
}
