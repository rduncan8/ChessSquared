
import java.awt.Color;
import javax.swing.ImageIcon;

public class King extends Piece
{
    public boolean isInCheck = false;
    
    public King(Color color, ChessBlock startingPosition)
    {
        super(color, startingPosition);
        pieceName = "king";
        value = 10000;
    }  
    
    @Override
    public ImageIcon getPieceIcon() 
    {
        if (color == Color.WHITE)
        {
            return new ImageIcon("White_King.png");
        }
        else
        {
            return new ImageIcon("Black_King.png");
        }
    }
}
