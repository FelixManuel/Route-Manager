package Building_Scheme.Classes;

import Building_Scheme.Utilities.CoordinateElementBuilding;
import Building_Scheme.Utilities.Dimension;
import Utilities.Point2D;
import java.util.HashMap;

/**
 * @author Felix Manuel Mellado
 */
public class Room {
    //Attributes
    private CoordinateElementBuilding coordinate;
    private HashMap<String,Door> doors;
    private HashMap<String,Window> windows;
    
    //Letter Attribute
    private static final String REPRESENTATIVE_LETTER = "W";
    
    //Getter Methods
    private CoordinateElementBuilding getCoordinate(){
        return this.coordinate;
    }
    
    //Methods
    public void createRoom(Dimension plane){
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
        
        for(Door door: this.doors.values()){
            door.createDoor(plane);
        }
        
        for(Window window: this.windows.values()){
            window.createWindow(plane);
        }
    }
    
    public void roomStatus(Dimension plane, Integer temperature){
        Point2D startingPoint = this.coordinate.getCoordinate()[0];
        Point2D finalPoint = this.coordinate.getCoordinate()[1];
        
        for (int i = startingPoint.getX(); i < finalPoint.getX(); i++) {
            for (int j = startingPoint.getY(); j < finalPoint.getY(); j++) {
                if(isNumeric(plane.getValue(i, j))){
                    plane.setValue(i, j, temperature.toString());
                }
            }
        }
    }
    
    private static boolean isNumeric(String string){
        try{
            Integer.parseInt(string);
            return true;
        }catch(NumberFormatException nfe){
            return false;
        }
    }
}
