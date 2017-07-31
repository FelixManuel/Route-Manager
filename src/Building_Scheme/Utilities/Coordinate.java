package Building_Scheme.Utilities;

import java.util.ArrayList;

/**
 * @author Felix Manuel Mellado
 */
public class Coordinate {
    //Atributes
    private Point2D[] coordinate;
    
    //Constructor
    public Coordinate(){
        this.coordinate = new Point2D[2];
    }
    
    public Coordinate(Point2D[] coordinate){
        this.coordinate = coordinate;
    }
    
    //Getter Methods
    public Point2D[] getCoordinate(){
        return this.coordinate;
    }
}
