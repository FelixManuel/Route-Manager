package Route;

import Agent_Scheme.Utilities.CoordinateAgent;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * @author Félix Manuel Mellado
 */
public class Route {
    
    public static ArrayList<CoordinateAgent> generationRoute(AgentRoute agentRoute){
        PriorityQueue<AgentRoute> mound = new PriorityQueue<>();
        int quota = agentRoute.narrow();
        AgentRoute solution = null;
        AgentRoute partialSolution = null;
        mound.add(agentRoute);
        
        while(!mound.isEmpty()){
            partialSolution = mound.poll();
            if(partialSolution.isSolution()){
                solution = (AgentRoute) partialSolution.clone();
                quota = partialSolution.getConsumedPoints();
            }else{
                for(AgentRoute sonAgentRoute: partialSolution.complections()){
                    int agentQuota = sonAgentRoute.getConsumedPoints();
                    if(agentQuota < quota){
                        mound.add(sonAgentRoute);
                    }
                }
            }
        }
        
        return solution.getRoute();
    }
}