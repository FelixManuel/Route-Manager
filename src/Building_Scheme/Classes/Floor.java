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
    private HashMap<String,Room> rooms;
    private HashMap<String,Elevator> elevators;
    private HashMap<String,Stairs> stairs;
    private HashMap<String,Door> doors;
    private HashMap<String,Window> windows;
    
    //Getter Methods    
    public Dimension getPlane(){
        return this.plane;
    }

    //Methods
    public void createFloorPlane(){
        this.plane.createPlane();
        createFloorElements(this.plane);
    }
    
    public void createTemperatureFloorPlane(){
        Dimension temperaturePlane = new Dimension();
        
        temperaturePlane.clone(this.plane);
        temperaturePlane.createTemperaturePlane();        
        createFloorElements(temperaturePlane);
        
        this.planes.put("Temperature", temperaturePlane);
    }
    
    private void createFloorElements(Dimension plane){        
        for(Room room: this.rooms.values()){
            room.createRoom(plane);
        }
        
        for (Elevator elevator: this.elevators.values()){
            elevator.createElevator(plane);
        }
        
        for (Stairs stairs: this.stairs.values()){
            stairs.createStairs(plane);
        }
        
        for (Door door: this.doors.values()){
            door.createDoor(plane);
        }
        
        for (Window windows: this.windows.values()){
            windows.createWindow(plane);
        }
    }
}
