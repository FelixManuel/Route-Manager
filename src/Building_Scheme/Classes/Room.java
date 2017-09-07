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
    private static final String HALL_LETTER = "H";
    private static final String EMPTY_LETTER = " ";
    
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
    
    public void roomStatus(Dimension schematicPlane, Dimension temperaturePlane, Integer temperature){
        Point2D startingPoint = this.coordinate.getCoordinate()[0];
        Point2D finalPoint = this.coordinate.getCoordinate()[1];
        
        int startingPointX = startingPoint.getX();
        int startingPointY = startingPoint.getY();
        int finalPointX = finalPoint.getX();
        int finalPointY = finalPoint.getY();
        
        for (int i = startingPointX; i < finalPointX; i++) {
            for (int j = startingPointY; j < finalPointY; j++) {
                if(isNumeric(temperaturePlane.getValue(i, j))){
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
        
        for(int i = startingPointY; i<=finalPointY; i++){
            if(startingPointX-1 >= 0){
                String value = schematicPlane.getValue(startingPointX-1, i);
                if(value.equals(HALL_LETTER)){
                    Integer planeTemperature = Integer.parseInt(temperaturePlane.getValue(startingPointX-1, i));
                    Integer newTemperature = (temperature+planeTemperature)/2;
                    temperaturePlane.setValue(startingPointX-1, i, newTemperature.toString());
                }
            }
            if(finalPointX+1 <= schematicPlane.getRows()-1){
                String value = schematicPlane.getValue(finalPointX+1, i);
                if(value.equals(HALL_LETTER)){
                    Integer planeTemperature = Integer.parseInt(temperaturePlane.getValue(finalPointX+1, i));
                    Integer newTemperature = (temperature+planeTemperature)/2;
                    temperaturePlane.setValue(finalPointX+1, i, newTemperature.toString());
                }
            }
        }
        
        for(int i = startingPointX; i<=finalPointX; i++){
            if(startingPointY-1 >= 0){
                String value = schematicPlane.getValue(i, startingPointY-1);
                if(value.equals(HALL_LETTER)){
                    Integer planeTemperature = Integer.parseInt(temperaturePlane.getValue(i, startingPointY-1));
                    Integer newTemperature = (temperature+planeTemperature)/2;
                    temperaturePlane.setValue(i, startingPointY-1, newTemperature.toString());
                }
            }
            if(finalPointY+1 <= schematicPlane.getColumns()-1){
                String value = schematicPlane.getValue(i, finalPointY+1);
                if(value.equals(HALL_LETTER)){
                    Integer planeTemperature = Integer.parseInt(temperaturePlane.getValue(i, finalPointY+1));
                    Integer newTemperature = (temperature+planeTemperature)/2;
                    temperaturePlane.setValue(i, finalPointY+1, newTemperature.toString());
                }
            }
        }
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
