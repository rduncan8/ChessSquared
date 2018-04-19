    // white king picture on TitleScreen came from http://pictures-and-images.com/content/white-chess-king.html
    // black king picture on TitleScreen came from https://www.pinterest.com/haydenwhaling/chess/

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class Game extends Screen {
    
    boolean checkMate;
    private static JFrame titleFrame;
    private static final int FONT_SIZE = 100;
    
    public static void main(String[] args)
    {
        newGame();
    }
    
    public static void newGame(){
        titleFrame = createFrame("Chess²");
        
        JButton exitButton = createButton("Exit", new ExitListener());
        JButton newGameButton = createButton("New Game", new NewGameListener());
        
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
        
        titleFrame.add(buttonPanel, BorderLayout.SOUTH);
        titleFrame.add(titlePanel, BorderLayout.NORTH);
        titleFrame.add(whitePiecePicture, BorderLayout.WEST);
        titleFrame.add(blackPiecePicture, BorderLayout.EAST);
        
        titleFrame.setVisible(true);
        
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        
        JMenuItem newGameMenuItem = new JMenuItem("New Game");
        newGameMenuItem.addActionListener(new NewGameListener());
        
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ExitListener());
        
        menuBar.add(file);
        file.add(newGameMenuItem);
        file.add(exitMenuItem);
        
        titleFrame.setJMenuBar(menuBar);
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
    
    
    //Make this ask if player wants to play white or black
    public static class NewGameListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {            
            Object[] options = {"White", "Black", "Cancel"};
            int dialogResult = JOptionPane.showOptionDialog(null, 
                    "Do you wish to play white or Black?", 
                    "Pick a side.", JOptionPane.YES_NO_CANCEL_OPTION, 
                    JOptionPane.YES_NO_CANCEL_OPTION, 
                    null, options, options[0]);
            //ConnectionScreen screen = new ConnectionScreen();
            if(dialogResult == 0){
                playerWhite playerOne = new playerWhite();
                titleFrame.setVisible(false);
            }else if(dialogResult == 1){
                playerBlack playerTwo = new playerBlack();
                titleFrame.setVisible(false);
            }
            /*for(int counter = 0, maxCounter = options.length; counter<maxCounter;
                    counter++){
                if(options[counter].equals(0)){
                   ChessBoard chess = new ChessBoard(); 
                   titleFrame.setVisible(false);
                }else if(options[counter].equals(1)){
                    ChessBoard chess = new ChessBoard();
                    titleFrame.setVisible(false);
                }
            }*/
        }
    }
}
