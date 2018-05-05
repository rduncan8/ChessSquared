
import java.awt.Color;
import java.util.ArrayList;


public class RealPlayer extends Player
{
    public RealPlayer(Color color)
    {
        super(color);
    }
    
    public void capturePiece(ChessBlock[][] board, ChessBlock selectedBlock, ChessBlock chessBlock)
    {
        if (!chessBlock.hasPiece())
        {
            if (selectedBlock.getPiece().color == Color.WHITE)
            {
                ChessBoard.blackPieces.remove(board[chessBlock.x][chessBlock.y - 1].getPiece());
                board[chessBlock.x][chessBlock.y - 1].setPiece(null);
            }
            else
            {
                ChessBoard.whitePieces.remove(board[chessBlock.x][chessBlock.y + 1].getPiece());
                board[chessBlock.x][chessBlock.y + 1].setPiece(null);
            }
        }
        else
        {
            if (chessBlock.getPiece().color == Color.WHITE)
            {
                ChessBoard.whitePieces.remove(chessBlock.getPiece());
            }
            else
            {
                ChessBoard.blackPieces.remove(chessBlock.getPiece());
            }
            
            if ("pawn".equals(selectedBlock.getPiece().pieceName))
            {
                if (selectedBlock.getPiece().color == Color.WHITE)
                {
                    if (chessBlock.y == 7)
                    {
                        ChessBoard.whitePawnPromotions.add(selectedBlock.getPiece());
                    }
                }
                else
                {
                    if (chessBlock.y == 0)
                    {
                        ChessBoard.blackPawnPromotions.add(selectedBlock.getPiece());
                    }
                }
            }
        }
        
        chessBlock.setPiece(null);
        selectedBlock.getPiece().move(chessBlock);
        selectedBlock.setSelectedPieceButtonColor(false);
        selectedBlock = null;
    }
    
    public void moveToBlock(ChessBlock[][] board, ChessBlock selectedBlock, ChessBlock chessBlock)
    {
        if ("pawn".equals(selectedBlock.getPiece().pieceName))
        {
            if (selectedBlock.getPiece().color == Color.WHITE)
            {
                if (selectedBlock.y + 2 < 8)
                {
                    if (chessBlock == board[selectedBlock.x][selectedBlock.y + 2])
                    {
                        if (selectedBlock.x + 1 < 8)
                        {
                            if (board[selectedBlock.x + 1][selectedBlock.y + 2].hasPiece())
                            {
                                if (board[selectedBlock.x + 1][selectedBlock.y + 2].getPiece().pieceName.equals("pawn") &&
                                    board[selectedBlock.x + 1][selectedBlock.y + 2].getPiece().color == Color.BLACK)
                                {
                                    board[selectedBlock.x + 1][selectedBlock.y + 2].getPiece().enpassantCaptures.add(selectedBlock.getPiece());
                                }
                            }
                        }
                    
                        if (selectedBlock.x - 1 >= 0)
                        {
                            if (board[selectedBlock.x - 1][selectedBlock.y + 2].hasPiece())
                            {
                                if (board[selectedBlock.x - 1][selectedBlock.y + 2].getPiece().pieceName.equals("pawn") &&
                                    board[selectedBlock.x - 1][selectedBlock.y + 2].getPiece().color == Color.BLACK)
                                {
                                    board[selectedBlock.x - 1][selectedBlock.y + 2].getPiece().enpassantCaptures.add(selectedBlock.getPiece());
                                }
                            }
                        }
                    }
                }
                
                if (chessBlock.y == 7)
                {
                    ChessBoard.whitePawnPromotions.add(selectedBlock.getPiece());
                }
            }
            else
            {
                if (selectedBlock.y - 2 >= 0)
                {
                    if (chessBlock == board[selectedBlock.x][selectedBlock.y - 2])
                    {
                        if (selectedBlock.x + 1 < 8)
                        {
                            if (board[selectedBlock.x + 1][selectedBlock.y - 2].hasPiece())
                            {
                                if (board[selectedBlock.x + 1][selectedBlock.y - 2].getPiece().pieceName.equals("pawn") &&
                                    board[selectedBlock.x + 1][selectedBlock.y - 2].getPiece().color == Color.WHITE)
                                {
                                    board[selectedBlock.x + 1][selectedBlock.y - 2].getPiece().enpassantCaptures.add(selectedBlock.getPiece());
                                }
                            }
                        }
                        
                        if (selectedBlock.x - 1 >= 0)
                        {
                            if (board[selectedBlock.x - 1][selectedBlock.y - 2].hasPiece())
                            {
                                if (board[selectedBlock.x - 1][selectedBlock.y - 2].getPiece().pieceName.equals("pawn") &&
                                    board[selectedBlock.x - 1][selectedBlock.y - 2].getPiece().color == Color.WHITE)
                                {
                                    board[selectedBlock.x - 1][selectedBlock.y - 2].getPiece().enpassantCaptures.add(selectedBlock.getPiece());
                                }
                            }
                        }
                    }
                }
                
                if (chessBlock.y == 0)
                {
                    ChessBoard.blackPawnPromotions.add(selectedBlock.getPiece());
                }
            }
        }
        
        if (chessBlock.castleMove)
        {
            if (selectedBlock.getPiece().currentPosition == board[4][0])
            {
                if (chessBlock == board[6][0])
                {
                    board[7][0].getPiece().move(board[5][0]);
                }
                
                if (chessBlock == board[2][0])
                {
                    board[0][0].getPiece().move(board[3][0]);
                }
            }
            else if (selectedBlock.getPiece().currentPosition == board[4][7])
            {
                if (chessBlock == board[6][7])
                {
                    board[7][7].getPiece().move(board[5][7]);
                }
                
                if (chessBlock == board[2][7])
                {
                    board[0][7].getPiece().move(board[3][7]);
                }
            }
            
            chessBlock.castleMove = false;
        }
        selectedBlock.getPiece().move(chessBlock);
        selectedBlock.setSelectedPieceButtonColor(false);
        selectedBlock = null;
    }
}
