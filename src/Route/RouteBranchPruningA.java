package Route;

import Agent_Scheme.Utilities.CoordAgent;
import static Route.Route.reduceQueue;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * @author Felix Manuel Mellado
 */
public class RouteBranchPruningA extends Route{

    @Override
    public ArrayList<CoordAgent> generationRoute(AgentRoute agentRoute){
        PriorityQueue<AgentRoute> mound = new PriorityQueue<>();
        AgentRoute solution = null;
        AgentRoute partialSolution = null;
        mound.add(agentRoute);
        
        while(!mound.isEmpty() && solution == null){
            partialSolution = mound.poll();
            if(partialSolution.isSolution()){
                System.out.println(mound.size());
                solution = partialSolution.clone();
            }else{
                for(AgentRoute sonAgentRoute: partialSolution.complections()){
                    int agentQuota = sonAgentRoute.evaluatedQuote();
                    mound.add(sonAgentRoute);                    
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
