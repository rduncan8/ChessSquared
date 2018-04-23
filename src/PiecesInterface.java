

public interface PiecesInterface 
{    
    public void move(ChessBlock position);
    
    public void moveDistance(int dis);
    
    public boolean canAttack();
    
    public boolean inCheck();
}
