package org.springframework.samples.travel.domain.model.booking;

import java.util.List;

import org.springframework.samples.travel.domain.model.user.User;

/**
 * Repository for {@linkplain Booking}
 */
public interface BookingRepository {

    /**
     * Find bookings for a given user
     * 
     * @param user
     *            The user
     * @return The user bookings
     */
    List<Booking> findUserBookings(User user);

    /**
     * Create a booking if it doesn't exist, otherwise an update is achieved
     * based on the booking id
     * 
     * @param booking
     *            The booking to save
     * @return The saved booking
     */
    Booking save(Booking booking);

    /**
     * Delete (physically) a booking
     * 
     * @param id
     *            The booking id
     */
    void delete(String id);

}
