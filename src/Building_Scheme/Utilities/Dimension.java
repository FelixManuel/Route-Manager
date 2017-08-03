package Building_Scheme.Utilities;

/**
 * @author Felix Manuel Mellado
 */
public class Dimension {
    //Attributes
    private int rows;
    private int columns;
    private String[][] plane;
    
    //Normative Attributes
    private static final String MEDIUM_TEMPERATURE = "26";
    
    //Letter Attribute
    private static final String REPRESENTATIVE_LETTER = "W";
    
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
    
    private String getValue(int row, int column){
        return this.plane[row][column];
    }
    
    public void setValue(int row, int column, String value){
        this.plane[row][column] = value;
    }
    
    //Methods
    public void createPlane(){
        this.plane = new String[rows][columns];
        this.fillWithNormalTemperature();
    }
    
    private void fillWithNormalTemperature(){
        for(int row = 0; row < this.plane.length; row++) {
            for (int column = 0; column < this.plane[row].length; column++) {
                if(row == 0 || column == 0 || row == this.plane.length-1 || column == this.plane[row].length-1){
                    this.setValue(row, column, REPRESENTATIVE_LETTER);
                }else{
                    this.setValue(row, column, MEDIUM_TEMPERATURE);
                }
            }
        }
    }
    
    @Override
    public String toString(){
        final String COLOR_BLUE = "\u001B[34m";
        final String COLOR_BLACK = "\u001B[30m";
        
        String representationPlane = "";
        
        for (int row = 0; row < this.plane.length; row++) {
            for (int column = 0; column < this.plane[row].length; column++){
                if(Dimension.isNumeric(this.getValue(row, column))){
                    representationPlane += COLOR_BLUE + this.getValue(row, column) + " ";
                }else{
                    representationPlane += COLOR_BLACK + this.getValue(row, column) + "  ";  
                }
            }
            representationPlane += "\n";
        }
        
        return representationPlane;
    }
    
    private static boolean isNumeric(String string){
        try{
            Integer.parseInt(string);
            return true;
        }catch(NumberFormatException nfe){
            return false;
        }
    }

}
