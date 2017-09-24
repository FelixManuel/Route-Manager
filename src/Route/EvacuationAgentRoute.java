package Route;

import Agent_Scheme.Utilities.CoordinateAgent;
import Building_Scheme.Utilities.Dimension;
import Utilities.Point2D;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author sinda
 */
public class EvacuationAgentRoute extends AgentRoute{

    public EvacuationAgentRoute(ArrayList<Point2D> exits, HashMap<String, Dimension> planes, int rows, int columns, CoordinateAgent coordinateAgent) {
        super(exits, planes, rows, columns, coordinateAgent);
        this.setConsumedPoints(0);
    }
    
    private EvacuationAgentRoute(ArrayList<Point2D> exits, ArrayList<CoordinateAgent> route, int movements,
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
            
            int newResult = (int)(calculate * 0.25 + calculate);
            if(newResult < result){
                result = newResult;
            }
        }
        
        return result;
    }

    @Override
    protected int addConsumedPoint(CoordinateAgent coordinateAgent) {
        return 1;
    }

    @Override
    public EvacuationAgentRoute clone() {
        HashMap<String,Dimension> temperaturePlanes = new HashMap<>(this.getPlanes());
        ArrayList<CoordinateAgent> route = new ArrayList<>(this.getRoute());
        EvacuationAgentRoute newAgent = new EvacuationAgentRoute(this.getExits(), route, this.getMovements(),
                                                                 this.getConsumedPoints(), temperaturePlanes,
                                                                 this.getRows(), this.getColumns());
        
        return newAgent;
    }
    
}
