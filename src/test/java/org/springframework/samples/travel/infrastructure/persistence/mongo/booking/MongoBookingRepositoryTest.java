package org.springframework.samples.travel.infrastructure.persistence.mongo.booking;

import static com.google.common.collect.Lists.newArrayList;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.samples.travel.infrastructure.persistence.mongo.booking.criteria.SearchBookingCriteria.newSearchBookingCriteria;

import java.util.List;
import java.util.UUID;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.samples.travel.domain.model.booking.Booking;
import org.springframework.samples.travel.domain.model.user.User;

@RunWith(MockitoJUnitRunner.class)
public class MongoBookingRepositoryTest {

    @Mock
    private MongoTemplate mongoTemplate;
    @InjectMocks
    private MongoBookingRepository repository;

    @Test
    public void souldFindUserBookings() {
        // Given
        User user = new User();
        List<Booking> bookings = newArrayList();
        when(mongoTemplate.find(newSearchBookingCriteria(user).toMongoQuery(), Booking.class)).thenReturn(bookings);

        // When & Then
        assertThat(repository.findUserBookings(user)).isSameAs(bookings);
    }

    @Test
    public void souldDeleteBooking() {
        // Given
        String id = "123456789123456789123456";

        // When
        repository.delete(id);

        // Then
        verify(mongoTemplate, times(1)).remove(query(where("_id").is(new ObjectId(id))),Booking.class);
    }

    @Test
    public void souldSaveBooking() {
        // Given
        Booking expectedBooking = new Booking();

        // When
        Booking savedBooking = repository.save(expectedBooking);

        // Then
        assertThat(savedBooking).isSameAs(expectedBooking);
        verify(mongoTemplate, times(1)).save(Mockito.same(expectedBooking));
    }
}
