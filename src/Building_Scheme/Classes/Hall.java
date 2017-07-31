package Building_Scheme.Classes;

import Building_Scheme.Utilities.Coordinate;
import java.util.ArrayList;

/**
 * @author Felix Manuel Mellado
 */
public class Hall {
    //Attributes
    private String name;
    private Coordinate coordinate;
    private ArrayList<Door> doors;
    private static int hallNumber;
    
    //Constructor
    public Hall(){
        this.name = "Hall_" + hallNumber++;
        this.coordinate = new Coordinate();
        this.doors = new ArrayList<>();
    }
    
    public Hall(String name, Coordinate coordinate, ArrayList<Door> doors){
        this.name = name;
        this.coordinate = coordinate;
        this.doors = doors;
    }
    
    //Getter Methods
    public String getName(){
        return this.name;
    }
    
    public Coordinate getCoordinate(){
        return this.coordinate;
    }
    
    public ArrayList<Door> getDoors(){
        return this.doors;
    }
    
}
