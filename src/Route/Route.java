package Route;

import Agent_Scheme.Utilities.CoordAgent;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Félix Manuel Mellado
 */
public abstract class Route {
    
    public abstract ArrayList<CoordAgent> generationRoute(AgentRoute agentRoute);
    
    protected static PriorityQueue<AgentRoute> reduceQueue(PriorityQueue<AgentRoute> mound){    
        PriorityQueue<AgentRoute> newMound = new PriorityQueue<>();
        for(int size = 0; size<15000; size++){
            AgentRoute proof = mound.poll();
            newMound.add(proof);
        }
        mound.clear();
        mound = newMound;
        
        return mound;
    }
}
