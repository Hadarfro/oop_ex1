public class Position {
private int x;
private int y;
 Position(int row,int col){
    this.x = col;
    this.y = row;
}
    Position(){

    }
    public boolean isValidPosition(){
     if(this.getX()>11||this.getY()>11||this.getX()<0||this.getY()<0) {
         return false;
     }
     return true;
    }
    public int getX(){
     return this.x;
    }
    public int getY(){
        return this.y;
    }

}
