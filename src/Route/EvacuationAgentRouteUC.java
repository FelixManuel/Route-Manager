package Route;

import Agent_Scheme.Utilities.CoordAgent;
import Building_Scheme.Utilities.Dimension;
import Utilities.Point2D;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author sinda
 */
public class EvacuationAgentRouteUC extends AgentRoute{

    public EvacuationAgentRouteUC(ArrayList<Point2D> exits, HashMap<String, Dimension> planes, int rows, int columns, CoordAgent coordinateAgent) {
        super(exits, planes, rows, columns, coordinateAgent);
        this.setConsumedPoints(0);
    }
    
    private EvacuationAgentRouteUC(ArrayList<Point2D> exits, ArrayList<CoordAgent> route, int movements,
                                 int consumedPoints, HashMap<String,Dimension> planes, int rows, int columns){
        super(exits, route, movements, consumedPoints, planes, rows, columns);
    }

    @Override
    public int evaluatedQuote() {
        return this.getConsumedPoints();
    }

    @Override
    public int narrow() {
        int result = Integer.MAX_VALUE;
        
        for(Point2D exit: this.getExits()){
            int routeSize = this.getRoute().size()-1;
            
            int exitX = exit.getX();
            int exitY = exit.getY();
            int actualPositionX = this.getRoute().get(routeSize).getCoordinate().getX();
            int actualPositionY = this.getRoute().get(routeSize).getCoordinate().getY();
            
            int calculate = (Math.abs(exitX-actualPositionX) + Math.abs(exitY-actualPositionY));
            
            int newResult = calculate;
            if(newResult < result){
                result = newResult;
            }
        }
        
        return result;
    }

    @Override
    protected int addConsumedPoint(CoordAgent coordinateAgent) {
        return 1;
    }

    @Override
    public EvacuationAgentRouteUC clone() {
        HashMap<String,Dimension> temperaturePlanes = new HashMap<>(this.getPlanes());
        ArrayList<CoordAgent> route = new ArrayList<>(this.getRoute());
        EvacuationAgentRouteUC newAgent = new EvacuationAgentRouteUC(this.getExits(), route, this.getMovements(),
                                                                 this.getConsumedPoints(), temperaturePlanes,
                                                                 this.getRows(), this.getColumns());
        
        return newAgent;
    }
    
    @Override
    public int compareTo(Object agent){
        AgentRoute newAgent = (AgentRoute) agent;
        
        if(this.narrow() < newAgent.narrow()){
            return -1;
        }else if(narrow() > newAgent.narrow()){
            return +1;
        }else{
            return 0;
        }
    }
    
}
