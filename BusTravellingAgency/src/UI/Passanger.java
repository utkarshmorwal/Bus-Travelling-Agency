package UI;

import DAO.BusDao;
import DAO.PassangerDao;

import java.util.Scanner;

public class Passanger {
    public static void PassangerMenu(){
        int choice;
        do{
            System.out.println("Choose 0 to Back menu.\n" +
                    "Choose 1 to Register.\n" +
                    "Choose 2 to view personal details.\n" +
                    "Choose 3 to tickets booking.\n" +
                    "Choose 4 to view booking details.\n" +
                    "Choose 5 to cancel booking.");

            System.out.print("\nEnter your choice here: ");
            choice=new Scanner(System.in).nextInt();
            System.out.println("\n====================\n");

            switch (choice){
                case 0:
                    break;
                case 1:
                    PassangerDao.Register();
                    break;
                case 2:
                    PassangerDao.viewPersonalDetails();
                    break;
                case 3:
                    PassangerDao.booking();
                    break;
                case 4:
                    PassangerDao.viewBookinDetails();
                    break;
                case 5:
                    PassangerDao.CancelBookin();
                    break;
                default:
                    System.out.println("enter valid output");
                    break;
            }
            System.out.println("\n=========================\n");
        }while (choice!=0);
    }
}
