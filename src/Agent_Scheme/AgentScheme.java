package Agent_Scheme;

import Agent_Scheme.Utilities.CoordinateAgent;

/**
 * @author Felix Manuel Mellado
 */
public class AgentScheme {
    //Attributes
    private CoordinateAgent coordinate;
    private int identification;
    
    //Constructor
    public AgentScheme(){
        this.coordinate = new CoordinateAgent();
        this.identification = 0;
    }
    
    //Getter Method
    public int getIdentification(){
        return this.identification;
    }
    
    public CoordinateAgent getCoordinate(){
        return this.coordinate;
    }
}
