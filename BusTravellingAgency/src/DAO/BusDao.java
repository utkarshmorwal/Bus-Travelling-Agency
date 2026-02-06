package DAO;

import DriverConnection.DriverConnection;

import java.sql.*;
import java.util.Scanner;

public class BusDao {
    public static void NewBus(){
        System.out.println("\n====================\n");
        Connection connection=DriverConnection.getConnection();
        try {
            Scanner input=new Scanner(System.in);
            String query="insert into bus_details values(?,?,?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            System.out.print("Enter Bus number : ");
            preparedStatement.setInt(1,input.nextInt());
            input.nextLine();
            System.out.print("Enter starting point : ");
            preparedStatement.setString(2,input.nextLine());
            System.out.print("Enter Destiney : ");
            preparedStatement.setString(3,input.nextLine());
            System.out.print("Enter capacity : ");
            preparedStatement.setInt(4,input.nextInt());
            input.nextLine();
            System.out.print("Enter departure time : " );
            preparedStatement.setString(5,input.nextLine());

            preparedStatement.executeUpdate();
            System.out.println("\n==========Congrats==========\n\n" +
                    "Welcome to our bus as a Driver..!!\n");

            preparedStatement.close();
            connection.close();

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    public static void SHowBus(){
        System.out.println("\n====================\n");
        Connection connection=DriverConnection.getConnection();
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("select *from bus_details where busnumber=?");
            System.out.print("Enter bus number you want to search : ");
            preparedStatement.setInt(1,new Scanner(System.in).nextInt());

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                System.out.println("\n-------Bus deatils-------\n");
                System.out.println("Bus Number: " + rs.getInt("busnumber"));
                System.out.println("Starting Point: " + rs.getString("starting"));
                System.out.println("Destination: " + rs.getString("destination"));
                System.out.println("Capacity: " + rs.getInt("available_seats"));
                System.out.println("Departure Time: " + rs.getString("time"));
            } else {
                System.out.println("\nNo bus found with this number.\n");
            }
            rs.close();
            preparedStatement.close();
            connection.close();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    public static void DeleteBus(){
        System.out.println("\n====================\n");
        Connection connection=DriverConnection.getConnection();
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("delete from bus_details where busnumber=?");
            System.out.print("Enter bus number want to remove from agency : ");
            preparedStatement.setInt(1,new Scanner(System.in).nextInt());

            int flag=preparedStatement.executeUpdate();

            String message=(flag>0) ?"You are not a part of agency anymore...":"Sorry... we can't found a bus with that number.";

            System.out.println("\n"+message+"\n");

            preparedStatement.close();
            connection.close();


        }catch (SQLException sqlException){
            sqlException.getMessage();
        }

    }
}
