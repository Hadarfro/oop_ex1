import java.util.ArrayList;
import java.util.List;

public abstract class  ConcretePiece implements Piece{
private Player owner;
private String typePiece; // king or pawn
    private Position piecePosition;
    private String numPiece;
    private int numKill;

    private List<Position> moveHistory;

   public ConcretePiece(){
        owner = this.getOwner();
        typePiece = this.getType();
        piecePosition = this.getPiecePosition();
       numPiece = this.getNumPiece();
       numKill = this.getNumKills();
       moveHistory= new ArrayList<>();
    }

   public ConcretePiece(Player player,String typePiece,Position piecePosition, String numPiece){
        this.owner = player;
        this.typePiece = typePiece;
        this.piecePosition = piecePosition;
        this.numPiece = numPiece;
        this.numKill = 0;
       this.moveHistory= new ArrayList<>();
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
    public int getNumKills(){
        return this.numKill++;
    }
    public int setNumKills(){
        return this.numKill++;
    }
    public List<Position> getMoveHistory() {
        return moveHistory;
    }

    public void addToMoveHistory(Position newPosition) {
        moveHistory.add(newPosition);
    }

}
