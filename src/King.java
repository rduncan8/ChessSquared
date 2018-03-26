

public class King implements PiecesInterface {
    ChessBlock StartingPos;
    
    public King(ChessBlock StartingPos){
        this.StartingPos = StartingPos;
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
