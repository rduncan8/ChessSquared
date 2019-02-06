
import java.awt.Color;
import javax.swing.ImageIcon;

public class Pawn extends Piece
{    
    public Pawn(Color color, ChessBlock startingPosition)
    {
        super(color, startingPosition);
        pieceName = "pawn";
        value = 100;
    }
    
    @Override
    public ImageIcon getPieceIcon() 
    {
        if (color == Color.WHITE)
        {
            return new ImageIcon("White_Pawn.png");
        }
        else
        {
            return new ImageIcon("Black_Pawn.png");
        }
    }
}
