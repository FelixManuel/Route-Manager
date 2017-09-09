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
    
    //Names Planes
    private static final String TEMPERATURE_PLANE = "Temperature";
    private static final String SCHEMATIC_PLANE = "Schematic";
    
    //Getter Methods    
    public Dimension getPlane(){
        return this.plane;
    }
    
    public Dimension getTemperaturePlane(){
        return this.planes.get(TEMPERATURE_PLANE);
    }

    //Methods
    public void createFloorPlane(){
        this.plane.createPlane();
        createFloorElements(this.plane);
    }
    
    public void createTemperatureFloorPlane(){
        Dimension temperaturePlane = new Dimension();
        
        temperaturePlane.cloneRowsColumns(this.plane);
        temperaturePlane.createTemperaturePlane();        
        createFloorElements(temperaturePlane);
        
        this.planes.put(TEMPERATURE_PLANE, temperaturePlane);
    }
    
    public void createSchematicFloor(){
        Dimension schematicPlane = new Dimension();
        
        schematicPlane.cloneRowsColumns(this.plane);
        schematicPlane.createSchematicPlane();
        createFloorElements(schematicPlane);
        cleanSchematicRooms(schematicPlane);
        
        this.planes.put(SCHEMATIC_PLANE, schematicPlane);
    }
    
    private void cleanSchematicRooms(Dimension plane){
        for(Room room: this.rooms.values()){
            room.cleanRoom(plane);
        }
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
        Dimension temperaturePlane = this.planes.get(TEMPERATURE_PLANE);
        Dimension schematicPlane = this.planes.get(SCHEMATIC_PLANE);
        
        for(RoomStatus room: rooms){
            String roomName = room.getName();
            
            if(this.rooms.containsKey(roomName)){
                Integer roomTemperature = room.getTemperature();                
                this.rooms.get(roomName).roomStatus(schematicPlane,temperaturePlane,roomTemperature);
            }
        }
    }
    
    /*ESTO SE ELIMINARÁ YA QUE NO SERÁ NECESARIO. ACTUALMENTE SE UTILIZA
      PARA COMPROBAR QUE SE FORMAN BIEN LOS PLANOS.*/    
    public void print(){
        System.out.println(this.plane.toString());
        System.out.println(this.planes.get("Temperature").toStringTemperaturePlane());
        System.out.println(this.planes.get("Schematic").toString());
    }
}
