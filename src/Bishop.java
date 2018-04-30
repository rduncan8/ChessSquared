
import java.awt.Color;
import javax.swing.ImageIcon;

public class Bishop extends Piece implements PiecesInterface 
{    
    public Bishop(Color color, ChessBlock startingPosition)
    {
        super(color, startingPosition);
        pieceName = "bishop";
    }

    @Override
    public ImageIcon getPieceIcon() 
    {
        if (color == Color.WHITE)
        {
            return new ImageIcon(getClass().getResource("resources/White_Bishop.png"));
        }
        else
        {
            return new ImageIcon(getClass().getResource("resources/Black_Bishop.png"));
        }
    }
}
