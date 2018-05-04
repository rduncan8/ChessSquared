
import java.awt.Color;
import javax.swing.ImageIcon;

public class Rook extends Piece implements PiecesInterface 
{    
    public Rook(Color color, ChessBlock startingPosition)
    {
        super(color, startingPosition);
        pieceName = "rook";
        value = 525;
    }
    
    public void castle()
    {
        //move the king left 3 squares and rook right 2 squares or move king right
        //2 squares and rook left 2 squares (essentially swapping position of king and rook
        // -can't happen through a threatened square
        // -castle can only happen if the king and rook have not moved
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
