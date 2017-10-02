package Route;

import Agent_Scheme.Utilities.CoordAgent;
import Building_Scheme.Utilities.Dimension;
import Utilities.Point2D;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Felix Manuel Mellado
 */
public class EvacuationAgentRouteA extends AgentRoute{

    public EvacuationAgentRouteA(ArrayList<Point2D> exits, HashMap<String, Dimension> planes, int rows, int columns, CoordAgent coordinateAgent) {
        super(exits, planes, rows, columns, coordinateAgent);
        this.setConsumedPoints(0);
    }
    
    private EvacuationAgentRouteA(ArrayList<Point2D> exits, ArrayList<CoordAgent> route, int movements,
                                 int consumedPoints, HashMap<String,Dimension> planes, int rows, int columns){
        super(exits, route, movements, consumedPoints, planes, rows, columns);
    }

    @Override
    public int evaluatedQuote() {
        return this.getConsumedPoints();
    }

    @Override
    public int narrow(){
        int result = Integer.MAX_VALUE;
        
        for(Point2D exit: this.getExits()){
            int routeSize = this.getRoute().size()-1;
            
            int exitX = exit.getX();
            int exitY = exit.getY();
            int actualPositionX = this.getRoute().get(routeSize).getCoordinate().getX();
            int actualPositionY = this.getRoute().get(routeSize).getCoordinate().getY();
            
            int pow1 = (int) (Math.pow((exitX-actualPositionX),2));
            int pow2 = (int) (Math.pow((exitY-actualPositionY),2));
            
            int calculate = (int) Math.sqrt(pow1 + pow2);
            
            int newResult = calculate;
            if(newResult < result){
                result = newResult;
            }
        }
        
        return result + getConsumedPoints();
    }

    @Override
    protected int addConsumedPoint(CoordAgent coordinateAgent) {
        return 1;
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

    @Override
    public AgentRoute clone() {
        HashMap<String,Dimension> temperaturePlanes = new HashMap<>(this.getPlanes());
        ArrayList<CoordAgent> route = new ArrayList<>(this.getRoute());
        EvacuationAgentRouteA newAgent = new EvacuationAgentRouteA(this.getExits(), route, this.getMovements(),
                                                                 this.getConsumedPoints(), temperaturePlanes,
                                                                 this.getRows(), this.getColumns());
        
        return newAgent;
    }
    
}
