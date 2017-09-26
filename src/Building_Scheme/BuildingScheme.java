package Building_Scheme;

import Building_Scheme.Classes.Floor;
import Building_Status.FloorStatus;
import Building_Status.RoomStatus;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class represents the building scheme.
 * @author Felix Manuel Mellado
 */
public class BuildingScheme {
    //Attributes
    private String name;
    private HashMap<String,Floor> floors;
    
    //Obligatory Attribute
    private static final String FIRST_FLOOR = "floor_0";
    
    //GetterMethods

    /**
     * Returns the first floor of the building. It help us to extract
     * the building exits, dimensions...
     * @return First floor
     */
    public Floor getFirstFloor(){
        return this.floors.get(FIRST_FLOOR);
    }
    
    /**
     * Returns the floors of the building.
     * @return floors
     */
    public HashMap<String, Floor> getFloors(){
        return this.floors;
    }

    //Methods

    /**
     * Build the differents floors of the building. Actually it builds:
     * - Normal Plane.
     * - Temperature Plane.
     * - Schematic Plane.
     */
    public void buildFloors(){
        for(Floor floor: this.floors.values()){
            floor.createFloorPlane();            
            floor.createTemperatureFloorPlane();
            floor.createSchematicFloor();
        }
    }
    
    /**
     * Update the rooms temperature of one floor.
     * @param floor
     */
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
