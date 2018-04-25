
import java.awt.Color;



public class Rook extends Piece implements PiecesInterface 
{    
    public Rook(Color color, ChessBlock startingPosition)
    {
        super(color, startingPosition);
        pieceName = "rook";
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
    
    public boolean canCastle()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void castle()
    {
        //move the king left 3 squares and rook right 2 squares or move king right
        //2 squares and rook left 2 squares (essentially swapping position of king and rook
        // -can't happen through a threatened square
        // -castle can only happen if the king and rook have not moved
    }
}
