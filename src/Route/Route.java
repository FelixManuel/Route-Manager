package Route;

import Agent_Scheme.Utilities.CoordAgent;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Félix Manuel Mellado
 */
public class Route {
    
    public static ArrayList<CoordAgent> generationRoute(AgentRoute agentRoute){
        PriorityQueue<AgentRoute> mound = new PriorityQueue<>();
        int quota = agentRoute.narrow();
        AgentRoute solution = null;
        AgentRoute partialSolution = null;
        mound.add(agentRoute);
        
        while(!mound.isEmpty()){
            partialSolution = mound.poll();
            if(partialSolution.isSolution()){
                if(solution == null){
                    solution = partialSolution.clone();
                    quota = partialSolution.evaluatedQuote();
                }else if(partialSolution.getConsumedPoints() < solution.getConsumedPoints()){                
                    solution = partialSolution.clone();
                    quota = partialSolution.evaluatedQuote();
                }
            }else{
                for(AgentRoute sonAgentRoute: partialSolution.complections()){
                    int agentQuota = sonAgentRoute.evaluatedQuote();
                    if(agentQuota <= quota){
                        mound.add(sonAgentRoute);
                    }
                }
            }
            
            if(mound.size() > 500000){
                PriorityQueue<AgentRoute> newMound = new PriorityQueue<>();
                for(int size = 0; size<5000; size++){
                    AgentRoute proof = mound.poll();
                    newMound.add(proof);
                }
                mound.clear();
                mound = newMound;
            }
        }
        
        if(solution == null){
            try {
                throw new Exception();
            } catch (Exception ex) {
                System.out.println("No existe ruta factible");
                return agentRoute.getRoute();
            }
        }
        
        return solution.getRoute();
        
    }
}
