
import java.awt.Color;
import javax.swing.ImageIcon;

public class Pawn extends Piece implements PiecesInterface 
{    
    public Pawn(Color color, ChessBlock startingPosition)
    {
        super(color, startingPosition);
        pieceName = "pawn";
        value = 100;
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

    @Override
    public ImageIcon getPieceIcon() 
    {
        if (color == Color.WHITE)
        {
            return new ImageIcon(getClass().getResource("resources/White_Pawn.png"));
        }
        else
        {
            return new ImageIcon(getClass().getResource("resources/Black_Pawn.png"));
        }
    }
}
