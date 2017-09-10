package Utilities;

/**
 * @author Felix Manuel Mellado
 */
public class Point2D {
    //Attributes
    private int x;
    private int y;
    
    //Constructor
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
    
    //Setter Methods
    public void setX(int x){
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    //Methods
    public boolean equals(Point2D point){
        return (this.getX() == point.getX() && this.getY() == point.getY());
    }
}
