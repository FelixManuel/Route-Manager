package Building_Scheme;

import Building_Scheme.Classes.Floor;
import Building_Status.FloorStatus;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Felix Manuel Mellado
 */
public class BuildingScheme {
    //Attributes
    private String name;
    private HashMap<String,Floor> floors;
    private static int buildingSchemeNumber;
    
    //Constructor
    public BuildingScheme(){
        this.name = "Building_" + buildingSchemeNumber++;
        //this.floors = new ArrayList<>();
    }

    //Methods
    public void buildFloors(){
        for(Floor floor: this.floors.values()){
            floor.createFloorPlane();
        }
    }
    
    public void updateFloors(FloorStatus floor){
        if(floors.containsKey(floor.getName())){
            floors.get(floor.getName()).createFloorTemperaturePlane(floor.getRooms());
        }
    }
}
