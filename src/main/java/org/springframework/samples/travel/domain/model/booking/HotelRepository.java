package org.springframework.samples.travel.domain.model.booking;

import java.util.List;

import org.springframework.samples.travel.domain.shared.SearchCriteria;

/**
 * Repository for {@linkplain Hotel}
 */
public interface HotelRepository {

    /**
     * Find hotels that match the {@linkplain SearchCriteria}
     * 
     * @param searchCriteria
     *            The search criteria
     * @return Hotels that match the <code>searchCriteria</code>
     */
    List<Hotel> findHotelsByCriteria(SearchCriteria searchCriteria);

    /**
     * Find a hotel given its id
     * 
     * @param id
     *            The hotel id
     * @return The corresponding hotel
     */
    Hotel findHotelById(String id);
}
