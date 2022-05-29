package bigProject;

import bigProject.admin.FlightDTO;
import bigProject.cashier.TicketDTO;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.Serializable;
import java.util.ArrayList;

public class PackageData implements Serializable {
    private String operationType;
    private Aircraft aircraft;
    private ArrayList<Aircraft> aircrafts;
    private City city;
    private ArrayList<City> cities;
    private Flight flight;
    private ArrayList<Flight> flights;
    private FlightDTO flightDTO;
    private ArrayList<FlightDTO> flightDTOS;
    private Ticket ticket;
    private ArrayList<Ticket> tickets;
    private TicketDTO ticketDTO;
    private ArrayList<TicketDTO> ticketDTOS;

    public PackageData(){
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public TicketDTO getTicketDTO() {
        return ticketDTO;
    }

    public void setTicketDTO(TicketDTO ticketDTO) {
        this.ticketDTO = ticketDTO;
    }

    public ArrayList<TicketDTO> getTicketDTOS() {
        return ticketDTOS;
    }

    public void setTicketDTOS(ArrayList<TicketDTO> ticketDTOS) {
        this.ticketDTOS = ticketDTOS;
    }

    public FlightDTO getFlightDTO() {
        return flightDTO;
    }

    public void setFlightDTO(FlightDTO flightDTO) {
        this.flightDTO = flightDTO;
    }

    public ArrayList<FlightDTO> getFlightDTOS() {
        return flightDTOS;
    }

    public void setFlightDTOS(ArrayList<FlightDTO> flightDTOS) {
        this.flightDTOS = flightDTOS;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }

    public void setFlights(ArrayList<Flight> flights) {
        this.flights = flights;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public ArrayList<Aircraft> getAircrafts() {
        return aircrafts;
    }

    public void setAircrafts(ArrayList<Aircraft> aircrafts) {
        this.aircrafts = aircrafts;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }
}
