package Main;

import Building_Scheme.BuildingScheme;
import Building_Scheme.Classes.Floor;
import File.BuildingSchemeFile;
import java.util.Scanner;

/**
 * @author Felix Manuel Mellado
 */
public class Main_Program {
    
    public static void main(String args[]){               
        Scanner sc = new Scanner(System.in);
        System.out.println("Insert the name of the file(add '.json' at the end): "); 
        String fileName = sc.next();
        
        BuildingSchemeFile buildingSchemeFile = new BuildingSchemeFile(fileName);
        BuildingScheme building = buildingSchemeFile.getBuilding();
        building.buildFloors();
        
        for (Floor floor: building.getFloors()) {            
            System.out.println(floor.getPlane().toString());
        }
    }
}
