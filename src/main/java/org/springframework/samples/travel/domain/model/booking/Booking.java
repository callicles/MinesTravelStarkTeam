package org.springframework.samples.travel.domain.model.booking;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.travel.domain.model.booking.Hotel.Amenity;
import org.springframework.samples.travel.domain.model.user.User;

/**
 * A Hotel Booking made by a User.
 */
@Document(collection = "bookings")
public class Booking implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    @DBRef
    private User user;
    @DBRef
    private Hotel hotel;

    @DateTimeFormat(pattern = "MM-dd-yyyy")
    private Date checkinDate;

    @DateTimeFormat(pattern = "MM-dd-yyyy")
    private Date checkoutDate;

    private String creditCard;

    private String creditCardName;

    private int creditCardExpiryMonth;

    private int creditCardExpiryYear;

    private boolean smoking;

    private int beds;

    private List<Hotel.Amenity> amenities;

    public Booking() {
        Calendar calendar = Calendar.getInstance();
        this.checkinDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        this.checkoutDate = calendar.getTime();
    }

    public Booking(Hotel hotel, User user) {
        this();
        this.hotel = hotel;
        this.user = user;
    }

    public BigDecimal getTotal() {
        return hotel.getPrice().multiply(new BigDecimal(getNights()));
    }

    public int getNights() {
        if (checkinDate == null || checkoutDate == null) {
            return 0;
        } else {
            return (int) (checkoutDate.getTime() - checkinDate.getTime()) / 1000 / 60 / 60 / 24;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(Date datetime) {
        this.checkinDate = datetime;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getDescription() {
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        return hotel == null ? null : hotel.getName() + ", " + df.format(getCheckinDate()) + " to "
                + df.format(getCheckoutDate());
    }

    public boolean isSmoking() {
        return smoking;
    }

    public void setSmoking(boolean smoking) {
        this.smoking = smoking;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public String getCreditCardName() {
        return creditCardName;
    }

    public void setCreditCardName(String creditCardName) {
        this.creditCardName = creditCardName;
    }

    public int getCreditCardExpiryMonth() {
        return creditCardExpiryMonth;
    }

    public void setCreditCardExpiryMonth(int creditCardExpiryMonth) {
        this.creditCardExpiryMonth = creditCardExpiryMonth;
    }

    public int getCreditCardExpiryYear() {
        return creditCardExpiryYear;
    }

    public void setCreditCardExpiryYear(int creditCardExpiryYear) {
        this.creditCardExpiryYear = creditCardExpiryYear;
    }

    public List<Amenity> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<Amenity> amenities) {
        this.amenities = amenities;
    }

    @Override
    public String toString() {
        return "Booking(" + user + "," + hotel + ")";
    }

}
