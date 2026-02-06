package DAO;

import DriverConnection.DriverConnection;

import java.sql.*;
import java.util.Scanner;

public class PassangerDao {

    public static void Register(){
        try {
            Connection connection=DriverConnection.getConnection();
            String query="insert into passanger_details values(?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            Scanner input=new Scanner(System.in);
            System.out.print("Enter username : ");
            preparedStatement.setString(1,input.nextLine().toLowerCase());
            System.out.print("Enter Name : ");
            preparedStatement.setString(2,input.nextLine());
            System.out.print("Enter age : ");
            preparedStatement.setInt(3,input.nextInt());

            int rowAffected=preparedStatement.executeUpdate();
            String message=(rowAffected>0)?"Register successfully":"Username is not available";
            System.out.println("\n"+message);
            preparedStatement.close();
            connection.close();

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    public static void viewPersonalDetails(){
        try {
            Connection connection=DriverConnection.getConnection();
            String query="select *from passanger_details where username=?";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            Scanner input=new Scanner(System.in);
            System.out.print("Enter username : ");
            String username=input.nextLine().toLowerCase();
            preparedStatement.setString(1,username);
            preparedStatement.executeQuery();
            ResultSet resultSet=preparedStatement.getResultSet();

            if(resultSet.next()){
                System.out.println("\nUsername is : "+ resultSet.getString("username"));
                System.out.println("Name is : "+ resultSet.getString("Name"));
                System.out.println("Age is : "+ resultSet.getString("Age"));
            }else{
                System.out.println("Username not found");
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }

    }

    public static void booking() {
        Connection connection=DriverConnection.getConnection();
        Scanner input=new Scanner(System.in);

        //checking for avalable
        System.out.print("Enter your username for booking: ");
        String username = input.nextLine();
        try{
            String checkUserQuery = "SELECT * FROM passanger_details WHERE username=?";
            PreparedStatement preparedStatement = connection.prepareStatement(checkUserQuery);

            preparedStatement.setString(1, username);
            ResultSet resultSet=preparedStatement.executeQuery();
            if (!resultSet.next()) {
                System.out.println("Username not found. Please register first.");
                return;
            }
            resultSet.close();
            preparedStatement.close();

        }catch (SQLException exception){
            exception.printStackTrace();
        }

        //If you are in system then all buses.....

        System.out.print("Enter starting point: ");
        String startingPoint = input.nextLine();
        System.out.print("Enter destination: ");
        String destination = input.nextLine();

        try {
            String checkUserQuery = "SELECT * FROM bus_details where `starting`=? and destination=?";
            PreparedStatement preparedStatement = connection.prepareStatement(checkUserQuery);
            preparedStatement.setString(1,startingPoint);
            preparedStatement.setString(2,destination);
            ResultSet resultSet=preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("\n-------Bus details-------\n");
                System.out.println("Bus Number | Starting Point | Destination | Available Seats | Departure Time");
                 do{
                     System.out.println(resultSet.getInt("busnumber") + " | " +
                         resultSet.getString("starting") + " | " +
                         resultSet.getString("destination") + " | " +
                         resultSet.getInt("available_seats")+ " | " +
                         resultSet.getString("time"));
                 }while (resultSet.next());

                 resultSet.close();
                 preparedStatement.close();
            }else {
                System.out.println("\nNo bus found on given route.");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //booking
        System.out.print("Enter bus number to book ticket : ");
        int selectedBus=input.nextInt();
        System.out.print("Enter number of seats to book : ");
        int seatsToBook=input.nextInt();

        // Insert booking

        try {
            String insertBookingQuery = "INSERT INTO booking(username, busnumber, seats) VALUES(?,?,?)";
            PreparedStatement prInsert = connection.prepareStatement(insertBookingQuery);
            prInsert.setString(1, username);
            prInsert.setInt(2, selectedBus);
            prInsert.setInt(3, seatsToBook);

            String ChoosingBus="select *from bus_details where busnumber=?";
            PreparedStatement choosingBus=connection.prepareStatement(ChoosingBus);
            choosingBus.setInt(1,selectedBus);
            ResultSet resultSet=choosingBus.executeQuery();
            resultSet.next();
            int avalableSeats=resultSet.getInt("available_seats");

            if(seatsToBook<avalableSeats){
                System.out.println("\nBooking confirmed....!!");
            }else {
                System.out.println("Only "+avalableSeats+" are left to book.");
                return;
            }
            prInsert.executeUpdate();
            prInsert.close();

            String updateSeatsQuery = "UPDATE bus_details SET available_seats=? where busnumber=?";
            PreparedStatement prUpdate = connection.prepareStatement(updateSeatsQuery);
            prUpdate.setInt(1, avalableSeats - seatsToBook);
            prUpdate.setInt(2, selectedBus);
            prUpdate.executeUpdate();

            prUpdate.close();
            connection.close();

        }catch (SQLException sqlException){
            System.out.println("\nYou already booked ticket.\nTry using different account.");
        }
    }

    public static void viewBookinDetails(){
        try {
            Connection connection=DriverConnection.getConnection();
            String insertBookingQuery = "select *from booking where username =?";
            PreparedStatement prInsert = connection.prepareStatement(insertBookingQuery);
            System.out.print("Enter username :");
            prInsert.setString(1, new Scanner(System.in).nextLine());
            ResultSet resultSet=prInsert.executeQuery();
            resultSet.next();
            System.out.println("\nBus number :"+resultSet.getInt("busnumber") + "\nusername : " +
                    resultSet.getString("username") + "\nSeats you booked : " +
                    resultSet.getString("seats") );

        }catch (SQLException sqlException){
            System.out.println("\nNo booking found by this user.");
        }
    }

    public static void CancelBookin(){
        int busnumber=0,seatsToBook=0;
        Connection connection=DriverConnection.getConnection();
        Scanner input=new Scanner(System.in);
        //validating booking
        System.out.print("Enter username to delete booking : ");
        String username=input.nextLine();
        String query="Select *from booking where username=? ";

        try (PreparedStatement preparedStatement=connection.prepareStatement(query)){
            preparedStatement.setString(1,username);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(!resultSet.next()){
                System.out.println("NO booking found of this username");
                return;
            }else {
                busnumber=resultSet.getInt("busnumber");
                seatsToBook=resultSet.getInt("seats");
            }
            resultSet.close();
            preparedStatement.close();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }


        try {
            String updateSeatsQuery = "UPDATE bus_details SET available_seats=available_seats+? where busnumber=?";
            PreparedStatement prUpdate = connection.prepareStatement(updateSeatsQuery);
            prUpdate.setInt(1, seatsToBook);
            prUpdate.setInt(2, busnumber);
            prUpdate.executeUpdate();

            String deletequery="delete from booking where username=?;";
            PreparedStatement preparedStatement=connection.prepareStatement(deletequery);
            preparedStatement.setString(1,username);
            preparedStatement.executeUpdate();
            System.out.println("\nBooking cancelled.");
            preparedStatement.close();

            prUpdate.executeUpdate();
            prUpdate.close();
            connection.close();

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }

    }

}

