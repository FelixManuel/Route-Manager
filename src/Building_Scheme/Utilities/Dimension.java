package Building_Scheme.Utilities;

import java.util.logging.Level;
import java.util.logging.Logger;

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
    private static final String HALL_LETTER = "H";
    
    //Constructor
    public Dimension(){}
    
    public Dimension(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        this.plane = new String[rows][columns];
    }
    
    //Getter Methods
    public String getValue(int row, int column){
        return this.plane[row][column];
    }
    
    public int getRows(){
        return this.rows;
    }
    
    public int getColumns(){
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
    
    public void createSchematicPlane(){
        this.plane = new String[rows][columns];
        this.fillInTheSchematicPlane();
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
    
    private void fillInTheSchematicPlane(){
        for(int row = 0; row < this.plane.length; row++){
            for(int column = 0; column < this.plane[row].length; column++){
                if(row == 0 || column == 0 || row == this.plane.length-1 || column == this.plane[row].length-1){
                    this.setValue(row, column, REPRESENTATIVE_LETTER);
                }else{
                    this.setValue(row, column, HALL_LETTER);
                }
            }
        }
    }
    
    @Override
    public String toString(){
        StringBuilder representationPlane = new StringBuilder("");
        String value;
        
        for (int row = 0; row < this.plane.length; row++) {
            for (int column = 0; column < this.plane[row].length; column++) {
                value = this.getValue(row, column);
                if((value.equals("W") || value.equals("w") || value.equals("E") || value.equals("S"))&&(row == 0 || row == this.plane.length-1)){
//                    value = "\u001B[30m-";
                      value = "-";
                }else if((value.equals("W") || value.equals("w") || value.equals("E") || value.equals("S"))&&(column == 0 || column == this.plane[row].length-1)){
//                    value = "\u001B[30m|";
                      value = "|";
                }else if(value.equals("D")){
                    value = " ";
                }else if(value.equals("W") || value.equals("E") || value.equals("S")){
                    //value = "\u001B[30m\\";
                    value = "\\";
                }else if(value.equals("X")){
//                    value = "\u001B[34mX";
                    value = "X";
                }
                representationPlane.append(value + " ");
            }            
            representationPlane.append("\n");
        }        
        return representationPlane.toString();
    }
    
    public String toStringTemperaturePlane(){
        final String colorBlue = "\u001B[34m";
        final String colorBlack = "\u001B[30m";
        
        StringBuilder representationPlane = new StringBuilder("");
        
        for (int row = 0; row < this.plane.length; row++) {
            for (int column = 0; column < this.plane[row].length; column++){
                if(Dimension.isNumeric(this.getValue(row, column))){
                    representationPlane.append(this.getValue(row, column) + " ");
                }else{
                    representationPlane.append(this.getValue(row, column) + "  ");
                }
            }
            representationPlane.append("\n");
        }        
        return representationPlane.toString();
    }
    
    private static boolean isNumeric(String string){
        try{
            Integer.parseInt(string);
            return true;
        }catch(NumberFormatException nfe){
            return false;
        }
    }
    
    public void cloneRowsColumns(Dimension plane){
        this.rows = plane.getRows();
        this.columns = plane.getColumns();
    }
    
    @Override
    public Dimension clone(){
        Dimension newPlane = new Dimension(this.rows, this.columns);
        
        for(int row = 0; row<this.rows; row++){
            for(int column = 0; column<this.columns; column++){
                String value = this.getValue(row, column);
                newPlane.setValue(row, column, value);
            }
        }
        
        return newPlane;
    }

}
