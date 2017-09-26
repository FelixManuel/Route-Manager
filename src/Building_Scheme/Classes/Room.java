package Building_Scheme.Classes;

import Building_Scheme.Utilities.CoordElemBuilding;
import Building_Scheme.Utilities.Dimension;
import Utilities.Point2D;
import java.util.HashMap;

/**
 * @author Felix Manuel Mellado
 */
public class Room {
    //Attributes
    private CoordElemBuilding coordinate;
    private HashMap<String,Door> doors;
    private HashMap<String,Window> windows;
    
    //Letter Attribute
    private static final String REPRESENTATIVE_LETTER = "W";
    private static final String HALL_LETTER = "H";
    private static final String EMPTY_LETTER = " ";
    private static final String DOOR_LETTER = "D";
    
    //Methods
    public void createRoom(Dimension plane){
        Point2D startingPoint = this.coordinate.getCoordinate()[0];
        Point2D finalPoint = this.coordinate.getCoordinate()[1];        
        int startingPointX = startingPoint.getX();
        int startingPointY = startingPoint.getY();
        int finalPointX = finalPoint.getX();
        int finalPointY = finalPoint.getY();        
        
        for (int row = startingPointX; row <= finalPointX; row++) {
            plane.setValue(row, startingPointY, REPRESENTATIVE_LETTER);
            plane.setValue(row, finalPointY, REPRESENTATIVE_LETTER);
        }        
        for (int i = startingPointY; i <= finalPointY; i++) {
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
    
    public void roomStatus(Dimension schematicPlane, Dimension temperaturePlane, Integer temperature){
        Point2D startingPoint = this.coordinate.getCoordinate()[0];
        Point2D finalPoint = this.coordinate.getCoordinate()[1];
        
        int startingPointX = startingPoint.getX();
        int startingPointY = startingPoint.getY();
        int finalPointX = finalPoint.getX();
        int finalPointY = finalPoint.getY();
        
        for (int i = startingPointX; i <= finalPointX; i++) {
            for (int j = startingPointY; j <= finalPointY; j++) {
                if(isNumeric(temperaturePlane.getValue(i, j)) || temperaturePlane.getValue(i, j).equals(DOOR_LETTER)){
                    temperaturePlane.setValue(i, j, temperature.toString());
                }
            }
        }
        
        readjustHallTemperature(schematicPlane, temperaturePlane, temperature);
    }
    
    private void readjustHallTemperature(Dimension schematicPlane, Dimension temperaturePlane, Integer temperature){
        Point2D startingPoint = this.coordinate.getCoordinate()[0];
        Point2D finalPoint = this.coordinate.getCoordinate()[1];        
        int startingPointX = startingPoint.getX();
        int startingPointY = startingPoint.getY();
        int finalPointX = finalPoint.getX();
        int finalPointY = finalPoint.getY();
        
        for(int column = startingPointY; column<=finalPointY; column++){
            if(startingPointX-1 >= 0 && hasHallValue(schematicPlane, startingPointX-1, column)){
                addTemperature(temperaturePlane, startingPointX-1, column, temperature);
            }
            if(finalPointX+1 <= schematicPlane.getRows()-1 && hasHallValue(schematicPlane, finalPointX+1, column)){
                addTemperature(temperaturePlane, finalPointX+1, column, temperature);               
            }
        }        
        for(int row = startingPointX; row<=finalPointX; row++){
            if(startingPointY-1 >= 0 && hasHallValue(schematicPlane, row, startingPointY-1)){
                addTemperature(temperaturePlane, row, startingPointY-1, temperature);             
            }
            if(finalPointY+1 <= schematicPlane.getColumns()-1 && hasHallValue(schematicPlane, row, finalPointY+1)){
                addTemperature(temperaturePlane, row, finalPointY+1, temperature);               
            }
        }
    }
    
    private boolean hasHallValue(Dimension schematicPlane, int row, int column){
        return schematicPlane.getValue(row, column).equals(HALL_LETTER);
    }    
        
    private void addTemperature(Dimension temperaturePlane, int row, int column, Integer temperature){
        Integer planeTemperature = Integer.parseInt(temperaturePlane.getValue(row, column));
        Integer newTemperature = (temperature+planeTemperature)/2;
        temperaturePlane.setValue(row, column, newTemperature.toString());
    }
    
    public void cleanRoom(Dimension plane){
        Point2D startingPoint = this.coordinate.getCoordinate()[0];
        Point2D finalPoint = this.coordinate.getCoordinate()[1];
        
        int startingPointX = startingPoint.getX();
        int startingPointY = startingPoint.getY();
        int finalPointX = finalPoint.getX();
        int finalPointY = finalPoint.getY();
        
        for (int i = startingPointX; i< finalPointX; i++){
            for(int j = startingPointY; j < finalPointY; j++){
                if(plane.getValue(i, j).equals(HALL_LETTER)){
                    plane.setValue(i, j, EMPTY_LETTER);
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
