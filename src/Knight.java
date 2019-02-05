
import java.awt.Color;
import javax.swing.ImageIcon;

public class Knight extends Piece
{    
    public Knight(Color color, ChessBlock startingPosition)
    {
        super(color, startingPosition);
        pieceName = "knight";
        value = 350;
    }
    
    @Override
    public ImageIcon getPieceIcon() 
    {
        if (color == Color.WHITE)
        {
            return new ImageIcon("White_Knight.png");
        }
        else
        {
            return new ImageIcon("Black_Knight.png");
        }
    }
}
