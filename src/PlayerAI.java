
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;


public class PlayerAI extends Player
{
    Random random;
    ChessBlock[][] board = new ChessBlock[8][8];
    ArrayList<ChessBlock> dangerousBlocks = new ArrayList<>();
    
    public PlayerAI(Color color)
    {
        super(color);
        random = new Random();
    }
    
    public ChessBlock[][] makeMove(ChessBlock[][] board, ArrayList<ChessBlock> dangerousBlocksForWhite, ArrayList<ChessBlock> dangerousBlocksForBlack)
    {
        this.board = board;
        ArrayList<ChessBlock> possibleMoves;
        ArrayList<Piece> pieces;
        ChessBlock selectedBlock;
        ChessBlock currentBlock = null;
        ChessBlock currentMove = null;
        int moveCost = Integer.MAX_VALUE;
        
        if (color == Color.BLACK)
        {
            pieces = ChessBoard.blackPieces;
            dangerousBlocks = dangerousBlocksForBlack;
        }
        else
        {
            pieces = ChessBoard.whitePieces;
            dangerousBlocks = dangerousBlocksForWhite;
        }
        
        int x = random.nextInt(pieces.size() - 1);
        int i = x + 1;
        
        while (i != x)
        {
            selectedBlock = pieces.get(i).currentPosition;
            possibleMoves = getPossibleMoves(selectedBlock, dangerousBlocksForWhite, dangerousBlocksForBlack);
            
            for (ChessBlock possibleMove : possibleMoves)
            {
                if (getMoveCost(selectedBlock, possibleMove) < moveCost)
                {
                    moveCost = getMoveCost(selectedBlock, possibleMove);
                    currentBlock = selectedBlock;
                    currentMove = possibleMove;
                }
            }
            
            if (i == pieces.size() - 1)
            {
                i = 0;
            }
            else
            {
                i++;
            }
        }
        
        selectedBlock = pieces.get(i).currentPosition;
        possibleMoves = getPossibleMoves(selectedBlock, dangerousBlocksForWhite, dangerousBlocksForBlack);
        
        for (ChessBlock possibleMove : possibleMoves)
        {
            if (getMoveCost(selectedBlock, possibleMove) < moveCost)
            {
                moveCost = getMoveCost(selectedBlock, possibleMove);
                currentBlock = selectedBlock;
                currentMove = possibleMove;
            }
        }
        
        if (currentMove.hasPiece())
        {
            capturePiece(currentBlock, currentMove);
        }
        else
        {
            moveToBlock(currentBlock, currentMove);
        }
        
        return board;
    }
    
    private ArrayList<ChessBlock> getPossibleMoves(ChessBlock selectedBlock, ArrayList<ChessBlock> dangerousBlocksForWhite, ArrayList<ChessBlock> dangerousBlocksForBlack)
    {
        ArrayList<ArrayList<ChessBlock>> movesAndCaptures = Logic.getPossibleMovesAndCaptures(board, selectedBlock, dangerousBlocksForWhite, dangerousBlocksForBlack);
        ArrayList<ChessBlock> possibleMoves = new ArrayList<>();
        
        for (int i = 0; i < movesAndCaptures.get(0).size(); i++)
        {
            possibleMoves.add(movesAndCaptures.get(0).get(i));
        }
        
        for (int i = 0; i < movesAndCaptures.get(1).size(); i++)
        {
            possibleMoves.add(movesAndCaptures.get(1).get(i));
        }
        
        return possibleMoves;
    }
    
    private int getMoveCost(ChessBlock selectedBlock, ChessBlock possibleMove)
    {
        int moveCost = 0;
        
        if (possibleMove.hasPiece() && possibleMove.getPiece().color != color)
        {
            moveCost -= possibleMove.value;
        }
        
        if (dangerousBlocks.contains(possibleMove))
        {
            moveCost += selectedBlock.value;
        }
                
        return moveCost;
    }
    
    private void capturePiece(ChessBlock selectedBlock, ChessBlock chessBlock)
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
        selectedBlock = null;
    }
    
    private void moveToBlock(ChessBlock selectedBlock, ChessBlock chessBlock)
    {
        selectedBlock.getPiece().move(chessBlock);
        selectedBlock = null;
    }
}
