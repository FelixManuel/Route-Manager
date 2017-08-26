package Building_Scheme;

import Building_Scheme.Classes.Floor;
import Building_Status.FloorStatus;
import java.util.HashMap;

/**
 * @author Felix Manuel Mellado
 */
public class BuildingScheme {
    //Attributes
    private String name;
    private HashMap<String,Floor> floors;

    //Methods
    public void buildFloors(){
        for(Floor floor: this.floors.values()){
            floor.createFloorPlane();            
            floor.createTemperatureFloorPlane();
        }
    }
    
    public void updateTemperatureFloors(FloorStatus floor){
        if(floors.containsKey(floor.getName())){
            this.floors.get(floor.getName()).floorTemperatureStatus(floor.getRooms());
        }
    }
    
    public void print(){
        for(Floor floor: this.floors.values()){
            floor.print();            
        }
    }
}
