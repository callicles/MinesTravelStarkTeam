package org.springframework.samples.travel.application.impl;

import static com.google.common.collect.Lists.newArrayList;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.samples.travel.domain.model.booking.Booking;
import org.springframework.samples.travel.domain.model.booking.BookingRepository;
import org.springframework.samples.travel.domain.model.booking.Hotel;
import org.springframework.samples.travel.domain.model.booking.HotelRepository;
import org.springframework.samples.travel.domain.model.user.User;
import org.springframework.samples.travel.domain.model.user.UserRepository;
import org.springframework.samples.travel.domain.shared.SearchCriteria;

@RunWith(MockitoJUnitRunner.class)
public class BookingServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private HotelRepository hotelRepository;

    @InjectMocks
    private BookingServiceImpl service;

    @Test
    public void shouldFindHotelsBySearchCriteria() {
        // Given
        SearchCriteria searchCriteria = new SearchCriteria();
        List<Hotel> expectedHotels = newArrayList();
        when(hotelRepository.findHotelsByCriteria(searchCriteria)).thenReturn(expectedHotels);

        // When
        List<Hotel> hotels = service.findHotels(searchCriteria);

        // Then
        assertThat(hotels).isSameAs(expectedHotels);
    }

    @Test
    public void shouldFindHotelsById() {
        // Given
        String id = "id";
        Hotel expectedHotel = new Hotel();
        when(hotelRepository.findHotelById(id)).thenReturn(expectedHotel);

        // When
        Hotel hotel = service.findHotel(id);

        // Then
        assertThat(hotel).isSameAs(expectedHotel);
    }

    @Test
    public void shouldFindBookingsGivenAnUsername() {
        // Given
        String username = "username";
        User user = new User();
        when(userRepository.findByUsername(username)).thenReturn(user);

        List<Booking> expectedBookings = newArrayList();
        when(bookingRepository.findUserBookings(user)).thenReturn(expectedBookings);

        // When
        List<Booking> bookings = service.findBookings(username);

        // Then
        assertThat(bookings).isSameAs(expectedBookings);
    }

    @Test
    public void shouldCreateBooking() {
        // Given
        String hotelId = "hotelId";
        String username = "username";

        Hotel hotel = mock(Hotel.class);
        when(hotelRepository.findHotelById(hotelId)).thenReturn(hotel);

        User user = new User();
        when(userRepository.findByUsername(username)).thenReturn(user);

        Booking bookingToSave = new Booking(), expectedBooking = new Booking();
        when(hotel.createBooking(user)).thenReturn(bookingToSave);
        when(bookingRepository.save(bookingToSave)).thenReturn(expectedBooking);

        // When
        Booking booking = service.createBooking(hotelId, username);

        // Then
        assertThat(booking).isSameAs(expectedBooking);
    }

    @Test
    public void shouldCancelBooking() {
        // When
        String id = "id";
        service.cancelBooking(id);

        // Then
        verify(bookingRepository, times(1)).delete(id);
    }
}
