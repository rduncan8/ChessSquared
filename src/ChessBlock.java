import javax.swing.*;

public class ChessBlock 
{
    private Piece piece = null;
    JButton button;
    private String blockDescription;
    public int x;
    public int y;
    
    public ChessBlock(String blockDescription, JButton button, int x, int y)
    {
        this.blockDescription = blockDescription;
        this.button = button;
        this.x = x;
        this.y = y;
    }
    
    public String getBlockDescription()
    {
        return blockDescription;
    }
    
    public void setPiece(Piece piece)
    {
        this.piece = piece;
    }
    
    public Piece getPiece()
    {
        return piece;
    }
    
    public boolean hasPiece()
    {
        return piece != null;
    }
}
