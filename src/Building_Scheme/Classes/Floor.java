package Building_Scheme.Classes;

import Building_Scheme.Utilities.Dimension;
import Building_Status.RoomStatus;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Felix Manuel Mellado
 */
public class Floor {
    //Attributes
    private Dimension plane;
    private HashMap<String,Dimension> planes;
    private ArrayList<Room> rooms;
    private ArrayList<Elevator> elevators;
    private ArrayList<Stairs> stairs;
    private ArrayList<Door> doors;
    private ArrayList<Window> windows;
    
    //Constructor
    public Floor(){
        this.plane = new Dimension();
        this.planes = new HashMap<>();
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
    
    public void createFloorTemperaturePlane(ArrayList<RoomStatus> rooms){
        if(!this.planes.containsKey("TEMPERATURE")){
            Dimension temperaturePlane = new Dimension();
            temperaturePlane.clone(this.plane);
            
            temperaturePlane.createPlane();
            for(Room room: this.rooms){
                room.roomStatus();
            }
            
            this.planes.put("TEMPERATURE", temperaturePlane);
        }
    }
}
