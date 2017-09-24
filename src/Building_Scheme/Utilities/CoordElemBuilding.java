package Building_Scheme.Utilities;

import Utilities.Point2D;

/**
 * @author Felix Manuel Mellado
 */
public class CoordElemBuilding {
    //Atributes
    private Point2D[] coordinate;
    
    //Constructor
    public CoordElemBuilding(){
        this.coordinate = new Point2D[2];
    }
    
    //Getter Methods
    public Point2D[] getCoordinate(){
        return this.coordinate;
    }
}
