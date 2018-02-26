

public class ChessBlock {
    Piece pce;
    boolean hasPce;
    
    public ChessBlock(Piece pce){
        hasPce = true;
        this.pce = pce;
    }
    
    ChessBlock(){
        hasPce = false;
    }    
    
}
