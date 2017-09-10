package File;

import Agent_Scheme.AgentScheme;
import Building_Scheme.BuildingScheme;
import Building_Status.FloorStatus;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Félix Manuel Mellado
 */
public class FileInformation {
    //Attributes
    private static final String BUILDING_FOLDER = "buildingScheme//";
    private static final String AGENT_FOLDER = "agentScheme//";
    private static final String BUILDINGSTATUS_FOLDER = "buildingStatus//";
    private static final String ROUTE_FOLDER = "route//";
    
    //Methods
    public static BuildingScheme extractBuildingInformation(String fileName){
        BuildingScheme building = null;
        FileReader fr = null;
        JsonParser parser = new JsonParser();
        Gson gson = new Gson();
        try {
            fr = new FileReader(BUILDING_FOLDER + fileName);            
            JsonElement data = parser.parse(fr);
            building = gson.fromJson(data.toString(), BuildingScheme.class);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileInformation.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(FileInformation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return building;
    }
    
    public static AgentScheme extractAgentInformation(String fileName){
        AgentScheme agent = null;
        FileReader fr = null ;
        JsonParser parser = new JsonParser();
        Gson gson = new Gson();
        try{
            fr = new FileReader(AGENT_FOLDER + fileName);
            JsonElement data = parser.parse(fr);
            agent = gson.fromJson(data.toString(), AgentScheme.class);
        }catch(FileNotFoundException ex){
            Logger.getLogger(FileInformation.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(FileInformation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return agent;
    }
    
    public static FloorStatus extractFloorStatusInformation(String fileName){
        FloorStatus floorStatus = null;
        FileReader fr = null;
        JsonParser parser = new JsonParser();
        Gson gson = new Gson();
        try{
            fr = new FileReader(BUILDINGSTATUS_FOLDER + fileName);
            JsonElement data = parser.parse(fr);
            floorStatus = gson.fromJson(data.toString(), FloorStatus.class);
        }catch(FileNotFoundException ex){
            Logger.getLogger(FileInformation.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(FileInformation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return floorStatus;
    }
    
    public static void saveAgentRoute(AgentScheme agent, String fileName){
        FileWriter fw = null;
        Gson gson = new Gson();
        String agentScheme = gson.toJson(agent);
        try {
            fw = new FileWriter(ROUTE_FOLDER+fileName+".json");
            fw.write(agentScheme);
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(FileInformation.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
}
