package Demostration;

import Controller.Controller;
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
        String folderName;
        int number = 1;
        
        while(number != 0){
            try{
                System.out.println("***** APPLICATION PROGRAMMING INTERFACE *****");
                System.out.println("1.- Add building from folder.");
                System.out.println("2.- Add agents from folder.");
                System.out.println("3.- Add floor temperature status from folder.");
                System.out.println("4.- Execute fire Emergency Evacuation.");
                System.out.println("5.- Execute short Emergency Evacuation.");
                System.out.println("0.- Exit.");

                Scanner sc = new Scanner(System.in);
                System.out.print("Select an option: ");
                number = sc.nextInt();

                System.out.print("\n");
                switch(number){
                    case 1:
                        controller = new Controller();       
                        controller.printMap();
                        break;
                    case 2:
                        controller.addAgentsFromFolder();
                        break;
                    case 3:
                        controller.addFloorStatusFromFolder();
                        controller.printMap();
                        break;
                    case 4:
                        controller.fireEvacuationA();
                        break;
                    case 5:
                        controller.shortEvacuationA();
                        break;
                }            
            }catch(NullPointerException npe){
                System.out.print("\u001B[31m You have to insert a building before");
                System.out.println("\n"+"\u001B[30m");
            }
        }
    }
}
