
public class King implements Pieces {
    ChessBoard cb = new ChessBoard();
    int col;
    int currPos;
    
    public King(int col, ChessBoard cb, int currPos){
        this.col = col;
        this.cb = cb;
        this.currPos = currPos;
    }
    
    public void color(){
        
    }
    
    public boolean startPos(){
        if(col == 1 && currPos == cb.e[0]){
            return true;
        }else if(col == 2 && currPos == cb.d[7]){
            return true;
        }else{
            return false;
        }
    }    
    
    public void move(int dir){
        
    }
    
    @Override
    public void moveDistance(int dis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean canAttack() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean inCheck(){
        
        return true;
    }
    
    public boolean canCastle(){
        
        return true;
    }    
    
}
