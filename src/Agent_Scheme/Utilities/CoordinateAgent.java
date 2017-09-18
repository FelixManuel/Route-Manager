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
    public CoordinateAgent(Point2D coordinate, String nameFloor){
        this.coordinate = coordinate;
        this.floor = nameFloor;
    }
    
    //Getter Methods
    public Point2D getCoordinate(){
        return this.coordinate;
    }
    
    public String getNameFloor(){
        return this.floor;
    }
}
