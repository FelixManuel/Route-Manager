package Controller;

import Agent_Scheme.AgentScheme;
import Agent_Scheme.Utilities.CoordAgent;
import Building_Scheme.BuildingScheme;
import Building_Scheme.Classes.Floor;
import Building_Scheme.Utilities.Dimension;
import Building_Status.FloorStatus;
import File.FileInformation;
import Route.AgentRoute;
import Route.EvacuationAgentRoute;
import Route.FireAgentRoute;
import Route.Route;
import Route.RouteBranchPruning;
import Utilities.Point2D;
import com.google.gson.Gson;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Felix Manuel Mellado
 */
public class Controller {
    //Attributes
    private BuildingScheme building;
    private HashMap<Integer,AgentScheme> agents;
    private static final String BUILDING_FOLDER = "buildingScheme//";
    private static final String AGENT_FOLDER = "agentScheme//";
    private static final String BUILDINGSTATUS_FOLDER = "buildingStatus//";
    
    //Identificate Attributes
    private static final String DOOR_LETTER = "D";
    
    //Constructor
    public Controller(){
        loadingBuilding();
        this.building.buildFloors();
        this.agents = new HashMap<>();
    }
    
    public Controller(String buildingString){
        Gson gson = new Gson();
        BuildingScheme building = gson.fromJson(buildingString, BuildingScheme.class);
        this.building = building;
        this.building.buildFloors();
        this.agents = new HashMap<>();
    }
    
    //Methods
    public void addAgent(String agentString){
        Gson gson = new Gson();
        AgentScheme agent = gson.fromJson(agentString, AgentScheme.class);
        this.agents.put(agent.getIdentification(), agent);
    }
    
    public void addAgents(ArrayList<String> agents){
        Gson gson = new Gson();
        for(String agentString: agents){
            AgentScheme agent = gson.fromJson(agentString, AgentScheme.class);
            this.agents.put(agent.getIdentification(), agent);
        }
    }
    
    public void addAgentsFromFolder(){
        loadingAgents();
    }
    
    public void addFloorStatus(String floorStatusString){
        Gson gson = new Gson();
        FloorStatus floorStatus = gson.fromJson(floorStatusString, FloorStatus.class);
        this.building.updateTemperatureFloors(floorStatus);
    }
    
    public void addFloorsStatus(ArrayList<String> floorsStatus){
        Gson gson = new Gson();
        for(String floorStatusString: floorsStatus){
            FloorStatus floorStatus = gson.fromJson(floorStatusString, FloorStatus.class);
            this.building.updateTemperatureFloors(floorStatus);
        }
    }
    
    public void addFloorStatusFromFolder(){
        loadingBuildingStatus();
    }
    
    private void loadingBuilding(){    
        BuildingScheme building;
        File buildingFolder = new File(BUILDING_FOLDER);
        File[] listOfBuilding = buildingFolder.listFiles();
        
        for(File file: listOfBuilding){
            building = FileInformation.extractBuildingInformation(file.getName());
            this.building = building;
        }
    }
    
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
    
    public void fireEvacuation(){
        AgentRoute agentRoute = null;
        ArrayList<Point2D> exits = getExits();
        HashMap<String, Dimension> temperaturePlanes = getTemperaturePlanes();
        int rows = this.building.getFirstFloor().getPlane().getRows();
        int columns = this.building.getFirstFloor().getPlane().getColumns();
        
        for(AgentScheme agent: this.agents.values()){
            CoordAgent coordinateAgent = agent.getCoordinate();
            agentRoute = new FireAgentRoute(exits, temperaturePlanes, rows, columns, coordinateAgent);
            Route routeBP = new RouteBranchPruning();
            ArrayList<CoordAgent> route = routeBP.generationRoute(agentRoute);
            agent.setRoute(route);
            FileInformation.saveAgentRoute(agent, "FireEvacuation_"+agent.getIdentification());            
            printRoute(agent.getIdentification());
        }
    }
    
    public void shortEvacuation(){
        AgentRoute agentRoute = null;
        ArrayList<Point2D> exits = getExits();
        HashMap<String, Dimension> planes = getPlanes();
        int rows = this.building.getFirstFloor().getPlane().getRows();
        int columns = this.building.getFirstFloor().getPlane().getColumns();
        
        for(AgentScheme agent: this.agents.values()){
            CoordAgent coordinateAgent = agent.getCoordinate();
            agentRoute = new EvacuationAgentRoute(exits, planes, rows, columns, coordinateAgent);
            Route routeBP = new RouteBranchPruning();
            ArrayList<CoordAgent> route = routeBP.generationRoute(agentRoute);
            agent.setRoute(route);
            FileInformation.saveAgentRoute(agent, "Evacuation_"+agent.getIdentification());
            printRoute(agent.getIdentification());
        }
    }
    
    private ArrayList<Point2D> getExits(){
        ArrayList<Point2D> exits = new ArrayList<>();        
        Point2D exitPoint;
        Dimension plane = this.building.getFirstFloor().getPlane();
        
        int columns = plane.getColumns()-1;
        int rows = plane.getRows()-1;
        
        for(int column = 0; column<columns; column++){
            String firsRowValue = plane.getValue(0, column);
            String lastRowValue = plane.getValue(rows, column);
            if(firsRowValue.equals(DOOR_LETTER)){
                exitPoint = new Point2D(0, column);
                exits.add(exitPoint);
            }
            if(lastRowValue.equals(DOOR_LETTER)){
                exitPoint = new Point2D(rows, column);
                exits.add(exitPoint);
            }
        }
        
        for(int row = 0; row<rows; row++){
            String firstColumnValue = plane.getValue(row, 0);
            String lastColumnValue = plane.getValue(row, columns);
            if(firstColumnValue.equals(DOOR_LETTER)){
               exitPoint = new Point2D(row, 0);
               exits.add(exitPoint);
            }
            if(lastColumnValue.equals(DOOR_LETTER)){
               exitPoint = new Point2D(row, columns);
               exits.add(exitPoint);
            }
        }
        
        return exits;
    }
    
    private HashMap<String, Dimension> getPlanes(){
        HashMap<String, Dimension> planes = new HashMap<>();
        HashMap<String, Floor> floors = this.building.getFloors();
        for(String floorName: floors.keySet()){
            Dimension plane = floors.get(floorName).getPlane();
            Dimension newPlane = plane.clone();
            planes.put(floorName, newPlane);
        }
        
        return planes;
    }
    
    private HashMap<String, Dimension> getTemperaturePlanes(){
        HashMap<String, Dimension> temperaturePlanes = new HashMap<>();
        HashMap<String, Floor> floors = this.building.getFloors();
        for(String floorName: floors.keySet()){
            Dimension temperaturePlane = floors.get(floorName).getTemperaturePlane();
            Dimension newTemperaturePlane = temperaturePlane.clone();
            temperaturePlanes.put(floorName, newTemperaturePlane);
        }
        
        return temperaturePlanes;
    }
    
    public void printMap(){
        this.building.print();
    }
    
    private void printRoute(int identification){        
        AgentScheme agent = this.agents.get(identification);
        Dimension plane = this.building.getFirstFloor().getPlane().clone();
        for(CoordAgent coordinateAgent: agent.getRoute()){
            int row = coordinateAgent.getCoordinate().getX();
            int column = coordinateAgent.getCoordinate().getY();
            plane.setValue(row, column, "X");
        }
        System.out.println(plane.toString());        
    }
}
