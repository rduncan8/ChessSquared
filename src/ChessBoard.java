import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;

public class ChessBoard extends Screen{
    
    public String blockName;
    public ChessBlock a1,a2,a3,a4,a5,a6,a7,a8,
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
    
    private final JFrame frame = createFrame("Chess");
    private final JPanel gui = new JPanel(new BorderLayout(3,3));
    private final JButton[][] chessBoardSquares = new JButton[8][8];
    private JPanel chessBoard;
    private final JLabel message = new JLabel("Chess Champ is ready to play!");
    private static final String COLS = "ABCDEFGH";
    private static final String ROWS = "12345678";
    
    ChessBoard() {
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
        
        setUpBoard();
        setUpPieces();
    }
    
    public void setUpBoard(){
        
        Insets buttonMargin = new Insets(0,0,0,0);
        for (int i = 0; i < chessBoardSquares.length; i++)
        {
            for (int j = 0; j < chessBoardSquares[i].length; j++)
            {
                JButton button = new JButton();
                button.setMargin(buttonMargin);             
                //button.addActionListener(new ChessBoard.MovePieceListener());
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
    
    
    public void setUpPieces(){
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
        
        ChessBlock nullArea;
        
        for (int i = 0; i < 8; i++){
            for (int j = 2; j < 6; j++) {
                block[i][j] = nullArea = new ChessBlock(COLS.substring(i, i + 1) + j);
            }
        }
        
        String squares;
        
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if(chessBoardSquares[i][j].getModel().isPressed()){

                    squares = Integer.toString(i) + Integer.toString(j);
                    
                    switch (squares){
                        case "00":  if(a1.hasPce){
                                        System.out.println(a1.getPce() + a1.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(a1.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                        
                        case "01":  if(a2.hasPce){
                                        System.out.println(a2.getPce() + a2.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(a2.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                        case "02":  if(a3.hasPce){
                                        System.out.println(a3.getPce() + a3.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(a3.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                        
                        case "03":  if(a4.hasPce){
                                        System.out.println(a4.getPce() + a4.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(a4.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                        case "04":  if(a5.hasPce){
                                        System.out.println(a5.getPce() + a5.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(a5.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                        
                        case "05":  if(a6.hasPce){
                                        System.out.println(a6.getPce() + a6.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(a6.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                            
                        case "06":  if(a7.hasPce){
                                        System.out.println(a7.getPce() + a7.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(a7.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                        
                        case "07":  if(a8.hasPce){
                                        System.out.println(a8.getPce() + a8.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(a8.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                            
                        case "10":  if(b1.hasPce){
                                        System.out.println(b1.getPce() + b1.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(b1.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                        
                        case "11":  if(b2.hasPce){
                                        System.out.println(b2.getPce() + b2.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(b2.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                        case "12":  if(b3.hasPce){
                                        System.out.println(b3.getPce() + b3.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(b3.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                        
                        case "13":  if(b4.hasPce){
                                        System.out.println(b4.getPce() + b4.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(b4.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                            
                        case "14":  if(b5.hasPce){
                                        System.out.println(b5.getPce() + b5.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(b5.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                        
                        case "15":  if(b6.hasPce){
                                        System.out.println(b6.getPce() + b6.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(b6.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                            
                        case "16":  if(b7.hasPce){
                                        System.out.println(b7.getPce() + b7.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(b7.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                        
                        case "17":  if(b8.hasPce){
                                        System.out.println(b8.getPce() + b8.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(b8.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                            
                        case "20":  if(c1.hasPce){
                                        System.out.println(c1.getPce() + c1.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(c1.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                        
                        case "21":  if(c2.hasPce){
                                        System.out.println(c2.getPce() + c2.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(c2.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                            
                        case "22":  if(c3.hasPce){
                                        System.out.println(c3.getPce() + c3.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(c3.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                        
                        case "23":  if(c4.hasPce){
                                        System.out.println(c4.getPce() + c4.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(c4.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                            
                        case "24":  if(c5.hasPce){
                                        System.out.println(c5.getPce() + c5.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(c5.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                        
                        case "25":  if(c6.hasPce){
                                        System.out.println(c6.getPce() + c6.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(c6.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                            
                        case "26":  if(c7.hasPce){
                                        System.out.println(c7.getPce() + c7.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(c7.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                        
                        case "27":  if(c8.hasPce){
                                        System.out.println(c8.getPce() + c8.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(c8.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                            
                        case "30":  if(d1.hasPce){
                                        System.out.println(d1.getPce() + d1.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(d1.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                        
                        case "31":  if(d2.hasPce){
                                        System.out.println(d2.getPce() + d2.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(d2.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                        case "32":  if(d3.hasPce){
                                        System.out.println(d3.getPce() + d3.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(d3.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                        
                        case "33":  if(d4.hasPce){
                                        System.out.println(d4.getPce() + d4.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(d4.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                        case "34":  if(d5.hasPce){
                                        System.out.println(d5.getPce() + d5.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(d5.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                        
                        case "35":  if(d6.hasPce){
                                        System.out.println(d6.getPce() + d6.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(d6.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                        case "36":  if(d7.hasPce){
                                        System.out.println(d7.getPce() + d7.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(d7.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                        
                        case "37":  if(d8.hasPce){
                                        System.out.println(d8.getPce() + d8.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(d8.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                          
                        case "40":  if(e1.hasPce){
                                        System.out.println(e1.getPce() + e1.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(e1.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                        
                        case "41":  if(e2.hasPce){
                                        System.out.println(e2.getPce() + e2.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(e2.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                            
                        case "42":  if(e3.hasPce){
                                        System.out.println(e3.getPce() + e3.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(e3.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                        
                        case "43":  if(e4.hasPce){
                                        System.out.println(e4.getPce() + e4.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(e4.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                            
                        case "44":  if(e5.hasPce){
                                        System.out.println(e5.getPce() + e5.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(e5.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                        
                        case "45":  if(e6.hasPce){
                                        System.out.println(e6.getPce() + e6.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(e6.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                            
                        case "46":  if(e7.hasPce){
                                        System.out.println(e7.getPce() + e7.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(e7.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                        
                        case "47":  if(e8.hasPce){
                                        System.out.println(e8.getPce() + e8.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(e8.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                            
                        case "50":  if(f1.hasPce){
                                        System.out.println(f1.getPce() + f1.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(f1.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                        
                        case "51":  if(f2.hasPce){
                                        System.out.println(f2.getPce() + f2.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(f2.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                            
                        case "52":  if(f3.hasPce){
                                        System.out.println(f3.getPce() + f3.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(f3.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                        
                        case "53":  if(f4.hasPce){
                                        System.out.println(f4.getPce() + f4.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(f4.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                            
                        case "54":  if(f5.hasPce){
                                        System.out.println(f5.getPce() + f5.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(f5.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                        
                        case "55":  if(f6.hasPce){
                                        System.out.println(f6.getPce() + f6.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(f6.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                            
                        case "56":  if(f7.hasPce){
                                        System.out.println(f7.getPce() + f7.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(f7.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                        
                        case "57":  if(f8.hasPce){
                                        System.out.println(f8.getPce() + f8.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(f8.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                        case "60":  if(g1.hasPce){
                                        System.out.println(g1.getPce() + g1.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(g1.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                        
                        case "61":  if(g2.hasPce){
                                        System.out.println(g2.getPce() + g2.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(g2.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                            
                        case "62":  if(g3.hasPce){
                                        System.out.println(g3.getPce() + g3.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(g3.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                        
                        case "63":  if(g4.hasPce){
                                        System.out.println(g4.getPce() + g4.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(g4.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                            
                        case "64":  if(g5.hasPce){
                                        System.out.println(g5.getPce() + g5.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(g5.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                        
                        case "65":  if(g6.hasPce){
                                        System.out.println(g6.getPce() + g6.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(g6.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                            
                        case "66":  if(g7.hasPce){
                                        System.out.println(g7.getPce() + g7.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(g7.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                        
                        case "67":  if(g8.hasPce){
                                        System.out.println(g8.getPce() + g8.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(g8.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                            
                        case "70":  if(h1.hasPce){
                                        System.out.println(h1.getPce() + h1.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(h1.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                        
                        case "71":  if(h2.hasPce){
                                        System.out.println(h2.getPce() + h2.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(h2.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                            
                        case "72":  if(h3.hasPce){
                                        System.out.println(h3.getPce() + h3.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(h3.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                        
                        case "73":  if(h4.hasPce){
                                        System.out.println(h4.getPce() + h4.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(h4.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                            
                        case "74":  if(h5.hasPce){
                                        System.out.println(h5.getPce() + h5.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(h5.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                        
                        case "75":  if(h6.hasPce){
                                        System.out.println(h6.getPce() + h6.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(h6.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                            
                        case "76":  if(h7.hasPce){
                                        System.out.println(h7.getPce() + h7.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(h7.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                        
                        case "77":  if(h8.hasPce){
                                        System.out.println(h8.getPce() + h8.getBlockName());
                                        chessBoardSquares[i][j].addActionListener
                                                    (new MovePieceListener());
                                    }else{
                                        System.out.println(h8.getBlockName());
                                        chessBoardSquares[j][j].addActionListener
                                                    (new MovePieceListener());
                                    }
                                    break;
                                
                    }
                }   
            }
        
        }
    }
    
    public class MovePieceListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.print(" was clicked");
        }
    }
    
}
