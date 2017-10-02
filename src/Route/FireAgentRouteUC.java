package Route;

import Agent_Scheme.Utilities.CoordAgent;
import Building_Scheme.Utilities.Dimension;
import Utilities.Point2D;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Felix Manuel Mellado
 */
public class FireAgentRouteUC extends AgentRoute{
    //Attributes
    private int mediumTemperature = 0;
    
    //Normative Attributes
    private static final int DOOR_VALUE = 0; 
    
    //Constructor
    public FireAgentRouteUC(ArrayList<Point2D> exits, HashMap<String, Dimension> planes, int rows, int columns, CoordAgent coordinateAgent) {
        super(exits, planes, rows, columns, coordinateAgent);
        this.mediumTemperature = temperatureResult();
        this.setConsumedPoints(this.addConsumedPoint(this.getRoute().get(this.getRoute().size()-1)));
    }
    
    private FireAgentRouteUC(ArrayList<Point2D> exits, ArrayList<CoordAgent> route, int movements,
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
            
            int newResult = calculate;
            if(newResult < result){
                result = newResult;
            }
        }        
        
        return result;
    }
    
        @Override
    protected ArrayList<Point2D> generatePoints(CoordAgent agent){
            Point2D lastPosition = agent.getCoordinate();
        String nameFloor = agent.getNameFloor();
        ArrayList<Point2D> solution = new ArrayList<>();
        
        int lastPositionX = lastPosition.getX();
        int lastPositionY = lastPosition.getY();
        
        Point2D rowLess = new Point2D(lastPositionX-1, lastPositionY);
        Point2D rowMore = new Point2D(lastPositionX+1, lastPositionY);
        Point2D columnLess = new Point2D(lastPositionX, lastPositionY-1);
        Point2D columnMore = new Point2D(lastPositionX, lastPositionY+1);
        
        Point2D upperRightDiagonal = new Point2D(lastPositionX-1, lastPositionY+1);
        Point2D upperLeftDiagonal = new Point2D(lastPositionX-1, lastPositionY-1);
        Point2D downRightDiagonal = new Point2D(lastPositionX+1, lastPositionY+1);
        Point2D downLeftDiagonal = new Point2D(lastPositionX+1, lastPositionY-1);
        
        solution.add(rowLess);
        solution.add(rowMore);
        solution.add(columnLess);
        solution.add(columnMore);
        
        solution.add(upperRightDiagonal);
        solution.add(upperLeftDiagonal);
        solution.add(downRightDiagonal);
        solution.add(downLeftDiagonal);
        
        ArrayList<Point2D> elementsToRemove = new ArrayList<>();
        int solutionSize = solution.size();
        
        for(int iterator = 0; iterator<solutionSize; iterator++){
            Point2D point = solution.get(iterator);
            if(!exist(point) || !isValid(point,nameFloor) || goneThere(point) || upTemperature(point,nameFloor)){
                elementsToRemove.add(point);
            }
        }
        
        for(Point2D point: elementsToRemove){
            solution.remove(point);
        }
        
        return solution;
    }
    
    private boolean upTemperature(Point2D point, String nameFloor){
        boolean upTemperature = false;
        Dimension plane = this.getPlanes().get(nameFloor);        
        int pointX = point.getX();
        int pointY = point.getY();        
        String value = plane.getValue(pointX, pointY);
        
        if(isNumeric(value)){        
            int temperature  = Integer.parseInt(value);
            if(temperature > 50){
                upTemperature = true;
            }        
        }
        
        return upTemperature;
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
    public FireAgentRouteUC clone() {
        HashMap<String,Dimension> temperaturePlanes = new HashMap<>(this.getPlanes());
        ArrayList<CoordAgent> route = new ArrayList<>(this.getRoute());
        FireAgentRouteUC newAgent = new FireAgentRouteUC(this.getExits(), route, this.getMovements(),
                                                     this.getConsumedPoints(), temperaturePlanes,
                                                     this.getRows(), this.getColumns(), this.mediumTemperature);
        
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
