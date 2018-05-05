import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ServerClientScreen extends Screen
{
    private static JFrame frame;
    private static JLabel descriptionLabel;
    private static JLabel ipAddressLabel;
    private static JPanel descriptionPanel;
    private static JPanel textAreaPanel;
    private static JPanel buttonPanel;
    private static JButton whiteButton;
    private static JButton blackButton;
    private static JButton exitButton;
    private static JButton connectButton;
    private static JButton startGameButton;
    private static JTextArea textArea;
    private static JTextField textField;
    
    private static final int QUESTION_FONT_SIZE = 28;
    private static final int IP_FONT_SIZE = 50;
    
    private static String ipAddress;
    
    public ServerClientScreen() throws UnknownHostException
    {       
        setupFrame();
    }
    
    public static void setupFrame() throws UnknownHostException
    {
        ipAddress = InetAddress.getLocalHost().getHostAddress();
        
        frame = createFrame("Server Client");
        
        textArea = new JTextArea(20, 30);
        textArea.setEditable(false);
        
        ipAddressLabel = new JLabel("Enter IP Address:");
        ipAddressLabel.setFont(new Font("Serif", Font.ITALIC, IP_FONT_SIZE));
        
        textField = new JTextField(10);
        textField.setEditable(false);
        
        textAreaPanel = new JPanel();
        textAreaPanel.add(textArea);
        textAreaPanel.add(ipAddressLabel);
        textAreaPanel.add(textField);
        
        whiteButton = createButton("White", new ServerListener());
        blackButton = createButton("Black", new ClientListener());
        connectButton = createButton("Connect", new ConnectListener());
        startGameButton = createButton("Start Game", new StartGameListener());
        exitButton = createButton("Exit", new ExitListener());
        
        connectButton.setEnabled(false);
        startGameButton.setEnabled(false);
        
        descriptionLabel = new JLabel("Would you like to play as white (host) or black (client)?");
        descriptionLabel.setFont(new Font("Serif", Font.ITALIC, QUESTION_FONT_SIZE));
        
        descriptionPanel = new JPanel();
        descriptionPanel.add(descriptionLabel);
        
        buttonPanel = new JPanel();
        buttonPanel.add(whiteButton);
        buttonPanel.add(blackButton);
        buttonPanel.add(connectButton);
        buttonPanel.add(startGameButton);
        buttonPanel.add(exitButton);
        
        frame.add(descriptionPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(textAreaPanel, BorderLayout.CENTER);
        
        frame.setVisible(true);
    }
    
    public static JTextArea getTextArea()
    {
        return textArea;
    }
    
    public static JTextField getTextField()
    {
        return textField;
    }
    
    public static JButton getStartGameButton()
    {
        return startGameButton;
    }
    
    public static JFrame getFrame()
    {
        return frame;
    }
    
    public static JButton getConnectButton()
    {
        return connectButton;
    }
    
    public static class ServerListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            whiteButton.setEnabled(false);
            blackButton.setEnabled(false);

            textArea.append("Waiting for connection" + "\n");
            textArea.append("Your IP Address is " + ipAddress + "\n" +
                    "Give this address to the client so they can connect to your game." + "\n");

            try
            {
                ChessServer server = new ChessServer();
            }
            catch (IOException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public static class ClientListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            whiteButton.setEnabled(false);
            blackButton.setEnabled(false);
            
            connectButton.setEnabled(true);
            textField.setEditable(true);
            
            textArea.append("Obtain the IP Addess from the server, type it into the textbox, "
                    + "and press Connect to connect to the server." + "\n");
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
    
    public static class ConnectListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            connectButton.setEnabled(false);
            ChessClient client = new ChessClient();
        }    
    }
    
    public static class StartGameListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            frame.dispose();
            
            ChessBoard chessBoard = new ChessBoard(new RealPlayer(Color.WHITE), false);
            
            SendToClientThread send = new SendToClientThread();
            send.setMessage("1");
            Thread t = new Thread(send);
            t.start();
        }
    }
}
