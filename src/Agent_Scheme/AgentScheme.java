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
    
    //Getter Method
    public int getIdentification(){
        return this.identification;
    }
    
    public CoordinateAgent getCoordinate(){
        return this.coordinate;
    }
}
