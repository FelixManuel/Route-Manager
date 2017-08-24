package Building_Scheme.Classes;

import Building_Scheme.Utilities.CoordinateElementBuilding;
import Building_Scheme.Utilities.Dimension;
import Utilities.Point2D;
import java.util.ArrayList;

/**
 * @author Felix Manuel Mellado
 */
public class Room {
    //Attributes
    private String name;
    private CoordinateElementBuilding coordinate;
    private ArrayList<Door> doors;
    private ArrayList<Window> windows;
    private static int roomNumber;
    
    //Letter Attribute
    private static final String REPRESENTATIVE_LETTER = "W";
    
    //Constructor
    public Room(){
        this.name = "Room_" + roomNumber++;
        this.coordinate = new CoordinateElementBuilding();
        this.doors = new ArrayList<>();
        this.windows = new ArrayList<>();
    }
    
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
        
        for(Door door: this.doors){
            door.createDoor(plane);
        }
        
        for(Window window: this.windows){
            window.createWindow(plane);
        }
    }
    
    public void roomStatus(){
    
    }
}
