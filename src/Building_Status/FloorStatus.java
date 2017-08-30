package Building_Status;

import java.util.ArrayList;

/**
 * @author Felix Manuel Mellado
 */
public class FloorStatus {
    //Attributes
    private String name;
    private ArrayList<RoomStatus> rooms;
    
    //Getter Methods
    public String getName(){
        return this.name;
    }
    
    public ArrayList<RoomStatus> getRooms(){
        return this.rooms;
    }
}
