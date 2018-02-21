

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
        
        a1 = new ChessBlock(new Piece(1, a1, new Pawn(a1)));
        
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
    }
    
    public void StartPos(){
        
    }
    
    
    
    
    
    
}
