
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Pawn implements PiecesInterface {
    ChessBlock StartingPos;
    
    public Pawn(ChessBlock StartingPos){
        this.StartingPos = StartingPos;
    }
    
    public String pieceImage(){
        return "";
    }
    
    public boolean startPos(){
        if(true){
            return true;
        }else if(false){
            return true;
        }else{
            return false;
        }
    }
    
    @Override
    public void move(int dir)  {
        
    }
    
    //private String getBlockName() {}
    
    @Override
    public void moveDistance(int dis) {
        if( startPos())
        {
            
        }
        
    }

    @Override
    public boolean canAttack() {
        if(true)
            return true;
        else
            return false;
    }
    
    @Override
    public boolean inCheck() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void promotion(){
        
        
    }

}
