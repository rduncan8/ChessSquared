
public class Rook implements Pieces {
    ChessBoard cb = new ChessBoard();
    int col;
    int currPos;
    
    public Rook(int col, ChessBoard cb, int currPos){
        this.col = col;
        this.cb = cb;
        this.currPos = currPos;
    }
    
    @Override
    public void color() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean startPos(){
        if(col == 1 && currPos == cb.a[0] || currPos == cb.h[0]){
            return true;
        }else if(col == 2 && currPos == cb.d[7] || currPos == cb.h[7]){
            return true;
        }else{
            return false;
        }
    }    

    @Override
    public void move(int dir) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void moveDistance(int dis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean canAttack() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean inCheck() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean canCastle(){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
