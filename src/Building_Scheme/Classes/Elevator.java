
package Building_Scheme.Classes;

import Building_Scheme.Utilities.CoordinateElementBuilding;
import Building_Scheme.Utilities.Dimension;
import Utilities.Point2D;
import java.util.HashMap;

/**
 * @author Felix Manuel Mellado
 */
public class Elevator {
    //Attributes
    private CoordinateElementBuilding coordinate;
    private HashMap<String,Boolean> floors;
    
    //Letter Attribute
    private static final String REPRESENTATIVE_LETTER = "E";
    
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
