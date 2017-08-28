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
    
    public void floorTemperatureStatus(ArrayList<RoomStatus> rooms){
        for(RoomStatus room: rooms){
            String roomName = room.getName();
            
            if(this.rooms.containsKey(roomName)){
                Dimension temperaturePlane = this.planes.get("Temperature");
                Integer roomTemperature = room.getTemperature();
                
                this.rooms.get(roomName).roomStatus(temperaturePlane,roomTemperature);
            }
        }
    }
    
    /*ESTO SE ELIMINARÁ YA QUE NO SERÁ NECESARIO. ACTUALMENTE SE UTILIZA
      PARA COMPROBAR QUE SE FORMAN BIEN LOS PLANOS.*/    
    public void print(){
        System.out.println(this.plane.toString());
        System.out.println(this.planes.get("Temperature").toStringTemperaturePlane());
    }
}
