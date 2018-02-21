

public class Piece {
    int col;
    ChessBlock CurrentPos;
    Pawn p;
    Bishop b;
    Knight kn;
    Rook r;
    Queen q;
    King k;
    
    
    public Piece(int col, ChessBlock CurrentPos, Pawn p){
         this.col = col;
         this.CurrentPos = CurrentPos;
         this.p = p;
    }
    
    public Piece(int col, Bishop b){
         this.col = col;
         this.b = b;
    }
    
    public Piece(int col, Knight kn){
         this.col = col;
         this.kn = kn;
    }
    
    public Piece(int col, ChessBlock CurrentPos, Rook r){
         this.col = col;
         this.CurrentPos = CurrentPos;
         this.r = r;
    }    
    
    public Piece(int col, Queen q){
         this.col = col;
         this.q = q;
    }    
    
    public Piece(int col, ChessBlock CurrentPos, King k){
         this.col = col;
         this.CurrentPos = CurrentPos;
         this.k = k;
    }    

    
}
