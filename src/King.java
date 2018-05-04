
import java.awt.Color;
import javax.swing.ImageIcon;

public class King extends Piece implements PiecesInterface 
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
            return new ImageIcon(getClass().getResource("resources/White_King.png"));
        }
        else
        {
            return new ImageIcon(getClass().getResource("resources/Black_King.png"));
        }
    }
}
