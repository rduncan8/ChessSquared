// borrowed and altered code from this website: https://stackoverflow.com/questions/21077322/create-a-chess-board-with-jpanel

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;

public class SingleChessBoard extends Screen
{
    private final JFrame frame = createFrame("Chess");
    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private final JButton[][] chessBoardSquares = new JButton[8][8];
    private JPanel chessBoard;
    private final JLabel message = new JLabel(
            "Chess Champ is ready to play!");
    private static final String COLS = "ABCDEFGH";

    SingleChessBoard()
    {
        createGUI();
        frame.add(gui);
        frame.setVisible(true);
    }

    public final void createGUI()
    {
        // set up the main GUI
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);
        tools.add(new JButton("New")); // TODO - add functionality!
        tools.add(new JButton("Save")); // TODO - add functionality!
        tools.add(new JButton("Restore")); // TODO - add functionality!
        tools.addSeparator();
        tools.add(new JButton("Resign")); // TODO - add functionality!
        tools.addSeparator();
        tools.add(message);

        chessBoard = new JPanel(new GridLayout(0, 9));
        chessBoard.setBorder(new LineBorder(Color.BLACK));
        gui.add(chessBoard);
        
        createBoard();
    }
    
    public void createBoard()
    {
        // create the chess board squares
        Insets buttonMargin = new Insets(0,0,0,0);
        for (int i = 0; i < chessBoardSquares.length; i++)
        {
            for (int j = 0; j < chessBoardSquares[i].length; j++)
            {
                JButton button = new JButton();
                button.setMargin(buttonMargin);             
                button.addActionListener(new MovePieceListener());
                // our chess pieces are 64x64 px in size, so we'll
                // 'fill this in' using a transparent icon..
                ImageIcon icon = new ImageIcon(
                        new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                button.setIcon(icon);
                if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0))
                {
                    button.setBackground(Color.WHITE);
                }
                else
                {
                    button.setBackground(Color.BLACK);
                }
                chessBoardSquares[j][i] = button;
            }
        }
        
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                switch (j)
                {
                    case 0:
                        chessBoard.add(new JLabel("" + (i + 1),
                                SwingConstants.CENTER));
                    default:
                        chessBoard.add(chessBoardSquares[j][i]);
                }
            }
        }
        
        chessBoard.add(new JLabel(""));
        
        for (int i = 0; i < 8; i++)
        {
            chessBoard.add(
                new JLabel(COLS.substring(i, i + 1),
                SwingConstants.CENTER));
        }
    }

//    public static void main(String[] args)
//    {
//        Runnable r = new Runnable() {
//            
//            @Override
//            public void run()
//            {
//                SingleChessBoard cb =
//                        new SingleChessBoard();
//
//                JFrame f = createFrame("ChessChamp");
//                f.add(cb.getGui());
//
//                f.setLocationRelativeTo(null);
//                f.setVisible(true);
//            }
//        };
//        SwingUtilities.invokeLater(r);
//    }
    
    public class MovePieceListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("I was clicked!");
        }
    }
}
