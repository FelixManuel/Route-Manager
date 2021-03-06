package Building_Scheme.Classes;

import Building_Scheme.Utilities.CoordElemBuilding;
import Building_Scheme.Utilities.Dimension;
import Utilities.Point2D;
import java.util.HashMap;

/**
 * @author Felix Manuel Mellado
 */
public class Stairs {
    //Attributes
    private CoordElemBuilding coordinate;
    private HashMap<String,Boolean> floors;
    
    //Letter Attributes
    private static final String REPRESENTATIVE_LETTER = "S";
    
    //Methods
    public void createStairs(Dimension plane){
        Point2D startingPoint = this.coordinate.getCoordinate()[0];
        Point2D finalPoint = this.coordinate.getCoordinate()[1];
        
        int startingPointX = startingPoint.getX();
        int startingPointY = startingPoint.getY();
        int finalPointX = finalPoint.getX();
        int finalPointY = finalPoint.getY();
        
        plane.setValue(startingPointX, startingPointY, REPRESENTATIVE_LETTER);
        plane.setValue(finalPointX, finalPointY, REPRESENTATIVE_LETTER);
        
        for (int i = startingPointX; i < finalPointX; i++) {
            plane.setValue(i, startingPointY, REPRESENTATIVE_LETTER);
            plane.setValue(i, finalPointY, REPRESENTATIVE_LETTER);
        }
        
        for (int i = startingPointY; i < finalPointY; i++) {
            plane.setValue(startingPointX, i, REPRESENTATIVE_LETTER);
            plane.setValue(finalPointX, i, REPRESENTATIVE_LETTER);
        }
    }
}
