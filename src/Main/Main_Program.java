package Main;

import Agent_Scheme.AgentScheme;
import Agent_Scheme.Utilities.CoordinateAgent;
import Building_Scheme.BuildingScheme;
import Building_Status.FloorStatus;
import File.FileInformation;
import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Felix Manuel Mellado
 */
public class Main_Program {
    //Attributes
    private BuildingScheme building;
    private HashMap<Integer,CoordinateAgent> agents;
    private static final String AGENT_FOLDER = "agentScheme//";
    private static final String BUILDINGSTATUS_FOLDER = "buildingStatus//";
    
    //Constructor
    public Main_Program(BuildingScheme building){
        this.building = building;
        this.agents = new HashMap<>();
        
        this.building.buildFloors();
        loadingAgents();
        loadingBuildingStatus();
    }
    
    //Methods
    private void loadingAgents(){     
        AgentScheme agentScheme;
        File agentFolder = new File(AGENT_FOLDER);
        File[] listOfAgents = agentFolder.listFiles();
        
        for(File file: listOfAgents){
            agentScheme = FileInformation.extractAgentInformation(file.getName());
            this.agents.put(agentScheme.getIdentification(), agentScheme.getCoordinate());
        }
    }
    
    private void loadingBuildingStatus(){
        FloorStatus floorStatus;
        File buildingStatusFolder = new File(BUILDINGSTATUS_FOLDER);
        File[] listOfFloorStatus = buildingStatusFolder.listFiles();
        
        for(File file: listOfFloorStatus){
            floorStatus = FileInformation.extractFloorStatusInformation(file.getName());
            this.building.updateFloors(floorStatus);
        }
    }
    
    public static void main(String args[]){               
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Insert the name of the file(add '.json' at the end): "); 
        String fileName = sc.next();        
        
        BuildingScheme building = FileInformation.extractBuildingInformation(fileName);
        Main_Program controller = new Main_Program(building);
    }
}
