package Demostration;

import Building_Scheme.BuildingScheme;
import Controller.Controller;
import File.FileInformation;
import java.util.Scanner;

/**
 * @author Felix Manuel Mellado
 */
public class Demostration {
    
    public static void main(String args[]){
        Controller controller = buildingMenu();
        Menu(controller);
    }
    
    private static Controller buildingMenu(){
        int number = 0;
        Controller controller = null;
        
        do{
            System.out.println("***** APPLICATION PROGRAMMING INTERFACE *****");
            System.out.println("1.- Add building.");
            System.out.println("2.- Add building from folder");
            
            Scanner sc = new Scanner(System.in);
            System.out.print("Select an option: ");
            number = sc.nextInt();
            System.out.println("\n");
            
            switch(number){
                case 1:
                    System.out.println("Insert the name of the file(add '.json' at the end): ");
                    String fileName = sc.next();
                    BuildingScheme building = FileInformation.extractBuildingInformation(fileName);
                    
                    controller = new Controller(building);
                    break;
                    
                case 2:
                    controller = new Controller();
                    break;
            }
        }while(number != 1 && number != 2);
        
        controller.printMap();
        
        return controller;
    }
    
    private static void Menu(Controller controller){
        int number = 0;
        
        do{
            System.out.println("***** APPLICATION PROGRAMMING INTERFACE *****");
            System.out.println("1.- Add agent.");
            System.out.println("2.- Add agents.");
            System.out.println("3.- Add agents from folder.");
            System.out.println("4.- Add floor temperature status.");
            System.out.println("5.- Add floor temperatures status.");
            System.out.println("5.- Add floor temperature status from folder.");
            System.out.println("6.- Fire Emergency Evacuation.");
            System.out.println("7.- Short Emergency Evacuation.");
            System.out.println("0.- Salir.");
            
            Scanner sc = new Scanner(System.in);
            System.out.print("Select an option: ");
            number = sc.nextInt();
            
            System.out.println("\n");
            switch(number){
                case 3:
                    controller.addAgentsFromFolder();
                    break;
                case 5:
                    controller.addFloorStatusFromFolder();
                    break;
                case 6:
                    controller.fireEvacuation();
                    break;
                case 7:
                    controller.shortEvacuation();
                    break;
            }
            
        }while(number != 0);
    }
}
