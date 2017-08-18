package Utilities;

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
    
    //Getter Methods
    public Point2D[] getCoordinate(){
        return this.coordinate;
    }
}
