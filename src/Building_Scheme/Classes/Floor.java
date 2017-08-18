package Building_Scheme.Classes;

import Building_Scheme.Utilities.Dimension;
import java.util.ArrayList;

/**
 * @author Felix Manuel Mellado
 */
public class Floor {
    //Attributes
    private String name;
    private Dimension plane;
    private ArrayList<Room> rooms;
    private ArrayList<Elevator> elevators;
    private ArrayList<Stairs> stairs;
    private ArrayList<Door> doors;
    private ArrayList<Window> windows;
    private static int floorNumber;
    
    //Constructor
    public Floor(){
        this.name = "Floor_" + this.floorNumber++;
        this.plane = new Dimension();
        this.rooms = new ArrayList<>();
        this.elevators = new ArrayList<>();
        this.stairs = new ArrayList<>();
        this.doors = new ArrayList<>();
        this.windows = new ArrayList<>();
    }
    
    //Getter Methods
    public Dimension getPlane(){
        return this.plane;
    }

    //Methods
    public void createFloorPlane(){
        this.plane.createPlane();
        
        for(Room room: this.rooms){
            room.createRoom(this.plane);
        }
        
        for (Elevator elevator: this.elevators){
            elevator.createElevator(this.plane);
        }
        
        for (Stairs stairs: this.stairs){
            stairs.createStairs(this.plane);
        }
        
        for (Door door: this.doors){
            door.createDoor(plane);
        }
        
        for (Window windows: this.windows){
            windows.createWindow(plane);
        }
    }
}
