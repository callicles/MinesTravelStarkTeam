package org.springframework.samples.travel.infrastructure.persistence.mongo.booking;

import static com.google.common.collect.Lists.newArrayList;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.samples.travel.infrastructure.persistence.mongo.booking.criteria.SearchHotelCriteria.newSearchHotelCriteria;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.samples.travel.domain.model.booking.Hotel;
import org.springframework.samples.travel.domain.shared.SearchCriteria;

@RunWith(MockitoJUnitRunner.class)
public class MongoHotelRepositoryTest {

    @Mock
    private MongoTemplate mongoTemplate;
    @InjectMocks
    private MongoHotelRepository repository;

    @Test
    public void souldFindHotelById() {
        // Given
        String id = "id";
        Hotel hotel = new Hotel();
        when(mongoTemplate.findById(id, Hotel.class)).thenReturn(hotel);

        // When & Then
        assertThat(repository.findHotelById(id)).isSameAs(hotel);
    }

    @Test
    public void souldFindAllMatchingHotelsForCriteria() {
        // Given
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setSearchString("test");
        List<Hotel> hotels = newArrayList();
        when(mongoTemplate.find(newSearchHotelCriteria(searchCriteria).toMongoQuery(), Hotel.class)).thenReturn(hotels);

        // When & Then
        assertThat(repository.findHotelsByCriteria(searchCriteria)).isSameAs(hotels);
    }

}
