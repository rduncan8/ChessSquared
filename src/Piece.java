
import java.awt.Color;



public class Piece 
{
    private final Color color;
    private ChessBlock currentPosition;
    private final ChessBlock startingPosition;
    public boolean isPieceSelected;
    protected String pieceName;
    
    public Piece(Color color, ChessBlock startingPosition)
    {
         this.color = color;
         this.startingPosition = startingPosition;
         currentPosition = startingPosition;
    }
    
    public Color getColor()
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
    
    public String getPieceName()
    {
        return pieceName;
    }
}