package Agent_Scheme.Utilities;

import Utilities.Point2D;

/**
 * @author Félix Manuel Mellado
 */
public class CoordinateAgent {
    //Attributes
    private Point2D coordinate;
    private String floor;
    
    //Constructor
    public CoordinateAgent(Point2D coordinate, String floor){
        this.coordinate = coordinate;
        this.floor = floor;
    }
    
    //Getter Methods
    public Point2D getCoordinate(){
        return this.coordinate;
    }
    
    public String getFloor(){
        return this.floor;
    }
}
