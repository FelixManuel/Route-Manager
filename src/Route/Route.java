package Route;

import java.util.PriorityQueue;

/**
 * @author Félix Manuel Mellado
 */
public class Route {
    
    public static void generationRoute(AgentRoute agentRoute, String nameExitFile){
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
            }
        }
    }
}
