package org.springframework.samples.travel.application;

import java.util.List;

import org.springframework.samples.travel.domain.model.booking.Booking;
import org.springframework.samples.travel.domain.model.booking.Hotel;
import org.springframework.samples.travel.domain.shared.SearchCriteria;

public interface BookingService {

    /**
     * Find hotels available for booking by some criteria.
     * 
     * @param criteria
     *            the search criteria
     * @return a list of hotels meeting the criteria
     */
    List<Hotel> findHotels(SearchCriteria criteria);

    /**
     * Find hotels by their identifier.
     * 
     * @param id
     *            the hotel id
     * @return the hotel
     */
    Hotel findHotel(String id);

    /**
     * Find bookings made by the given user
     * 
     * @param username
     *            the user's name
     * @return their bookings
     */
    List<Booking> findBookings(String username);

    /**
     * Create a new, transient hotel booking instance for the given user.
     * 
     * @param hotelId
     *            the hotelId
     * @param userName
     *            the user name
     * @return the new transient booking instance
     */
    Booking createBooking(String hotelId, String userName);

    /**
     * Create a new, transient hotel booking instance for the given user.
     * 
     * @param booking
     *            the booking to save
     * @return the saved booking
     */
    Booking saveBooking(Booking booking);

    /**
     * Cancel an existing booking.
     * 
     * @param id
     *            the booking id
     */
    void cancelBooking(String id);
}
