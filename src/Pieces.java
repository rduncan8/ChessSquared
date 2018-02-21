
public interface Pieces {
    
    public void color();
    
    public void move(int dir);
    
    public void moveDistance(int dis);
    
    public boolean canAttack();
    
    public boolean inCheck();
    
            
}
