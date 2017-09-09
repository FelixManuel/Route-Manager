package Main;

import Agent_Scheme.AgentScheme;
import Agent_Scheme.Utilities.CoordinateAgent;
import Building_Scheme.BuildingScheme;
import Building_Scheme.Classes.Door;
import Building_Scheme.Classes.Floor;
import Building_Scheme.Utilities.Dimension;
import Building_Status.FloorStatus;
import File.FileInformation;
import Route.AgentRoute;
import Utilities.Point2D;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Felix Manuel Mellado
 */
public class Main_Program {
    //Attributes
    private BuildingScheme building;
    private HashMap<Integer,AgentScheme> agents;
    private static final String AGENT_FOLDER = "agentScheme//";
    private static final String BUILDINGSTATUS_FOLDER = "buildingStatus//";
    
    //Identificate Attributes
    private static final String DOOR_LETTER = "D";
    
    //Constructor
    public Main_Program(BuildingScheme building){
        this.building = building;
        this.agents = new HashMap<>();
        
        this.building.buildFloors();
        
        this.building.print();
        System.out.println("--------------------");
        
        loadingAgents();
        loadingBuildingStatus();
        
        this.building.print();
        System.out.println("--------------------");
        
        fireAlarm();
    }
    
    //Methods
    private void loadingAgents(){     
        AgentScheme agentScheme;
        File agentFolder = new File(AGENT_FOLDER);
        File[] listOfAgents = agentFolder.listFiles();
        
        for(File file: listOfAgents){
            agentScheme = FileInformation.extractAgentInformation(file.getName());
            this.agents.put(agentScheme.getIdentification(), agentScheme);
        }
    }
    
    private void loadingBuildingStatus(){
        FloorStatus floorStatus;
        File buildingStatusFolder = new File(BUILDINGSTATUS_FOLDER);
        File[] listOfFloorStatus = buildingStatusFolder.listFiles();
        
        for(File file: listOfFloorStatus){
            floorStatus = FileInformation.extractFloorStatusInformation(file.getName());
            this.building.updateTemperatureFloors(floorStatus);
        }
    }
    
    private void fireAlarm(){
        ArrayList<Point2D> exits = getExits();
        HashMap<String, Dimension> temperaturePlanes = getTemperaturePlanes();
        int rows = this.building.getFirstFloor().getPlane().getRows();
        int columns = this.building.getFirstFloor().getPlane().getColumns();
        
        for(AgentScheme agent: this.agents.values()){
            CoordinateAgent coordinateAgent = agent.getCoordinate();
            AgentRoute agentRoute = new AgentRoute(exits, temperaturePlanes, rows, columns, coordinateAgent);
        }
    }
    
    private ArrayList<Point2D> getExits(){
        ArrayList<Point2D> exits = new ArrayList<>();        
        Point2D exitPoint;
        Dimension plane = this.building.getFirstFloor().getPlane();
        
        for(int column = 0; column<plane.getColumns()-1; column++){
            String firsRowValue = plane.getValue(0, column);
            String lastRowValue = plane.getValue(plane.getRows()-1, column);
            if(firsRowValue.equals(DOOR_LETTER)){
                exitPoint = new Point2D(0, column);
                exits.add(exitPoint);
            }
            if(lastRowValue.equals(DOOR_LETTER)){
                exitPoint = new Point2D(plane.getRows()-1, column);
                exits.add(exitPoint);
            }
        }
        
        for(int row = 0; row<plane.getRows()-1; row++){
            String firstColumnValue = plane.getValue(row, 0);
            String lastColumnValue = plane.getValue(row, plane.getColumns()-1);
            if(firstColumnValue.equals(DOOR_LETTER)){
               exitPoint = new Point2D(row, 0);
               exits.add(exitPoint);
            }
            if(lastColumnValue.equals(DOOR_LETTER)){
               exitPoint = new Point2D(row, plane.getColumns()-1);
               exits.add(exitPoint);
            }
        }
        
        return exits;
    }
    
    private HashMap<String, Dimension> getTemperaturePlanes(){
        HashMap<String, Dimension> temperaturePlanes = new HashMap<>();
        HashMap<String, Floor> floors = this.building.getFloors();
        for(String floorName: floors.keySet()){
            Dimension temperaturePlane = floors.get(floorName).getTemperaturePlane();
            Dimension newTemperaturePlane = (Dimension) temperaturePlane.clone();
            temperaturePlanes.put(floorName, newTemperaturePlane);
        }
        
        return temperaturePlanes;
    }
    
    public static void main(String args[]){               
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Insert the name of the file(add '.json' at the end): "); 
        String fileName = sc.next();        
        
        BuildingScheme building = FileInformation.extractBuildingInformation(fileName);
        Main_Program controller = new Main_Program(building);
    }
}
