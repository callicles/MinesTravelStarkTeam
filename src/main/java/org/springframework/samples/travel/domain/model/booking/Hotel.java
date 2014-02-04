package org.springframework.samples.travel.domain.model.booking;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.samples.travel.domain.model.user.User;

/**
 * A hotel where users may book stays.
 */
@Document(collection = "hotels")
public class Hotel implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 6688678236160248906L;

    public enum Amenity {
        OCEAN_VIEW("Ocean View"),
        LATE_CHECKOUT("Late Checkout"),
        MINIBAR("Minibar");

        private String name;

        Amenity(String name){
            this.name = name;
        }

        public String getName(){
            return name;
        }
    }

    @Id
    private String id;

    private String name;

    private String address;

    private String city;

    private String state;

    private String zip;

    private String country;

    private BigDecimal price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Booking createBooking(User user) {
        return new Booking(this, user);
    }

    @Override
    public String toString() {
        return "Hotel(" + name + "," + address + "," + city + "," + zip + ")";
    }

}
