package Building_Scheme.Utilities;

/**
 * @author Felix Manuel Mellado
 */
public class Dimension {
    //Attributes
    private int rows;
    private int columns;
    private String[][] plane;
    
    //Constructor
    public Dimension(){
        this.rows = 0;
        this.columns = 0;
    }
    
    public Dimension(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        this.plane = new String[rows][columns];
    }
    
    //Getter Methods
    public int getRows(){
        return this.rows;
    }
    
    public int getColumns(){
        return this.columns;
    }

}
