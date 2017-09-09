
package Route;

import Agent_Scheme.Utilities.CoordinateAgent;
import Building_Scheme.Utilities.Dimension;
import Utilities.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author Felix Manuel Mellado
 */
public class AgentRoute implements Comparable{
    //Attributes
    private ArrayList<Point2D> exits;
    private ArrayList<CoordinateAgent> route;    
    private int movements;
    private int consumedPoints;
    private HashMap<String, Dimension> temperaturePlanes;
    private int rows;
    private int columns;
    
    //Normative attribute
    private static final int MEDIUM_TEMPERATURE = 26;
    
    //Constructor
    public AgentRoute(ArrayList<Point2D> exits, HashMap<String, Dimension> temperaturePlanes,
                      int rows, int columns, CoordinateAgent coordinateAgent){
        this.exits = exits;
        this.route = new ArrayList<>();
        this.movements = 1;
        this.temperaturePlanes = temperaturePlanes;
        this.rows = rows;
        this.columns = columns;
                
        this.route.add(coordinateAgent);
        this.consumedPoints = addConsumedPoint(this.route.get(this.route.size()-1));
    }
    
    public AgentRoute(ArrayList<Point2D> exits, ArrayList<CoordinateAgent> route, int movements,
                      int consumedPoints, HashMap<String, Dimension> temperaturePlane, int rows,
                      int columns){
        this.exits = exits;
        this.route = route;
        this.movements = movements;
        this.consumedPoints = consumedPoints;
        this.temperaturePlanes = temperaturePlane;
        this.rows = rows;
        this.columns = columns;
    }
    
    //Get Methods
    public int getConsumedPoints(){
        return this.consumedPoints;
    }
    
    //Methods
    public int narrow(){
        int size = temperaturePlanes.size();
        
        if(this.rows > 1){
            this.rows -= this.movements; 
        }else if(this.columns > 1){
            this.columns -= this.movements;
        }
        
        return (this.rows * this.columns * size * MEDIUM_TEMPERATURE);
    }
    
    public boolean isSolution(){
        boolean solution = false;
        Point2D agentPosition = this.route.get(this.movements-1).getCoordinate();
        Iterator iterator = this.exits.iterator();
        
        while(solution == false && iterator.hasNext()){
            if(agentPosition.equals(iterator.next())){
                solution = true;
            }
        }
        
        return solution;
    }
    
    private int addConsumedPoint(CoordinateAgent coordinateAgent){
        int rowAgent = coordinateAgent.getCoordinate().getX();
        int columnAgent = coordinateAgent.getCoordinate().getY();
        Dimension temperaturePlane = this.temperaturePlanes.get(coordinateAgent.getFloor());
        
        return Integer.parseInt(temperaturePlane.getValue(rowAgent, columnAgent));
    }
    
    @Override
    public int compareTo(Object agent) {
        AgentRoute newAgent = (AgentRoute) agent;
        
        if(this.consumedPoints < newAgent.getConsumedPoints()){
            return -1;
        }else if(this.consumedPoints > newAgent.getConsumedPoints()){
            return +1;
        }else{
            return 0;
        }
    }

    @Override
    public Object clone(){
        HashMap<String, Dimension> temperatureplanes = new HashMap<>(this.temperaturePlanes);
        ArrayList<CoordinateAgent> route = new ArrayList<>(this.route);
        AgentRoute newAgent = new AgentRoute(this.exits, route, this.movements,
                                             this.consumedPoints, temperatureplanes,
                                             this.rows, this.columns);
        
        return newAgent;
    }
}
