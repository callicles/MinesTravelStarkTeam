package org.springframework.samples.travel.infrastructure.persistence.mongo.booking;

import static org.springframework.samples.travel.infrastructure.persistence.mongo.booking.criteria.SearchHotelCriteria.newSearchHotelCriteria;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.samples.travel.domain.model.booking.Hotel;
import org.springframework.samples.travel.domain.model.booking.HotelRepository;
import org.springframework.samples.travel.domain.shared.SearchCriteria;
import org.springframework.samples.travel.infrastructure.persistence.mongo.shared.AbstractMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Mongo implementation of {@linkplain HotelRepository}
 */
@Repository
public class MongoHotelRepository extends AbstractMongoRepository<Hotel> implements HotelRepository {

    @Inject
    public MongoHotelRepository(MongoTemplate mongoTemplate) {
        super(mongoTemplate, Hotel.class);
    }

    @Override
    public List<Hotel> findHotelsByCriteria(SearchCriteria searchCriteria) {
        return findByQuery(newSearchHotelCriteria(searchCriteria).toMongoQuery());
    }

    @Override
    public Hotel findHotelById(String id) {
        return findOneById(id);
    }

}
