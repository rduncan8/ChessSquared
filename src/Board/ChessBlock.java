package Board;

import Pieces.Piece;
import java.awt.Color;
import javax.swing.*;

public class ChessBlock 
{
    private Piece piece = null;
    JButton button;
    private final Color DEFAULT_BUTTON_COLOR;
    private String blockDescription;
    public int x;
    public int y;
    public int value;
    public boolean castleMove = false;
    public boolean castleRook = false;
    
    public ChessBlock(String blockDescription, JButton button, int x, int y, Color color)
    {
        this.blockDescription = blockDescription;
        this.button = button;
        this.x = x;
        this.y = y;
        DEFAULT_BUTTON_COLOR = color;
        setDefaultButtonColor();
    }
    
    public String getBlockDescription()
    {
        return blockDescription;
    }
    
    public void setPiece(Piece piece)
    {
        this.piece = piece;
        udpateBlockImage();
        if (this.piece == null)
        {
            value = 0;
        }
        else
        {
            value = piece.value;
        }
    }
    
    private void udpateBlockImage()
    {
        if (hasPiece())
        {
            button.setIcon(piece.getPieceIcon());
        }
        else
        {
            button.setIcon(null);
        }
    }
    
    public void setDangerousButtonColor(boolean isActive)
    {
        if (isActive)
        {
            button.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 3));
            button.setBorderPainted(true);
        }
        else
        {
            setDefaultButtonColor();
        }
    }
    
    public void setWinnerButtonColor(boolean isActive, Color color)
    {
        if (isActive)
        {
            button.setBackground(color);
            button.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 5));
            button.setBorderPainted(true);
        }
    }
    
    public void setPossibleMoveButtonColor(boolean isActive)
    {
        if (isActive)
        {
            button.setBackground(DEFAULT_BUTTON_COLOR.darker());
            button.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
            button.setBorderPainted(true);
        }
        else
        {
            setDefaultButtonColor();
        }
    }
    
    public void setPossibleCaptureButtonColor(boolean isActive)
    {
        if (isActive)
        {
            button.setBackground(DEFAULT_BUTTON_COLOR.darker());
            button.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            button.setBorderPainted(true);
        }
        else
        {
            setDefaultButtonColor();
        }
    }
    
    public void setSelectedPieceButtonColor(boolean isActive)
    {
        if (isActive)
        {
            button.setBackground(DEFAULT_BUTTON_COLOR.darker());
            button.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 3));
            button.setBorderPainted(true);
        }
        else
        {
            setDefaultButtonColor();
        }
    }
    
    public void setDefaultButtonColor()
    {
        button.setBackground(DEFAULT_BUTTON_COLOR);
        button.setOpaque(true);
        button.setBorderPainted(false);
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
