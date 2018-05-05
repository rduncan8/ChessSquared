
import java.awt.Color;
import javax.swing.ImageIcon;

public class Rook extends Piece
{    
    public Rook(Color color, ChessBlock startingPosition)
    {
        super(color, startingPosition);
        pieceName = "rook";
        value = 525;
    }
    
    @Override
    public ImageIcon getPieceIcon() 
    {
        if (color == Color.WHITE)
        {
            return new ImageIcon(getClass().getResource("resources/White_Rook.png"));
        }
        else
        {
            return new ImageIcon(getClass().getResource("resources/Black_Rook.png"));
        }
    }
}
