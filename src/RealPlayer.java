
import java.awt.Color;
import java.util.ArrayList;


public class RealPlayer extends Player
{
    public RealPlayer(Color color)
    {
        super(color);
    }
    
    public void capturePiece(ChessBlock selectedBlock, ChessBlock chessBlock)
    {
        
        if (chessBlock.getPiece().color == Color.WHITE)
        {
            ChessBoard.whitePieces.remove(chessBlock.getPiece());
        }
        else
        {
            ChessBoard.blackPieces.remove(chessBlock.getPiece());
        }
        chessBlock.setPiece(null);
        selectedBlock.getPiece().move(chessBlock);
        selectedBlock.setSelectedPieceButtonColor(false);
        selectedBlock = null;
    }
    
    public void moveToBlock(ChessBlock selectedBlock, ChessBlock chessBlock)
    {
        if (chessBlock == null)
            System.out.println("NULL");
        selectedBlock.getPiece().move(chessBlock);
        selectedBlock.setSelectedPieceButtonColor(false);
        selectedBlock = null;
    }
}
