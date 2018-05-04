
import java.awt.Color;
import java.util.ArrayList;

public class Logic 
{
    public static ArrayList<ArrayList<ChessBlock>> getPossibleMovesAndCaptures(ChessBlock[][] board, ChessBlock block, ArrayList<ChessBlock> dangerousBlocksForWhite, ArrayList<ChessBlock> dangerousBlocksForBlack)
    {
        ArrayList<ArrayList<ChessBlock>> movesAndCaptures = new ArrayList<>();
        
        if (!ChessBoard.isKingInCheck())
        {
            if(!willKingBeInCheckIfPieceIsMoved(board, block))
            {
                if ("pawn".equals(block.getPiece().pieceName)) 
                {
                    movesAndCaptures = getPossiblePawnMovesAndCaptures(board, block, false);
                }
                else if ("rook".equals(block.getPiece().pieceName))
                {
                    movesAndCaptures = getPossibleRookMovesAndCaptures(board, block, false);
                }
                else if ("knight".equals(block.getPiece().pieceName))
                {
                    movesAndCaptures = getPossibleKnightMovesAndCaptures(board, block, false);
                }
                else if ("bishop".equals(block.getPiece().pieceName))
                {
                    movesAndCaptures = getPossibleBishopMovesAndCaptures(board, block, false);
                }
                else if ("queen".equals(block.getPiece().pieceName))
                {
                    movesAndCaptures = getPossibleQueenMovesAndCaptures(board, block, false);
                }
                else if ("king".equals(block.getPiece().pieceName))
                {
                    movesAndCaptures = getPossibleKingMovesAndCaptures(board, block, dangerousBlocksForWhite, dangerousBlocksForBlack, false);
                }
            }
        }
        else
        {
            if ("pawn".equals(block.getPiece().pieceName)) 
            {
                movesAndCaptures = getPossiblePawnMovesAndCaptures(board, block, true);
            }
            else if ("rook".equals(block.getPiece().pieceName))
            {
                movesAndCaptures = getPossibleRookMovesAndCaptures(board, block, true);
            }
            else if ("knight".equals(block.getPiece().pieceName))
            {
                movesAndCaptures = getPossibleKnightMovesAndCaptures(board, block, true);
            }
            else if ("bishop".equals(block.getPiece().pieceName))
            {
                movesAndCaptures = getPossibleBishopMovesAndCaptures(board, block, true);
            }
            else if ("queen".equals(block.getPiece().pieceName))
            {
                movesAndCaptures = getPossibleQueenMovesAndCaptures(board, block, true);
            }
            else if ("king".equals(block.getPiece().pieceName))
            {
                movesAndCaptures = getPossibleKingMovesAndCaptures(board, block, dangerousBlocksForWhite, dangerousBlocksForBlack, true);
            }
        }
        
        return movesAndCaptures;
    }
    
    private static boolean willKingBeInCheckIfPieceIsMoved(ChessBlock[][] board, ChessBlock block)
    {
        Piece tempPiece = block.getPiece();
        boolean willKingBeInCheck = false;
        block.setPiece(null);
        if (tempPiece.color == Color.WHITE)
        {
            willKingBeInCheck = getDangerousBlocksForWhite(board).contains(ChessBoard.whiteKing.currentPosition);
        }
        else
        {
            willKingBeInCheck = getDangerousBlocksForBlack(board).contains(ChessBoard.blackKing.currentPosition);
        }
        block.setPiece(tempPiece);
        return willKingBeInCheck;
    }
    
    private static boolean willKingBeInCheckAfterMove(ChessBlock[][] board, ChessBlock block, ChessBlock moveToBlock)
    {
        boolean willKingBeInCheck = false;
        Piece tempPiece = null;
                
        if (moveToBlock.hasPiece())
        {
            if (moveToBlock.getPiece().color == Color.WHITE)
            {
                ChessBoard.whitePieces.remove(moveToBlock.getPiece());
            }
            else
            {
                ChessBoard.blackPieces.remove(moveToBlock.getPiece());
            }
            tempPiece = moveToBlock.getPiece();
        }
        
        moveToBlock.setPiece(block.getPiece());
        block.getPiece().currentPosition = moveToBlock;
        block.setPiece(null);
        
        if (moveToBlock.getPiece().color == Color.WHITE)
        {
            willKingBeInCheck = getDangerousBlocksForWhite(board).contains(ChessBoard.whiteKing.currentPosition);
        }
        else
        {            
            willKingBeInCheck = getDangerousBlocksForBlack(board).contains(ChessBoard.blackKing.currentPosition);
        }
                
        block.setPiece(moveToBlock.getPiece());
        block.getPiece().currentPosition = block;
        moveToBlock.setPiece(tempPiece);
        
        if (moveToBlock.hasPiece())
        {
            if (moveToBlock.getPiece().color == Color.WHITE)
            {
                ChessBoard.whitePieces.add(moveToBlock.getPiece());
            }
            else
            {
                ChessBoard.blackPieces.add(moveToBlock.getPiece());
            }
        }
                
        return willKingBeInCheck;
    }
    
    private static ArrayList<ArrayList<ChessBlock>> getPossiblePawnMovesAndCaptures(ChessBlock[][] board, ChessBlock block, boolean isKingInCheck)
    {
        ArrayList<ChessBlock> possibleMoves = new ArrayList<>();
        ArrayList<ChessBlock> possibleCaptures = new ArrayList<>();
        ArrayList<ArrayList<ChessBlock>> movesAndCaptures = new ArrayList<>();
        
        if (block.getPiece().color == Color.WHITE)
        {
            if (block.y + 1 < 8)
            {
                if (!board[block.x][block.y + 1].hasPiece())
                {
                    if (isKingInCheck)
                    {
                        if (!willKingBeInCheckAfterMove(board, block, board[block.x][block.y + 1]))
                        {
                            possibleMoves.add(board[block.x][block.y + 1]);
                        }
                    }
                    else
                    {
                        possibleMoves.add(board[block.x][block.y + 1]);
                    }
                    
                    if (!block.getPiece().hasMoved)
                    {
                        if (!board[block.x][block.y + 2].hasPiece())
                        {
                            if (isKingInCheck)
                            {
                                if (!willKingBeInCheckAfterMove(board, block, board[block.x][block.y + 2]))
                                {
                                    possibleMoves.add(board[block.x][block.y + 2]);
                                }
                            }
                            else
                            {
                                possibleMoves.add(board[block.x][block.y + 2]);
                            }
                        }
                    }
                }
                
                if (block.x + 1 < 8)
                {
                    if (board[block.x + 1][block.y + 1].hasPiece())
                    {
                        if (board[block.x + 1][block.y + 1].getPiece().color != block.getPiece().color)
                        {
                            if (isKingInCheck)
                            {
                                if (!willKingBeInCheckAfterMove(board, block, board[block.x + 1][block.y + 1]))
                                {
                                    possibleCaptures.add(board[block.x + 1][block.y + 1]);
                                }
                            }
                            else
                            {
                                possibleCaptures.add(board[block.x + 1][block.y + 1]);
                            }
                        }
                    }
                }
                
                if (block.x - 1 >= 0)
                {
                    if (board[block.x - 1][block.y + 1].hasPiece())
                    {
                        if (board[block.x - 1][block.y + 1].getPiece().color != block.getPiece().color)
                        {
                            if (isKingInCheck)
                            {
                                if (!willKingBeInCheckAfterMove(board, block, board[block.x - 1][block.y + 1]))
                                {
                                    possibleCaptures.add(board[block.x - 1][block.y + 1]);
                                }
                            }
                            else
                            {
                                possibleCaptures.add(board[block.x - 1][block.y + 1]);
                            }
                        }
                    }
                }
            }
        }
        else
        {
            if (block.y - 1 >= 0)
            {
                if (!board[block.x][block.y - 1].hasPiece())
                {
                    if (isKingInCheck)
                    {
                        if (!willKingBeInCheckAfterMove(board, block, board[block.x][block.y - 1]))
                        {
                            possibleMoves.add(board[block.x][block.y - 1]);
                        }
                    }
                    else
                    {
                        possibleMoves.add(board[block.x][block.y - 1]);
                    }
                    
                    if (!block.getPiece().hasMoved)
                    {
                        if (!board[block.x][block.y - 2].hasPiece())
                        {
                            if (isKingInCheck)
                            {
                                if (!willKingBeInCheckAfterMove(board, block, board[block.x][block.y - 2]))
                                {
                                    possibleMoves.add(board[block.x][block.y - 2]);
                                }
                            }
                            else
                            {
                                possibleMoves.add(board[block.x][block.y - 2]);
                            }
                        }
                    }
                }
                
                if (block.x + 1 < 8)
                {
                    if (board[block.x + 1][block.y - 1].hasPiece())
                    {
                        if (board[block.x + 1][block.y - 1].getPiece().color != block.getPiece().color)
                        {
                            if (isKingInCheck)
                            {
                                if (!willKingBeInCheckAfterMove(board, block, board[block.x + 1][block.y - 1]))
                                {
                                    possibleCaptures.add(board[block.x + 1][block.y - 1]);
                                }
                            }
                            else
                            {
                                possibleCaptures.add(board[block.x + 1][block.y - 1]);
                            }
                        }
                    }
                }
                
                if (block.x - 1 >= 0)
                {
                    if (board[block.x - 1][block.y - 1].hasPiece())
                    {
                        if (board[block.x - 1][block.y - 1].getPiece().color != block.getPiece().color)
                        {
                            if (isKingInCheck)
                            {
                                if (!willKingBeInCheckAfterMove(board, block, board[block.x - 1][block.y - 1]))
                                {
                                    possibleCaptures.add(board[block.x - 1][block.y - 1]);
                                }
                            }
                            else
                            {
                                possibleCaptures.add(board[block.x - 1][block.y - 1]);
                            }
                        }
                    }
                }
            }
        }
        
        movesAndCaptures.add(possibleMoves);
        movesAndCaptures.add(possibleCaptures);
        return movesAndCaptures;
    }
    
    private static ArrayList<ArrayList<ChessBlock>> getPossibleRookMovesAndCaptures(ChessBlock[][] board, ChessBlock block, boolean isKingInCheck)
    {  
        ArrayList<ChessBlock> possibleMoves = new ArrayList<>();
        ArrayList<ChessBlock> possibleCaptures = new ArrayList<>();
        ArrayList<ArrayList<ChessBlock>> movesAndCaptures = new ArrayList<>();
        
        for (int y = block.y + 1; y < 8; y++)
        {
            if (board[block.x][y].hasPiece())
            {
                if (board[block.x][y].getPiece().color != block.getPiece().color)
                {
                    if (isKingInCheck)
                    {
                        if (!willKingBeInCheckAfterMove(board, block, board[block.x][y]))
                        {
                            possibleCaptures.add(board[block.x][y]);
                        }
                    }
                    else
                    {
                        possibleCaptures.add(board[block.x][y]);
                    }
                }
                break;
            }
            else
            {
                if (isKingInCheck)
                {
                    if (!willKingBeInCheckAfterMove(board, block, board[block.x][y]))
                    {
                        possibleMoves.add(board[block.x][y]);
                    }
                }
                else
                {
                    possibleMoves.add(board[block.x][y]);
                }
            }
        }
        
        for (int y = block.y - 1; y >= 0; y--)
        {
            if (board[block.x][y].hasPiece())
            {
                if (board[block.x][y].getPiece().color != block.getPiece().color)
                {
                    if (isKingInCheck)
                    {
                        if (!willKingBeInCheckAfterMove(board, block, board[block.x][y]))
                        {
                            possibleCaptures.add(board[block.x][y]);
                        }
                    }
                    else
                    {
                        possibleCaptures.add(board[block.x][y]);
                    }
                }
                break;
            }
            else
            {
                if (isKingInCheck)
                {
                    if (!willKingBeInCheckAfterMove(board, block, board[block.x][y]))
                    {
                        possibleMoves.add(board[block.x][y]);
                    }
                }
                else
                {
                    possibleMoves.add(board[block.x][y]);
                }
            }
        }
        
        for (int x = block.x + 1; x < 8; x++)
        {
            if (board[x][block.y].hasPiece())
            {
                if (board[x][block.y].getPiece().color != block.getPiece().color)
                {
                    if (isKingInCheck)
                    {
                        if (!willKingBeInCheckAfterMove(board, block, board[x][block.y]))
                        {
                            possibleCaptures.add(board[x][block.y]);
                        }
                    }
                    else
                    {
                        possibleCaptures.add(board[x][block.y]);
                    }
                }
                break;
            }
            else
            {
                if (isKingInCheck)
                {
                    if (!willKingBeInCheckAfterMove(board, block, board[x][block.y]))
                    {
                        possibleMoves.add(board[x][block.y]);
                    }
                }
                else
                {
                    possibleMoves.add(board[x][block.y]);
                }
            }
        }
        
        for (int x = block.x - 1; x >= 0; x--)
        {
            if (board[x][block.y].hasPiece())
            {
                if (board[x][block.y].getPiece().color != block.getPiece().color)
                {
                    if (isKingInCheck)
                    {
                        if (!willKingBeInCheckAfterMove(board, block, board[x][block.y]))
                        {
                            possibleCaptures.add(board[x][block.y]);
                        }
                    }
                    else
                    {
                        possibleCaptures.add(board[x][block.y]);
                    }
                }
                break;
            }
            else
            {
                if (isKingInCheck)
                {
                    if (!willKingBeInCheckAfterMove(board, block, board[x][block.y]))
                    {
                        possibleMoves.add(board[x][block.y]);
                    }
                }
                else
                {
                    possibleMoves.add(board[x][block.y]);
                }
            }
        }
        
        movesAndCaptures.add(possibleMoves);
        movesAndCaptures.add(possibleCaptures);
        return movesAndCaptures;
    }
    
    private static ArrayList<ArrayList<ChessBlock>> getPossibleKnightMovesAndCaptures(ChessBlock[][] board, ChessBlock block, boolean isKingInCheck)
    {
        ArrayList<ChessBlock> possibleMoves = new ArrayList<>();
        ArrayList<ChessBlock> possibleCaptures = new ArrayList<>();
        ArrayList<ArrayList<ChessBlock>> movesAndCaptures = new ArrayList<>();
        
        int[] x = { block.x - 2, block.x - 2, block.x + 2, block.x + 2,
                    block.x - 1, block.x + 1, block.x - 1, block.x + 1 };
        int[] y = { block.y - 1, block.y + 1, block.y - 1, block.y + 1,
                    block.y - 2, block.y - 2, block.y + 2, block.y + 2 };

        for (int i = 0; i < 8; i++)
        {
            if (x[i] >= 0 && x[i] < 8 && y[i] >= 0 && y[i] < 8)
            {
                if (!board[x[i]][y[i]].hasPiece())
                {
                    if (isKingInCheck)
                    {
                        if (!willKingBeInCheckAfterMove(board, block, board[x[i]][y[i]]))
                        {
                            possibleMoves.add(board[x[i]][y[i]]);
                        }
                    }
                    else
                    {
                        possibleMoves.add(board[x[i]][y[i]]);
                    }
                }
                else if (board[x[i]][y[i]].getPiece().color != block.getPiece().color)
                {
                    if (isKingInCheck)
                    {
                        if (!willKingBeInCheckAfterMove(board, block, board[x[i]][y[i]]))
                        {
                            possibleCaptures.add(board[x[i]][y[i]]);
                        }
                    }
                    else
                    {
                        possibleCaptures.add(board[x[i]][y[i]]);
                    }
                }
            }
        }
        
        movesAndCaptures.add(possibleMoves);
        movesAndCaptures.add(possibleCaptures);
        return movesAndCaptures;
    }
    
    private static ArrayList<ArrayList<ChessBlock>> getPossibleBishopMovesAndCaptures(ChessBlock[][] board, ChessBlock block, boolean isKingInCheck)
    {
        ArrayList<ChessBlock> possibleMoves = new ArrayList<>();
        ArrayList<ChessBlock> possibleCaptures = new ArrayList<>();
        ArrayList<ArrayList<ChessBlock>> movesAndCaptures = new ArrayList<>();
     
        for (int i = 1; i < 8; i++)
        {
            if (block.x + i > 7 || block.y + i > 7)
            {
                break;
            }
            else
            {
                if (board[block.x + i][block.y + i].hasPiece())
                {
                    if (board[block.x + i][block.y + i].getPiece().color != block.getPiece().color)
                    {
                        if (isKingInCheck)
                        {
                            if (!willKingBeInCheckAfterMove(board, block, board[block.x + i][block.y + i]))
                            {
                                possibleCaptures.add(board[block.x + i][block.y + i]);
                            }
                        }
                        else
                        {
                            possibleCaptures.add(board[block.x + i][block.y + i]);
                        }
                    }
                    break;
                }
                else
                {
                    if (isKingInCheck)
                    {
                        if (!willKingBeInCheckAfterMove(board, block, board[block.x + i][block.y + i]))
                        {
                            possibleMoves.add(board[block.x + i][block.y + i]);
                        }
                    }
                    else
                    {
                        possibleMoves.add(board[block.x + i][block.y + i]);
                    }
                }
            }
        }
        
        for (int i = 1; i < 8; i++)
        {
            if (block.x - i < 0 || block.y + i > 7)
            {
                break;
            }
            else
            {
                if (board[block.x - i][block.y + i].hasPiece())
                {
                    if (board[block.x - i][block.y + i].getPiece().color != block.getPiece().color)
                    {
                        if (isKingInCheck)
                        {
                            if (!willKingBeInCheckAfterMove(board, block, board[block.x - i][block.y + i]))
                            {
                                possibleCaptures.add(board[block.x - i][block.y + i]);
                            }
                        }
                        else
                        {
                            possibleCaptures.add(board[block.x - i][block.y + i]);
                        }
                    }
                    break;
                }
                else
                {
                    if (isKingInCheck)
                    {
                        if (!willKingBeInCheckAfterMove(board, block, board[block.x - i][block.y + i]))
                        {
                            possibleMoves.add(board[block.x - i][block.y + i]);
                        }
                    }
                    else
                    {
                        possibleMoves.add(board[block.x - i][block.y + i]);
                    }
                }
            }
        }
        
        for (int i = 1; i < 8; i++)
        {
            if (block.x - i < 0 || block.y - i < 0)
            {
                break;
            }
            else
            {
                if (board[block.x - i][block.y - i].hasPiece())
                {
                    if (board[block.x - i][block.y - i].getPiece().color != block.getPiece().color)
                    {
                        if (isKingInCheck)
                        {
                            if (!willKingBeInCheckAfterMove(board, block, board[block.x - i][block.y - i]))
                            {
                                possibleCaptures.add(board[block.x - i][block.y - i]);
                            }
                        }
                        else
                        {
                            possibleCaptures.add(board[block.x - i][block.y - i]);
                        }
                    }
                    break;
                }
                else
                {
                    if (isKingInCheck)
                    {
                        if (!willKingBeInCheckAfterMove(board, block, board[block.x - i][block.y - i]))
                        {
                            possibleMoves.add(board[block.x - i][block.y - i]);
                        }
                    }
                    else
                    {
                        possibleMoves.add(board[block.x - i][block.y - i]);
                    }
                }
            }
        }
        
        for (int i = 1; i < 8; i++)
        {
            if (block.x + i > 7 || block.y - i < 0)
            {
                break;
            }
            else
            {
                if (board[block.x + i][block.y - i].hasPiece())
                {
                    if (board[block.x + i][block.y - i].getPiece().color != block.getPiece().color)
                    {
                        if (isKingInCheck)
                        {
                            if (!willKingBeInCheckAfterMove(board, block, board[block.x + i][block.y - i]))
                            {
                                possibleCaptures.add(board[block.x + i][block.y - i]);
                            }
                        }
                        else
                        {
                            possibleCaptures.add(board[block.x + i][block.y - i]);
                        }
                    }
                    break;
                }
                else
                {
                    if (isKingInCheck)
                    {
                        if (!willKingBeInCheckAfterMove(board, block, board[block.x + i][block.y - i]))
                        {
                            possibleMoves.add(board[block.x + i][block.y - i]);
                        }
                    }
                    else
                    {
                        possibleMoves.add(board[block.x + i][block.y - i]);
                    }
                }
            }
        }
        
        movesAndCaptures.add(possibleMoves);
        movesAndCaptures.add(possibleCaptures);
        return movesAndCaptures;
    }
    
    private static ArrayList<ArrayList<ChessBlock>> getPossibleQueenMovesAndCaptures(ChessBlock[][] board, ChessBlock block, boolean isKingInCheck)
    {
        ArrayList<ChessBlock> possibleMoves = new ArrayList<>();
        ArrayList<ChessBlock> possibleCaptures = new ArrayList<>();
        ArrayList<ArrayList<ChessBlock>> movesAndCaptures = new ArrayList<>();
        
        for (int y = block.y + 1; y < 8; y++)
        {
            if (board[block.x][y].hasPiece())
            {
                if (board[block.x][y].getPiece().color != block.getPiece().color)
                {
                    if (isKingInCheck)
                    {
                        if (!willKingBeInCheckAfterMove(board, block, board[block.x][y]))
                        {
                            possibleCaptures.add(board[block.x][y]);
                        }
                    }
                    else
                    {
                        possibleCaptures.add(board[block.x][y]);
                    }
                }
                break;
            }
            else
            {
                if (isKingInCheck)
                {
                    if (!willKingBeInCheckAfterMove(board, block, board[block.x][y]))
                    {
                        possibleMoves.add(board[block.x][y]);
                    }
                }
                else
                {
                    possibleMoves.add(board[block.x][y]);
                }
            }
        }
        
        for (int y = block.y - 1; y >= 0; y--)
        {
            if (board[block.x][y].hasPiece())
            {
                if (board[block.x][y].getPiece().color != block.getPiece().color)
                {
                    if (isKingInCheck)
                    {
                        if (!willKingBeInCheckAfterMove(board, block, board[block.x][y]))
                        {
                            possibleCaptures.add(board[block.x][y]);
                        }
                    }
                    else
                    {
                        possibleCaptures.add(board[block.x][y]);
                    }
                }
                break;
            }
            else
            {
                if (isKingInCheck)
                {
                    if (!willKingBeInCheckAfterMove(board, block, board[block.x][y]))
                    {
                        possibleMoves.add(board[block.x][y]);
                    }
                }
                else
                {
                    possibleMoves.add(board[block.x][y]);
                }
            }
        }
        
        for (int x = block.x + 1; x < 8; x++)
        {
            if (board[x][block.y].hasPiece())
            {
                if (board[x][block.y].getPiece().color != block.getPiece().color)
                {
                    if (isKingInCheck)
                    {
                        if (!willKingBeInCheckAfterMove(board, block, board[x][block.y]))
                        {
                            possibleCaptures.add(board[x][block.y]);
                        }
                    }
                    else
                    {
                        possibleCaptures.add(board[x][block.y]);
                    }
                }
                break;
            }
            else
            {
                if (isKingInCheck)
                {
                    if (!willKingBeInCheckAfterMove(board, block, board[x][block.y]))
                    {
                        possibleMoves.add(board[x][block.y]);
                    }
                }
                else
                {
                    possibleMoves.add(board[x][block.y]);
                }
            }
        }
        
        for (int x = block.x - 1; x >= 0; x--)
        {
            if (board[x][block.y].hasPiece())
            {
                if (board[x][block.y].getPiece().color != block.getPiece().color)
                {
                    if (isKingInCheck)
                    {
                        if (!willKingBeInCheckAfterMove(board, block, board[x][block.y]))
                        {
                            possibleCaptures.add(board[x][block.y]);
                        }
                    }
                    else
                    {
                        possibleCaptures.add(board[x][block.y]);
                    }
                }
                break;
            }
            else
            {
                if (isKingInCheck)
                {
                    if (!willKingBeInCheckAfterMove(board, block, board[x][block.y]))
                    {
                        possibleMoves.add(board[x][block.y]);
                    }
                }
                else
                {
                    possibleMoves.add(board[x][block.y]);
                }
            }
        }
        
        for (int i = 1; i < 8; i++)
        {
            if (block.x + i > 7 || block.y + i > 7)
            {
                break;
            }
            else
            {
                if (board[block.x + i][block.y + i].hasPiece())
                {
                    if (board[block.x + i][block.y + i].getPiece().color != block.getPiece().color)
                    {
                        if (isKingInCheck)
                        {
                            if (!willKingBeInCheckAfterMove(board, block, board[block.x + i][block.y + i]))
                            {
                                possibleCaptures.add(board[block.x + i][block.y + i]);
                            }
                        }
                        else
                        {
                            possibleCaptures.add(board[block.x + i][block.y + i]);
                        }
                    }
                    break;
                }
                else
                {
                    if (isKingInCheck)
                    {
                        if (!willKingBeInCheckAfterMove(board, block, board[block.x + i][block.y + i]))
                        {
                            possibleMoves.add(board[block.x + i][block.y + i]);
                        }
                    }
                    else
                    {
                        possibleMoves.add(board[block.x + i][block.y + i]);
                    }
                }
            }
        }
        
        for (int i = 1; i < 8; i++)
        {
            if (block.x - i < 0 || block.y + i > 7)
            {
                break;
            }
            else
            {
                if (board[block.x - i][block.y + i].hasPiece())
                {
                    if (board[block.x - i][block.y + i].getPiece().color != block.getPiece().color)
                    {
                        if (isKingInCheck)
                        {
                            if (!willKingBeInCheckAfterMove(board, block, board[block.x - i][block.y + i]))
                            {
                                possibleCaptures.add(board[block.x - i][block.y + i]);
                            }
                        }
                        else
                        {
                            possibleCaptures.add(board[block.x - i][block.y + i]);
                        }
                    }
                    break;
                }
                else
                {
                    if (isKingInCheck)
                    {
                        if (!willKingBeInCheckAfterMove(board, block, board[block.x - i][block.y + i]))
                        {
                            possibleMoves.add(board[block.x - i][block.y + i]);
                        }
                    }
                    else
                    {
                        possibleMoves.add(board[block.x - i][block.y + i]);
                    }
                }
            }
        }
        
        for (int i = 1; i < 8; i++)
        {
            if (block.x - i < 0 || block.y - i < 0)
            {
                break;
            }
            else
            {
                if (board[block.x - i][block.y - i].hasPiece())
                {
                    if (board[block.x - i][block.y - i].getPiece().color != block.getPiece().color)
                    {
                        if (isKingInCheck)
                        {
                            if (!willKingBeInCheckAfterMove(board, block, board[block.x - i][block.y - i]))
                            {
                                possibleCaptures.add(board[block.x - i][block.y - i]);
                            }
                        }
                        else
                        {
                            possibleCaptures.add(board[block.x - i][block.y - i]);
                        }
                    }
                    break;
                }
                else
                {
                    if (isKingInCheck)
                    {
                        if (!willKingBeInCheckAfterMove(board, block, board[block.x - i][block.y - i]))
                        {
                            possibleMoves.add(board[block.x - i][block.y - i]);
                        }
                    }
                    else
                    {
                        possibleMoves.add(board[block.x - i][block.y - i]);
                    }
                }
            }
        }
        
        for (int i = 1; i < 8; i++)
        {
            if (block.x + i > 7 || block.y - i < 0)
            {
                break;
            }
            else
            {
                if (board[block.x + i][block.y - i].hasPiece())
                {
                    if (board[block.x + i][block.y - i].getPiece().color != block.getPiece().color)
                    {
                        if (isKingInCheck)
                        {
                            if (!willKingBeInCheckAfterMove(board, block, board[block.x + i][block.y - i]))
                            {
                                possibleCaptures.add(board[block.x + i][block.y - i]);
                            }
                        }
                        else
                        {
                            possibleCaptures.add(board[block.x + i][block.y - i]);
                        }
                    }
                    break;
                }
                else
                {
                    if (isKingInCheck)
                    {
                        if (!willKingBeInCheckAfterMove(board, block, board[block.x + i][block.y - i]))
                        {
                            possibleMoves.add(board[block.x + i][block.y - i]);
                        }
                    }
                    else
                    {
                        possibleMoves.add(board[block.x + i][block.y - i]);
                    }
                }
            }
        }
        
        movesAndCaptures.add(possibleMoves);
        movesAndCaptures.add(possibleCaptures);
        return movesAndCaptures;
    }
    
    private static ArrayList<ArrayList<ChessBlock>> getPossibleKingMovesAndCaptures(ChessBlock[][] board, ChessBlock block, ArrayList<ChessBlock> dangerousBlocksForWhite, ArrayList<ChessBlock> dangerousBlocksForBlack, boolean isKingInCheck)
    {
        ArrayList<ChessBlock> possibleMoves = new ArrayList<>();
        ArrayList<ChessBlock> possibleCaptures = new ArrayList<>();
        ArrayList<ArrayList<ChessBlock>> movesAndCaptures = new ArrayList<>();
        
        int[][] xy = { {block.x, block.y + 1},
                       {block.x + 1, block.y + 1},
                       {block.x + 1, block.y},
                       {block.x + 1, block.y - 1},
                       {block.x, block.y - 1},
                       {block.x - 1, block.y - 1},
                       {block.x - 1, block.y},
                       {block.x - 1, block.y + 1} };
        
        for (int i = 0; i < 8; i++)
        {
            if (xy[i][0] >= 0 && xy[i][0] < 8 && xy[i][1] >= 0 && xy[i][1] < 8)
            {
                if (!board[xy[i][0]][xy[i][1]].hasPiece())
                {
                    if (block.getPiece().color == Color.WHITE)
                    {
                        if (!dangerousBlocksForWhite.contains(board[xy[i][0]][xy[i][1]]))
                        {
                            if (isKingInCheck)
                            {
                                if (!willKingBeInCheckAfterMove(board, block, board[xy[i][0]][xy[i][1]]))
                                {
                                    possibleMoves.add(board[xy[i][0]][xy[i][1]]);
                                }
                            }
                            else
                            {
                                possibleMoves.add(board[xy[i][0]][xy[i][1]]);
                            }
                        }
                    }
                    else
                    {
                        if (!dangerousBlocksForBlack.contains(board[xy[i][0]][xy[i][1]]))
                        {
                            if (isKingInCheck)
                            {
                                if (!willKingBeInCheckAfterMove(board, block, board[xy[i][0]][xy[i][1]]))
                                {
                                    possibleMoves.add(board[xy[i][0]][xy[i][1]]);
                                }
                            }
                            else
                            {
                                possibleMoves.add(board[xy[i][0]][xy[i][1]]);
                            }
                        }
                    }
                }
                else if (board[xy[i][0]][xy[i][1]].getPiece().color != block.getPiece().color)
                {
                    if (block.getPiece().color == Color.WHITE)
                    {
                        if (!dangerousBlocksForWhite.contains(board[xy[i][0]][xy[i][1]]))
                        {
                            if (isKingInCheck)
                            {
                                if (!willKingBeInCheckAfterMove(board, block, board[xy[i][0]][xy[i][1]]))
                                {
                                    possibleCaptures.add(board[xy[i][0]][xy[i][1]]);
                                }
                            }
                            else
                            {
                                possibleCaptures.add(board[xy[i][0]][xy[i][1]]);
                            }
                        }
                    }
                    else
                    {
                        if (!dangerousBlocksForBlack.contains(board[xy[i][0]][xy[i][1]]))
                        {
                            if (isKingInCheck)
                            {
                                if (!willKingBeInCheckAfterMove(board, block, board[xy[i][0]][xy[i][1]]))
                                {
                                    possibleCaptures.add(board[xy[i][0]][xy[i][1]]);
                                }
                            }
                            else
                            {
                                possibleCaptures.add(board[xy[i][0]][xy[i][1]]);
                            }
                        }
                    }
                }
            }
        }
        
        movesAndCaptures.add(possibleMoves);
        movesAndCaptures.add(possibleCaptures);
        return movesAndCaptures;
    }
    
    public static ArrayList<ChessBlock> getDangerousBlocksForWhite(ChessBlock[][] board)
    {
        ArrayList<ChessBlock> dangerousBlocks = new ArrayList<>();
                
        for (Piece piece : ChessBoard.blackPieces)
        {
            if ("pawn".equals(piece.pieceName))
            {
                getDangerousBlocksFromPawn(board, piece.currentPosition).forEach(block -> dangerousBlocks.add(block));
            }
            else if ("rook".equals(piece.pieceName))
            {
                getDangerousBlocksFromRook(board, piece.currentPosition).forEach(block -> dangerousBlocks.add(block));
            }
            else if ("knight".equals(piece.pieceName))
            {
                getDangerousBlocksFromKnight(board, piece.currentPosition).forEach(block -> dangerousBlocks.add(block));
            }
            else if ("bishop".equals(piece.pieceName))
            {
                getDangerousBlocksFromBishop(board, piece.currentPosition).forEach(block -> dangerousBlocks.add(block));
            }
            else if ("queen".equals(piece.pieceName))
            {
                getDangerousBlocksFromQueen(board, piece.currentPosition).forEach(block -> dangerousBlocks.add(block));
            }
            else if ("king".equals(piece.pieceName))
            {
                getDangerousBlocksFromKing(board, piece.currentPosition).forEach(block -> dangerousBlocks.add(block));
            }
        }
        
        return dangerousBlocks;
    }
    
    public static ArrayList<ChessBlock> getDangerousBlocksForBlack(ChessBlock[][] board)
    {
        ArrayList<ChessBlock> dangerousBlocks = new ArrayList<>();
        
        for (Piece piece : ChessBoard.whitePieces)
        {
            if ("pawn".equals(piece.pieceName))
            {
                getDangerousBlocksFromPawn(board, piece.currentPosition).forEach(block -> dangerousBlocks.add(block));
            }
            else if ("rook".equals(piece.pieceName))
            {
                getDangerousBlocksFromRook(board, piece.currentPosition).forEach(block -> dangerousBlocks.add(block));
            }
            else if ("knight".equals(piece.pieceName))
            {
                getDangerousBlocksFromKnight(board, piece.currentPosition).forEach(block -> dangerousBlocks.add(block));
            }
            else if ("bishop".equals(piece.pieceName))
            {
                getDangerousBlocksFromBishop(board, piece.currentPosition).forEach(block -> dangerousBlocks.add(block));
            }
            else if ("queen".equals(piece.pieceName))
            {
                getDangerousBlocksFromQueen(board, piece.currentPosition).forEach(block -> dangerousBlocks.add(block));
            }
            else if ("king".equals(piece.pieceName))
            {
                getDangerousBlocksFromKing(board, piece.currentPosition).forEach(block -> dangerousBlocks.add(block));
            }
        }
        
        return dangerousBlocks;
    }
    
    private static ArrayList<ChessBlock> getDangerousBlocksFromPawn(ChessBlock[][] board, ChessBlock block)
    {
        ArrayList<ChessBlock> dangerousBlocks = new ArrayList<>();
        
        if (block.getPiece().color == Color.WHITE)
        {
            if (block.x + 1 < 8 && block.y + 1 < 8)
            {
                dangerousBlocks.add(board[block.x + 1][block.y + 1]);
            }
            
            if (block.x - 1 >= 0 && block.y + 1 < 8)
            {
                dangerousBlocks.add(board[block.x - 1][block.y + 1]);
            }
        }
        else
        {
            if (block.x + 1 < 8 && block.y - 1 >= 0)
            {
                dangerousBlocks.add(board[block.x + 1][block.y - 1]);
            }
            
            if (block.x - 1 >= 0 && block.y - 1 >= 0)
            {
                dangerousBlocks.add(board[block.x - 1][block.y - 1]);
            }
        }
        
        return dangerousBlocks;
    }
    
    private static ArrayList<ChessBlock> getDangerousBlocksFromRook(ChessBlock[][] board, ChessBlock block)
    {
        ArrayList<ChessBlock> dangerousBlocks = new ArrayList<>();
        
        for (int y = block.y + 1; y < 8; y++)
        {
            if (board[block.x][y].hasPiece())
            {
                dangerousBlocks.add(board[block.x][y]);
                break;
            }
            else
            {
                dangerousBlocks.add(board[block.x][y]);
            }
        }
        
        for (int y = block.y - 1; y >= 0; y--)
        {
            if (board[block.x][y].hasPiece())
            {
                dangerousBlocks.add(board[block.x][y]);
                break;
            }
            else
            {
                dangerousBlocks.add(board[block.x][y]);
            }
        }
        
        for (int x = block.x + 1; x < 8; x++)
        {
            if (board[x][block.y].hasPiece())
            {
                dangerousBlocks.add(board[x][block.y]);
                break;
            }
            else
            {
                dangerousBlocks.add(board[x][block.y]);
            }
        }
        
        for (int x = block.x - 1; x >= 0; x--)
        {
            if (board[x][block.y].hasPiece())
            {
                dangerousBlocks.add(board[x][block.y]);
                break;
            }
            else
            {
                dangerousBlocks.add(board[x][block.y]);
            }
        }
        
        return dangerousBlocks;
    }
    
    private static ArrayList<ChessBlock> getDangerousBlocksFromKnight(ChessBlock[][] board, ChessBlock block)
    {
        ArrayList<ChessBlock> dangerousBlocks = new ArrayList<>();
        
        int[] x = { block.x - 2, block.x - 2, block.x + 2, block.x + 2,
                    block.x - 1, block.x + 1, block.x - 1, block.x + 1 };
        int[] y = { block.y - 1, block.y + 1, block.y - 1, block.y + 1,
                    block.y - 2, block.y - 2, block.y + 2, block.y + 2 };

        for (int i = 0; i < 8; i++)
        {
            if (x[i] >= 0 && x[i] < 8 && y[i] >= 0 && y[i] < 8)
            {
                dangerousBlocks.add(board[x[i]][y[i]]);
            }
        }
        
        return dangerousBlocks;
    }
    
    private static ArrayList<ChessBlock> getDangerousBlocksFromBishop(ChessBlock[][] board, ChessBlock block)
    {
        ArrayList<ChessBlock> dangerousBlocks = new ArrayList<>();
        
        for (int i = 1; i < 8; i++)
        {
            if (block.x + i > 7 || block.y + i > 7)
            {
                break;
            }
            else
            {
                dangerousBlocks.add(board[block.x + i][block.y + i]);
                if (board[block.x + i][block.y + i].hasPiece())
                {
                    break;
                }
            }
        }
        
        for (int i = 1; i < 8; i++)
        {
            if (block.x - i < 0 || block.y + i > 7)
            {
                break;
            }
            else
            {
                dangerousBlocks.add(board[block.x - i][block.y + i]);
                if (board[block.x - i][block.y + i].hasPiece())
                {
                    break;
                }
            }
        }
        
        for (int i = 1; i < 8; i++)
        {
            if (block.x - i < 0 || block.y - i < 0)
            {
                break;
            }
            else
            {
                dangerousBlocks.add(board[block.x - i][block.y - i]);
                if (board[block.x - i][block.y - i].hasPiece())
                {
                    break;
                }
            }
        }
        
        for (int i = 1; i < 8; i++)
        {
            if (block.x + i > 7 || block.y - i < 0)
            {
                break;
            }
            else
            {
                dangerousBlocks.add(board[block.x + i][block.y - i]);
                if (board[block.x + i][block.y - i].hasPiece())
                {
                    break;
                }
            }
        }
        
        return dangerousBlocks;
    }
    
    private static ArrayList<ChessBlock> getDangerousBlocksFromQueen(ChessBlock[][] board, ChessBlock block)
    {
        ArrayList<ChessBlock> dangerousBlocks = new ArrayList<>();
        
        getDangerousBlocksFromRook(board, block).forEach(b -> dangerousBlocks.add(b));
        getDangerousBlocksFromBishop(board, block).forEach(b -> dangerousBlocks.add(b));
        
        return dangerousBlocks;
    }
    
    private static ArrayList<ChessBlock> getDangerousBlocksFromKing(ChessBlock[][] board, ChessBlock block)
    {
        ArrayList<ChessBlock> dangerousBlocks = new ArrayList<>();
        
        int[][] xy = { {block.x, block.y + 1},
                       {block.x + 1, block.y + 1},
                       {block.x + 1, block.y},
                       {block.x + 1, block.y - 1},
                       {block.x, block.y - 1},
                       {block.x - 1, block.y - 1},
                       {block.x - 1, block.y},
                       {block.x - 1, block.y + 1} };
        
        for (int i = 0; i < 8; i++)
        {
            if (xy[i][0] >= 0 && xy[i][0] < 8 && xy[i][1] >= 0 && xy[i][1] < 8)
            {
                dangerousBlocks.add(board[xy[i][0]][xy[i][1]]);
            }
        }
        
        return dangerousBlocks;
    }
}
