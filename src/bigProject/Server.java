package bigProject;

import bigProject.admin.FlightDTO;
import bigProject.cashier.TicketDTO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;

public class Server {
    private static Connection connection;
    public static void main(String[] args) throws IOException {
        connect();
        ServerSocket server = new ServerSocket(2050);
        while(true){
            System.out.println("WAITING FOR CLIENT");
            Socket socket = server.accept();
            System.out.println("CLIENT IS CONNECTED");
            ServerHandler client = new ServerHandler(socket);
            client.start();
        }
    }
    public static void connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/jdbc_sample?useUnicode=true&serverTimezone=UTC","root", ""
            );
            System.out.println("CONNECTED");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void addCity (City city) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(""+
                "INSERT INTO cities(id, name, country, short_name) VALUES (null, ?, ?, ?)");
        statement.setString(1, city.getName());
        statement.setString(2, city.getCountry());
        statement.setString(3,city.getShort_name());
        statement.executeUpdate();
        System.out.println("City Added");
    }
    public static ArrayList<City> listCity () throws SQLException {
        ArrayList<City> cities = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("" +
                "SELECT * FROM cities");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            String name = resultSet.getString("name");
            String country = resultSet.getString("country");
            Long id = resultSet.getLong("id");
            String short_name = resultSet.getString("short_name");
            cities.add(new City(id, name, country, short_name));
        }
        return cities;
    }

    public static void deleteCity (Long id){
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM cities WHERE id =  ?");
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println("CITY DELETED");
        }
        catch (Exception e) {
            System.out.println("CITY IS USED IN FLIGHT");
        }
    }

    public static void editCity(City city) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE cities SET name = ?, country = ?, short_name = ? WHERE id = ?;");
            statement.setLong(4, city.getId());
            statement.setString(1, city.getName());
            statement.setString(2, city.getCountry());
            statement.setString(3, city.getShort_name());
            statement.executeUpdate();
            statement.close();
            System.out.println("CITY EDITED");
        } catch (Exception f) {
            System.out.println("CITY IS NOT EDITED");
        }
    }

    public static void addTicket (Ticket ticket) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(""+
                "INSERT INTO tickets(id, flight_id, name, surname, passport_number, ticket_type) VALUES (null, ?, ?, ?, ?, ?)");
        statement.setLong(1, ticket.getFlight_id());
        statement.setString(2, ticket.getName());
        statement.setString(3, ticket.getSurname());
        statement.setString(4, ticket.getPassport_name());
        statement.setString(5, ticket.getTicket_type());

        statement.executeUpdate();
        System.out.println("City Added");
    }

    public static ArrayList<TicketDTO> listTicket () throws SQLException {
        ArrayList<TicketDTO> ticketDTOS = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT T.id, C.name as 'departure city', C1.name as 'arrival city', F.econom_place_price, F.business_place_price, F.departure_time, T.name, T.surname, T.passport_number, T.ticket_type FROM tickets as T\n" +
                "INNER JOIN flights as F on T.flight_id = F.id\n" +
                "INNER JOIN `aircrafts` AS A ON F.aircraft_id = A.id\n" +
                "INNER JOIN cities AS C ON C.id = F.departure_city_id\n" +
                "INNER JOIN cities as C1 ON C1.id = F.arrival_city_id;");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            String departure_city = resultSet.getString("departure city");
            String arrival_city = resultSet.getString("arrival city");
            int econom = resultSet.getInt("econom_place_price");
            int business = resultSet.getInt("business_place_price");
            String departure_time = resultSet.getString("departure_time");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String passport_number = resultSet.getString("passport_number");
            String ticket_type = resultSet.getString("ticket_type");
            ticketDTOS.add(new TicketDTO(id, departure_city, arrival_city, econom, business, departure_time, name, surname, passport_number, ticket_type));
        }
        return ticketDTOS;
    }

    public static void deleteTicket (Long id){
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM tickets WHERE id =  ?");
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println("TICKET DELETED");
        }
        catch (Exception e) {
            System.out.println("TICKET IS USED IN FLIGHT");
        }
    }

    public static void editTicket (Ticket ticket){
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE tickets SET flight_id = ?, name = ?, surname = ?, passport_number = ?, ticket_type = ? WHERE id = ?;");
            statement.setLong(1, ticket.getFlight_id());
            statement.setString(2, ticket.getName());
            statement.setString(3, ticket.getSurname());
            statement.setString(4, ticket.getPassport_name());
            statement.setString(5, ticket.getTicket_type());
            statement.setLong(6, ticket.getId());
            statement.executeUpdate();
            statement.close();
            System.out.println("TICKET EDITED");
        } catch (Exception f) {
            System.out.println("TICKET IS NOT EDITED");
        }
    }

    public static void deleteFlight (Long id){
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM `flights` WHERE id =  ?");
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println("FLIGHT DELETED");
        }
        catch (Exception e) {
            System.out.println("FLIGHT IS NOT DELETED");
        }
    }

    public static void editFlight (Flight flight){
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE flights SET aircraft_id = ?, departure_city_id = ?, arrival_city_id = ?, departure_time = ?, " +
                    "econom_place_price = ?, business_place_price = ? WHERE id = ?;");
            statement.setLong(1, flight.getAircraft_id());
            statement.setLong(2, flight.getDeparture_city_id());
            statement.setLong(3, flight.getArrival_city_id());
            statement.setString(4, flight.getDeparture_time());
            statement.setInt(5, flight.getEconom_place_price());
            statement.setInt(6, flight.getBusiness_place_price());
            statement.setLong(7, flight.getId());
            statement.executeUpdate();
            statement.close();
            System.out.println("FLIGHT EDITED");
        } catch (Exception f) {
            System.out.println("FLIGHT IS NOT EDITED");
        }
    }

    public static void addFlight (Flight flight) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(""+
                "INSERT INTO flights(id, aircraft_id, departure_city_id, arrival_city_id, departure_time, econom_place_price, business_place_price) " +
                " VALUES (null, ?, ?, ?, ?, ?, ?)");
        statement.setLong(1, flight.getAircraft_id());
        statement.setLong(2, flight.getDeparture_city_id());
        statement.setLong(3, flight.getArrival_city_id());
        statement.setString(4, flight.getDeparture_time());
        statement.setInt(5, flight.getEconom_place_price());
        statement.setInt(6, flight.getEconom_place_price());
        statement.executeUpdate();
        System.out.println("Flight Added");
    }

    public static ArrayList<FlightDTO> listFlight() throws SQLException {
            ArrayList<FlightDTO> flights = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement("SELECT F.id, A.name, A.model, C.name as 'departure city', C.country as 'departure country'," +
                    " C1.name as 'arrival city', C1.country as 'arrival country', F.econom_place_price, " +
                    "F.business_place_price, F.departure_time FROM flights AS F" +
                    " INNER JOIN `aircrafts` AS A ON F.aircraft_id = A.id " +
                    "INNER JOIN cities AS C ON C.id = F.departure_city_id " +
                    "INNER JOIN cities as C1 ON C1.id = F.arrival_city_id;");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String model = resultSet.getString("model");
                int econom_place_price = resultSet.getInt("econom_place_price");
                int business_place_price = resultSet.getInt("business_place_price");
                String departure_city = resultSet.getString("departure city");
                String departure_country = resultSet.getString("departure country");
                String arrival_city = resultSet.getString("arrival city");
                String arrival_country = resultSet.getString("arrival country");
                String departure_time = resultSet.getString("departure_time");
                flights.add(new FlightDTO(id, name, model, econom_place_price, business_place_price, departure_city, departure_country, arrival_city, arrival_country, departure_time));
            }
            return flights;
    }


    public static void deleteAircraft (Long id){
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM `aircrafts` WHERE id =  ?");
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println("AIRCRAFT DELETED");
        }
        catch (Exception e) {
            System.out.println("AIRCRAFT IS USED IN FLIGHT");
        }
    }

    public static void addAircraft (Aircraft aircraft) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(""+
                "INSERT INTO aircrafts(id, name, model, business_class_capacity, econom_class_capacity) VALUES (null, ?, ?, ?, ?)");
        statement.setInt(3,aircraft.getBusiness_class_capacity());
        statement.setInt(4,aircraft.getEconom_class_capacity());
        statement.setString(1, aircraft.getName());
        statement.setString(2, aircraft.getModel());
        statement.executeUpdate();
        System.out.println("Aircraft Added");
    }

    public static void editAircraft(Aircraft aircraft) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE aircrafts SET name = ?, model = ?, business_class_capacity = ?, econom_class_capacity = ? WHERE id = ?;");
            statement.setLong(5, aircraft.getId());
            statement.setString(1, aircraft.getName());
            statement.setString(2, aircraft.getModel());
            statement.setInt(3, aircraft.getBusiness_class_capacity());
            statement.setInt(4, aircraft.getEconom_class_capacity());
            statement.executeUpdate();
            statement.close();
            System.out.println("AIRCRAFT EDITED");
        } catch (Exception f) {
            System.out.println("AIRCRAFT IS NOT EDITED");
        }
    }

    public static ArrayList<Aircraft> listAircraft () throws SQLException {
        ArrayList<Aircraft> aircrafts = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("" +
                "SELECT * FROM aircrafts");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            String name = resultSet.getString("name");
            String model = resultSet.getString("model");
            Long id = resultSet.getLong("id");
            int business_class_capacity = resultSet.getInt("business_class_capacity");
            int econom_class_capacity = resultSet.getInt("econom_class_capacity");
            aircrafts.add(new Aircraft(id, name, model, business_class_capacity, econom_class_capacity));
        }
        return aircrafts;
    }

//    SELECT F.id, F.econom_place_price, F.business_place_price, A.name, A.model, C.name as 'departure city', C.country as 'departure country', C1.name as 'arrival city', C1.country as 'arrival country'
//    FROM flights AS F
//    INNER JOIN `aircrafts` AS A
//    ON F.aircraft_id = A.id
//    INNER JOIN cities AS C
//    ON C.id = F.departure_city_id
//    INNER JOIN cities as C1
//    ON C1.id = F.arrival_city_id;

}
