package Building_Scheme.Classes;

import Building_Scheme.Utilities.CoordElemBuilding;
import Building_Scheme.Utilities.Dimension;
import Utilities.Point2D;

/**
 * This class represents the Door in the building
 * @author Felix Manuel Mellado
 */
public class Door {
    //Attributes
    private CoordElemBuilding coordinate;
    
    //Letter Attribute
    private static final String REPRESENTATIVE_LETTER = "D";
    
    //Getter Methods

    /**
     * Returns the door coordinate in the plane.
     * @return coordinate
     */
    public CoordElemBuilding getCoordinate(){
        return this.coordinate;
    }
    
    //Methods

    /**
     * Create de door in the plane.
     * @param plane
     */
    public void createDoor(Dimension plane){
        Point2D startingPoint = this.coordinate.getCoordinate()[0];
        Point2D finalPoint = this.coordinate.getCoordinate()[1];
        
        int startingPointX = startingPoint.getX();
        int startingPointY = startingPoint.getY();
        int finalPointX = finalPoint.getX();
        int finalPointY = finalPoint.getY();
        
        plane.setValue(startingPointX, startingPointY, REPRESENTATIVE_LETTER);
        plane.setValue(finalPointX, finalPointY, REPRESENTATIVE_LETTER);
        
        if(startingPointX == finalPointX){
            for (int i = startingPointY; i <= finalPointY; i++) {
                plane.setValue(startingPointX, i, REPRESENTATIVE_LETTER);
            }
        }else{
            for (int i = startingPointX; i <= finalPointX; i++) {
                plane.setValue(i, startingPointY, REPRESENTATIVE_LETTER);
            }
        }
    }
}
