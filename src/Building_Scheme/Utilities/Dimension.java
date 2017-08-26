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
    private static final String EMPTY_LETTER = " ";
    
    //Constructor
    public Dimension(){
        this.rows = 0;
        this.columns = 0;
    }
    
    //Getter Methods
    public String getValue(int row, int column){
        return this.plane[row][column];
    }
    
    private int getRows(){
        return this.rows;
    }
    
    private int getColumns(){
        return this.columns;
    }
    
    //Setter Methods
    public void setValue(int row, int column, String value){
        this.plane[row][column] = value;
    }
    
    //Methods
    public void createPlane(){
        this.plane = new String[rows][columns];
        this.fillInThePlane();
    }
    
    public void createTemperaturePlane(){
        this.plane = new String[rows][columns];
        this.fillInTheTemperaturePlane();
    }
    
    private void fillInThePlane(){
        for (int row = 0; row < this.plane.length; row++) {
            for (int column = 0; column < this.plane[row].length; column++) {
                if(row == 0 || column == 0 || row == this.plane.length-1 || column == this.plane[row].length-1){
                    this.setValue(row, column, REPRESENTATIVE_LETTER);
                }else{
                    this.setValue(row, column, EMPTY_LETTER);
                }
            }
        }
    }
    
    private void fillInTheTemperaturePlane(){
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
        String representationPlane = "";
        
        for (int row = 0; row < this.plane.length; row++) {
            for (int column = 0; column < this.plane[row].length; column++) {
                representationPlane += this.getValue(row, column) + " ";
            }
            
            representationPlane += "\n";
        }
        
        return representationPlane;
    }
    
    public String toStringTemperaturePlane(){
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
    
    public void clone(Dimension plane){
        this.rows = plane.getRows();
        this.columns = plane.getColumns();
    }

}
