package Agent_Scheme;

import Agent_Scheme.Utilities.CoordAgent;
import java.util.ArrayList;

/**
 * This class represents the agent scheme of the building.
 * @author Felix Manuel Mellado
 */
public class AgentScheme {
    //Attributes
    private CoordAgent coordinate;
    private int identification;
    private ArrayList<CoordAgent> route;
    
    //Getter Method

    /**
     * Return the agent identification.
     * @return identification
     */
    public int getIdentification(){
        return this.identification;
    }
    
    /**
     * Return the agent coordinate in the plane.
     * @return Coordinate
     */
    public CoordAgent getCoordinate(){
        return this.coordinate;
    }
    
    /**
     * Return the route that the agent has to follow to escape from the building.
     * @return route
     */
    public ArrayList<CoordAgent> getRoute(){
        return this.route;
    }
    
    //Setter Methods

    /**
     * Insert the route that the agent has to follow to escape from the building.
     * @param route
     */
    public void setRoute(ArrayList<CoordAgent> route){
        this.route = route;
    }
}
