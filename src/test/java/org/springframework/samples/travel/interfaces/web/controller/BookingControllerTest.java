package org.springframework.samples.travel.interfaces.web.controller;

import static com.google.common.collect.Lists.newArrayList;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.samples.travel.application.BookingService;
import org.springframework.samples.travel.domain.model.booking.Booking;
import org.springframework.samples.travel.domain.model.booking.Hotel;
import org.springframework.samples.travel.domain.shared.SearchCriteria;
import org.springframework.ui.Model;

@RunWith(MockitoJUnitRunner.class)
public class BookingControllerTest {

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private BookingController bookingController;

    @Test
    public void shouldAddUserBookingsToModelIfUserPresent() {
        // Given
        SearchCriteria searchCriteria = new SearchCriteria();
        Principal currentUser = mock(Principal.class);
        Model model = mock(Model.class);

        when(currentUser.getName()).thenReturn("name");

        List<Booking> bookings = newArrayList(new Booking());
        when(bookingService.findBookings(currentUser.getName())).thenReturn(bookings);

        // When
        bookingController.search(searchCriteria, currentUser, model);

        // Then
        verify(model, times(1)).addAttribute(Mockito.eq("bookingList"), Mockito.same(bookings));
    }

    @Test
    public void shouldNotTryToAddUserBookingsIfUserNotPresent() {
        // Given
        SearchCriteria searchCriteria = new SearchCriteria();
        Model model = mock(Model.class);

        // When
        bookingController.search(searchCriteria, null, model);

        // Then
        verify(bookingService, never()).findBookings(Mockito.anyString());
        verify(model, never()).addAttribute(Mockito.anyString(), Mockito.anyObject());
    }

    @Test
    public void shouldAddHotelsToModelIfMatchSearchCriteria() {
        // Given
        SearchCriteria searchCriteria = new SearchCriteria();
        Model model = mock(Model.class);

        List<Hotel> hotels = newArrayList(new Hotel());
        when(bookingService.findHotels(searchCriteria)).thenReturn(hotels);

        // When
        bookingController.list(searchCriteria, model);

        // Then
        verify(model, times(1)).addAttribute(Mockito.same(hotels));
    }

    @Test
    public void shouldReturnCorrectPathWhenListingHotels() {
        // Given
        SearchCriteria searchCriteria = new SearchCriteria();
        Model model = mock(Model.class);

        // When
        String result = bookingController.list(searchCriteria, model);

        // Then
        assertThat(result).isEqualTo("hotels/list");
    }

    @Test
    public void shouldAddHotelToModelIfHotelIdMatchesRequestedId() {
        // Given
        String id = "id";
        Model model = mock(Model.class);

        Hotel hotel = new Hotel();
        when(bookingService.findHotel("id")).thenReturn(hotel);

        // When
        bookingController.show(id, model);

        // Then
        verify(model, times(1)).addAttribute(Mockito.same(hotel));
    }

    @Test
    public void shouldReturnCorrectPathWhenShowingAHotel() {
        // Given
        String id = "id";
        Model model = mock(Model.class);

        // When
        String result = bookingController.show(id, model);

        // Then
        assertThat(result).isEqualTo("hotels/show");
    }

    @Test
    public void shouldCancelBookingThatMatchesRequestedId() {
        // Given
        String id = "id";

        // When
        bookingController.deleteBooking(id);

        // Then
        verify(bookingService, times(1)).cancelBooking(Mockito.same(id));
    }

    @Test
    public void shouldReturnCorrectPathWhenDeletingBooking() {
        // Given
        String id = "id";

        // When
        String result = bookingController.deleteBooking(id);

        // Then
        assertThat(result).isEqualTo("redirect:../hotels/search");
    }

}
