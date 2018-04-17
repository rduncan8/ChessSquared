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
    
    public ChessBlock(JButton block, String blockName){
        hasPce = false;
        this.block = block;
        this.blockName = blockName;
    }    
    
    public Boolean hasPce(){
        return hasPce;
    }
    
    public String getPce(){
        return pce.getPieceName();
    }
    
    public String getBlockName(){
        return blockName;
    }
    
}
