
package Building_Scheme.Classes;

import Building_Scheme.Utilities.Coordinate;
import Building_Scheme.Utilities.Dimension;
import Building_Scheme.Utilities.Point2D;
import java.util.ArrayList;

/**
 * @author Felix Manuel Mellado
 */
public class Elevator {
    //Attributes
    private String name;
    private Coordinate coordinate;
    private ArrayList<String> floors;
    private static int elevatorNumber;
    
    //Constructor
    public Elevator(){
        this.name = "Elevator_" + elevatorNumber++;
        this.coordinate = new Coordinate();
        this.floors = new ArrayList<>();
    }
    
    public Elevator(String name, Coordinate coordinate, ArrayList<String> floors){
        this.name = name;
        this.coordinate = coordinate;
        this.floors = floors;
    }
    
    //Getter Methods
    public String getName(){
        return this.name;
    }
    
    public Coordinate getCoordinate(){
        return this.coordinate;
    }
    
    public ArrayList<String> getFloors(){
        return this.floors;
    }
    
    //Methods
    public void createElevator(Dimension plane){
        Point2D startingPoint = this.coordinate.getCoordinate()[0];
        Point2D finalPoint = this.coordinate.getCoordinate()[1];
    }
}
