package Building_Scheme;

import Building_Scheme.Classes.Floor;
import Building_Status.FloorStatus;
import Building_Status.RoomStatus;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Felix Manuel Mellado
 */
public class BuildingScheme {
    //Attributes
    private String name;
    private HashMap<String,Floor> floors;
    
    //Obligatory Attribute
    private static String FIRST_FLOOR = "floor_0";
    
    //GetterMethods
    public Floor getFirstFloor(){
        return this.floors.get(FIRST_FLOOR);
    }
    
    public HashMap<String, Floor> getFloors(){
        return this.floors;
    }

    //Methods
    public void buildFloors(){
        for(Floor floor: this.floors.values()){
            floor.createFloorPlane();            
            floor.createTemperatureFloorPlane();
            floor.createSchematicFloor();
        }
    }
    
    public void updateTemperatureFloors(FloorStatus floor){
        String floorName = floor.getName();
        ArrayList<RoomStatus> floorRooms= floor.getRooms();
        
        if(floors.containsKey(floorName)){
            this.floors.get(floorName).floorTemperatureStatus(floorRooms);
        }
    }
    
    /*ESTO SE ELIMINARÁ YA QUE NO SERÁ NECESARIO. ACTUALMENTE SE UTILIZA
      PARA COMPROBAR QUE SE FORMAN BIEN LOS PLANOS.*/
    public void print(){
        for(Floor floor: this.floors.values()){
            floor.print();            
        }
    }
}
