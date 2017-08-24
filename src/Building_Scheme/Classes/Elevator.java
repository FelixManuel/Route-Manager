
package Building_Scheme.Classes;

import Building_Scheme.Utilities.CoordinateElementBuilding;
import Building_Scheme.Utilities.Dimension;
import Utilities.Point2D;
import java.util.ArrayList;

/**
 * @author Felix Manuel Mellado
 */
public class Elevator {
    //Attributes
    private String name;
    private CoordinateElementBuilding coordinate;
    private ArrayList<String> floors;
    private static int elevatorNumber;
    
    //Letter Attribute
    private static final String REPRESENTATIVE_LETTER = "E";
    
    //Constructor
    public Elevator(){
        this.name = "Elevator_" + elevatorNumber++;
        this.coordinate = new CoordinateElementBuilding();
        this.floors = new ArrayList<>();
    }
    
    //Getter Methods
    private CoordinateElementBuilding getCoordinate(){
        return this.coordinate;
    }
    
    //Methods
    public void createElevator(Dimension plane){
        Point2D startingPoint = this.coordinate.getCoordinate()[0];
        Point2D finalPoint = this.coordinate.getCoordinate()[1];
        
        plane.setValue(startingPoint.getX(), finalPoint.getY(), REPRESENTATIVE_LETTER);
        plane.setValue(finalPoint.getX(), finalPoint.getY(), REPRESENTATIVE_LETTER);
        
        for (int i = startingPoint.getX(); i < finalPoint.getX(); i++) {
            plane.setValue(i, startingPoint.getY(), REPRESENTATIVE_LETTER);
            plane.setValue(i, finalPoint.getY(), REPRESENTATIVE_LETTER);
        }
        
        for (int i = startingPoint.getY(); i < finalPoint.getY(); i++) {
            plane.setValue(startingPoint.getX(), i, REPRESENTATIVE_LETTER);
            plane.setValue(finalPoint.getY(), i, REPRESENTATIVE_LETTER);
        }
    }
}
