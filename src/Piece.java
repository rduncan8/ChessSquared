

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
         if(col == 1){
            PieceName = "wP";
         }else{
             PieceName = "bP";
         }
    }
    
    public Piece(int col, Bishop b){
         this.col = col;
         this.b = b;
         if(col == 1){
            PieceName = "wB";
         }else{
             PieceName = "bB";
         }
    }
    
    public Piece(int col, Knight kn){
         this.col = col;
         this.kn = kn;
         if(col == 1){
            PieceName = "wKn";
         }else{
             PieceName = "bKn";
         }
    }
    
    public Piece(int col, ChessBlock CurrentPos, Rook r){
         this.col = col;
         this.CurrentPos = CurrentPos;
         this.r = r;
         if(col == 1){
            PieceName = "wR";
         }else{
             PieceName = "bR";
         }
    }    
    
    public Piece(int col, Queen q){
         this.col = col;
         this.q = q;
         if(col == 1){
            PieceName = "wQ";
         }else{
             PieceName = "bQ";
         }
    }    
    
    public Piece(int col, ChessBlock CurrentPos, King k){
         this.col = col;
         this.CurrentPos = CurrentPos;
         this.k = k;
         if(col == 1){
            PieceName = "wK";
         }else{
             PieceName = "bK";
         }
    }    
    
    public String getPieceName(){
        return PieceName;
    }
    
    public ChessBlock getCurrPos(){
        
        return CurrentPos;
    }
    
}
