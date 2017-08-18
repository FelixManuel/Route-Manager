package Building_Scheme.Classes;

import Utilities.Coordinate;
import Building_Scheme.Utilities.Dimension;
import Utilities.Point2D;

/**
 * @author Felix Manuel Mellado
 */
public class Door {
    //Attributes
    private String name;
    private Coordinate coordinate;
    private static int doorNumber;
    
    //Letter Attribute
    private static final String REPRESENTATIVE_LETTER = "D";
    
    //Constructor
    public Door(){
        this.name = "Door_" + doorNumber;
        this.coordinate = new Coordinate();
    }
    
    //Getter Methods
    private Coordinate getCoordinate(){
        return this.coordinate;
    }
    
    //Methods
    public void createDoor(Dimension plane){
        Point2D startingPoint = this.coordinate.getCoordinate()[0];
        Point2D finalPoint = this.coordinate.getCoordinate()[1];
        
        plane.setValue(startingPoint.getX(), startingPoint.getY(), REPRESENTATIVE_LETTER);
        plane.setValue(finalPoint.getX(), finalPoint.getY(), REPRESENTATIVE_LETTER);
        
        if(startingPoint.getX() == finalPoint.getX()){
            for (int i = startingPoint.getY(); i <= finalPoint.getY(); i++) {
                plane.setValue(startingPoint.getX(), i, REPRESENTATIVE_LETTER);
            }
        }else{
            for (int i = startingPoint.getX(); i <= finalPoint.getX(); i++) {
                plane.setValue(i, startingPoint.getY(), REPRESENTATIVE_LETTER);
            }
        }
    }
}
