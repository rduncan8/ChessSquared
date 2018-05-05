// white king picture on TitleScreen came from http://pictures-and-images.com/content/white-chess-king.html
// black king picture on TitleScreen came from https://www.pinterest.com/haydenwhaling/chess/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TitleScreen extends Screen
{
    private static JFrame titleFrame;
    private static final int FONT_SIZE = 100;
    
    public TitleScreen()
    {
        setupFrame();
    }
    
    public static void main(String[] args)
    {
        TitleScreen screen = new TitleScreen();
    }
    
    public static void setupFrame()
    {
        titleFrame = createFrame("Chess²");
        
        JButton newGameButton = createButton("New Game", new NewGameListener());
        JButton exitButton = createButton("Exit", new ExitListener());
        
        JLabel title = new JLabel("Chess²");
        title.setFont(new Font("Serif", Font.ITALIC, FONT_SIZE));
        
        JPanel titlePanel = new JPanel();
        titlePanel.add(title);
        
        JLabel whitePiecePicture = createPictureLabel("white king.jpg");
        JLabel blackPiecePicture = createPictureLabel("black king.jpg");
        
        JPanel picturePanel = new JPanel();
        picturePanel.setLayout(new FlowLayout());
        picturePanel.add(whitePiecePicture);
        picturePanel.add(blackPiecePicture);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(newGameButton);
        buttonPanel.add(exitButton);
        
        titleFrame.add(titlePanel, BorderLayout.NORTH);
        titleFrame.add(buttonPanel, BorderLayout.SOUTH);
        titleFrame.add(blackPiecePicture, BorderLayout.EAST);
        titleFrame.add(whitePiecePicture, BorderLayout.WEST);
        
        titleFrame.setVisible(true);
        
        JMenuBar menuBar = new JMenuBar();
        
        JMenu file = new JMenu("File");       
        JMenu help = new JMenu("Help");
        
        JMenuItem newGameMenuItem = new JMenuItem("New Game");
        newGameMenuItem.addActionListener(new NewGameListener());
        
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ExitListener());
        
        JMenuItem aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.addActionListener(new AboutListener());
        
        menuBar.add(file);
        menuBar.add(help);
        file.add(newGameMenuItem);
        file.add(exitMenuItem);
        help.add(aboutMenuItem);
        
        titleFrame.setJMenuBar(menuBar);
    }
    
    public static void startSingleplayerGame()
    {
        Object[] playOptions = {"Computer", "Player", "Cancel"};
        Object[] pieceOptions = {"White", "Black", "Cancel"};
            
        int playDialogResult = JOptionPane.showOptionDialog(null,
                "Do you wish to play against the computer or a player?",
                "Pick an opponent.", JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.YES_NO_CANCEL_OPTION,
                null, playOptions, playOptions[0]);
            
        if(playDialogResult == 0) // play against computer
        {
            int pieceDialogResult = JOptionPane.showOptionDialog(null,
                    "Do you wish to play white or Black?",
                    "Pick a side.", JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    null, pieceOptions, pieceOptions[0]);
                            
            if(pieceDialogResult == 0) // play as white
            {
                ChessBoard chessBoard = new ChessBoard(new RealPlayer(Color.WHITE), true);
                titleFrame.setVisible(false);
            } 
            else if(pieceDialogResult == 1) // play as black
            {
                ChessBoard chessBoard = new ChessBoard(new RealPlayer(Color.BLACK), true);
                titleFrame.setVisible(false);
            }
        } 
        else if(playDialogResult == 1) // play against player
        {
            ChessBoard chessBoard = new ChessBoard(new RealPlayer(Color.WHITE), false);
            titleFrame.setVisible(false);
        }
    }
    
    public static void startMultiplayerGame()
    {
        try
        {
            ServerClientScreen screen = new ServerClientScreen();
            titleFrame.setVisible(false);
        }
        catch (UnknownHostException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
    public static JFrame getTitleFrame()
    {
        return titleFrame;
    }
    
    public static class NewGameListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            Object[] gameOptions = {"Singleplayer", "Multiplayer", "Cancel"};
            
            int gameDialogResult = JOptionPane.showOptionDialog(null,
                    "Would you like to play singlerplayer or multiplayer?",
                    "Single or Multiplayer", JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.YES_NO_CANCEL_OPTION, null, gameOptions, gameOptions[0]);
            
            if (gameDialogResult == 0)
                startSingleplayerGame();
            else if (gameDialogResult == 1)
                startMultiplayerGame();
        }
    }
    
    public static class ExitListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            int dialogResult = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to exit the game?", "Exit Game", JOptionPane.YES_NO_OPTION);
            
            if(dialogResult == 0)
                System.exit(0);
        }
    }
    
    public static class AboutListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            JOptionPane.showMessageDialog(titleFrame, "This is the digital version "
                    + "of the classic game of chess.\n\n If you would like to play "
                    + "against the computer or a friend on a \n single computer, "
                    + "click the new game button, then click the singleplayer button,"
                    + "\n and follow the on screen instructions for starting your game."
                    + "\n\n If you would like to play against another player over a \n "
                    + "local area network on another computer, click the new game \n button, "
                    + "then click the multiplayer button, and follow the on screen\n instructions "
                    + "on how to connect your computers and to start the game."
                    + "\n\n To exit the game, press the red X at the top right \n "
                    + "hand corner, or press the exit button.");
        }  
    }
}
