package Agent_Scheme.Utilities;

import Utilities.Point2D;

/**
 * This class represents thae agent coordinate in the building.
 * @author Félix Manuel Mellado
 */
public class CoordAgent {
    //Attributes
    private Point2D coordinate;
    private String floor;
    
    //Constructor

    /**
     * Constructor.
     * @param coordinate
     * @param nameFloor
     */
    public CoordAgent(Point2D coordinate, String nameFloor){
        this.coordinate = coordinate;
        this.floor = nameFloor;
    }
    
    //Getter Methods

    /**
     * Returns the agent coordinate in the building.
     * @return coordinate
     */
    public Point2D getCoordinate(){
        return this.coordinate;
    }
    
    /**
     * Returns the floor name where the agent is.
     * @return nameFloor
     */
    public String getNameFloor(){
        return this.floor;
    }
}
