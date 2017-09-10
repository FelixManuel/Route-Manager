
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
    private static final int DOOR_VALUE = 0;
    
    //Letter attribute
    private static final String WALL_LETTER = "W";
    private static final String WINDOW_LETTER = "w";
    private static final String ELEVATOR_LETTER = "E";
    private static final String STAIRS_LETTER = "S";
    
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
    
    //Getter Methods
    public ArrayList<CoordinateAgent> getRoute(){
        return this.route;
    }
    
    public int getConsumedPoints(){
        return this.consumedPoints;
    }
    
    private int getMovements(){
        return this.movements;
    }
    
    //Setter Methods
    private void setConsumedPoints(int consumedPoints){
        this.consumedPoints = consumedPoints;
    }
    
    private void setMovements(int movements){
        this.movements = movements;
    }
    
    //Methods    
    public int narrow(){
        int size = temperaturePlanes.size();
        int rows = this.rows;
        int columns = this.columns;
        
        int total = rows + columns - this.movements;
        
        return (total * size * MEDIUM_TEMPERATURE);
    }
    
    public boolean isSolution(){
        boolean solution = false;
        Point2D agentPosition = this.route.get(this.movements-1).getCoordinate();
        Iterator iterator = this.exits.iterator();
        
        while(solution == false && iterator.hasNext()){
            if(agentPosition.equals((Point2D)iterator.next())){
                solution = true;
            }
        }
        
        return solution;
    }
    
    private int addConsumedPoint(CoordinateAgent coordinateAgent){
        int rowAgent = coordinateAgent.getCoordinate().getX();
        int columnAgent = coordinateAgent.getCoordinate().getY();
        Dimension temperaturePlane = this.temperaturePlanes.get(coordinateAgent.getFloor());
        String value = temperaturePlane.getValue(rowAgent, columnAgent);
        
        if(isNumeric(value)){
            return Integer.parseInt(value);
        }else{
            return DOOR_VALUE;
        }
    }
    
    private void addCoordinateAgentInRoute(CoordinateAgent corrdianteAgent){
        this.route.add(corrdianteAgent);
    }
    
    public ArrayList<AgentRoute> complections(){
        ArrayList<AgentRoute> complections = new ArrayList<>();
        
        CoordinateAgent agent = this.route.get(this.route.size()-1);
        ArrayList<Point2D> newPositions = generatePoints(agent);
        for(Point2D point: newPositions){
            AgentRoute newAgent = (AgentRoute)this.clone();
            
            CoordinateAgent newCoordinate = new CoordinateAgent(point, agent.getFloor());
            newAgent.addCoordinateAgentInRoute(newCoordinate);
            
            newAgent.setMovements(newAgent.getMovements()+1);
            newAgent.setConsumedPoints(newAgent.getConsumedPoints()+newAgent.addConsumedPoint(newCoordinate));
            
            complections.add(newAgent);
        }
        
        return complections;
    }
    
    private ArrayList<Point2D> generatePoints(CoordinateAgent agent){
        Point2D lastPosition = agent.getCoordinate();
        String floor = agent.getFloor();
        ArrayList<Point2D> solution = new ArrayList<>();
        
        int lastPositionX = lastPosition.getX();
        int lastPositionY = lastPosition.getY();
        
        Point2D rowLess = new Point2D(lastPositionX-1, lastPositionY);
        Point2D rowMore = new Point2D(lastPositionX+1, lastPositionY);
        Point2D ColumnLess = new Point2D(lastPositionX, lastPositionY-1);
        Point2D ColumnMore = new Point2D(lastPositionX, lastPositionY+1);
        
        solution.add(rowLess);
        solution.add(rowMore);
        solution.add(ColumnLess);
        solution.add(ColumnMore);
        
        ArrayList<Point2D> elementsToRemove = new ArrayList<>();
        
        for(int iterator = 0; iterator<solution.size(); iterator++){
            Point2D point = solution.get(iterator);
            if(!exist(point) || !isValid(point,floor) || goneThere(point)){
                elementsToRemove.add(point);
            }
        }
        
        for(Point2D point: elementsToRemove){
            solution.remove(point);
        }
        
        return solution;
    }
    
    private boolean exist(Point2D point){
        boolean exist = true;
        int pointX = point.getX();
        int pointY = point.getY();
        
        if(pointX < 0 || pointX >= this.rows || pointY < 0 || pointY >= this.columns){
            exist = false;
        }
        
        return exist;
    }
    
    private boolean isValid(Point2D point, String floor){
        boolean valid = true;
        Dimension temperaturePlane = this.temperaturePlanes.get(floor);
        int pointX = point.getX();
        int pointY = point.getY();

        String letter = temperaturePlane.getValue(pointX, pointY);
        
        if(letter.equals(WALL_LETTER) || letter.equals(WINDOW_LETTER) ||
           letter.equals(ELEVATOR_LETTER) || letter.equals(STAIRS_LETTER)){
            valid = false;
        }
        
        return valid;
    }
    
    private boolean goneThere(Point2D point){
        boolean goneThere = false;
        Iterator routeIterator = this.route.iterator();
        
        while(routeIterator.hasNext() && goneThere == false){
            CoordinateAgent coordinateAgent = (CoordinateAgent) routeIterator.next();
            Point2D agentPoint = coordinateAgent.getCoordinate();
            if(point.equals(agentPoint)){
                goneThere = true;
            }
        }
        
        return goneThere;
    }
    
    private static boolean isNumeric(String value){
        try{
            Integer.parseInt(value);
            return true;
        }catch(NumberFormatException nfe){
            return false;
        }
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
