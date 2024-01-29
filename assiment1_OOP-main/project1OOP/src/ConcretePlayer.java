public class ConcretePlayer implements Player{
private int playerNum;
private int numWins;
    ConcretePlayer(int plyerNumber){
     this.playerNum = plyerNumber;
     this.numWins = 0;
    }
    @Override
    public boolean isPlayerOne() {
     if(this.playerNum==2){
         return false;
     }
        return true;
    }

    @Override
    public int getWins() {
        return  this.numWins;
    }

    public void setWins(){
     this.numWins++;
    }
    public int getPlayerNum(){
        return this.playerNum;
    }
}
