package Demostration;

import Building_Scheme.BuildingScheme;
import Controller.Controller;
import File.FileInformation;
import java.util.Scanner;

/**
 * This class serves as an interface for testing new functions that are implemented without the need to use external software. 
 * @author Felix Manuel Mellado
 */
public class Demostration {
    
    private Demostration(){}
    
    public static void main(String args[]){
        menu();
    }
    
    /**
     * Menu to select an option that proves manager functionality.
     */
    private static void menu(){
        Controller controller = null;
        int number = 1;
        
        while(number != 0){
            System.out.println("***** APPLICATION PROGRAMMING INTERFACE *****");
            System.out.println("1.- Add building.");
            System.out.println("2.- Add building from folder.");
            System.out.println("3.- Add agent.");
            System.out.println("4.- Add agents.");
            System.out.println("5.- Add agents from folder.");
            System.out.println("6.- Add floor temperature status.");
            System.out.println("7.- Add floor temperatures status.");
            System.out.println("8.- Add floor temperature status from folder.");
            System.out.println("9.- Fire Emergency Evacuation.");
            System.out.println("10.- Short Emergency Evacuation.");
            System.out.println("0.- Salir.");
            
            Scanner sc = new Scanner(System.in);
            System.out.print("Select an option: ");
            number = sc.nextInt();
            
            System.out.println("\n");
            switch(number){
                case 2:
                    controller = new Controller();       
                    controller.printMap();
                    break;                    
                case 5:
                    controller.addAgentsFromFolder();
                    break;
                case 8:
                    controller.addFloorStatusFromFolder();
                    controller.printMap();
                    break;
                case 9:
                    controller.fireEvacuation();
                    break;
                case 10:
                    controller.shortEvacuation();
                    break;
            }            
        }
    }
}
