package bigProject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;

public class ServerHandler extends Thread{
    ObjectInputStream inputStream;
    ObjectOutputStream outputStream;

    public ServerHandler(Socket socket) throws IOException {
        inputStream = new ObjectInputStream(socket.getInputStream());
        outputStream = new ObjectOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        try{
            while (true){
                PackageData packageData = (PackageData) inputStream.readObject();
                if (packageData.getOperationType().equals("ADD AIRCRAFT")){
                    Aircraft aircraft = packageData.getAircraft();
                    Server.addAircraft(aircraft);
                }
                if (packageData.getOperationType().equals("LIST AIRCRAFTS")){
                    packageData.setAircrafts(Server.listAircraft());
                    outputStream.writeObject(packageData);
                }
                if (packageData.getOperationType().equals("EXIT")){
                    System.exit(1);
                }
                if (packageData.getOperationType().equals("DELETE AIRCRAFT")){
                    Server.deleteAircraft(packageData.getAircraft().getId());
                }
                if(packageData.getOperationType().equals("EDIT AIRCRAFT")){
                    Server.editAircraft(packageData.getAircraft());
                }
                if (packageData.getOperationType().equals("LIST CITIES")){
                    packageData.setCities(Server.listCity());
                    outputStream.writeObject(packageData);
                }
                if (packageData.getOperationType().equals("ADD CITY")){
                    Server.addCity(packageData.getCity());
                }
                if (packageData.getOperationType().equals("DELETE CITY")){
                    Server.deleteCity(packageData.getCity().getId());
                }
                if (packageData.getOperationType().equals("EDIT CITY")){
                    Server.editCity(packageData.getCity());
                }
                if (packageData.getOperationType().equals("LIST FLIGHTS")){
                    packageData.setFlightDTOS(Server.listFlight());
                    outputStream.writeObject(packageData);
                }
                if (packageData.getOperationType().equals("ADD FLIGHT")){
                    Server.addFlight(packageData.getFlight());
                }
                if (packageData.getOperationType().equals("DELETE FLIGHT")){
                    Server.deleteFlight(packageData.getFlight().getId());
                }
                if (packageData.getOperationType().equals("EDIT FLIGHT")){
                    Server.editFlight(packageData.getFlight());
                }
                if (packageData.getOperationType().equals("LIST TICKETS")){
                    packageData.setTicketDTOS(Server.listTicket());
                    outputStream.writeObject(packageData);
                }
                if (packageData.getOperationType().equals("ADD TICKET")){
                    Server.addTicket(packageData.getTicket());
                }
                if (packageData.getOperationType().equals("DELETE TICKET")){
                    Server.deleteTicket(packageData.getTicket().getId());
                }
                if (packageData.getOperationType().equals("EDIT TICKET")){
                    Server.editTicket(packageData.getTicket());
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
