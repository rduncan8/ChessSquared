package Pieces;

// artwork for pieces made using piskelapp.com

import Board.ChessBlock;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Piece
{
    public final Color color;
    public ChessBlock currentPosition;
    public final ChessBlock startingPosition;
    public String pieceName;
    public boolean hasMoved = false;
    public int value = 0;
    public ArrayList<Piece> enpassantCaptures = new ArrayList<>();
    
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
    
    public ImageIcon getPieceIcon()
    {
        return new ImageIcon();
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