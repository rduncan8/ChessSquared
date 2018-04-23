
import java.awt.Color;

public class King extends Piece implements PiecesInterface 
{
    ChessBlock StartingPos;
    
    public King(Color color, ChessBlock startingPosition)
    {
        super(color, startingPosition);
        pieceName = "king";
    }  
    
    @Override
    public void move(ChessBlock position)
    {
        
    }
    
    @Override
    public void moveDistance(int dis) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean canAttack() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean inCheck()
    {
        
        return true;
    }
    
    public boolean canCastle()
    {
        
        return true;
    }    
    
    public boolean hasMoved()
    {
        return true;
    }
}
