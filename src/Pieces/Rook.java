package Pieces;


import Board.ChessBlock;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Rook extends Piece
{    
    public Rook(Color color, ChessBlock startingPosition)
    {
        super(color, startingPosition);
        pieceName = "rook";
        value = 525;
    }
    
    @Override
    public ImageIcon getPieceIcon() 
    {
        if (color == Color.WHITE)
        {
            return new ImageIcon("White_Rook.png");
        }
        else
        {
            return new ImageIcon("Black_Rook.png");
        }
    }
}
