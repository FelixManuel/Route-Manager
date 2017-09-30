package Route;

import Agent_Scheme.Utilities.CoordAgent;
import static Route.Route.reduceQueue;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * @author Feíx Manuel Mellado
 */
public class RouteBranchPruning extends Route{
    
    public ArrayList<CoordAgent> generationRoute(AgentRoute agentRoute){
        PriorityQueue<AgentRoute> mound = new PriorityQueue<>();
        int quota = agentRoute.narrow();
        AgentRoute solution = null;
        AgentRoute partialSolution = null;
        mound.add(agentRoute);
        
        while(!mound.isEmpty()){
            partialSolution = mound.poll();
            if(partialSolution.isSolution() && (solution == null || partialSolution.getConsumedPoints() < solution.getConsumedPoints())){
                solution = partialSolution.clone();
                quota = partialSolution.evaluatedQuote();
            }else{
                for(AgentRoute sonAgentRoute: partialSolution.complections()){
                    int agentQuota = sonAgentRoute.evaluatedQuote();
                    if(agentQuota <= quota){
                        mound.add(sonAgentRoute);
                    }
                }
            }
            
            if(mound.size() > 500000){
                mound = reduceQueue(mound);
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
