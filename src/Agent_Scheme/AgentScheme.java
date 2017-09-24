package Agent_Scheme;

import Agent_Scheme.Utilities.CoordAgent;
import java.util.ArrayList;

/**
 * @author Felix Manuel Mellado
 */
public class AgentScheme {
    //Attributes
    private CoordAgent coordinate;
    private int identification;
    private ArrayList<CoordAgent> route;
    
    //Getter Method
    public int getIdentification(){
        return this.identification;
    }
    
    public CoordAgent getCoordinate(){
        return this.coordinate;
    }
    
    public ArrayList<CoordAgent> getRoute(){
        return this.route;
    }
    
    //Setter Methods
    public void setRoute(ArrayList<CoordAgent> route){
        this.route = route;
    }
}
