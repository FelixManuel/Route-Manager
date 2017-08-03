package Building_Scheme.Classes;

import Building_Scheme.Utilities.Coordinate;
import Building_Scheme.Utilities.Dimension;
import Building_Scheme.Utilities.Point2D;
import java.util.ArrayList;

/**
 * @author Felix Manuel Mellado
 */
public class Room {
    //Attributes
    private String name;
    private Coordinate coordinate;
    private ArrayList<Door> doors;
    private ArrayList<Window> windows;
    private static int roomNumber;
    
    //Letter Attribute
    private static final String REPRESENTATIVE_LETTER = "W";
    
    //Constructor
    public Room(){
        this.name = "Room_" + roomNumber++;
        this.coordinate = new Coordinate();
        this.doors = new ArrayList<>();
        this.windows = new ArrayList<>();
    }
    
    //Getter Methods
    private Coordinate getCoordinate(){
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
}
