import UI.Bus;
import UI.Passanger;

import java.util.Scanner;

public class BusTravellingAgency {
    public static void main(String[] args) {
        int choice;
       do{
           System.out.println("\n====================\n");

           System.out.println("Choose 0 to exit.\n" +
                   "Choose 1 if you are Driver.\n" +
                   "Choose 2 if you are Passanege.\n");
           System.out.print("Enter your choice here : ");
           choice=new Scanner(System.in).nextInt();

           switch (choice){
               case 0:break;
               case 1:
                   System.out.println("\n========== Welcome Driver ==========\n");
                   Bus.BusMenu();
                   break;
               case 2:
                   System.out.println("\n========== Welcome Passanger ==========\n");
                   Passanger.PassangerMenu();
                   break;
               default:
                   System.out.print("\nenter valid output\n");
                   break;
           }
       }while (choice!=0);

       System.out.println("\n========== Thanks for visiting ==========");
    }
}
