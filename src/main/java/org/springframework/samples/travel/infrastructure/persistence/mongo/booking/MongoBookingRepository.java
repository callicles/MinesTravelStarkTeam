package org.springframework.samples.travel.infrastructure.persistence.mongo.booking;

import static org.springframework.samples.travel.infrastructure.persistence.mongo.booking.criteria.SearchBookingCriteria.newSearchBookingCriteria;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.samples.travel.domain.model.booking.Booking;
import org.springframework.samples.travel.domain.model.booking.BookingRepository;
import org.springframework.samples.travel.domain.model.user.User;
import org.springframework.samples.travel.infrastructure.persistence.mongo.shared.AbstractMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Mongo implementation of {@linkplain BookingRepository}
 */
@Repository
public class MongoBookingRepository extends AbstractMongoRepository<Booking> implements BookingRepository {

    @Inject
    public MongoBookingRepository(MongoTemplate mongoTemplate) {
        super(mongoTemplate, Booking.class);
    }

    @Override
    public List<Booking> findUserBookings(User user) {
        return findByQuery(newSearchBookingCriteria(user).toMongoQuery());
    }

}
