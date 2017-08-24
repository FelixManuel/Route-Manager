package Building_Status;

/**
 * @author Felix Manuel Mellado
 */
public class RoomStatus {
    //Attributes        
    private String name;
    private int temperature;
    
    //Constructor
    public RoomStatus(){
        this.name = "";
        this.temperature = 0;
    }
    
    //Getter Methods
    public String getName(){
        return this.name;
    }
    
    public int getTemperature(){
        return this.temperature;
    }
}
