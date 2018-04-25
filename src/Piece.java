
import java.awt.Color;

public class Piece
{
    protected final Color color;
    protected ChessBlock currentPosition;
    protected final ChessBlock startingPosition;
    protected String pieceName;
    protected boolean hasMoved = false;
    
    public Piece(Color color, ChessBlock startingPosition)
    {
         this.color = color;
         this.startingPosition = startingPosition;
         currentPosition = startingPosition;
    }
    
    public void move(ChessBlock position)
    {
        position.setPiece(currentPosition.getPiece());
        currentPosition.setPiece(null);
        currentPosition = position;
        hasMoved = true;
    }
    
    public Color getPieceColor()
    {
        return color;
    }
    
    public ChessBlock getCurrentPosition()
    {
        return currentPosition;
    }
    
    public ChessBlock getStartingPosition()
    {
        return startingPosition;
    }
    
    public boolean isPieceInStartingPosition()
    {
        return currentPosition == startingPosition;
    }
    
    public void setCurrentPosition(ChessBlock position)
    {
        currentPosition = position;
    }
    
    public String getPieceDescription()
    {
        if (color == Color.BLACK)
        {
            return "Black" + pieceName;
        }
        else
        {
            return "White" + pieceName;
        }
    }
}