

public class King implements PiecesInterface {
    ChessBoard cb = new ChessBoard();
    int currPos;
    
    public King(ChessBoard cb, int currPos){
        this.cb = cb;
        this.currPos = currPos;
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
    
    public boolean hasMoved(){
        return true;
    }
    
}
