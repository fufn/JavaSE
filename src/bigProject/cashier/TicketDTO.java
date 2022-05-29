package bigProject.cashier;

import java.io.Serializable;

public class TicketDTO implements Serializable {
    private Long id;
    private String departure_city;
    private String arrival_city;
    private int econom_place_price;
    private int business_place_price;
    private String departure_time;
    private String name;
    private String surname;
    private String passport_number;
    private String ticket_type;

    public TicketDTO(){

    }

    public TicketDTO(Long id, String departure_city, String arrival_city, int econom_place_price, int business_place_price, String departure_time, String name, String surname, String passport_number, String ticket_type) {
        this.id = id;
        this.departure_city = departure_city;
        this.arrival_city = arrival_city;
        this.econom_place_price = econom_place_price;
        this.business_place_price = business_place_price;
        this.departure_time = departure_time;
        this.name = name;
        this.surname = surname;
        this.passport_number = passport_number;
        this.ticket_type = ticket_type;
    }

    public int getEconom_place_price() {
        return econom_place_price;
    }

    public void setEconom_place_price(int econom_place_price) {
        this.econom_place_price = econom_place_price;
    }

    public int getBusiness_place_price() {
        return business_place_price;
    }

    public void setBusiness_place_price(int business_place_price) {
        this.business_place_price = business_place_price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassport_number() {
        return passport_number;
    }

    public void setPassport_number(String passport_number) {
        this.passport_number = passport_number;
    }

    public String getTicket_type() {
        return ticket_type;
    }

    public void setTicket_type(String ticket_type) {
        this.ticket_type = ticket_type;
    }

    public String getDeparture_city() {
        return departure_city;
    }

    public void setDeparture_city(String departure_city) {
        this.departure_city = departure_city;
    }

    public String getArrival_city() {
        return arrival_city;
    }

    public void setArrival_city(String arrival_city) {
        this.arrival_city = arrival_city;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }
}
