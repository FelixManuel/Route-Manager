package Route;

import Agent_Scheme.Utilities.CoordAgent;
import Building_Scheme.Utilities.Dimension;
import Utilities.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author Felix Manuel Mellado
 */
public abstract class AgentRoute implements Comparable{
    //Attributes
    private ArrayList<Point2D> exits;
    private ArrayList<CoordAgent> route;
    private int consumedPoints;
    private int movements;
    private HashMap<String, Dimension> planes;
    private int rows;
    private int columns;
    
    //Letter Attributes
    private static final String WALL_LETTER = "W";
    private static final String WINDOW_LETTER = "w";
    private static final String ELEVATOR_LETTER = "E";
    private static final String STAIRS_LETTER = "S";
    
    //Constructor
    protected AgentRoute(ArrayList<Point2D> exits, HashMap<String, Dimension> planes,
                      int rows, int columns, CoordAgent coordinateAgent){
        this.exits = exits;
        this.route = new ArrayList<>();
        this.movements = 0;
        this.planes = planes;
        this.rows = rows;
        this.columns = columns;
        
        this.route.add(coordinateAgent);        
    }
    
    protected AgentRoute(ArrayList<Point2D> exits, ArrayList<CoordAgent> route, int movements,
                       int consumedPoints, HashMap<String, Dimension> planes, int rows, int columns){
        this.exits = exits;
        this.route = route;
        this.consumedPoints = consumedPoints;
        this.movements = movements;
        this.planes = planes;
        this.rows = rows;
        this.columns = columns;
    }
    
    //Getter Methods
    protected ArrayList<Point2D> getExits(){
        return this.exits;
    }
    
    public ArrayList<CoordAgent> getRoute(){
        return this.route;
    }
    
    protected int getConsumedPoints(){
        return this.consumedPoints;
    }
    
    protected int getMovements(){
        return this.movements;
    }
    
    protected HashMap<String,Dimension> getPlanes(){
        return this.planes;
    }
    
    protected int getRows(){
        return this.rows;
    }
    
    protected int getColumns(){
        return this.columns;
    }
    
    //Setter Methods
    protected void setConsumedPoints(int consumedPoints){
        this.consumedPoints = consumedPoints;
    }
    
    private void setMovements(int movements){
        this.movements = movements;
    }
    
    //Methods
    public abstract int evaluatedQuote();
    
    public abstract int narrow();
    
    public boolean isSolution(){
        boolean solution = false;
        Point2D agentPosition = this.route.get(this.movements).getCoordinate();
        Iterator iterator = this.exits.iterator();
        
        while(solution == false && iterator.hasNext()){
            if(agentPosition.equals((Point2D)iterator.next())){
                solution = true;
            }
        }
        
        return solution;
    }
    
    protected abstract int addConsumedPoint(CoordAgent coordinateAgent);
    
    private void addCoordinateAgentInRoute(CoordAgent coordinateAgent){
        this.route.add(coordinateAgent);
    }
    
    public ArrayList<AgentRoute> complections(){
        CoordAgent newCoordinate = null;
        ArrayList<AgentRoute> complections = new ArrayList<>();
        
        CoordAgent agent = this.route.get(this.route.size()-1);
        ArrayList<Point2D> newPositions = generatePoints(agent);
        for(Point2D point: newPositions){
            AgentRoute newAgent = this.clone();
            
            newCoordinate = new CoordAgent(point, agent.getNameFloor());
            newAgent.addCoordinateAgentInRoute(newCoordinate);
            
            newAgent.setMovements(newAgent.getMovements()+1);
            newAgent.setConsumedPoints(newAgent.getConsumedPoints()+newAgent.addConsumedPoint(newCoordinate));
            
            complections.add(newAgent);
        }
        
        return complections;
    }
    
    private ArrayList<Point2D> generatePoints(CoordAgent agent){
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
            if(!exist(point) || !isValid(point,nameFloor) || goneThere(point)){
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
    
    private boolean isValid(Point2D point, String nameFloor){
        boolean valid = true;
        Dimension plane = this.planes.get(nameFloor);
        int pointX = point.getX();
        int pointY = point.getY();
        
        String letter = plane.getValue(pointX, pointY);
        
        if(letter.equals(WALL_LETTER) || letter.equals(WINDOW_LETTER) ||
           letter.equals(ELEVATOR_LETTER) || letter.equals(STAIRS_LETTER)){
            valid = false;
        }
        
        return valid;
    }
    
    private boolean goneThere(Point2D point){
        boolean goneThere = false;
        Iterator routeIterator = this.route.iterator();
        
        while(goneThere == false && routeIterator.hasNext()){
            CoordAgent coordinateAgent = (CoordAgent) routeIterator.next();
            Point2D agentPoint = coordinateAgent.getCoordinate();
            if(point.equals(agentPoint)){
                goneThere = true;
            }
        }
        
        return goneThere;
    }
    
    protected static boolean isNumeric(String value){
        try{
            Integer.parseInt(value);
            return true;
        }catch(NumberFormatException nfe){
            return false;
        }
    }
    
    @Override
    public int compareTo(Object agent){
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
    public boolean equals(Object agent){
        return false;
    }
    
    @Override
    public abstract AgentRoute clone();
}
