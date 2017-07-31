package Building_Scheme.Classes;

import Building_Scheme.Utilities.Coordinate;
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
    
    //Constructor
    public Room(){
        this.name = "Room_" + roomNumber++;
        this.coordinate = new Coordinate();
        this.doors = new ArrayList<>();
        this.windows = new ArrayList<>();
    }
    
    public Room(String name, Coordinate coordinate, ArrayList<Door> doors, ArrayList<Window> windows){
        this.name = name;
        this.coordinate = coordinate;
        this.doors = doors;
        this.windows = windows;
    }
    
    //Getter Methods
    public String getName(){
        return this.name;
    }
    
    public Coordinate getCoordinate(){
        return this.coordinate;
    }
    
    public ArrayList<Door> getDoors(){
        return this.doors;
    }
    
    public ArrayList<Window> getWindows(){
        return this.windows;
    }
}
