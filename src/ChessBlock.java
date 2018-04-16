import javax.swing.*;

public class ChessBlock {
    Piece pce;
    boolean hasPce;
    JButton block;
    String blockName;
    
    public ChessBlock(Piece pce, JButton block, String blockName){
        hasPce = true;
        this.pce = pce;
        this.block = block;
        this.blockName = blockName;
    }
    
    public ChessBlock(String blockName){
        hasPce = false;
        this.blockName = blockName;
    }    
    
    public String getPce(){
        return pce.getPieceName();
    }
    
    public String getBlockName(){
        return blockName;
    }
    
}
