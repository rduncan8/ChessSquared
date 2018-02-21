

public class Rook implements PiecesInterface {
    ChessBoard cb = new ChessBoard();
    int currPos;
    
    public Rook(ChessBoard cb, int currPos){
        this.cb = cb;
        this.currPos = currPos;
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
    
    public boolean hasMoved(){
        return true;
    }
    
}
