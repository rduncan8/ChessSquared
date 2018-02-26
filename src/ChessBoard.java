

public class ChessBoard {
    
    public String data;
    public ChessBlock a1,a2,a3,a4,a5,a6,a7,a8,
                      b1,b2,b3,b4,b5,b6,b7,b8,
                      c1,c2,c3,c4,c5,c6,c7,c8,
                      d1,d2,d3,d4,d5,d6,d7,d8,
                      e1,e2,e3,e4,e5,e6,e7,e8,
                      f1,f2,f3,f4,f5,f6,f7,f8,
                      g1,g2,g3,g4,g5,g6,g7,g8,
                      h1,h2,h3,h4,h5,h6,h7,h8;
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
    
    public void setUp(){
        
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
        
        ChessBlock nullArea = new ChessBlock();
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
        
        a1 = new ChessBlock(new Piece(1, a1, wR1));
        b1 = new ChessBlock(new Piece(1, wKn1));
        c1 = new ChessBlock(new Piece(1, wB1));
        d1 = new ChessBlock(new Piece(1, wQ));
        e1 = new ChessBlock(new Piece(1, e1, wK));
        f1 = new ChessBlock(new Piece(1, wB2));
        g1 = new ChessBlock(new Piece(1, wKn2));
        h1 = new ChessBlock(new Piece(1, h1, wR2));
        a2 = new ChessBlock(new Piece(1, a2, wP1));
        b2 = new ChessBlock(new Piece(1, b2, wP2));
        c2 = new ChessBlock(new Piece(1, c2, wP3));
        d2 = new ChessBlock(new Piece(1, d2, wP4));
        e2 = new ChessBlock(new Piece(1, e2, wP5));
        f2 = new ChessBlock(new Piece(1, f2, wP6));
        g2 = new ChessBlock(new Piece(1, g2, wP7));
        h2 = new ChessBlock(new Piece(1, h2, wP8));
        
        a8 = new ChessBlock(new Piece(2, a1, bR1));
        b8 = new ChessBlock(new Piece(2, bKn1));
        c8 = new ChessBlock(new Piece(2, bB1));
        d8 = new ChessBlock(new Piece(2, bQ));
        e8 = new ChessBlock(new Piece(2, e1, bK));
        f8 = new ChessBlock(new Piece(2, bB2));
        g8 = new ChessBlock(new Piece(2, bKn2));
        h8 = new ChessBlock(new Piece(2, h1, bR2));
        a7 = new ChessBlock(new Piece(2, a7, bP1));
        b7 = new ChessBlock(new Piece(2, b7, bP2));
        c7 = new ChessBlock(new Piece(2, c7, bP3));
        d7 = new ChessBlock(new Piece(2, d7, bP4));
        e7 = new ChessBlock(new Piece(2, e7, bP5));
        f7 = new ChessBlock(new Piece(2, f7, bP6));
        g7 = new ChessBlock(new Piece(2, g7, bP7));
        h7 = new ChessBlock(new Piece(2, h7, bP8));
        
        
        for (int i = 0; i < 8; i++){
            for (int j = 2; j < 6; j++) {
                block[i][j] = nullArea;
            }
        }
    }    
    
}
