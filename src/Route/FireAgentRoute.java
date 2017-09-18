package Route;

import Agent_Scheme.Utilities.CoordinateAgent;
import Building_Scheme.Utilities.Dimension;
import Utilities.Point2D;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Felix Manuel Mellado
 */
public class FireAgentRoute extends AgentRoute{
    //Normative Attributes
    private static final int MEDIUM_TEMPERATURE = 26;
    private static final int DOOR_VALUE = 0; 
    
    //Constructor
    public FireAgentRoute(ArrayList<Point2D> exits, HashMap<String, Dimension> planes, int rows, int columns, CoordinateAgent coordinateAgent) {
        super(exits, planes, rows, columns, coordinateAgent);
        this.setConsumedPoints(this.addConsumedPoint(this.getRoute().get(this.getRoute().size()-1)));
    }
    
    private FireAgentRoute(ArrayList<Point2D> exits, ArrayList<CoordinateAgent> route, int movements,
                       int consumedPoints, HashMap<String, Dimension> temperaturePlanes, int rows, int columns){
        super(exits, route, movements, consumedPoints, temperaturePlanes, rows, columns);
    }

    @Override
    public int evaluatedQuote() {
        return this.getConsumedPoints();
    }

    @Override
    public int narrow() {
        int size = this.getPlanes().size();
        int rows = this.getRows();
        int columns = this.getColumns();
        
        int total = rows + columns - this.getMovements();
        
        return (total * size * MEDIUM_TEMPERATURE);
    }

    @Override
    protected int addConsumedPoint(CoordinateAgent coordinateAgent) {
        int rowAgent = coordinateAgent.getCoordinate().getX();
        int columnAgent = coordinateAgent.getCoordinate().getY();
        Dimension temperaturePlane = this.getPlanes().get(coordinateAgent.getNameFloor());
        String value = temperaturePlane.getValue(rowAgent, columnAgent);
        
        if(isNumeric(value)){
            return Integer.parseInt(value);
        }else{
            return DOOR_VALUE;
        }
    }

    @Override
    public FireAgentRoute clone() {
        HashMap<String,Dimension> temperaturePlanes = new HashMap<>(this.getPlanes());
        ArrayList<CoordinateAgent> route = new ArrayList<>(this.getRoute());
        FireAgentRoute newAgent = new FireAgentRoute(this.getExits(), route, this.getMovements(),
                                                     this.getConsumedPoints(), temperaturePlanes,
                                                     this.getRows(), this.getColumns());
        
        return newAgent;
    }
    
}
