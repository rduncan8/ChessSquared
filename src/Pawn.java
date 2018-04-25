
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pawn extends Piece implements PiecesInterface 
{    
    public Pawn(Color color, ChessBlock startingPosition)
    {
        super(color, startingPosition);
        pieceName = "pawn";
    }
    
    @Override
    public boolean canAttack() 
    {
        if(true)
            return true;
        else
            return false;
    }
    
    @Override
    public boolean inCheck() 
    {
        throw new UnsupportedOperationException("Not supported yet."); 
        //To change body of generated methods, choose Tools | Templates.
    }
    
    public void promotion()
    {
        //once the pawn reaches the enemy's back line it can become a 
        //bishop, knight, rook, or queen
        
    }
    
    public void enpassent()
    {
        //pawn1 can capture pawn2 if pawn1 is in position to be side by side with pawn2
    }
}
