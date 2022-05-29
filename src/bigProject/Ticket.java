package bigProject;

import java.io.Serializable;

public class Ticket implements Serializable {
    private Long id;
    private Long flight_id;
    private String name;
    private String surname;
    private String passport_name;
    private String ticket_type;

    public Ticket(){

    }

    public Ticket(Long id, Long flight_id, String name, String surname, String passport_name, String ticket_type) {
        this.id = id;
        this.flight_id = flight_id;
        this.name = name;
        this.surname = surname;
        this.passport_name = passport_name;
        this.ticket_type = ticket_type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(Long flight_id) {
        this.flight_id = flight_id;
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

    public String getPassport_name() {
        return passport_name;
    }

    public void setPassport_name(String passport_name) {
        this.passport_name = passport_name;
    }

    public String getTicket_type() {
        return ticket_type;
    }

    public void setTicket_type(String ticket_type) {
        this.ticket_type = ticket_type;
    }
}
