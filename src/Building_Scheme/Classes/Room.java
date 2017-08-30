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
    
    //Methods
    public void createRoom(Dimension plane){
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
        
        int startingPointX = startingPoint.getX();
        int startingPointY = startingPoint.getY();
        int finalPointX = finalPoint.getX();
        int finalPointY = finalPoint.getY();
        
        for (int i = startingPointX; i < finalPointX; i++) {
            for (int j = startingPointY; j < finalPointY; j++) {
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
