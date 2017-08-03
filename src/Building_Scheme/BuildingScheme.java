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
    
    public BuildingScheme(String name, ArrayList<Floor> floors){
        this.name = name;
        this.floors = floors;
    }
    
    //Getter Methods
    public ArrayList<Floor> getFloors(){
        return this.floors;
    }
    
    public String getName(){
        return this.name;
    }
    
    //Methods
    public void buildFloors(){
        for(Floor floor: this.floors){
            floor.createBuildingPlane();
        }
    }
}
