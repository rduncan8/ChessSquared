import javax.swing.*;

public class ChessBlock 
{
    private Piece piece;
    public boolean hasPiece;
    JButton button;
    private String blockDescription;
    
    public ChessBlock(String blockDescription, Piece piece, JButton button)
    {
        hasPiece = true;
        this.blockDescription = blockDescription;
        this.piece = piece;
        this.button = button;
    }
    
    public ChessBlock(String blockDescription, JButton button)
    {
        hasPiece = false;
        this.blockDescription = blockDescription;
        this.button = button;
    }
    
    public String getPieceName()
    {
        return piece.getPieceName();
    }
    
    public String getBlockDescription()
    {
        return blockDescription;
    }
    
    public Piece getPiece()
    {
        return piece;
    }
}
