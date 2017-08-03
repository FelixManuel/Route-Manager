package Building_Scheme.Classes;

import Building_Scheme.Utilities.Coordinate;
import Building_Scheme.Utilities.Dimension;
import Building_Scheme.Utilities.Point2D;
import java.util.ArrayList;

/**
 * @author Felix Manuel Mellado
 */
public class Stairs {
    //Attributes
    private String name;
    private Coordinate coordinate;
    private ArrayList<String> floors;
    private static int stairsNumber;
    
    //Letter Attributes
    private static final String REPRESENTATIVE_LETTER = "S";
    
    //Constructor
    public Stairs(){
        this.name = "Stairs_" + stairsNumber++;
        this.coordinate = new Coordinate();
        this.floors = new ArrayList<>();
    }
    
    //Getter Methods
    private Coordinate getCoordinate(){
        return this.coordinate;
    }
    
    //Methods
    public void createStairs(Dimension plane){
        Point2D startingPoint = this.coordinate.getCoordinate()[0];
        Point2D finalPoint = this.coordinate.getCoordinate()[1];
        
        plane.setValue(startingPoint.getX(), startingPoint.getY(), REPRESENTATIVE_LETTER);
        plane.setValue(finalPoint.getX(), finalPoint.getY(), REPRESENTATIVE_LETTER);
        
        for (int i = startingPoint.getX(); i < finalPoint.getX(); i++) {
            plane.setValue(i, startingPoint.getY(), REPRESENTATIVE_LETTER);
            plane.setValue(i, finalPoint.getY(), REPRESENTATIVE_LETTER);
        }
        
        for (int i = startingPoint.getY(); i < finalPoint.getY(); i++) {
            plane.setValue(startingPoint.getX(), i, REPRESENTATIVE_LETTER);
            plane.setValue(finalPoint.getX(), i, REPRESENTATIVE_LETTER);
        }
    }
}
