package Building_Scheme;

import Building_Scheme.Classes.Floor;
import java.util.ArrayList;

/**
 * @author Felix Manuel Mellado
 */
public class BuildingScheme {
    //Attributes
    private String name;
    private ArrayList<Floor> floors;
    private static int buildingSchemeNumber;
    
    //Constructor
    public BuildingScheme(){
        this.name = "Building_" + buildingSchemeNumber++;
        this.floors = new ArrayList<>();
    }
    
    //Getter Methods
    public ArrayList<Floor> getFloors(){
        return this.floors;
    }

    //Methods
    public void buildFloors(){
        for(Floor floor: this.floors){
            floor.createBuildingPlane();
        }
    }
}
