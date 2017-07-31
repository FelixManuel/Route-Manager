package Building_Scheme.Utilities;

/**
 * @author Felix Manuel Mellado
 */
public class Point2D {
    //Attributes
    private int x;
    private int y;
    
    //Constructor
    public Point2D(){
        this.x = 0;
        this.y = 0;
    }
    
    public Point2D(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    //Getter Methods
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
}
