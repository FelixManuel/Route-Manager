package Building_Scheme.Classes;

import Building_Scheme.Utilities.Coordinate;

/**
 * @author Felix Manuel Mellado
 */
public class Door {
    //Attributes
    private String name;
    private Coordinate coordinate;
    private static int doorNumber;
    
    //Constructor
    public Door(){
        this.name = "Door_" + doorNumber;
        this.coordinate = new Coordinate();
    }
    
    public Door(String name, Coordinate coordinate){
        this.name = name;
        this.coordinate = coordinate;
    }
    
    //Getter Methods
    public String getName(){
        return this.name;
    }
    
    public Coordinate getCoordinate(){
        return this.coordinate;
    }
}
