package Building_Scheme.Classes;

import Building_Scheme.Utilities.Dimension;
import java.util.ArrayList;

/**
 * @author Felix Manuel Mellado
 */
public class Floor {
    //Attributes
    private String name;
    private Dimension plane;
    private ArrayList<Room> rooms;
    private ArrayList<Elevator> elevators;
    private ArrayList<Stairs> stairs;
    private static int floorNumber;
    
    //Constructor
    public Floor(){
        this.name = "Floor_" + this.floorNumber++;
        this.plane = new Dimension();
        this.rooms = new ArrayList<>();
        this.elevators = new ArrayList<>();
        this.stairs = new ArrayList<>();
    }
    
    public Floor(String name, Dimension plane, ArrayList<Room> rooms, ArrayList<Elevator> elevators,
                 ArrayList<Stairs> stairs){
        this.name = name;
        this.plane = plane;
        this.rooms = rooms;
        this.elevators = elevators;
        this.stairs = stairs;
    }
    
    //Getter Methods
    public String getName(){
        return this.name;
    }
    
    public Dimension getPlane(){
        return this.plane;
    }
    
    public ArrayList<Room> getRooms(){
        return this.rooms;
    }
    
    public ArrayList<Elevator> getElevators(){
        return this.elevators;
    }
    
    public ArrayList<Stairs> getStairs(){
        return this.stairs;
    }
    
    //Methods
    public void createBuildingPlane(){
        this.plane.createPlane();
        
        for(Room room: this.rooms){
            room.createRoom(this.plane);
        }
        
        for (Elevator elevator: this.elevators){
            elevator.createElevator(this.plane);
        }
    }
}
