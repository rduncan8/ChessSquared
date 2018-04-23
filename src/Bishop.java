
import java.awt.Color;

public class Bishop extends Piece implements PiecesInterface 
{    
    public Bishop(Color color, ChessBlock startingPosition)
    {
        super(color, startingPosition);
        pieceName = "bishop";
    }

    @Override
    public void move(ChessBlock position) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
