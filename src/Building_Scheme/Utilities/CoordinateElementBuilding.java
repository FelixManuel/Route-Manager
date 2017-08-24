package Building_Scheme.Utilities;

import Utilities.Point2D;
import java.util.ArrayList;

/**
 * @author Felix Manuel Mellado
 */
public class CoordinateElementBuilding {
    //Atributes
    private Point2D[] coordinate;
    
    //Constructor
    public CoordinateElementBuilding(){
        this.coordinate = new Point2D[2];
    }
    
    //Getter Methods
    public Point2D[] getCoordinate(){
        return this.coordinate;
    }
}
