import java.util.ArrayList;
import java.util.List;

public class GameLogic implements PlayableLogic{
    private Player edit;
    private ConcretePlayer firstPlayer;
    private ConcretePlayer secendPlayer;
    private Player currentPlayer;
    private King king;
    private ConcretePiece[][] board;
    private List<Position> moveHistory;

    public GameLogic() {
        this.board = new ConcretePiece[11][11];
        this.firstPlayer = new ConcretePlayer(1);
        this.secendPlayer = new ConcretePlayer(2);
        this.currentPlayer = secendPlayer;
        this.king = new King(this.firstPlayer,new Position(5,5),"K7");
       initBoard();
    }
   public void initBoard(){// placing the board how it should be at the start
        initKing();
        initPawnPlayer1();
        initPawnPlayer2();
    }

    public void initKing(){ //placing the king at the start of the game
     Position kingPositionPlayer1 = new Position(5,5);
     ConcretePiece player1King = this.king;
     placePiece(player1King,kingPositionPlayer1);
    }

    public void initPawnPlayer1(){
        //right and left sides
        setPawn(4,7,4, false, getFirstPlayer());
        setPawn(4,7,6, false, getFirstPlayer());

        //up and downsides
        setPawn(4,7,4, true, getFirstPlayer());
        setPawn(4,7,6, true, getFirstPlayer());

        //leftOvers :)
        setOnePawn(5,3, getFirstPlayer(),"1D");
        setOnePawn(3,5,getFirstPlayer(),"5D");
        setOnePawn(7,5,getFirstPlayer(),"9D");
        setOnePawn(5,7, getFirstPlayer(),"13D");
    }

    public void initPawnPlayer2(){

        //right and left sides
        setPawn(3,8,0, false, getSecondPlayer());
        setPawn(3,8,10, false,getSecondPlayer());

        //up and downsides
        setPawn(3,8,0, true, getSecondPlayer());
        setPawn(3,8,10, true,getSecondPlayer());

        //leftOvers :)
        setOnePawn(5,1, getSecondPlayer(),"6A");
        setOnePawn(1,5, getSecondPlayer(), "12A");
        setOnePawn(5,9, getSecondPlayer(), "19A");
        setOnePawn(9,5, getSecondPlayer(), "13A");

    }


    /**
     *  set few pawns at the same time
     *
     * @param start - the start of the for-loop (in the matrix - row/col)
     * @param end - the end of the for-loop (in the matrix - row/col)
     * @param staticNum - the num of index who don't change in loop
     * @param isRowChange - if the row change or the col
     * @param player - first player or second player
     */

    public void setPawn(int start, int end, int staticNum, boolean isRowChange, Player player) {
        int countFirstPlayer = 0;
        int countSecondPlayer = 0;
        //set pawn when the number of row is static
        if (!isRowChange) {
            for (int col = start; col < end; col++) {
                countFirstPlayer++;
                if(countFirstPlayer==7){
                    countFirstPlayer++;
                }
                //if its first player:
                if (player.isPlayerOne()) {
                    Position pawnPositionPlayer = new Position(staticNum, col);
                    ConcretePiece player1Pawn = new Pawn(getFirstPlayer(), pawnPositionPlayer, "D"+countFirstPlayer);
                    placePiece(player1Pawn, pawnPositionPlayer);
                }
                //if its second player:
                else {
                    countSecondPlayer++;
                    Position pawnPositionPlayer = new Position(staticNum, col);
                    ConcretePiece player2Pawn = new Pawn(getSecondPlayer(), pawnPositionPlayer,"A"+countSecondPlayer);
                    placePiece(player2Pawn, pawnPositionPlayer);
                }
            }
        }
        //set pawn when the number of col is static
        if (isRowChange) {

            for (int row = start; row < end; row++) {

                //if its first player:
                if (player.isPlayerOne()) {
                    Position pawnPositionPlayer = new Position(row, staticNum);
                    ConcretePiece player1Pawn = new Pawn(getFirstPlayer(), pawnPositionPlayer,"D"+countFirstPlayer);
                    placePiece(player1Pawn, pawnPositionPlayer);
                }
                //if its second player:
                else {
                    Position pawnPositionPlayer = new Position(row, staticNum);
                    ConcretePiece player2PAWN = new Pawn(getSecondPlayer(), pawnPositionPlayer,"A"+countSecondPlayer);
                    placePiece(player2PAWN, pawnPositionPlayer);
                }
            }
        }
    }

    //set only one pawn in every time
    public void setOnePawn(int row, int col, Player player, String numPiece) {//check the numPiece
        Position pawnPos = new Position(row, col);

        if (player.isPlayerOne()) {
            ConcretePiece playerPawn = new Pawn(getFirstPlayer(), pawnPos,numPiece);
            placePiece(playerPawn, pawnPos);
        }
        else {
            ConcretePiece playerPawn = new Pawn(getSecondPlayer(), pawnPos,numPiece);
            placePiece(playerPawn, pawnPos);
        }

    }



    public void placePiece(ConcretePiece piece, Position position) {
        if (position.isValidPosition()) {
            board[position.getX()][position.getY()] = piece;
        } else {
            System.out.println("Invalid position on the board");
        }
    }

    //*****************************end init board ******************************


    @Override
    public boolean move(Position a , Position b) {
        int positionX = a.getX(); //represent the current position that we are checking of x
        int positionY = a.getY();  //represent the current position that we are checking of y
        ConcretePiece piece = null;
        if(board[b.getX()][b.getY()]!=null){// if in position b there's a piece
            return false;
        }
        else if (a.getY()!=b.getY()&&a.getX()!=b.getX()){//if the player try to move diagonally
            return false;
        }
        else if(a.getY()==b.getY()&&a.getX()==b.getX()){//if position a and position b are the same position
            return true;
        }
       else if (!a.isValidPosition()||!b.isValidPosition()){// if one of the positions aren't on the board
            return false;
        }
        else if(!board[a.getX()][a.getY()].getOwner().equals(getCurrentPlayer())||getPieceAtPosition(a)==null){//if it's not a's piece turn return false or there is no piece at a's position
            return false;
        }
        else if (!board[positionX][positionY].getType().equals("♔")&&((b.getX()==0&&b.getY()==0)||(b.getX()==0&&
                b.getY()==10)||(b.getX()==10&&b.getY()==0)||(b.getX()==10&&b.getY()==10))) {//if the piece want to go to the corners
          return false;
        }
        //while there is no piece in the way to position b and that we didn't make it to position b
        while (piece == null && positionY != b.getY() || positionX != b.getX()) {
           if(positionX == b.getX() && positionY < b.getY()){//if we are on the x-axis and bellow the y of b's position
                positionY++;
            }
             else if(positionX == b.getX() && positionY > b.getY()){//if we are on the x-axis and above the y of b's position
                positionY--;
            }
            else  if(positionY == b.getY() && positionX < b.getX()){//if we are on the y-axis and bellow the x of b's position
                positionX++;
            }
            else if(positionY == b.getY() && positionX > b.getX()){//if we are on the y-axis and above the x of b's position
                positionX--;
            }
            piece = board[positionX][positionY];
        }
            if (positionY == b.getY() && positionX == b.getX()) {//if we make it to b's position with no pieces in the way
                if(isGameFinished()){//put func that print what we want
                    if(firstPlayerWin()){
                        this.firstPlayer.setWins();
                    }
                    else if(secondPlayerWin()){
                         this.secendPlayer.setWins();
                    }
                    reset();
                }
                board[positionX][positionY] = board[a.getX()][a.getY()];
                board[a.getX()][a.getY()] = null;
                eatingPiece(positionX,positionY);
                return true;
            }
            else {
                return false;
            }
    }


    public void eatingPiece(int positionX, int positionY){//get the piece that we want to check if he killed someone
        ConcretePiece piece = board[positionX][positionY];
        if (!piece.getType().equals("♔")) { // continue!!!!!!!!!!!!!!!!
            Position pos1 = new Position(positionX+1,positionY);
            Position pos2 = new Position(positionX-1,positionY);
            Position pos3 = new Position(positionX,positionY+1);
            Position pos4 = new Position(positionX,positionY-1);
            if(positionX+1<11) {
                if (board[positionX + 1][positionY] != null && pos1.isValidPosition()) {
                    if (!piece.getOwner().equals(board[positionX + 1][positionY].getOwner())) {
                        if (checkNeighbour(positionX + 1, positionY)) {
                            piece.setNumKills();
                            isGameFinished();
                            board[positionX + 1][positionY] = null;
                        }
                    }
                }
            }
            if(positionX-1>-1) {
                if (board[positionX - 1][positionY] != null && pos2.isValidPosition()) {
                    if (!piece.getOwner().equals(board[positionX - 1][positionY].getOwner())) {
                        if (checkNeighbour(positionX - 1, positionY)) {
                            piece.setNumKills();
                            isGameFinished();
                            board[positionX - 1][positionY] = null;
                        }
                    }
                }
            }
            if(positionY+1<11) {
                if (board[positionX][positionY + 1] != null && pos3.isValidPosition()) {
                    if (!piece.getOwner().equals(board[positionX][positionY + 1].getOwner())) {
                        if (checkNeighbour(positionX, positionY + 1)) {
                            piece.setNumKills();
                            isGameFinished();
                            board[positionX][positionY + 1] = null;
                        }
                    }
                }
            }
            if(positionY-1>-1) {
                if (board[positionX][positionY - 1] != null && pos4.isValidPosition()) {
                    if (!piece.getOwner().equals(board[positionX][positionY - 1].getOwner())) {
                        if (checkNeighbour(positionX, positionY - 1)) {
                            piece.setNumKills();
                            isGameFinished();
                            board[positionX][positionY - 1] = null;
                        }
                    }
                }
            }
        }
        changeTurn();
    }

    public boolean checkKingNeighbour(int posX , int posY){
        if(posX==0){
            if(board[posX+1][posY]!=null&&board[posX][posY+1]!=null&&board[posX][posY-1]!=null) {
                if (!board[posX+1][posY].getOwner().isPlayerOne() && !board[posX][posY + 1].getOwner().isPlayerOne() &&
                        !board[posX][posY-1].getOwner().isPlayerOne()) {
                    return true;
                }
            }
        }
        else if(posX == 10){
            if(board[posX-1][posY]!=null&&board[posX][posY+1]!=null&&
                    board[posX][posY-1]!=null) {
                if (!board[posX - 1][posY].getOwner().isPlayerOne() && !board[posX][posY + 1].getOwner().isPlayerOne() &&
                        !board[posX][posY - 1].getOwner().isPlayerOne()) {
                    return true;
                }
            }
        }
        else if(posY==0){
            if(board[posX+1][posY]!=null&&board[posX-1][posY]!=null&&
                    board[posX][posY+1]!=null) {
                if (!board[posX + 1][posY].getOwner().isPlayerOne() && !board[posX - 1][posY].getOwner().isPlayerOne() &&
                        !board[posX][posY + 1].getOwner().isPlayerOne()) {
                    return true;
                }
            }
        }
        else  if(posY==10){
            if(board[posX+1][posY]!=null&&board[posX-1][posY]!=null&&
                    board[posX][posY-1]!=null) {
                if (!board[posX + 1][posY].getOwner().isPlayerOne() && !board[posX - 1][posY].getOwner().isPlayerOne() &&
                        !board[posX][posY - 1].getOwner().isPlayerOne()) {
                    return true;
                }
            }
        }
        else if(board[posX+1][posY]!=null&&board[posX-1][posY]!=null&&board[posX][posY+1]!=null&&
                board[posX][posY-1]!=null){
            if(!board[posX+1][posY].getOwner().isPlayerOne()&&!board[posX-1][posY].getOwner().isPlayerOne()&&
                    !board[posX][posY-1].getOwner().isPlayerOne()&&!board[posX][posY+1].getOwner().isPlayerOne()){
                return true;
            }
        }
        return false;
    }

    //check the extreme case when the pawn is near the wall and pawn cant go to the corners
    public boolean checkNeighbour(int posX , int posY){// return true if the piece in the position is eaten else return false
       if (board[posX][posY]==null){
           return false;
       }
        int count = 0;
        Player myOwner = board[posX][posY].getOwner();
        int OpponnentLeftRight = 0;
        int OpponnentUpDown = 0;
        int countWall = 0;
        Position neighbour1 = new Position(posX+1,posY);
        Position neighbour2 = new Position(posX-1,posY);
        Position neighbour3 = new Position(posX,posY+1);
        Position neighbour4 = new Position(posX,posY-1);
        if(board[posX][posY].getType().equals("♔")){// if the king might be eaten or winning
            if(checkKingNeighbour(posX,posY)){
                return true;
            }
            return false;
        }
        if ((neighbour1.getX() > 10 || neighbour1.getX() < 0 ||
                neighbour1.getY() > 10 || neighbour1.getY() < 0)) {
            countWall++;
            OpponnentLeftRight++;

        }
         else if(board[posX+1][posY]!=null) {
            if (neighbour1.isValidPosition()&&!board[posX+1][posY].getType().equals("♔") &&
                    !myOwner.equals(board[posX+1][posY].getOwner())) {//if the position is on the board and the two neighbours are opponents
                count++;
                OpponnentLeftRight++;
            }
        }
        if((neighbour2.getX()>10||neighbour2.getX()<0||neighbour2.getY()>10||
                neighbour2.getY()<0)){
            countWall++;
                OpponnentLeftRight++;


        }
        else if (board[posX-1][posY]!=null) {
            if (neighbour2.isValidPosition()&&!board[posX-1][posY].getType().equals("♔") &&!myOwner.equals(board[posX-1][posY].getOwner())) {
                  count++;
                OpponnentLeftRight++;
              }
            }
        if((neighbour3.getX()>10||neighbour3.getX()<0||neighbour3.getY()>10||
                neighbour3.getY()<0)){
            countWall++;
                OpponnentUpDown++;

        }
        else if (board[posX][posY+1]!=null) {
            if (neighbour3.isValidPosition()&&!board[posX][posY+1].getType().equals("♔") &&!myOwner.equals(board[posX][posY+1].getOwner())) {
                 count++;
                OpponnentUpDown++;
             }
         }
         if ((neighbour4.getX()>10||neighbour4.getX()<0||neighbour4.getY()>10||
                 neighbour4.getY()<0)){
                 countWall++;
                  OpponnentUpDown++;
         }
         else if(board[posX][posY-1]!=null) {
             if (neighbour4.isValidPosition()&&!board[posX][posY-1].getType().equals("♔") &&!myOwner.equals(board[posX][posY-1].getOwner())) {
                 count++;
                 OpponnentUpDown++;
             }
         }
        if(count>2|OpponnentUpDown==2||OpponnentLeftRight==2||(countWall+count>2)||
                (OpponnentLeftRight+countWall==2)||(OpponnentUpDown+countWall==2)){
            return true;
        }
        return false;
    }
    public Player getCurrentPlayer(){ //finished
        return this.currentPlayer;
    }

    public void changeTurn(){// finished
        if (this.currentPlayer.equals(getFirstPlayer())){
            this.currentPlayer = getSecondPlayer();
        }
        else {
            this.currentPlayer = getFirstPlayer();
        }
    }

    //***************************end move*****************************************************
    @Override // finished
    public Piece getPieceAtPosition(Position position) {
           return board[position.getX()][position.getY()];
    }

    @Override  // finished
    public Player getFirstPlayer() {
        return this.firstPlayer;
    }

    @Override  // finished
    public Player getSecondPlayer() {
        return this.secendPlayer;
    }
//********************************************************************************************

    @Override
    public boolean isGameFinished() {//continue!!!!!
        if(firstPlayerWin()){
            //need to print first player and then second player

            return true;
        }
        if(secondPlayerWin()){
            //need to print second player and then first player
            return true;
        }
        return false;
    }
    public Position getKingPosition(){
        for(int i=0;i<11;i++){
            for (int j=0;j<11;j++){
                if(board[i][j]!=null) {
                    if (board[i][j].getType().equals("♔")) {
                        return new Position(i, j);
                    }
                }
            }
        }
        return null;
    }
    public boolean firstPlayerWin(){//if the first player wins
        if(getKingPosition()==null){
            return false;
        }
        int kingPosX = getKingPosition().getX();
        int kingPosY = getKingPosition().getY();
        if(kingPosX==0&&kingPosY==0){
            return true;
        }
        if(kingPosX==0&&kingPosY==10){
            return true;
        }
        if (kingPosX==10&&kingPosY==0){
            return true;
        }
        if (kingPosX==10&&kingPosY==10){
            return true;
        }
        return false;
    }

    public boolean secondPlayerWin(){
        int kingPosX = getKingPosition().getX();
        int kingPosY = getKingPosition().getY();
        if(checkNeighbour(kingPosX,kingPosY)){
            return true;
        }
        return false;
    }

    //************************************is game finished*************************************************

    @Override
    public boolean isSecondPlayerTurn() {//finished
        if (!this.currentPlayer.isPlayerOne()){
            return false;
        }
        return true;
    }

    @Override  // finished
    public void reset() {// need to clear the rest of the matrix
        for (int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                board[i][j] = null;
            }
        }
        this.currentPlayer = secendPlayer;
        initBoard();
    }

    @Override
    public void undoLastMove() {

    }

    @Override  // finished
    public int getBoardSize() {
        return 11;
    }
    public ConcretePiece[][] getBoard() {  // finished
        return this.board;
    }
}
