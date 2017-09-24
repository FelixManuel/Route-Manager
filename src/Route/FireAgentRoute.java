package Route;

import Agent_Scheme.Utilities.CoordAgent;
import Building_Scheme.Utilities.Dimension;
import Utilities.Point2D;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Felix Manuel Mellado
 */
public class FireAgentRoute extends AgentRoute{
    //Attributes
    private int mediumTemperature = 0;
    
    //Normative Attributes
    private static final int DOOR_VALUE = 0; 
    
    //Constructor
    public FireAgentRoute(ArrayList<Point2D> exits, HashMap<String, Dimension> planes, int rows, int columns, CoordAgent coordinateAgent) {
        super(exits, planes, rows, columns, coordinateAgent);
        this.mediumTemperature = temperatureResult();
        this.setConsumedPoints(this.addConsumedPoint(this.getRoute().get(this.getRoute().size()-1)));
    }
    
    private FireAgentRoute(ArrayList<Point2D> exits, ArrayList<CoordAgent> route, int movements,
                       int consumedPoints, HashMap<String, Dimension> temperaturePlanes, int rows, int columns,
                       int mediumTemperature){
        super(exits, route, movements, consumedPoints, temperaturePlanes, rows, columns);
        this.mediumTemperature = mediumTemperature;
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
        
        return result * this.mediumTemperature;
    }

    @Override
    protected int addConsumedPoint(CoordAgent coordinateAgent) {
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
    
    private int temperatureResult(){
        int totalTemperature = 0;
        int totalCount = 0;
        
        for(Dimension plane: this.getPlanes().values()){
            int count = 0;
            int temperature = 0;
            for(int row = 0; row<plane.getRows(); row++){
                for(int column = 0; column<plane.getColumns(); column++){
                    String value = plane.getValue(row, column);
                    if(isNumeric(value)){
                        temperature += Integer.parseInt(value);
                        count += 1;
                    }
                }
            }
            totalTemperature += temperature;
            totalCount += count;
        }
        
        return totalTemperature/totalCount;
    }

    @Override
    public FireAgentRoute clone() {
        HashMap<String,Dimension> temperaturePlanes = new HashMap<>(this.getPlanes());
        ArrayList<CoordAgent> route = new ArrayList<>(this.getRoute());
        FireAgentRoute newAgent = new FireAgentRoute(this.getExits(), route, this.getMovements(),
                                                     this.getConsumedPoints(), temperaturePlanes,
                                                     this.getRows(), this.getColumns(), this.mediumTemperature);
        
        return newAgent;
    }
    
}
