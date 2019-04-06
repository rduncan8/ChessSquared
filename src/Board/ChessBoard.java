package Board;

import Title.Screen;
import Players.Logic;
import Players.RealPlayer;
import Players.PlayerAI;
import Pieces.Queen;
import Pieces.King;
import Pieces.Knight;
import Pieces.Piece;
import Pieces.Pawn;
import Pieces.Bishop;
import Pieces.Rook;
import static Title.TitleScreen.startMultiplayerGame;
import static Title.TitleScreen.startSingleplayerGame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;

public class ChessBoard extends Screen
{
    public int turn;
    private ChessBlock selectedBlock = null;
    private final Color white = Color.WHITE;
    private final Color black = Color.BLACK;
    private final Color buttonColor1 = new Color(0, 130, 0);
    private final Color buttonColor2 = new Color(255, 255, 255);
    private ArrayList<ChessBlock> possibleMoves = new ArrayList<>();
    private ArrayList<ChessBlock> possibleCaptures = new ArrayList<>();
    private ArrayList<ChessBlock> dangerousBlocksForWhite = new ArrayList<>();
    private ArrayList<ChessBlock> dangerousBlocksForBlack = new ArrayList<>();
    private ArrayList<ArrayList<ChessBlock>> movesAndCaptures = new ArrayList<>();
    private boolean gameOver = false;
    
    private PlayerAI ai = null;
    private RealPlayer realPlayer;
    
    private static final JFrame frame = createFrame("Chess");
    private final JPanel gui = new JPanel(new BorderLayout(3,3));
    private final JButton[][] chessBoardSquares = new JButton[8][8];
    private JPanel theChessBoard;
    private JToolBar tools;
    private JLabel message = new JLabel("Let the games begin! When ready White go first.");
    private static final String wCOLS = "ABCDEFGH";
    private static final String bCOLS = "HGFEDCBA";
    private static final String ROWS = "12345678";
    
    public static ChessBlock[][] board = new ChessBlock[8][8];
    
    private final Piece[] whitePawns = new Pawn[8];
    private final Piece[] whiteRooks = new Rook[2];
    private final Piece[] whiteKnights = new Knight[2];
    private final Piece[] whiteBishops = new Bishop[2];
    private Piece whiteQueen;
    public static Piece whiteKing;
    public static ArrayList<Piece> whitePieces = new ArrayList<>();
    
    private final Piece[] blackPawns = new Pawn[8];
    private final Piece[] blackRooks = new Rook[2];
    private final Piece[] blackKnights = new Knight[2];
    private final Piece[] blackBishops = new Bishop[2];
    private Piece blackQueen;
    public static Piece blackKing;
    public static ArrayList<Piece> blackPieces = new ArrayList<>();
    
    public static boolean isWhiteKingInCheck = false;
    public static boolean isBlackKingInCheck = false;
    
    public static ArrayList<Piece> whitePawnPromotions = new ArrayList<>();
    public static ArrayList<Piece> blackPawnPromotions = new ArrayList<>();
    
    public ChessBoard(RealPlayer realPlayer, boolean withAI) 
    {
        this.realPlayer = realPlayer;
        createGUI(realPlayer.getColor());
        frame.add(gui);
        turn = 1;
        frame.setVisible(true);
        if (withAI)
        {
            if (realPlayer.getColor() == Color.WHITE)
            {
                ai = new PlayerAI(Color.BLACK);
            }
            else
            {
                ai = new PlayerAI(Color.WHITE);
            }
        }
        dangerousBlocksForWhite = Logic.getDangerousBlocksForWhite(board);
        dangerousBlocksForBlack = Logic.getDangerousBlocksForBlack(board);
        
        if (realPlayer.color == Color.BLACK)
        {
            switchTurn();
        }
    }
    
    public final void createGUI(Color color)
    {
        // set up the main GUI
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);
        
        updateGUI();
        
        theChessBoard = new JPanel(new GridLayout(0, 9));
        theChessBoard.setBorder(new LineBorder(Color.BLACK));
        gui.add(theChessBoard);
        
        setUpBoard(color);
        setUpPieces(color);
    }
    
    public void updateGUI()
    {
        tools.add(createButton("New Game", new newGameListener()));
        tools.addSeparator();
        tools.add(createButton("Resign", new ResignListener()));
        tools.addSeparator();
        tools.add(createButton("Rules", new RulesListener()));
        tools.addSeparator();
        tools.add(createButton("Exit", new ExitListener()));
        tools.addSeparator();
        tools.add(message);
    }
    
    public void setUpBoard(Color color)
    {    
        if(color == Color.WHITE)
        {
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
            
        }
        else if(color == Color.BLACK)
        {    
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
    
    public void setUpPieces(Color color)
    {    
        createBlocks(color);
        createPieces();
        putPiecesOnBlocks();
    }
    
    public void createBlocks(Color color)
    {
        if(color == Color.WHITE){
            board[0][0] = new ChessBlock("A1", chessBoardSquares[0][7], 0, 0, buttonColor1);
            board[1][0] = new ChessBlock("B1", chessBoardSquares[1][7], 1, 0, buttonColor2);
            board[2][0] = new ChessBlock("C1", chessBoardSquares[2][7], 2, 0, buttonColor1);
            board[3][0] = new ChessBlock("D1", chessBoardSquares[3][7], 3, 0, buttonColor2);
            board[4][0] = new ChessBlock("E1", chessBoardSquares[4][7], 4, 0, buttonColor1);
            board[5][0] = new ChessBlock("F1", chessBoardSquares[5][7], 5, 0, buttonColor2);
            board[6][0] = new ChessBlock("G1", chessBoardSquares[6][7], 6, 0, buttonColor1);
            board[7][0] = new ChessBlock("H1", chessBoardSquares[7][7], 7, 0, buttonColor2);
            
            board[0][1] = new ChessBlock("A2", chessBoardSquares[0][6], 0, 1, buttonColor2);
            board[1][1] = new ChessBlock("B2", chessBoardSquares[1][6], 1, 1, buttonColor1);
            board[2][1] = new ChessBlock("C2", chessBoardSquares[2][6], 2, 1, buttonColor2);
            board[3][1] = new ChessBlock("D2", chessBoardSquares[3][6], 3, 1, buttonColor1);
            board[4][1] = new ChessBlock("E2", chessBoardSquares[4][6], 4, 1, buttonColor2);
            board[5][1] = new ChessBlock("F2", chessBoardSquares[5][6], 5, 1, buttonColor1);
            board[6][1] = new ChessBlock("G2", chessBoardSquares[6][6], 6, 1, buttonColor2);
            board[7][1] = new ChessBlock("H2", chessBoardSquares[7][6], 7, 1, buttonColor1);
            
            board[0][2] = new ChessBlock("A3", chessBoardSquares[0][5], 0, 2, buttonColor1);
            board[1][2] = new ChessBlock("B3", chessBoardSquares[1][5], 1, 2, buttonColor2);
            board[2][2] = new ChessBlock("C3", chessBoardSquares[2][5], 2, 2, buttonColor1);
            board[3][2] = new ChessBlock("D3", chessBoardSquares[3][5], 3, 2, buttonColor2);
            board[4][2] = new ChessBlock("E3", chessBoardSquares[4][5], 4, 2, buttonColor1);
            board[5][2] = new ChessBlock("F3", chessBoardSquares[5][5], 5, 2, buttonColor2);
            board[6][2] = new ChessBlock("G3", chessBoardSquares[6][5], 6, 2, buttonColor1);
            board[7][2] = new ChessBlock("H3", chessBoardSquares[7][5], 7, 2, buttonColor2);
            
            board[0][3] = new ChessBlock("A4", chessBoardSquares[0][4], 0, 3, buttonColor2);
            board[1][3] = new ChessBlock("B4", chessBoardSquares[1][4], 1, 3, buttonColor1);
            board[2][3] = new ChessBlock("C4", chessBoardSquares[2][4], 2, 3, buttonColor2);
            board[3][3] = new ChessBlock("D4", chessBoardSquares[3][4], 3, 3, buttonColor1);
            board[4][3] = new ChessBlock("E4", chessBoardSquares[4][4], 4, 3, buttonColor2);
            board[5][3] = new ChessBlock("F4", chessBoardSquares[5][4], 5, 3, buttonColor1);
            board[6][3] = new ChessBlock("G4", chessBoardSquares[6][4], 6, 3, buttonColor2);
            board[7][3] = new ChessBlock("H4", chessBoardSquares[7][4], 7, 3, buttonColor1);
            
            board[0][4] = new ChessBlock("A5", chessBoardSquares[0][3], 0, 4, buttonColor1);
            board[1][4] = new ChessBlock("B5", chessBoardSquares[1][3], 1, 4, buttonColor2);
            board[2][4] = new ChessBlock("C5", chessBoardSquares[2][3], 2, 4, buttonColor1);
            board[3][4] = new ChessBlock("D5", chessBoardSquares[3][3], 3, 4, buttonColor2);
            board[4][4] = new ChessBlock("E5", chessBoardSquares[4][3], 4, 4, buttonColor1);
            board[5][4] = new ChessBlock("F5", chessBoardSquares[5][3], 5, 4, buttonColor2);
            board[6][4] = new ChessBlock("G5", chessBoardSquares[6][3], 6, 4, buttonColor1);
            board[7][4] = new ChessBlock("H5", chessBoardSquares[7][3], 7, 4, buttonColor2);
            
            board[0][5] = new ChessBlock("A6", chessBoardSquares[0][2], 0, 5, buttonColor2);
            board[1][5] = new ChessBlock("B6", chessBoardSquares[1][2], 1, 5, buttonColor1);
            board[2][5] = new ChessBlock("C6", chessBoardSquares[2][2], 2, 5, buttonColor2);
            board[3][5] = new ChessBlock("D6", chessBoardSquares[3][2], 3, 5, buttonColor1);
            board[4][5] = new ChessBlock("E6", chessBoardSquares[4][2], 4, 5, buttonColor2);
            board[5][5] = new ChessBlock("F6", chessBoardSquares[5][2], 5, 5, buttonColor1);
            board[6][5] = new ChessBlock("G6", chessBoardSquares[6][2], 6, 5, buttonColor2);
            board[7][5] = new ChessBlock("H6", chessBoardSquares[7][2], 7, 5, buttonColor1);
            
            board[0][6] = new ChessBlock("A7", chessBoardSquares[0][1], 0, 6, buttonColor1);
            board[1][6] = new ChessBlock("B7", chessBoardSquares[1][1], 1, 6, buttonColor2);
            board[2][6] = new ChessBlock("C7", chessBoardSquares[2][1], 2, 6, buttonColor1);
            board[3][6] = new ChessBlock("D7", chessBoardSquares[3][1], 3, 6, buttonColor2);
            board[4][6] = new ChessBlock("E7", chessBoardSquares[4][1], 4, 6, buttonColor1);
            board[5][6] = new ChessBlock("F7", chessBoardSquares[5][1], 5, 6, buttonColor2);
            board[6][6] = new ChessBlock("G7", chessBoardSquares[6][1], 6, 6, buttonColor1);
            board[7][6] = new ChessBlock("H7", chessBoardSquares[7][1], 7, 6, buttonColor2);
                                                      
            board[0][7] = new ChessBlock("A8", chessBoardSquares[0][0], 0, 7, buttonColor2);
            board[1][7] = new ChessBlock("B8", chessBoardSquares[1][0], 1, 7, buttonColor1);
            board[2][7] = new ChessBlock("C8", chessBoardSquares[2][0], 2, 7, buttonColor2);
            board[3][7] = new ChessBlock("D8", chessBoardSquares[3][0], 3, 7, buttonColor1);
            board[4][7] = new ChessBlock("E8", chessBoardSquares[4][0], 4, 7, buttonColor2);
            board[5][7] = new ChessBlock("F8", chessBoardSquares[5][0], 5, 7, buttonColor1);
            board[6][7] = new ChessBlock("G8", chessBoardSquares[6][0], 6, 7, buttonColor2);
            board[7][7] = new ChessBlock("H8", chessBoardSquares[7][0], 7, 7, buttonColor1);
        }
        else
        {    
            board[0][0] = new ChessBlock("A1", chessBoardSquares[7][0], 0, 0, buttonColor1);
            board[1][0] = new ChessBlock("B1", chessBoardSquares[6][0], 1, 0, buttonColor2);
            board[2][0] = new ChessBlock("C1", chessBoardSquares[5][0], 2, 0, buttonColor1);
            board[3][0] = new ChessBlock("D1", chessBoardSquares[4][0], 3, 0, buttonColor2);
            board[4][0] = new ChessBlock("E1", chessBoardSquares[3][0], 4, 0, buttonColor1);
            board[5][0] = new ChessBlock("F1", chessBoardSquares[2][0], 5, 0, buttonColor2);
            board[6][0] = new ChessBlock("G1", chessBoardSquares[1][0], 6, 0, buttonColor1);
            board[7][0] = new ChessBlock("H1", chessBoardSquares[0][0], 7, 0, buttonColor2);
            
            board[0][1] = new ChessBlock("A2", chessBoardSquares[7][1], 0, 1, buttonColor2);
            board[1][1] = new ChessBlock("B2", chessBoardSquares[6][1], 1, 1, buttonColor1);
            board[2][1] = new ChessBlock("C2", chessBoardSquares[5][1], 2, 1, buttonColor2);
            board[3][1] = new ChessBlock("D2", chessBoardSquares[4][1], 3, 1, buttonColor1);
            board[4][1] = new ChessBlock("E2", chessBoardSquares[3][1], 4, 1, buttonColor2);
            board[5][1] = new ChessBlock("F2", chessBoardSquares[2][1], 5, 1, buttonColor1);
            board[6][1] = new ChessBlock("G2", chessBoardSquares[1][1], 6, 1, buttonColor2);
            board[7][1] = new ChessBlock("H2", chessBoardSquares[0][1], 7, 1, buttonColor1);
            
            board[0][2] = new ChessBlock("A3", chessBoardSquares[7][2], 0, 2, buttonColor1);
            board[1][2] = new ChessBlock("B3", chessBoardSquares[6][2], 1, 2, buttonColor2);
            board[2][2] = new ChessBlock("C3", chessBoardSquares[5][2], 2, 2, buttonColor1);
            board[3][2] = new ChessBlock("D3", chessBoardSquares[4][2], 3, 2, buttonColor2);
            board[4][2] = new ChessBlock("E3", chessBoardSquares[3][2], 4, 2, buttonColor1);
            board[5][2] = new ChessBlock("F3", chessBoardSquares[2][2], 5, 2, buttonColor2);
            board[6][2] = new ChessBlock("G3", chessBoardSquares[1][2], 6, 2, buttonColor1);
            board[7][2] = new ChessBlock("H3", chessBoardSquares[0][2], 7, 2, buttonColor2);
            
            board[0][3] = new ChessBlock("A4", chessBoardSquares[7][3], 0, 3, buttonColor2);
            board[1][3] = new ChessBlock("B4", chessBoardSquares[6][3], 1, 3, buttonColor1);
            board[2][3] = new ChessBlock("C4", chessBoardSquares[5][3], 2, 3, buttonColor2);
            board[3][3] = new ChessBlock("D4", chessBoardSquares[4][3], 3, 3, buttonColor1);
            board[4][3] = new ChessBlock("E4", chessBoardSquares[3][3], 4, 3, buttonColor2);
            board[5][3] = new ChessBlock("F4", chessBoardSquares[2][3], 5, 3, buttonColor1);
            board[6][3] = new ChessBlock("G4", chessBoardSquares[1][3], 6, 3, buttonColor2);
            board[7][3] = new ChessBlock("H4", chessBoardSquares[0][3], 7, 3, buttonColor1);
            
            board[0][4] = new ChessBlock("A5", chessBoardSquares[7][4], 0, 4, buttonColor1);
            board[1][4] = new ChessBlock("B5", chessBoardSquares[6][4], 1, 4, buttonColor2);
            board[2][4] = new ChessBlock("C5", chessBoardSquares[5][4], 2, 4, buttonColor1);
            board[3][4] = new ChessBlock("D5", chessBoardSquares[4][4], 3, 4, buttonColor2);
            board[4][4] = new ChessBlock("E5", chessBoardSquares[3][4], 4, 4, buttonColor1);
            board[5][4] = new ChessBlock("F5", chessBoardSquares[2][4], 5, 4, buttonColor2);
            board[6][4] = new ChessBlock("G5", chessBoardSquares[1][4], 6, 4, buttonColor1);
            board[7][4] = new ChessBlock("H5", chessBoardSquares[0][4], 7, 4, buttonColor2);
            
            board[0][5] = new ChessBlock("A6", chessBoardSquares[7][5], 0, 5, buttonColor2);
            board[1][5] = new ChessBlock("B6", chessBoardSquares[6][5], 1, 5, buttonColor1);
            board[2][5] = new ChessBlock("C6", chessBoardSquares[5][5], 2, 5, buttonColor2);
            board[3][5] = new ChessBlock("D6", chessBoardSquares[4][5], 3, 5, buttonColor1);
            board[4][5] = new ChessBlock("E6", chessBoardSquares[3][5], 4, 5, buttonColor2);
            board[5][5] = new ChessBlock("F6", chessBoardSquares[2][5], 5, 5, buttonColor1);
            board[6][5] = new ChessBlock("G6", chessBoardSquares[1][5], 6, 5, buttonColor2);
            board[7][5] = new ChessBlock("H6", chessBoardSquares[0][5], 7, 5, buttonColor1);

            board[0][6] = new ChessBlock("A7", chessBoardSquares[7][6], 0, 6, buttonColor1);
            board[1][6] = new ChessBlock("B7", chessBoardSquares[6][6], 1, 6, buttonColor2);
            board[2][6] = new ChessBlock("C7", chessBoardSquares[5][6], 2, 6, buttonColor1);
            board[3][6] = new ChessBlock("D7", chessBoardSquares[4][6], 3, 6, buttonColor2);
            board[4][6] = new ChessBlock("E7", chessBoardSquares[3][6], 4, 6, buttonColor1);
            board[5][6] = new ChessBlock("F7", chessBoardSquares[2][6], 5, 6, buttonColor2);
            board[6][6] = new ChessBlock("G7", chessBoardSquares[1][6], 6, 6, buttonColor1);
            board[7][6] = new ChessBlock("H7", chessBoardSquares[0][6], 7, 6, buttonColor2);
            
            board[0][7] = new ChessBlock("A8", chessBoardSquares[7][7], 0, 7, buttonColor2);
            board[1][7] = new ChessBlock("B8", chessBoardSquares[6][7], 1, 7, buttonColor1);
            board[2][7] = new ChessBlock("C8", chessBoardSquares[5][7], 2, 7, buttonColor2);
            board[3][7] = new ChessBlock("D8", chessBoardSquares[4][7], 3, 7, buttonColor1);
            board[4][7] = new ChessBlock("E8", chessBoardSquares[3][7], 4, 7, buttonColor2);
            board[5][7] = new ChessBlock("F8", chessBoardSquares[2][7], 5, 7, buttonColor1);
            board[6][7] = new ChessBlock("G8", chessBoardSquares[1][7], 6, 7, buttonColor2);
            board[7][7] = new ChessBlock("H8", chessBoardSquares[0][7], 7, 7, buttonColor1);
        }
    }
    
    public void createPieces()
    {
        whitePawns[0] = new Pawn(white, board[0][1]);
        whitePawns[1] = new Pawn(white, board[1][1]);
        whitePawns[2] = new Pawn(white, board[2][1]);
        whitePawns[3] = new Pawn(white, board[3][1]);
        whitePawns[4] = new Pawn(white, board[4][1]);
        whitePawns[5] = new Pawn(white, board[5][1]);
        whitePawns[6] = new Pawn(white, board[6][1]);
        whitePawns[7] = new Pawn(white, board[7][1]);
        whiteRooks[0] = new Rook(white, board[0][0]); 
        whiteRooks[1] = new Rook(white, board[7][0]);
        whiteKnights[0] = new Knight(white, board[1][0]); 
        whiteKnights[1] = new Knight(white, board[6][0]);
        whiteBishops[0] = new Bishop(white, board[2][0]); 
        whiteBishops[1] = new Bishop(white, board[5][0]);
        whiteKing = new King(white, board[4][0]);
        whiteQueen = new Queen(white, board[3][0]);
        
        whitePieces.add(whitePawns[0]);
        whitePieces.add(whitePawns[1]);
        whitePieces.add(whitePawns[2]);
        whitePieces.add(whitePawns[3]);
        whitePieces.add(whitePawns[4]);
        whitePieces.add(whitePawns[5]);
        whitePieces.add(whitePawns[6]);
        whitePieces.add(whitePawns[7]);
        whitePieces.add(whiteRooks[0]);
        whitePieces.add(whiteRooks[1]);
        whitePieces.add(whiteKnights[0]);
        whitePieces.add(whiteKnights[1]);
        whitePieces.add(whiteBishops[0]);
        whitePieces.add(whiteBishops[1]);
        whitePieces.add(whiteQueen);
        whitePieces.add(whiteKing);
        
        blackPawns[0] = new Pawn(black, board[0][6]);
        blackPawns[1] = new Pawn(black, board[1][6]);
        blackPawns[2] = new Pawn(black, board[2][6]);
        blackPawns[3] = new Pawn(black, board[3][6]);
        blackPawns[4] = new Pawn(black, board[4][6]);
        blackPawns[5] = new Pawn(black, board[5][6]);
        blackPawns[6] = new Pawn(black, board[6][6]);
        blackPawns[7] = new Pawn(black, board[7][6]);
        blackRooks[0] = new Rook(black, board[0][7]); 
        blackRooks[1] = new Rook(black, board[7][7]);
        blackKnights[0] = new Knight(black, board[1][7]); 
        blackKnights[1] = new Knight(black, board[6][7]);
        blackBishops[0] = new Bishop(black, board[2][7]); 
        blackBishops[1] = new Bishop(black, board[5][7]);
        blackKing = new King(black, board[4][7]);
        blackQueen = new Queen(black, board[3][7]);
        
        blackPieces.add(blackPawns[0]);
        blackPieces.add(blackPawns[1]);
        blackPieces.add(blackPawns[2]);
        blackPieces.add(blackPawns[3]);
        blackPieces.add(blackPawns[4]);
        blackPieces.add(blackPawns[5]);
        blackPieces.add(blackPawns[6]);
        blackPieces.add(blackPawns[7]);
        blackPieces.add(blackRooks[0]);
        blackPieces.add(blackRooks[1]);
        blackPieces.add(blackKnights[0]);
        blackPieces.add(blackKnights[1]);
        blackPieces.add(blackBishops[0]);
        blackPieces.add(blackBishops[1]);
        blackPieces.add(blackQueen);
        blackPieces.add(blackKing);
    }
    
    public void putPiecesOnBlocks()
    {
        board[0][0].setPiece(whiteRooks[0]);
        board[1][0].setPiece(whiteKnights[0]);
        board[2][0].setPiece(whiteBishops[0]);
        board[3][0].setPiece(whiteQueen);
        board[4][0].setPiece(whiteKing);
        board[5][0].setPiece(whiteBishops[1]);
        board[6][0].setPiece(whiteKnights[1]);
        board[7][0].setPiece(whiteRooks[1]);
        
        board[0][1].setPiece(whitePawns[0]);
        board[1][1].setPiece(whitePawns[1]);
        board[2][1].setPiece(whitePawns[2]);
        board[3][1].setPiece(whitePawns[3]);
        board[4][1].setPiece(whitePawns[4]);
        board[5][1].setPiece(whitePawns[5]);
        board[6][1].setPiece(whitePawns[6]);
        board[7][1].setPiece(whitePawns[7]);
        
        board[0][6].setPiece(blackPawns[0]);
        board[1][6].setPiece(blackPawns[1]);
        board[2][6].setPiece(blackPawns[2]);
        board[3][6].setPiece(blackPawns[3]);
        board[4][6].setPiece(blackPawns[4]);
        board[5][6].setPiece(blackPawns[5]);
        board[6][6].setPiece(blackPawns[6]);
        board[7][6].setPiece(blackPawns[7]);
        
        board[0][7].setPiece(blackRooks[0]);
        board[1][7].setPiece(blackKnights[0]);
        board[2][7].setPiece(blackBishops[0]);
        board[3][7].setPiece(blackQueen);
        board[4][7].setPiece(blackKing);
        board[5][7].setPiece(blackBishops[1]);
        board[6][7].setPiece(blackKnights[1]);
        board[7][7].setPiece(blackRooks[1]);
    }
    
    private boolean isPieceSelected()
    {
        return selectedBlock != null;
    }
    
    private void blockClicked(ChessBlock chessBlock)
    {
        if (!gameOver)
        {
            if (chessBlock.hasPiece())
            {
                if (isPieceSelected() && chessBlock != selectedBlock && possibleCaptures.contains(chessBlock))
                {
                    realPlayer.capturePiece(board, selectedBlock, chessBlock);
                    clearPossibleMovesAndCaptures();
                    switchTurn();
                }
                else if ((realPlayer.getColor() == chessBlock.getPiece().color && turn == 1) || (realPlayer.getColor() != chessBlock.getPiece().color && turn == 2)) //(currentPlayer.getColor() == chessBlock.getPiece().color) DO NOT DELETE
                {
                    selectBlock(chessBlock);
                }
            }
            else
            {
                if (isPieceSelected())
                {
                    if (chessBlock != selectedBlock)
                    {
                        if (possibleMoves.contains(chessBlock))
                        {
                            realPlayer.moveToBlock(board, selectedBlock, chessBlock);
                            clearPossibleMovesAndCaptures();
                            switchTurn();
                        }
                        
                        if (possibleCaptures.contains(chessBlock))
                        {
                            realPlayer.capturePiece(board, selectedBlock, chessBlock);
                            clearPossibleMovesAndCaptures();
                            switchTurn();
                        }
                    }
                }
            }
        }
    }
    
    private void selectBlock(ChessBlock chessBlock)
    {
        if (selectedBlock != null)
        {
            selectedBlock.setSelectedPieceButtonColor(false);
        }
        selectedBlock = chessBlock;
        selectedBlock.setSelectedPieceButtonColor(true);
        getPossibleMovesAndCaptures(selectedBlock);
    }
    
    private void promote(ChessBlock block)
    {
        Object[] options = {"Queen", "Rook", "Knight", "Bishop"};
        
        int dialogResult = JOptionPane.showOptionDialog(null,
                "Please selected a piece you would like to promote to ",
                "Pick a piece.", JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.YES_NO_CANCEL_OPTION,
                null, options, options[0]);
        
        if (block.getPiece().color == Color.WHITE)
        {
            if (dialogResult == 0 || dialogResult == JOptionPane.CLOSED_OPTION)
            {
                whitePieces.remove(block.getPiece());
                block.setPiece(new Queen(Color.WHITE, block));
                whitePieces.add(block.getPiece());
            }
            else if (dialogResult == 1)
            {
                whitePieces.remove(block.getPiece());
                block.setPiece(new Rook(Color.WHITE, block));
                whitePieces.add(block.getPiece());
            }
            else if (dialogResult == 2)
            {
                whitePieces.remove(block.getPiece());
                block.setPiece(new Knight(Color.WHITE, block));
                whitePieces.add(block.getPiece());
            }
            else if (dialogResult == 3)
            {
                whitePieces.remove(block.getPiece());
                block.setPiece(new Bishop(Color.WHITE, block));
                whitePieces.remove(block.getPiece());
            }
        }
        else
        {
            if (dialogResult == 0 || dialogResult == JOptionPane.CLOSED_OPTION)
            {
                blackPieces.remove(block.getPiece());
                block.setPiece(new Queen(Color.BLACK, block));
                blackPieces.add(block.getPiece());
            }
            else if (dialogResult == 1)
            {
                blackPieces.remove(block.getPiece());
                block.setPiece(new Rook(Color.BLACK, block));
                blackPieces.add(block.getPiece());
            }
            else if (dialogResult == 2)
            {
                blackPieces.remove(block.getPiece());
                block.setPiece(new Knight(Color.BLACK, block));
                blackPieces.add(block.getPiece());
            }
            else if (dialogResult == 3)
            {
                blackPieces.remove(block.getPiece());
                block.setPiece(new Bishop(Color.BLACK, block));
                blackPieces.remove(block.getPiece());
            }
        }
    }
    
    private void switchTurn()
    {
        dangerousBlocksForWhite.clear();
        dangerousBlocksForBlack.clear();
        dangerousBlocksForWhite = Logic.getDangerousBlocksForWhite(board);
        dangerousBlocksForBlack = Logic.getDangerousBlocksForBlack(board);
        
        if (whitePawnPromotions.size() > 0)
        {
            promote(whitePawnPromotions.get(0).currentPosition);
            whitePawnPromotions.clear();
        }
        
        if (blackPawnPromotions.size() > 0)
        {
            promote(blackPawnPromotions.get(0).currentPosition);
            blackPawnPromotions.clear();
        }
        
        if (isWhiteKingInCheck)
        {
            isWhiteKingInCheck = false;
        }
        
        if (isBlackKingInCheck)
        {
            isBlackKingInCheck = false;
        }
        
        if (turn == 1)
        {
            turn = 2;
            
            if (realPlayer.color == Color.WHITE)
            {
                if (dangerousBlocksForBlack.contains(blackKing.currentPosition))
                {
                    setBlackKingInCheck();
                }
                whitePieces.forEach(piece -> piece.enpassantCaptures.clear());
            }
            else
            {
                if (dangerousBlocksForWhite.contains(whiteKing.currentPosition))
                {
                    setWhiteKingInCheck();
                }  
                blackPieces.forEach(piece -> piece.enpassantCaptures.clear());
            }
            if (ai != null)
            {
                board = ai.makeMove(board, dangerousBlocksForWhite, dangerousBlocksForBlack);
                switchTurn();
                if (realPlayer.color == Color.WHITE)
                {
                    tools.removeAll();
                    message = new JLabel("Player White's turn.                    "
                            + "                                        ");
                    updateGUI();
                }
                else
                {
                    tools.removeAll();
                    message = new JLabel("Player Black's turn.                    "
                            + "                                        ");
                    updateGUI();
                }
            }
            else
            {
                tools.removeAll();
                message = new JLabel("Player Black's turn.                    "
                        + "                                        ");
                updateGUI();
            }
        }
        else
        {
            turn = 1;
            tools.removeAll();
            message = new JLabel("Player White's turn.                    "
                    + "                                        ");
            updateGUI();
            if (realPlayer.color == Color.WHITE)
            {
                if (dangerousBlocksForWhite.contains(whiteKing.currentPosition))
                {
                    setWhiteKingInCheck();
                }
                blackPieces.forEach(piece -> piece.enpassantCaptures.clear());
            }
            else
            {
                if (dangerousBlocksForBlack.contains(blackKing.currentPosition))
                {
                    setBlackKingInCheck();
                }
                whitePieces.forEach(piece -> piece.enpassantCaptures.clear());
            }
        }
    }
    
    private void setWhiteKingInCheck()
    {
        tools.removeAll();
        message = new JLabel("Player White's turn. Check.");
        updateGUI();
        
        isWhiteKingInCheck = true;
        ArrayList<ArrayList<ChessBlock>> possibleWhiteMoves = new ArrayList<>();
        
        for (Piece piece : whitePieces)
        {
            getPossibleMovesAndCaptures(piece.currentPosition);
            
            if (possibleMoves.size() > 0)
            {
                possibleWhiteMoves.add(possibleMoves);
            }
            
            if (possibleCaptures.size() > 0)
            {
                possibleWhiteMoves.add(possibleCaptures);
            }
            
            clearPossibleMovesAndCaptures();
        }
        
        if (possibleWhiteMoves.size() == 0)
        {
            tools.removeAll();
            message = new JLabel("White king is in Checkmate, Black wins!");
            updateGUI();
            
            gameOver = true;
            for (int y = 0; y < 8; y++)
            {
                for (int x = 0; x < 8; x++)
                {
                    board[x][y].setWinnerButtonColor(gameOver, Color.BLACK);
                }
            }
        }
    }
    
    private void setBlackKingInCheck()
    {
        tools.removeAll();
        message = new JLabel("Player Black's turn. Check.");
        updateGUI();
        
        isBlackKingInCheck = true;
        ArrayList<ArrayList<ChessBlock>> possibleBlackMoves = new ArrayList<>();
        
        for (Piece piece : blackPieces)
        {
            getPossibleMovesAndCaptures(piece.currentPosition);
            
            if (possibleMoves.size() > 0)
            {
                possibleBlackMoves.add(possibleMoves);
            }
            
            if (possibleCaptures.size() > 0)
            {
                possibleBlackMoves.add(possibleCaptures);
            }
            
            clearPossibleMovesAndCaptures();
        }
        
        if (possibleBlackMoves.size() == 0)
        {
            tools.removeAll();
            message = new JLabel("Black king is in Checkmate, White wins!");
            updateGUI();
            
            gameOver = true;
            for (int y = 0; y < 8; y++)
            {
                for (int x = 0; x < 8; x++)
                {
                    board[x][y].setWinnerButtonColor(gameOver, Color.WHITE);
                }
            }
        }
    }
    
    public static boolean isKingInCheck()
    {
        return isWhiteKingInCheck || isBlackKingInCheck;
    }
    
    private void clearPossibleMovesAndCaptures()
    {
        possibleMoves.forEach(block -> block.setPossibleMoveButtonColor(false));
        possibleMoves.clear();
        possibleCaptures.forEach(block -> block.setPossibleCaptureButtonColor(false));
        possibleCaptures.clear();
    }
    
    private void getPossibleMovesAndCaptures(ChessBlock selectedBlock)
    {
        clearPossibleMovesAndCaptures();
        
        movesAndCaptures = Logic.getPossibleMovesAndCaptures(board, selectedBlock, dangerousBlocksForWhite, dangerousBlocksForBlack);
        
        if (movesAndCaptures.size() > 0)
        {
            possibleMoves = movesAndCaptures.get(0);
            possibleMoves.forEach(block -> block.setPossibleMoveButtonColor(true));
        }
        
        if (movesAndCaptures.size() > 1)
        {
            possibleCaptures = movesAndCaptures.get(1);
            possibleCaptures.forEach(block -> block.setPossibleCaptureButtonColor(true));
        }
    }
    
    public class newGameListener implements ActionListener
    {
        
        @Override
        public void actionPerformed(ActionEvent e)
        {
            int dialogResult = JOptionPane.showConfirmDialog(null, 
                    "Are you sure you want to start a new game?", "New Game", JOptionPane.YES_NO_OPTION);
            
            if (dialogResult == 0)
            {
                Object[] gameOptions = {"Singleplayer", "Multiplayer", "Cancel"};
                Object[] playerOptions = {"Player", "Computer", "Cancel"};
                Object[] colorOptions = {"White", "Black", "Cancel"};
            
                int gameDialogResult = JOptionPane.showOptionDialog(null,
                    "Would you like to play singlerplayer or multiplayer?",
                    "Single or Multiplayer", JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.YES_NO_CANCEL_OPTION, null, gameOptions, gameOptions[0]);
                
                int colorDialogResult;
                
                if (gameDialogResult == 0){
                    colorDialogResult = JOptionPane.showOptionDialog(null, 
                        "Would you like to play White or Black?",
                        "Pick a color", JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.YES_NO_CANCEL_OPTION, null, colorOptions, colorOptions[0]);
                    if (colorDialogResult == 0){
                        
                    }
                }
                else if (gameDialogResult == 1)
                    startMultiplayerGame();
            }
        }
    }
    
    public class ResignListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            int dialogResult = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to resign?", "Resign", JOptionPane.YES_NO_OPTION);
            
            if(dialogResult == 0)
            {
                if (turn == 1)
                    JOptionPane.showMessageDialog(frame, "Congratulations Black! You win!");
                else if (turn == 2)
                    JOptionPane.showMessageDialog(frame, "Congratulations White! You win!");
                
                System.exit(0);
            }
        } 
    }
    
    public static class RulesListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            JOptionPane.showMessageDialog(frame, "The standard rules of Chess are as "
                    + "follows:\n       • Eight white/black Pawns (a2-h2),"
                    + " two white/black Bishops (c1 and f1),\n two white/black "
                    + "Knights (b1 and g1), two white/black Rooks (a1 and h1), \n "
                    + "one white/black Queen (d1), and one white/black King (e1).\n"
                    + "       • Pawns move one block forward at a time, and cannot go backward "
                    + "(on first move it can advance \n two spaces forward).\n       "
                    + "• Bishops move back and forth diagonally.\n       • Knights move back "
                    + "and forth in an “L” shape.\n       • Rooks move back and forth, "
                    + "left and right, but not diagonally.\n       • Queens move back "
                    + "and forth, left and right, and diagonally.\n       • Kings move back and "
                    + "forth, left and right, and diagonally, but only one space "
                    + "at a time.\n       • Whoever plays as White always go first. "
                    + "\n       • To win, one player must put their opponent in “checkmate”."
                    + "\n       • Checkmate occurs when one player puts the enemy"
                    + "king in “check” and the player whose king is in check\ncannot avoid getting "
                    + "their king out of “check” within the next move.\n       • Check happens when you "
                    + "can capture the enemy king on your next turn with one of your pieces.");
        }
    }
    
    public class ExitListener implements ActionListener
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
    
    public class MovePieceListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (realPlayer.getColor() == Color.WHITE){
                if("00".equals(e.getActionCommand())){
                    blockClicked(board[0][0]);
                }else if("01".equals(e.getActionCommand())){
                    blockClicked(board[1][0]);
                }else if("02".equals(e.getActionCommand())){
                    blockClicked(board[2][0]);
                }else if("03".equals(e.getActionCommand())){
                    blockClicked(board[3][0]);
                }else if("04".equals(e.getActionCommand())){
                    blockClicked(board[4][0]);
                }else if("05".equals(e.getActionCommand())){
                    blockClicked(board[5][0]);
                }else if("06".equals(e.getActionCommand())){
                    blockClicked(board[6][0]);
                }else if("07".equals(e.getActionCommand())){
                    blockClicked(board[7][0]);
                }else if("10".equals(e.getActionCommand())){
                    blockClicked(board[0][1]);
                }else if("11".equals(e.getActionCommand())){
                    blockClicked(board[1][1]);
                }else if("12".equals(e.getActionCommand())){
                    blockClicked(board[2][1]);
                }else if("13".equals(e.getActionCommand())){
                    blockClicked(board[3][1]);
                }else if("14".equals(e.getActionCommand())){
                    blockClicked(board[4][1]);
                }else if("15".equals(e.getActionCommand())){
                    blockClicked(board[5][1]);
                }else if("16".equals(e.getActionCommand())){
                    blockClicked(board[6][1]);
                }else if("17".equals(e.getActionCommand())){
                    blockClicked(board[7][1]);
                }else if("20".equals(e.getActionCommand())){
                    blockClicked(board[0][2]);
                }else if("21".equals(e.getActionCommand())){
                    blockClicked(board[1][2]);
                }else if("22".equals(e.getActionCommand())){
                    blockClicked(board[2][2]);
                }else if("23".equals(e.getActionCommand())){
                    blockClicked(board[3][2]);
                }else if("24".equals(e.getActionCommand())){
                    blockClicked(board[4][2]);
                }else if("25".equals(e.getActionCommand())){
                    blockClicked(board[5][2]);
                }else if("26".equals(e.getActionCommand())){
                    blockClicked(board[6][2]);
                }else if("27".equals(e.getActionCommand())){
                    blockClicked(board[7][2]);
                }else if("30".equals(e.getActionCommand())){
                    blockClicked(board[0][3]);
                }else if("31".equals(e.getActionCommand())){
                    blockClicked(board[1][3]);
                }else if("32".equals(e.getActionCommand())){
                    blockClicked(board[2][3]);
                }else if("33".equals(e.getActionCommand())){
                    blockClicked(board[3][3]);
                }else if("34".equals(e.getActionCommand())){
                    blockClicked(board[4][3]);
                }else if("35".equals(e.getActionCommand())){
                    blockClicked(board[5][3]);
                }else if("36".equals(e.getActionCommand())){
                    blockClicked(board[6][3]);
                }else if("37".equals(e.getActionCommand())){
                    blockClicked(board[7][3]);
                }else if("40".equals(e.getActionCommand())){
                    blockClicked(board[0][4]);
                }else if("41".equals(e.getActionCommand())){
                    blockClicked(board[1][4]);
                }else if("42".equals(e.getActionCommand())){
                    blockClicked(board[2][4]);
                }else if("43".equals(e.getActionCommand())){
                    blockClicked(board[3][4]);
                }else if("44".equals(e.getActionCommand())){
                    blockClicked(board[4][4]);
                }else if("45".equals(e.getActionCommand())){
                    blockClicked(board[5][4]);
                }else if("46".equals(e.getActionCommand())){
                    blockClicked(board[6][4]);
                }else if("47".equals(e.getActionCommand())){
                    blockClicked(board[7][4]);
                }else if("50".equals(e.getActionCommand())){
                    blockClicked(board[0][5]);
                }else if("51".equals(e.getActionCommand())){
                    blockClicked(board[1][5]);
                }else if("52".equals(e.getActionCommand())){
                    blockClicked(board[2][5]);
                }else if("53".equals(e.getActionCommand())){
                    blockClicked(board[3][5]);
                }else if("54".equals(e.getActionCommand())){
                    blockClicked(board[4][5]);
                }else if("55".equals(e.getActionCommand())){
                    blockClicked(board[5][5]);
                }else if("56".equals(e.getActionCommand())){
                    blockClicked(board[6][5]);
                }else if("57".equals(e.getActionCommand())){
                    blockClicked(board[7][5]);
                }else if("60".equals(e.getActionCommand())){
                    blockClicked(board[0][6]);
                }else if("61".equals(e.getActionCommand())){
                    blockClicked(board[1][6]);
                }else if("62".equals(e.getActionCommand())){
                    blockClicked(board[2][6]);
                }else if("63".equals(e.getActionCommand())){
                    blockClicked(board[3][6]);
                }else if("64".equals(e.getActionCommand())){
                    blockClicked(board[4][6]);
                }else if("65".equals(e.getActionCommand())){
                    blockClicked(board[5][6]);
                }else if("66".equals(e.getActionCommand())){
                    blockClicked(board[6][6]);
                }else if("67".equals(e.getActionCommand())){
                    blockClicked(board[7][6]);
                }else if("70".equals(e.getActionCommand())){
                    blockClicked(board[0][7]);
                }else if("71".equals(e.getActionCommand())){
                    blockClicked(board[1][7]);
                }else if("72".equals(e.getActionCommand())){
                    blockClicked(board[2][7]);
                }else if("73".equals(e.getActionCommand())){
                    blockClicked(board[3][7]);
                }else if("74".equals(e.getActionCommand())){
                    blockClicked(board[4][7]);
                }else if("75".equals(e.getActionCommand())){
                    blockClicked(board[5][7]);
                }else if("76".equals(e.getActionCommand())){
                    blockClicked(board[6][7]);
                }else if("77".equals(e.getActionCommand())){
                    blockClicked(board[7][7]);
                }
            }else// if (player == 2){
                
                if("77".equals(e.getActionCommand())){
                    blockClicked(board[0][0]);
                }else if("76".equals(e.getActionCommand())){
                    blockClicked(board[1][0]);
                }else if("75".equals(e.getActionCommand())){
                    blockClicked(board[2][0]);
                }else if("74".equals(e.getActionCommand())){
                    blockClicked(board[3][0]);
                }else if("73".equals(e.getActionCommand())){
                    blockClicked(board[4][0]);
                }else if("72".equals(e.getActionCommand())){
                    blockClicked(board[5][0]);
                }else if("71".equals(e.getActionCommand())){
                    blockClicked(board[6][0]);
                }else if("70".equals(e.getActionCommand())){
                    blockClicked(board[7][0]);
                }else if("67".equals(e.getActionCommand())){
                    blockClicked(board[0][1]);
                }else if("66".equals(e.getActionCommand())){
                    blockClicked(board[1][1]);
                }else if("65".equals(e.getActionCommand())){
                    blockClicked(board[2][1]);
                }else if("64".equals(e.getActionCommand())){
                    blockClicked(board[3][1]);
                }else if("63".equals(e.getActionCommand())){
                    blockClicked(board[4][1]);
                }else if("62".equals(e.getActionCommand())){
                    blockClicked(board[5][1]);
                }else if("61".equals(e.getActionCommand())){
                    blockClicked(board[6][1]);
                }else if("60".equals(e.getActionCommand())){
                    blockClicked(board[7][1]);
                }else if("57".equals(e.getActionCommand())){
                    blockClicked(board[0][2]);
                }else if("56".equals(e.getActionCommand())){
                    blockClicked(board[1][2]);
                }else if("55".equals(e.getActionCommand())){
                    blockClicked(board[2][2]);
                }else if("54".equals(e.getActionCommand())){
                    blockClicked(board[3][2]);
                }else if("53".equals(e.getActionCommand())){
                    blockClicked(board[4][2]);
                }else if("52".equals(e.getActionCommand())){
                    blockClicked(board[5][2]);
                }else if("51".equals(e.getActionCommand())){
                    blockClicked(board[6][2]);
                }else if("50".equals(e.getActionCommand())){
                    blockClicked(board[7][2]);
                }else if("47".equals(e.getActionCommand())){
                    blockClicked(board[0][3]);
                }else if("46".equals(e.getActionCommand())){
                    blockClicked(board[1][3]);
                }else if("45".equals(e.getActionCommand())){
                    blockClicked(board[2][3]);
                }else if("44".equals(e.getActionCommand())){
                    blockClicked(board[3][3]);
                }else if("43".equals(e.getActionCommand())){
                    blockClicked(board[4][3]);
                }else if("42".equals(e.getActionCommand())){
                    blockClicked(board[5][3]);
                }else if("41".equals(e.getActionCommand())){
                    blockClicked(board[6][3]);
                }else if("40".equals(e.getActionCommand())){
                    blockClicked(board[7][3]);
                }else if("37".equals(e.getActionCommand())){
                    blockClicked(board[0][4]);
                }else if("36".equals(e.getActionCommand())){
                    blockClicked(board[1][4]);
                }else if("35".equals(e.getActionCommand())){
                    blockClicked(board[2][4]);
                }else if("34".equals(e.getActionCommand())){
                    blockClicked(board[3][4]);
                }else if("33".equals(e.getActionCommand())){
                    blockClicked(board[4][4]);
                }else if("32".equals(e.getActionCommand())){
                    blockClicked(board[5][4]);
                }else if("31".equals(e.getActionCommand())){
                    blockClicked(board[6][4]);
                }else if("30".equals(e.getActionCommand())){
                    blockClicked(board[7][4]);
                }else if("27".equals(e.getActionCommand())){
                    blockClicked(board[0][5]);
                }else if("26".equals(e.getActionCommand())){
                    blockClicked(board[1][5]);
                }else if("25".equals(e.getActionCommand())){
                    blockClicked(board[2][5]);
                }else if("24".equals(e.getActionCommand())){
                    blockClicked(board[3][5]);
                }else if("23".equals(e.getActionCommand())){
                    blockClicked(board[4][5]);
                }else if("22".equals(e.getActionCommand())){
                    blockClicked(board[5][5]);
                }else if("21".equals(e.getActionCommand())){
                    blockClicked(board[6][5]);
                }else if("20".equals(e.getActionCommand())){
                    blockClicked(board[7][5]);
                }else if("17".equals(e.getActionCommand())){
                    blockClicked(board[0][6]);
                }else if("16".equals(e.getActionCommand())){
                    blockClicked(board[1][6]);
                }else if("15".equals(e.getActionCommand())){
                    blockClicked(board[2][6]);
                }else if("14".equals(e.getActionCommand())){
                    blockClicked(board[3][6]);
                }else if("13".equals(e.getActionCommand())){
                    blockClicked(board[4][6]);
                }else if("12".equals(e.getActionCommand())){
                    blockClicked(board[5][6]);
                }else if("11".equals(e.getActionCommand())){
                    blockClicked(board[6][6]);
                }else if("10".equals(e.getActionCommand())){
                    blockClicked(board[7][6]);
                }else if("07".equals(e.getActionCommand())){
                    blockClicked(board[0][7]);
                }else if("06".equals(e.getActionCommand())){
                    blockClicked(board[1][7]);
                }else if("05".equals(e.getActionCommand())){
                    blockClicked(board[2][7]);
                }else if("04".equals(e.getActionCommand())){
                    blockClicked(board[3][7]);
                }else if("03".equals(e.getActionCommand())){
                    blockClicked(board[4][7]);
                }else if("02".equals(e.getActionCommand())){
                    blockClicked(board[5][7]);
                }else if("01".equals(e.getActionCommand())){
                    blockClicked(board[6][7]);
                }else if("00".equals(e.getActionCommand())){
                    blockClicked(board[7][7]);
                }
            }
        }
}
