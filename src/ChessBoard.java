import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;

public class ChessBoard extends Screen{
    
    public int player;
    public String blockName;
    public static ChessBlock a1,a2,a3,a4,a5,a6,a7,a8,
                      b1,b2,b3,b4,b5,b6,b7,b8,
                      c1,c2,c3,c4,c5,c6,c7,c8,
                      d1,d2,d3,d4,d5,d6,d7,d8,
                      e1,e2,e3,e4,e5,e6,e7,e8,
                      f1,f2,f3,f4,f5,f6,f7,f8,
                      g1,g2,g3,g4,g5,g6,g7,g8,
                      h1,h2,h3,h4,h5,h6,h7,h8;
    
    public Piece p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,
                 p11,p12,p13,p14,p15,p16,
                 r1,r2,r3,r4,
                 kn1,kn2,kn3,kn4,
                 bi1,bi2,bi3,bi4,
                 q1,q2,
                 k1,k2;
    
    public Pawn wP1,wP2,wP3,wP4,wP5,wP6,wP7,wP8,
                bP1,bP2,bP3,bP4,bP5,bP6,bP7,bP8;
    public Bishop wB1,wB2,
                  bB1,bB2;
    public Knight wKn1,wKn2,
                  bKn1,bKn2;
    public Rook wR1,wR2,
                bR1,bR2;
    public Queen wQ,
                 bQ;
    public King wK,
                bK;
    
    public playerWhite pWhite;
    public playerBlack pBlack;
    
    private final JFrame frame = createFrame("Chess");
    private final JPanel gui = new JPanel(new BorderLayout(3,3));
    private final JButton[][] chessBoardSquares = new JButton[8][8];
    private JPanel theChessBoard;
    private final JLabel message = new JLabel("Chess Champ is ready to play!");
    private static final String wCOLS = "ABCDEFGH";
    private static final String bCOLS = "HGFEDCBA";
    private static final String ROWS = "12345678";
    
    public ChessBoard(int player) {
        this.player = player;
        createGUI(player);
        frame.add(gui);
        frame.setVisible(true);
    }
    
    public final void createGUI(int player)
    {
        // set up the main GUI
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);
        JButton newButton;
        JButton saveButton;
        JButton restoreButton;
        JButton resignButton;
        tools.add(newButton = new JButton("New")); // TODO - add functionality!
        newButton.addActionListener(new toolsListener());
        newButton.setActionCommand("New");
        tools.add(saveButton = new JButton("Save")); // TODO - add functionality!
        saveButton.addActionListener(new toolsListener());
        saveButton.setActionCommand("Save");
        tools.add(restoreButton = new JButton("Restore")); // TODO - add functionality!
        restoreButton.addActionListener(new toolsListener());
        restoreButton.setActionCommand("Restore");
        tools.addSeparator();
        tools.add(resignButton = new JButton("Resign")); // TODO - add functionality!
        resignButton.addActionListener(new toolsListener());
        resignButton.setActionCommand("Resign");
        tools.addSeparator();
        tools.add(message);
        

        theChessBoard = new JPanel(new GridLayout(0, 9));
        theChessBoard.setBorder(new LineBorder(Color.BLACK));
        gui.add(theChessBoard);
        
        setUpBoard(player);
        setUpPieces(player);
    }
    
    public void setUpBoard(int player){
        
        if(player == 1){
            Insets buttonMargin = new Insets(0,0,0,0);
            for (int row = 7, actRow = 0; row >= 0; row--, actRow++)
            {
                for (int col = 0; col < chessBoardSquares[row].length; col++)
                {
                    JButton button = new JButton();
                    button.setMargin(buttonMargin);             
                    button.addActionListener(new MovePieceListener());
                    button.setActionCommand("" + Integer.toString(actRow) + 
                                                    Integer.toString(col));
                    // our chess pieces are 64x64 px in size, so we'll
                    // 'fill this in' using a transparent icon..
                    ImageIcon icon = new ImageIcon(
                            new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                    button.setIcon(icon);
                    if ((col % 2 == 0 && row % 2 == 0) || (col % 2 == 1 && row % 2 == 1))
                    {
                        button.setBackground(Color.WHITE);
                    }
                    else
                    {
                        button.setBackground(Color.GREEN);
                    }
                    chessBoardSquares[col][row] = button;
                }
            }

            for (int row = 0, actRow = 8; row < 8; row++, actRow--)
            {
                for (int col = 0; col < 8; col++)
                {
                    switch (col)
                    {
                        case 0:
                            theChessBoard.add(new JLabel("" + (actRow),
                                    SwingConstants.CENTER));
                        default:
                            theChessBoard.add(chessBoardSquares[col][row]);
                    }
                }
            }

            theChessBoard.add(new JLabel(""));

            for (int letter = 0; letter < 8; letter++)
            {
                theChessBoard.add(
                    new JLabel(wCOLS.substring(letter, letter + 1),
                    SwingConstants.CENTER));
            }
            
        }else if(player == 2){
            
            Insets buttonMargin = new Insets(0,0,0,0);
            for (int row = 7, actRow = 0; row >= 0; row--, actRow++)
            {
                for (int col = 0; col < chessBoardSquares[row].length; col++)
                {
                    JButton button = new JButton();
                    button.setMargin(buttonMargin);             
                    button.addActionListener(new MovePieceListener());
                    button.setActionCommand("" + Integer.toString(actRow) + 
                                                    Integer.toString(col));
                    // our chess pieces are 64x64 px in size, so we'll
                    // 'fill this in' using a transparent icon..
                    ImageIcon icon = new ImageIcon(
                            new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                    button.setIcon(icon);
                    if ((col % 2 == 0 && row % 2 == 0) || (col % 2 == 1 && row % 2 == 1))
                    {
                        button.setBackground(Color.WHITE);
                    }
                    else
                    {
                        button.setBackground(Color.GREEN);
                    }
                    chessBoardSquares[col][row] = button;
                }
            }

            for (int row = 0; row < 8; row++)
            {
                for (int col = 0; col < 8; col++)
                {
                    switch (col)
                    {
                        case 0:
                            theChessBoard.add(new JLabel("" + (row+1),
                                    SwingConstants.CENTER));
                        default:
                            theChessBoard.add(chessBoardSquares[col][row]);
                    }
                }
            }

            theChessBoard.add(new JLabel(""));

            for (int letter = 0; letter < 8; letter++)
            {
                theChessBoard.add(
                    new JLabel(bCOLS.substring(letter, letter+1),
                    SwingConstants.CENTER));
            }
        }
    }
    
    
    public void setUpPieces(int player){
        if (player == 1){
            ChessBlock block[][] = {
                    {a1,a2,a3,a4,a5,a6,a7,a8},
                    {b1,b2,b3,b4,b5,b6,b7,b8},
                    {c1,c2,c3,c4,c5,c6,c7,c8},
                    {d1,d2,d3,d4,d5,d6,d7,d8},
                    {e1,e2,e3,e4,e5,e6,e7,e8},
                    {f1,f2,f3,f4,f5,f6,f7,f8},
                    {g1,g2,g3,g4,g5,g6,g7,g8},
                    {h1,h2,h3,h4,h5,h6,h7,h8}
                    };
        }else if (player == 2){
            ChessBlock block[][] = {
                    {h8,h7,h6,h5,h4,h3,h2,h1},
                    {g8,g7,g6,g5,g4,g3,g2,g1},
                    {f8,f7,f6,f5,f4,f3,f2,f1},
                    {e8,e7,e6,e5,e4,e3,e2,e1},
                    {d8,d7,d6,d5,d4,e3,e2,e1},
                    {c8,c7,c6,c5,c4,c3,c2,c1},
                    {b8,b7,b6,b5,b4,b3,b2,b1},
                    {a8,a7,a6,a5,a4,a3,a2,a1}
                    };
        }
        
        wP1 = new Pawn(a2);
        wP2 = new Pawn(b2);
        wP3 = new Pawn(c2);
        wP4 = new Pawn(d2);
        wP5 = new Pawn(e2);
        wP6 = new Pawn(f2);
        wP7 = new Pawn(g2);
        wP8 = new Pawn(h2);
        bP1 = new Pawn(a7);
        bP2 = new Pawn(b7);
        bP3 = new Pawn(c7);
        bP4 = new Pawn(d7);
        bP5 = new Pawn(e7);
        bP6 = new Pawn(f7);
        bP7 = new Pawn(g7);
        bP8 = new Pawn(h7);
        wB1 = new Bishop();
        wB2 = new Bishop();
        bB1 = new Bishop();
        bB2 = new Bishop();
        wKn1 = new Knight();
        wKn2 = new Knight();
        bKn1 = new Knight();
        bKn2 = new Knight();
        wR1 = new Rook(a1);
        wR2 = new Rook(h1);
        bR1 = new Rook(a8);
        bR2 = new Rook(h8);
        wQ = new Queen();
        wK = new King(e1);
        bQ = new Queen();
        bK = new King(e8);
        
        p1 = new Piece(1, a2, wP1);
        p2 = new Piece(1, b2, wP2);
        p3 = new Piece(1, c2, wP3);
        p4 = new Piece(1, d2, wP4);
        p5 = new Piece(1, e2, wP5);
        p6 = new Piece(1, f2, wP6);
        p7 = new Piece(1, g2, wP7);
        p8 = new Piece(1, h2, wP8);
        p9 = new Piece(2, a7, bP1);
        p10 = new Piece(2, b7, bP2);
        p11 = new Piece(2, c7, bP3);
        p12 = new Piece(2, d7, bP4);
        p13 = new Piece(2, e7, bP5);
        p14 = new Piece(2, f7, bP6);
        p15 = new Piece(2, g7, bP7);
        p16 = new Piece(2, h7, bP8);
        r1 = new Piece(1, a1, wR1);
        r2 = new Piece(1, h1, wR2);
        r3 = new Piece(2, a8, bR1);
        r4 = new Piece(2, h8, bR2);
        kn1 = new Piece(1, wKn1);
        kn2 = new Piece(1, wKn2);
        kn3 = new Piece(2, bKn1);
        kn4 = new Piece(2, bKn2);
        bi1 = new Piece(1, wB1);
        bi2 = new Piece(1, wB2);
        bi3 = new Piece(2, bB1);
        bi4 = new Piece(2, bB1);
        q1 = new Piece(1, wQ);
        q2 = new Piece(2, bQ);
        k1 = new Piece(1, e1, wK);
        k2 = new Piece(2, e8, bK);
        
        if(player == 1){
            
            a1 = new ChessBlock(r1, chessBoardSquares[0][0], "A1");
            b1 = new ChessBlock(kn1, chessBoardSquares[1][0], "B1");
            c1 = new ChessBlock(bi1, chessBoardSquares[2][0], "C1");
            d1 = new ChessBlock(q1, chessBoardSquares[3][0], "D1");
            e1 = new ChessBlock(k1, chessBoardSquares[4][0], "E1");
            f1 = new ChessBlock(bi2, chessBoardSquares[5][0], "F1");
            g1 = new ChessBlock(kn2, chessBoardSquares[6][0], "G1");
            h1 = new ChessBlock(r2, chessBoardSquares[7][0], "H1");
            a2 = new ChessBlock(p1, chessBoardSquares[0][1], "A2");
            b2 = new ChessBlock(p2, chessBoardSquares[1][1], "B2");
            c2 = new ChessBlock(p3, chessBoardSquares[2][1], "C2");
            d2 = new ChessBlock(p4, chessBoardSquares[3][1], "D2");
            e2 = new ChessBlock(p5, chessBoardSquares[4][1], "E2");
            f2 = new ChessBlock(p6, chessBoardSquares[5][1], "F2");
            g2 = new ChessBlock(p7, chessBoardSquares[6][1], "G2");
            h2 = new ChessBlock(p8, chessBoardSquares[7][1], "H2");

            a8 = new ChessBlock(r3, chessBoardSquares[0][7], "A8");
            b8 = new ChessBlock(kn3, chessBoardSquares[1][7], "B8");
            c8 = new ChessBlock(bi3, chessBoardSquares[2][7], "C8");
            d8 = new ChessBlock(q2, chessBoardSquares[3][7], "D8");
            e8 = new ChessBlock(k2, chessBoardSquares[4][7], "E8");
            f8 = new ChessBlock(bi4, chessBoardSquares[5][7], "F8");
            g8 = new ChessBlock(kn4, chessBoardSquares[6][7], "G8");
            h8 = new ChessBlock(r4, chessBoardSquares[7][7], "H8");
            a7 = new ChessBlock(p9, chessBoardSquares[0][6], "A7");
            b7 = new ChessBlock(p10, chessBoardSquares[1][6], "B7");
            c7 = new ChessBlock(p11, chessBoardSquares[2][6], "C7");
            d7 = new ChessBlock(p12, chessBoardSquares[3][6], "D7");
            e7 = new ChessBlock(p13, chessBoardSquares[4][6], "E7");
            f7 = new ChessBlock(p14, chessBoardSquares[5][6], "F7");
            g7 = new ChessBlock(p15, chessBoardSquares[6][6], "G7");
            h7 = new ChessBlock(p16, chessBoardSquares[7][6], "H7");

            a3 = new ChessBlock(chessBoardSquares[0][2], "A3");
            b3 = new ChessBlock(chessBoardSquares[1][2], "B3");
            c3 = new ChessBlock(chessBoardSquares[2][2], "C3");
            d3 = new ChessBlock(chessBoardSquares[3][2], "D3");
            e3 = new ChessBlock(chessBoardSquares[4][2], "E3");
            f3 = new ChessBlock(chessBoardSquares[5][2], "F3");
            g3 = new ChessBlock(chessBoardSquares[6][2], "G3");
            h3 = new ChessBlock(chessBoardSquares[7][2], "H3");
            a4 = new ChessBlock(chessBoardSquares[0][3], "A4");
            b4 = new ChessBlock(chessBoardSquares[1][3], "B4");
            c4 = new ChessBlock(chessBoardSquares[2][3], "C4");
            d4 = new ChessBlock(chessBoardSquares[3][3], "D4");
            e4 = new ChessBlock(chessBoardSquares[4][3], "E4");
            f4 = new ChessBlock(chessBoardSquares[5][3], "F4");
            g4 = new ChessBlock(chessBoardSquares[6][3], "G4");
            h4 = new ChessBlock(chessBoardSquares[7][3], "H4");
            a5 = new ChessBlock(chessBoardSquares[0][4], "A5");
            b5 = new ChessBlock(chessBoardSquares[1][4], "B5");
            c5 = new ChessBlock(chessBoardSquares[2][4], "C5");
            d5 = new ChessBlock(chessBoardSquares[3][4], "D5");
            e5 = new ChessBlock(chessBoardSquares[4][4], "E5");
            f5 = new ChessBlock(chessBoardSquares[5][4], "F5");
            g5 = new ChessBlock(chessBoardSquares[6][4], "G5");
            h5 = new ChessBlock(chessBoardSquares[7][4], "H5");
            a6 = new ChessBlock(chessBoardSquares[0][5], "A6");
            b6 = new ChessBlock(chessBoardSquares[1][5], "B6");
            c6 = new ChessBlock(chessBoardSquares[2][5], "C6");
            d6 = new ChessBlock(chessBoardSquares[3][5], "D6");
            e6 = new ChessBlock(chessBoardSquares[4][5], "E6");
            f6 = new ChessBlock(chessBoardSquares[5][5], "F6");
            g6 = new ChessBlock(chessBoardSquares[6][5], "G6");
            h6 = new ChessBlock(chessBoardSquares[7][5], "H6");
            
        }else if(player == 2){
            
            a1 = new ChessBlock(r1, chessBoardSquares[7][7], "A1");
            b1 = new ChessBlock(kn1, chessBoardSquares[6][7], "B1");
            c1 = new ChessBlock(bi1, chessBoardSquares[5][7], "C1");
            d1 = new ChessBlock(q1, chessBoardSquares[4][7], "D1");
            e1 = new ChessBlock(k1, chessBoardSquares[3][7], "E1");
            f1 = new ChessBlock(bi2, chessBoardSquares[2][7], "F1");
            g1 = new ChessBlock(kn2, chessBoardSquares[1][7], "G1");
            h1 = new ChessBlock(r2, chessBoardSquares[0][7], "H1");
            a2 = new ChessBlock(p1, chessBoardSquares[7][6], "A2");
            b2 = new ChessBlock(p2, chessBoardSquares[6][6], "B2");
            c2 = new ChessBlock(p3, chessBoardSquares[5][6], "C2");
            d2 = new ChessBlock(p4, chessBoardSquares[4][6], "D2");
            e2 = new ChessBlock(p5, chessBoardSquares[3][6], "E2");
            f2 = new ChessBlock(p6, chessBoardSquares[2][6], "F2");
            g2 = new ChessBlock(p7, chessBoardSquares[1][6], "G2");
            h2 = new ChessBlock(p8, chessBoardSquares[0][6], "H2");

            a8 = new ChessBlock(r3, chessBoardSquares[7][0], "A8");
            b8 = new ChessBlock(kn3, chessBoardSquares[6][0], "B8");
            c8 = new ChessBlock(bi3, chessBoardSquares[5][0], "C8");
            d8 = new ChessBlock(q2, chessBoardSquares[4][0], "D8");
            e8 = new ChessBlock(k2, chessBoardSquares[3][0], "E8");
            f8 = new ChessBlock(bi4, chessBoardSquares[2][0], "F8");
            g8 = new ChessBlock(kn4, chessBoardSquares[1][0], "G8");
            h8 = new ChessBlock(r4, chessBoardSquares[0][0], "H8");
            a7 = new ChessBlock(p9, chessBoardSquares[7][1], "A7");
            b7 = new ChessBlock(p10, chessBoardSquares[6][1], "B7");
            c7 = new ChessBlock(p11, chessBoardSquares[5][1], "C7");
            d7 = new ChessBlock(p12, chessBoardSquares[4][1], "D7");
            e7 = new ChessBlock(p13, chessBoardSquares[3][1], "E7");
            f7 = new ChessBlock(p14, chessBoardSquares[2][1], "F7");
            g7 = new ChessBlock(p15, chessBoardSquares[1][1], "G7");
            h7 = new ChessBlock(p16, chessBoardSquares[0][1], "H7");

            a3 = new ChessBlock(chessBoardSquares[0][2], "A3");
            b3 = new ChessBlock(chessBoardSquares[1][2], "B3");
            c3 = new ChessBlock(chessBoardSquares[2][2], "C3");
            d3 = new ChessBlock(chessBoardSquares[3][2], "D3");
            e3 = new ChessBlock(chessBoardSquares[4][2], "E3");
            f3 = new ChessBlock(chessBoardSquares[5][2], "F3");
            g3 = new ChessBlock(chessBoardSquares[6][2], "G3");
            h3 = new ChessBlock(chessBoardSquares[7][2], "H3");
            a4 = new ChessBlock(chessBoardSquares[0][3], "A4");
            b4 = new ChessBlock(chessBoardSquares[1][3], "B4");
            c4 = new ChessBlock(chessBoardSquares[2][3], "C4");
            d4 = new ChessBlock(chessBoardSquares[3][3], "D4");
            e4 = new ChessBlock(chessBoardSquares[4][3], "E4");
            f4 = new ChessBlock(chessBoardSquares[5][3], "F4");
            g4 = new ChessBlock(chessBoardSquares[6][3], "G4");
            h4 = new ChessBlock(chessBoardSquares[7][3], "H4");
            a5 = new ChessBlock(chessBoardSquares[0][4], "A5");
            b5 = new ChessBlock(chessBoardSquares[1][4], "B5");
            c5 = new ChessBlock(chessBoardSquares[2][4], "C5");
            d5 = new ChessBlock(chessBoardSquares[3][4], "D5");
            e5 = new ChessBlock(chessBoardSquares[4][4], "E5");
            f5 = new ChessBlock(chessBoardSquares[5][4], "F5");
            g5 = new ChessBlock(chessBoardSquares[6][4], "G5");
            h5 = new ChessBlock(chessBoardSquares[7][4], "H5");
            a6 = new ChessBlock(chessBoardSquares[0][5], "A6");
            b6 = new ChessBlock(chessBoardSquares[1][5], "B6");
            c6 = new ChessBlock(chessBoardSquares[2][5], "C6");
            d6 = new ChessBlock(chessBoardSquares[3][5], "D6");
            e6 = new ChessBlock(chessBoardSquares[4][5], "E6");
            f6 = new ChessBlock(chessBoardSquares[5][5], "F6");
            g6 = new ChessBlock(chessBoardSquares[6][5], "G6");
            h6 = new ChessBlock(chessBoardSquares[7][5], "H6");

        }
        
        
        /*ChessBlock nullArea;
        
        for (int col = 0; col < 8; col++){
            for (int row = 2; row < 6; row++) {
                block[col][row] = nullArea = new ChessBlock(COLS.substring(col, col + 1) 
                        + Integer.toString(row), chessBoardSquares[col][row]);
            }
        }*/
        
    }
    
    public class toolsListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e){
            //new save restore resign
            if("new".equals(e.getActionCommand())){
                Game.newGame();
                frame.setVisible(false);
            }else if("save".equals(e.getActionCommand())){
                
            }else if("restore".equals(e.getActionCommand())){
                
            }else if("resign".equals(e.getActionCommand())){
                
            }
        }
        
    }
    
    public class MovePieceListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (player == 1){
                if("00".equals(e.getActionCommand())){
                    if(a1.hasPce()){
                        System.out.println(a1.getPce() + 
                                a1.getBlockName()+ " was clicked.");
                    }else{
                        System.out.println(a1.getBlockName() +
                                " was clicked");
                    }
                }else if("01".equals(e.getActionCommand())){
                    if(b1.hasPce()){
                        System.out.println(b1.getPce() +
                                b1.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(b1.getBlockName() +
                                " was clicked");
                    }
                }else if("02".equals(e.getActionCommand())){
                    if(c1.hasPce()){
                        System.out.println(c1.getPce() +
                                c1.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(c1.getBlockName() +
                                " was clicked");
                    }
                }else if("03".equals(e.getActionCommand())){
                    if(d1.hasPce()){
                        System.out.println(d1.getPce() + 
                                d1.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(d1.getBlockName() +
                                " was clicked");
                    }
                }else if("04".equals(e.getActionCommand())){
                    if(e1.hasPce()){
                        System.out.println(e1.getPce() + 
                                e1.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(e1.getBlockName() +
                                " was clicked");
                    }
                }else if("05".equals(e.getActionCommand())){
                    if(f1.hasPce()){
                        System.out.println(f1.getPce() + 
                                f1.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(f1.getBlockName() + 
                                " was clicked");
                    }
                }else if("06".equals(e.getActionCommand())){
                    if(g1.hasPce()){
                        System.out.println(g1.getPce() + 
                                g1.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(g1.getBlockName() + 
                                " was clicked");
                    }
                }else if("07".equals(e.getActionCommand())){
                    if(h1.hasPce()){
                        System.out.println(h1.getPce() + 
                                h1.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(h1.getBlockName() + 
                                " was clicked");
                    }
                }else if("10".equals(e.getActionCommand())){
                    if(a2.hasPce()){
                        System.out.println(a2.getPce() + 
                                a2.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(a2.getBlockName() + 
                                " was clicked");
                    }
                }else if("11".equals(e.getActionCommand())){
                    if(b2.hasPce()){
                        System.out.println(b2.getPce() + 
                                b2.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(b2.getBlockName() + 
                                " was clicked");
                    }
                }else if("12".equals(e.getActionCommand())){
                    if(c2.hasPce()){
                        System.out.println(c2.getPce() + 
                                c2.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(c2.getBlockName() + 
                                " was clicked");
                    }
                }else if("13".equals(e.getActionCommand())){
                    if(d2.hasPce()){
                        System.out.println(d2.getPce() + 
                                d2.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(d2.getBlockName() + 
                                " was clicked");
                    }
                }else if("14".equals(e.getActionCommand())){
                    if(e2.hasPce()){
                        System.out.println(e2.getPce() + 
                                e2.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(e2.getBlockName() + 
                                " was clicked");
                    }
                }else if("15".equals(e.getActionCommand())){
                    if(f2.hasPce()){
                        System.out.println(f2.getPce() + 
                                f2.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(f2.getBlockName() + 
                                " was clicked");
                    }
                }else if("16".equals(e.getActionCommand())){
                    if(g2.hasPce()){
                        System.out.println(g2.getPce() + 
                                g2.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(g2.getBlockName() + 
                                " was clicked");
                    }
                }else if("17".equals(e.getActionCommand())){
                    if(h2.hasPce()){
                        System.out.println(h2.getPce() + 
                                h2.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(h2.getBlockName() + 
                                " was clicked");
                    }
                }else if("20".equals(e.getActionCommand())){
                    if(a3.hasPce()){
                        System.out.println(a3.getPce() + 
                                a3.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(a3.getBlockName() + 
                                " was clicked");
                    }
                }else if("21".equals(e.getActionCommand())){
                    if(b3.hasPce()){
                        System.out.println(b3.getPce() + 
                                b3.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(b3.getBlockName() + 
                                " was clicked");
                    }
                }else if("22".equals(e.getActionCommand())){
                    if(c3.hasPce()){
                        System.out.println(c3.getPce() + 
                                c3.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(c3.getBlockName() + 
                                " was clicked");
                    }
                }else if("23".equals(e.getActionCommand())){
                    if(d3.hasPce()){
                        System.out.println(d3.getPce() + 
                                d3.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(d3.getBlockName() + 
                                " was clicked");
                    }
                }else if("24".equals(e.getActionCommand())){
                    if(e3.hasPce()){
                        System.out.println(e3.getPce() + 
                                e3.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(e3.getBlockName() +
                                " was clicked");
                    }
                }else if("25".equals(e.getActionCommand())){
                    if(f3.hasPce()){
                        System.out.println(f3.getPce() + 
                                f3.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(f3.getBlockName() +
                                " was clicked");
                    }
                }else if("26".equals(e.getActionCommand())){
                    if(g3.hasPce()){
                        System.out.println(g3.getPce() +
                                g3.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(g3.getBlockName() + 
                                " was clicked");
                    }
                }else if("27".equals(e.getActionCommand())){
                    if(h3.hasPce()){
                        System.out.println(h3.getPce() +
                                h3.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(h3.getBlockName() +
                                " was clicked");
                    }
                }else if("30".equals(e.getActionCommand())){
                    if(a4.hasPce()){
                        System.out.println(a4.getPce() +
                                a4.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(a4.getBlockName() + 
                                " was clicked");
                    }
                }else if("31".equals(e.getActionCommand())){
                    if(b4.hasPce()){
                        System.out.println(b4.getPce() +
                                b4.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(b4.getBlockName() +
                                " was clicked");
                    }
                }else if("32".equals(e.getActionCommand())){
                    if(c4.hasPce()){
                        System.out.println(c4.getPce() +
                                c4.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(c4.getBlockName() +
                                " was clicked");
                    }
                }else if("33".equals(e.getActionCommand())){
                    if(d4.hasPce()){
                        System.out.println(d4.getPce() + 
                                d4.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(d4.getBlockName() + 
                                " was clicked");
                    }
                }else if("34".equals(e.getActionCommand())){
                    if(e4.hasPce()){
                        System.out.println(e4.getPce() + 
                                e4.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(e4.getBlockName() +
                                " was clicked");
                    }
                }else if("35".equals(e.getActionCommand())){
                    if(f4.hasPce()){
                        System.out.println(f4.getPce() +
                                f4.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(f4.getBlockName() +
                                " was clicked");
                    }
                }else if("36".equals(e.getActionCommand())){
                    if(g4.hasPce()){
                        System.out.println(g4.getPce() +
                                g4.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(g4.getBlockName() +
                                " was clicked");
                    }
                }else if("37".equals(e.getActionCommand())){
                    if(h4.hasPce()){
                        System.out.println(h4.getPce() + 
                                h4.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(h4.getBlockName() +
                                " was clicked");
                    }
                }else if("40".equals(e.getActionCommand())){
                    if(a5.hasPce()){
                        System.out.println(a5.getPce() + 
                                a5.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(a5.getBlockName() +
                                " was clicked");
                    }
                }else if("41".equals(e.getActionCommand())){
                    if(b5.hasPce()){
                        System.out.println(b5.getPce() +
                                b5.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(b5.getBlockName() +
                                " was clicked");
                    }
                }else if("42".equals(e.getActionCommand())){
                    if(c5.hasPce()){
                        System.out.println(c5.getPce() + 
                                c5.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(c5.getBlockName() +
                                " was clicked");
                    }
                }else if("43".equals(e.getActionCommand())){
                    if(d5.hasPce()){
                        System.out.println(d5.getPce() + 
                                d5.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(d5.getBlockName() + 
                                " was clicked");
                    }
                }else if("44".equals(e.getActionCommand())){
                    if(e5.hasPce()){
                        System.out.println(e5.getPce() +
                                e5.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(e5.getBlockName() +
                                " was clicked");
                    }
                }else if("45".equals(e.getActionCommand())){
                    if(f5.hasPce()){
                        System.out.println(f5.getPce() + 
                                f5.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(f5.getBlockName() +
                                " was clicked");
                    }
                }else if("46".equals(e.getActionCommand())){
                    if(g5.hasPce()){
                        System.out.println(g5.getPce() + 
                                g5.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(g5.getBlockName() + 
                                " was clicked");
                    }
                }else if("47".equals(e.getActionCommand())){
                    if(h5.hasPce()){
                        System.out.println(h5.getPce() + 
                                h5.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(h5.getBlockName() +
                                " was clicked");
                    }
                }else if("50".equals(e.getActionCommand())){
                    if(a6.hasPce()){
                        System.out.println(a6.getPce() +
                                a6.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(a6.getBlockName() +
                                " was clicked");
                    }
                }else if("51".equals(e.getActionCommand())){
                    if(b6.hasPce()){
                        System.out.println(b6.getPce() + 
                                b6.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(b6.getBlockName() +
                                " was clicked");
                    }
                }else if("52".equals(e.getActionCommand())){
                    if(c6.hasPce()){
                        System.out.println(c6.getPce() +
                                c6.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(c6.getBlockName() +
                                " was clicked");
                    }
                }else if("53".equals(e.getActionCommand())){
                    if(d6.hasPce()){
                        System.out.println(d6.getPce() + 
                                d6.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(d6.getBlockName() +
                                " was clicked");
                    }
                }else if("54".equals(e.getActionCommand())){
                    if(e6.hasPce()){
                        System.out.println(e6.getPce() +
                                e6.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(e6.getBlockName() +
                                " was clicked");
                    }
                }else if("55".equals(e.getActionCommand())){
                    if(f6.hasPce()){
                        System.out.println(f6.getPce() + 
                                f6.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(f6.getBlockName() + 
                                " was clicked");
                    }
                }else if("56".equals(e.getActionCommand())){
                    if(g6.hasPce()){
                        System.out.println(g6.getPce() + 
                                g6.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(g6.getBlockName() +
                                " was clicked");
                    }
                }else if("57".equals(e.getActionCommand())){
                    if(h6.hasPce()){
                        System.out.println(h6.getPce() + 
                                h6.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(h6.getBlockName() +
                                " was clicked");
                    }
                }else if("60".equals(e.getActionCommand())){
                    if(a7.hasPce()){
                        System.out.println(a7.getPce() + 
                                a7.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(a7.getBlockName() +
                                " was clicked");
                    }
                }else if("61".equals(e.getActionCommand())){
                    if(b7.hasPce()){
                        System.out.println(b7.getPce() + 
                                b7.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(b7.getBlockName() +
                                " was clicked");
                    }
                }else if("62".equals(e.getActionCommand())){
                    if(c7.hasPce()){
                        System.out.println(c7.getPce() +
                                c7.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(c7.getBlockName() + 
                                " was clicked");
                    }
                }else if("63".equals(e.getActionCommand())){
                    if(d7.hasPce()){
                        System.out.println(d7.getPce() +
                                d7.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(d7.getBlockName() +
                                " was clicked");
                    }
                }else if("64".equals(e.getActionCommand())){
                    if(e7.hasPce()){
                        System.out.println(e7.getPce() + 
                                e7.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(e7.getBlockName() + 
                                " was clicked");
                    }
                }else if("65".equals(e.getActionCommand())){
                    if(f7.hasPce()){
                        System.out.println(f7.getPce() +
                                f7.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(f7.getBlockName() +
                                " was clicked");
                    }
                }else if("66".equals(e.getActionCommand())){
                    if(g7.hasPce()){
                        System.out.println(g7.getPce() + 
                                g7.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(g7.getBlockName() +
                                " was clicked");
                    }
                }else if("67".equals(e.getActionCommand())){
                    if(h7.hasPce()){
                        System.out.println(h7.getPce() + 
                                h7.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(h7.getBlockName() +
                                " was clicked");
                    }
                }else if("70".equals(e.getActionCommand())){
                    if(a8.hasPce()){
                        System.out.println(a8.getPce() + 
                                a8.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(a8.getBlockName() +
                                " was clicked");
                    }
                }else if("71".equals(e.getActionCommand())){
                    if(b8.hasPce()){
                        System.out.println(b8.getPce() + 
                                b8.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(b8.getBlockName() + 
                                " was clicked");
                    }
                }else if("72".equals(e.getActionCommand())){
                    if(c8.hasPce()){
                        System.out.println(c8.getPce() + 
                                c8.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(c8.getBlockName() + 
                                " was clicked");
                    }
                }else if("73".equals(e.getActionCommand())){
                    if(d8.hasPce()){
                        System.out.println(d8.getPce() + 
                                d8.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(d8.getBlockName() +
                                " was clicked");
                    }
                }else if("74".equals(e.getActionCommand())){
                    if(e8.hasPce()){
                        System.out.println(e8.getPce() + 
                                e8.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(e8.getBlockName() + 
                                " was clicked");
                    }
                }else if("75".equals(e.getActionCommand())){
                    if(f8.hasPce()){
                        System.out.println(f8.getPce() + 
                                f8.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(f8.getBlockName() + 
                                " was clicked");
                    }
                }else if("76".equals(e.getActionCommand())){
                    if(g8.hasPce()){
                        System.out.println(g8.getPce() + 
                                g8.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(g8.getBlockName() + 
                                " was clicked");
                    }
                }else if("77".equals(e.getActionCommand())){
                    if(h8.hasPce()){
                        System.out.println(h8.getPce() + 
                                h8.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(h8.getBlockName() + 
                                " was clicked");
                    }
                }
                
            }else if (player == 2){
                
                if("77".equals(e.getActionCommand())){
                    if(a1.hasPce()){
                        System.out.println(a1.getPce() + 
                               a1.getBlockName()+ " was clicked.");
                    }else{
                        System.out.println(a1.getBlockName() +
                                " was clicked");
                    }
                }else if("76".equals(e.getActionCommand())){
                    if(b1.hasPce()){
                        System.out.println(b1.getPce() +
                               b1.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(b1.getBlockName() +
                                " was clicked");
                    }
                }else if("75".equals(e.getActionCommand())){
                    if(c1.hasPce()){
                        System.out.println(c1.getPce() +
                               c1.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(c1.getBlockName() +
                                " was clicked");
                    }
                }else if("74".equals(e.getActionCommand())){
                    if(d1.hasPce()){
                        System.out.println(d1.getPce() + 
                               d1.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(d1.getBlockName() +
                                " was clicked");
                    }
                }else if("73".equals(e.getActionCommand())){
                    if(e1.hasPce()){
                        System.out.println(e1.getPce() + 
                               e1.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(e1.getBlockName() +
                                " was clicked");
                    }
                }else if("72".equals(e.getActionCommand())){
                    if(f1.hasPce()){
                        System.out.println(f1.getPce() + 
                               f1.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(f1.getBlockName() + 
                                " was clicked");
                    }
                }else if("71".equals(e.getActionCommand())){
                    if(g1.hasPce()){
                        System.out.println(g1.getPce() + 
                               g1.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(g1.getBlockName() + 
                                " was clicked");
                    }
                }else if("70".equals(e.getActionCommand())){
                    if(h1.hasPce()){
                        System.out.println(h1.getPce() + 
                               h1.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(h1.getBlockName() + 
                                " was clicked");
                    }
                }else if("67".equals(e.getActionCommand())){
                    if(a2.hasPce()){
                        System.out.println(a2.getPce() + 
                               a2.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(a2.getBlockName() + 
                                " was clicked");
                    }
                }else if("66".equals(e.getActionCommand())){
                    if(b2.hasPce()){
                        System.out.println(b2.getPce() + 
                               b2.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(b2.getBlockName() + 
                                " was clicked");
                    }
                }else if("65".equals(e.getActionCommand())){
                    if(c2.hasPce()){
                        System.out.println(c2.getPce() + 
                               c2.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(c2.getBlockName() + 
                                " was clicked");
                    }
                }else if("64".equals(e.getActionCommand())){
                    if(d2.hasPce()){
                        System.out.println(d2.getPce() + 
                               d2.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(d2.getBlockName() + 
                                " was clicked");
                    }
                }else if("63".equals(e.getActionCommand())){
                    if(e2.hasPce()){
                        System.out.println(e2.getPce() + 
                               e2.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(e2.getBlockName() + 
                                " was clicked");
                    }
                }else if("62".equals(e.getActionCommand())){
                    if(f2.hasPce()){
                        System.out.println(f2.getPce() + 
                               f2.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(f2.getBlockName() + 
                                " was clicked");
                    }
                }else if("61".equals(e.getActionCommand())){
                    if(g2.hasPce()){
                        System.out.println(g2.getPce() + 
                               g2.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(g2.getBlockName() + 
                                " was clicked");
                    }
                }else if("60".equals(e.getActionCommand())){
                    if(h2.hasPce()){
                        System.out.println(h2.getPce() + 
                               h2.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(h2.getBlockName() + 
                                " was clicked");
                    }
                }else if("57".equals(e.getActionCommand())){
                    if(a3.hasPce()){
                        System.out.println(a3.getPce() + 
                               a3.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(a3.getBlockName() + 
                                " was clicked");
                    }
                }else if("56".equals(e.getActionCommand())){
                    if(b3.hasPce()){
                        System.out.println(b3.getPce() + 
                               b3.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(b3.getBlockName() + 
                                " was clicked");
                    }
                }else if("55".equals(e.getActionCommand())){
                    if(c3.hasPce()){
                        System.out.println(c3.getPce() + 
                               c3.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(c3.getBlockName() + 
                                " was clicked");
                    }
                }else if("54".equals(e.getActionCommand())){
                    if(d3.hasPce()){
                        System.out.println(d3.getPce() + 
                               d3.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(d3.getBlockName() + 
                                " was clicked");
                    }
                }else if("53".equals(e.getActionCommand())){
                    if(e3.hasPce()){
                        System.out.println(e3.getPce() + 
                               e3.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(e3.getBlockName() +
                                " was clicked");
                    }
                }else if("52".equals(e.getActionCommand())){
                    if(f3.hasPce()){
                        System.out.println(f3.getPce() + 
                               f3.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(f3.getBlockName() +
                                " was clicked");
                    }
                }else if("51".equals(e.getActionCommand())){
                    if(g3.hasPce()){
                        System.out.println(g3.getPce() +
                               g3.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(g3.getBlockName() + 
                                " was clicked");
                    }
                }else if("50".equals(e.getActionCommand())){
                    if(h3.hasPce()){
                        System.out.println(h3.getPce() +
                               h3.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(h3.getBlockName() +
                                " was clicked");
                    }
                }else if("47".equals(e.getActionCommand())){
                    if(a4.hasPce()){
                        System.out.println(a4.getPce() +
                               a4.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(a4.getBlockName() + 
                                " was clicked");
                    }
                }else if("46".equals(e.getActionCommand())){
                    if(b4.hasPce()){
                        System.out.println(b4.getPce() +
                               b4.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(b4.getBlockName() +
                                " was clicked");
                    }
                }else if("45".equals(e.getActionCommand())){
                    if(c4.hasPce()){
                        System.out.println(c4.getPce() +
                               c4.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(c4.getBlockName() +
                                " was clicked");
                    }
                }else if("44".equals(e.getActionCommand())){
                    if(d4.hasPce()){
                        System.out.println(d4.getPce() + 
                               d4.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(d4.getBlockName() + 
                                " was clicked");
                    }
                }else if("43".equals(e.getActionCommand())){
                    if(e4.hasPce()){
                        System.out.println(e4.getPce() + 
                               e4.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(e4.getBlockName() +
                                " was clicked");
                    }
                }else if("42".equals(e.getActionCommand())){
                    if(f4.hasPce()){
                        System.out.println(f4.getPce() +
                               f4.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(f4.getBlockName() +
                                " was clicked");
                    }
                }else if("41".equals(e.getActionCommand())){
                    if(g4.hasPce()){
                        System.out.println(g4.getPce() +
                               g4.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(g4.getBlockName() +
                                " was clicked");
                    }
                }else if("40".equals(e.getActionCommand())){
                    if(h4.hasPce()){
                        System.out.println(h4.getPce() + 
                               h4.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(h4.getBlockName() +
                                " was clicked");
                    }
                }else if("37".equals(e.getActionCommand())){
                    if(a5.hasPce()){
                        System.out.println(a5.getPce() + 
                               a5.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(a5.getBlockName() +
                                " was clicked");
                    }
                }else if("36".equals(e.getActionCommand())){
                    if(b5.hasPce()){
                        System.out.println(b5.getPce() +
                               b5.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(b5.getBlockName() +
                                " was clicked");
                    }
                }else if("35".equals(e.getActionCommand())){
                    if(c5.hasPce()){
                        System.out.println(c5.getPce() + 
                               c5.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(c5.getBlockName() +
                                " was clicked");
                    }
                }else if("34".equals(e.getActionCommand())){
                    if(d5.hasPce()){
                        System.out.println(d5.getPce() + 
                               d5.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(d5.getBlockName() + 
                                " was clicked");
                    }
                }else if("33".equals(e.getActionCommand())){
                    if(e5.hasPce()){
                        System.out.println(e5.getPce() +
                               e5.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(e5.getBlockName() +
                                " was clicked");
                    }
                }else if("32".equals(e.getActionCommand())){
                    if(f5.hasPce()){
                        System.out.println(f5.getPce() + 
                               f5.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(f5.getBlockName() +
                                " was clicked");
                    }
                }else if("31".equals(e.getActionCommand())){
                    if(g5.hasPce()){
                        System.out.println(g5.getPce() + 
                               g5.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(g5.getBlockName() + 
                                " was clicked");
                    }
                }else if("30".equals(e.getActionCommand())){
                    if(h5.hasPce()){
                        System.out.println(h5.getPce() + 
                               h5.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(h5.getBlockName() +
                                " was clicked");
                    }
                }else if("27".equals(e.getActionCommand())){
                    if(a6.hasPce()){
                        System.out.println(a6.getPce() +
                               a6.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(a6.getBlockName() +
                                " was clicked");
                    }
                }else if("26".equals(e.getActionCommand())){
                    if(b6.hasPce()){
                        System.out.println(b6.getPce() + 
                               b6.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(b6.getBlockName() +
                                " was clicked");
                    }
                }else if("25".equals(e.getActionCommand())){
                    if(c6.hasPce()){
                        System.out.println(c6.getPce() +
                               c6.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(c6.getBlockName() +
                                " was clicked");
                    }
                }else if("24".equals(e.getActionCommand())){
                    if(d6.hasPce()){
                        System.out.println(d6.getPce() + 
                               d6.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(d6.getBlockName() +
                                " was clicked");
                    }
                }else if("23".equals(e.getActionCommand())){
                    if(e6.hasPce()){
                        System.out.println(e6.getPce() +
                               e6.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(e6.getBlockName() +
                                " was clicked");
                    }
                }else if("22".equals(e.getActionCommand())){
                    if(f6.hasPce()){
                        System.out.println(f6.getPce() + 
                               f6.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(f6.getBlockName() + 
                                " was clicked");
                    }
                }else if("21".equals(e.getActionCommand())){
                    if(g6.hasPce()){
                        System.out.println(g6.getPce() + 
                               g6.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(g6.getBlockName() +
                                " was clicked");
                    }
                }else if("20".equals(e.getActionCommand())){
                    if(h6.hasPce()){
                        System.out.println(h6.getPce() + 
                               h6.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(h6.getBlockName() +
                                " was clicked");
                    }
                }else if("17".equals(e.getActionCommand())){
                    if(a7.hasPce()){
                        System.out.println(a7.getPce() + 
                               a7.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(a7.getBlockName() +
                                " was clicked");
                    }
                }else if("16".equals(e.getActionCommand())){
                    if(b7.hasPce()){
                        System.out.println(b7.getPce() + 
                               b7.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(b7.getBlockName() +
                                " was clicked");
                    }
                }else if("15".equals(e.getActionCommand())){
                    if(c7.hasPce()){
                        System.out.println(c7.getPce() +
                               c7.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(c7.getBlockName() + 
                                " was clicked");
                    }
                }else if("14".equals(e.getActionCommand())){
                    if(d7.hasPce()){
                        System.out.println(d7.getPce() +
                               d7.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(d7.getBlockName() +
                                " was clicked");
                    }
                }else if("13".equals(e.getActionCommand())){
                    if(e7.hasPce()){
                        System.out.println(e7.getPce() + 
                               e7.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(e7.getBlockName() + 
                                " was clicked");
                    }
                }else if("12".equals(e.getActionCommand())){
                    if(f7.hasPce()){
                        System.out.println(f7.getPce() +
                               f7.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(f7.getBlockName() +
                                " was clicked");
                    }
                }else if("11".equals(e.getActionCommand())){
                    if(g7.hasPce()){
                        System.out.println(g7.getPce() + 
                               g7.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(g7.getBlockName() +
                                " was clicked");
                    }
                }else if("10".equals(e.getActionCommand())){
                    if(h7.hasPce()){
                        System.out.println(h7.getPce() + 
                               h7.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(h7.getBlockName() +
                                " was clicked");
                    }
                }else if("07".equals(e.getActionCommand())){
                    if(a8.hasPce()){
                        System.out.println(a8.getPce() + 
                               a8.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(a8.getBlockName() +
                                " was clicked");
                    }
                }else if("06".equals(e.getActionCommand())){
                    if(b8.hasPce()){
                        System.out.println(b8.getPce() + 
                               b8.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(b8.getBlockName() + 
                                " was clicked");
                    }
                }else if("05".equals(e.getActionCommand())){
                    if(c8.hasPce()){
                        System.out.println(c8.getPce() + 
                               c8.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(c8.getBlockName() + 
                                " was clicked");
                    }
                }else if("04".equals(e.getActionCommand())){
                    if(d8.hasPce()){
                        System.out.println(d8.getPce() + 
                               d8.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(d8.getBlockName() +
                                " was clicked");
                    }
                }else if("03".equals(e.getActionCommand())){
                    if(e8.hasPce()){
                        System.out.println(e8.getPce() + 
                               e8.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(e8.getBlockName() + 
                                " was clicked");
                    }
                }else if("02".equals(e.getActionCommand())){
                    if(f8.hasPce()){
                        System.out.println(f8.getPce() + 
                               f8.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(f8.getBlockName() + 
                                " was clicked");
                    }
                }else if("01".equals(e.getActionCommand())){
                    if(g8.hasPce()){
                        System.out.println(g8.getPce() + 
                               g8.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(g8.getBlockName() + 
                                " was clicked");
                    }
                }else if("00".equals(e.getActionCommand())){
                    if(h8.hasPce()){
                        System.out.println(h8.getPce() + 
                               h8.getBlockName() + " was clicked.");
                    }else{
                        System.out.println(h8.getBlockName() + 
                                " was clicked");
                    }
                }
            }
        }
    }
    
}
