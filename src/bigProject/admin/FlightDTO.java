package bigProject.admin;

import java.io.Serializable;

public class FlightDTO implements Serializable {
    private Long id;
    private String name;
    private String model;
    private int econom_prlace_price;
    private int busines_place_price;
    private String departure_city;
    private String departure_country;
    private String arrival_city;
    private String arrival_country;
    private String departure_time;

    public FlightDTO(Long id, String name, String model, int econom_prlace_price, int busines_place_price, String departure_city, String departure_country, String arrival_city, String arrival_country, String departure_time) {
        this.id = id;
        this.name = name;
        this.model = model;
        this.econom_prlace_price = econom_prlace_price;
        this.busines_place_price = busines_place_price;
        this.departure_city = departure_city;
        this.departure_country = departure_country;
        this.arrival_city = arrival_city;
        this.arrival_country = arrival_country;
        this.departure_time = departure_time;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getEconom_prlace_price() {
        return econom_prlace_price;
    }

    public void setEconom_prlace_price(int econom_prlace_price) {
        this.econom_prlace_price = econom_prlace_price;
    }

    public int getBusines_place_price() {
        return busines_place_price;
    }

    public void setBusines_place_price(int busines_place_price) {
        this.busines_place_price = busines_place_price;
    }

    public String getDeparture_city() {
        return departure_city;
    }

    public void setDeparture_city(String departure_city) {
        this.departure_city = departure_city;
    }

    public String getDeparture_country() {
        return departure_country;
    }

    public void setDeparture_country(String departure_country) {
        this.departure_country = departure_country;
    }

    public String getArrival_city() {
        return arrival_city;
    }

    public void setArrival_city(String arrival_city) {
        this.arrival_city = arrival_city;
    }

    public String getArrival_country() {
        return arrival_country;
    }

    public void setArrival_country(String arrival_country) {
        this.arrival_country = arrival_country;
    }
}
