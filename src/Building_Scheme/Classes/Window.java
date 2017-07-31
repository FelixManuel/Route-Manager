package Building_Scheme.Classes;

import Building_Scheme.Utilities.Coordinate;

/**
 * @author Felix Manuel Mellado
 */
public class Window {
    //Attributes
    private String name;
    private Coordinate coordinate;
    private static int windowNumber;
    
    //Constructor
    public Window(){
        this.name = "Window_" + windowNumber;
        this.coordinate = new Coordinate();
    }
    
    public Window(String name, Coordinate coordinate){
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
