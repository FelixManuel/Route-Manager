package Agent_Scheme;

import Agent_Scheme.Utilities.CoordinateAgent;
import java.util.ArrayList;

/**
 * @author Felix Manuel Mellado
 */
public class AgentScheme {
    //Attributes
    private CoordinateAgent coordinate;
    private int identification;
    private ArrayList<CoordinateAgent> route;
    
    //Getter Method
    public int getIdentification(){
        return this.identification;
    }
    
    public CoordinateAgent getCoordinate(){
        return this.coordinate;
    }
    
    //Setter Methods
    public void setRoute(ArrayList<CoordinateAgent> route){
        this.route = route;
    }
}
