
public class Pawn implements Pieces {
    ChessBoard cb = new ChessBoard();
    int col;
    int currPos;
    
    public Pawn(int col, ChessBoard cb, int currPos){
        this.col = col;
        this.cb = cb;
        this.currPos = currPos;
    }
    
    @Override
    public void color() {
        
    }
    
    public boolean startPos(){
        if(col == 1 && currPos == cb.a[1] || currPos == cb.b[1] || currPos == cb.c[1] || currPos == cb.d[1] ||
                currPos == cb.e[1] || currPos == cb.f[1] || currPos == cb.g[1] || currPos == cb.h[1]){
            return true;
        }else if(col == 2 && currPos == cb.a[6] || currPos == cb.b[6] || currPos == cb.c[6] || currPos == cb.d[6] ||
                currPos == cb.e[6] || currPos == cb.f[6] || currPos == cb.g[6] || currPos == cb.h[6]){
            return true;
        }else{
            return false;
        }
    }
    
    @Override
    public void move(int dir) {
        
    }
    
    @Override
    public void moveDistance(int dis) {
        if(startPos())
        {
            
        }
        
    }

    @Override
    public boolean canAttack() {
        if(playerBlack.pieceLocation() == currPos)
        return true;
    }
    
    @Override
    public boolean inCheck() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void promotion(){
        
        
    }

}
