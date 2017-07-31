package File;

import Building_Scheme.BuildingScheme;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Felix Manuel Mellado
 */
public class BuildingSchemeFile {
    //Attributes
    private static final String FOLDER = "buildingScheme//";
    private BuildingScheme building;
    
    //Constuctor
    public BuildingSchemeFile(String name){
        JsonParser parser = new JsonParser();
        Gson gson = new Gson();
        try {
            FileReader fr = new FileReader(FOLDER + name);            
            JsonElement datos = parser.parse(fr);
            this.building = gson.fromJson(datos.toString(), BuildingScheme.class);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BuildingSchemeFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Getter Methods
    public BuildingScheme getBuilding(){
        return this.building;
    }
}
