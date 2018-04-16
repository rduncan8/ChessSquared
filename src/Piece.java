

public class Piece {
    int col;
    String PieceName;
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
         PieceName = "P";
    }
    
    public Piece(int col, Bishop b){
         this.col = col;
         this.b = b;
         PieceName = "B";
    }
    
    public Piece(int col, Knight kn){
         this.col = col;
         this.kn = kn;
         PieceName = "Kn";
    }
    
    public Piece(int col, ChessBlock CurrentPos, Rook r){
         this.col = col;
         this.CurrentPos = CurrentPos;
         this.r = r;
         PieceName = "R";
    }    
    
    public Piece(int col, Queen q){
         this.col = col;
         this.q = q;
         PieceName = "Q";
    }    
    
    public Piece(int col, ChessBlock CurrentPos, King k){
         this.col = col;
         this.CurrentPos = CurrentPos;
         this.k = k;
         PieceName = "K";
    }    
    
    public String getPieceName(){
        return PieceName;
    }
    
    public ChessBlock getCurrPos(){
        
        return CurrentPos;
    }
    
}
