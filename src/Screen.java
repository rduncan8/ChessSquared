// generic screen class; every frame or screen will follow similar rules of size, buttons, layouts, etc.

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Screen
{
    private static final int FRAME_HEIGHT = 700;
    private static final int FRAME_WIDTH = 700;
    private static final int PICTURE_HEIGHT = 300;
    private static final int PICTURE_WIDTH = 300;
    
    public static JFrame createFrame(String title)
    {
        JFrame frame = new JFrame(title);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);  // centers the frame in the middle of the screen
        
        return frame;
    }
    
    public static JLabel createPictureLabel(String picture)
    {
        JLabel label = new JLabel();
        
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(picture).getImage().getScaledInstance(
            PICTURE_WIDTH, PICTURE_HEIGHT, Image.SCALE_DEFAULT));
        
        label.setIcon(imageIcon);
        
        return label;
    }
    
    public static JButton createButton(String title, ActionListener listener)
    {
        JButton button = new JButton(title);
        button.addActionListener(listener);
        
        return button;
    }
}
