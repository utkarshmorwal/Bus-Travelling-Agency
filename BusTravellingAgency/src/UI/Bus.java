package UI;

import DAO.BusDao;

import java.util.Scanner;

public class Bus {
    public static void BusMenu(){
        int choice;
        do{
            System.out.println("Choose 0 to Back menu.\n" +
                    "Choose 1 to add new bus in agency.\n" +
                    "Choose 2 to view bus details.\n" +
                    "Choose 3 to remove bus from agency.\n");
            System.out.print("Enter your choice here: ");
            choice=new Scanner(System.in).nextInt();

            switch (choice){
                case 0:
                    break;
                case 1:
                    BusDao.NewBus();
                    break;
                case 2:
                    BusDao.SHowBus();
                    break;
                case 3:
                    BusDao.DeleteBus();
                    break;
                default:
                    System.out.println("\nenter valid output\n");
                    break;
            }
            System.out.println("\n=========================\n");
        }while (choice!=0);
    }
}