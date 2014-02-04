/**
 * 
 */
package org.springframework.samples.travel.infrastructure.persistence.mongo.booking.criteria;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.samples.travel.domain.model.user.User;
import org.springframework.samples.travel.infrastructure.persistence.mongo.shared.CriteriaToMongoQuery;
import org.springframework.util.Assert;

/**
 * Criteria to search booking
 * 
 */
public class SearchBookingCriteria implements CriteriaToMongoQuery {

    private final User user;

    private SearchBookingCriteria(User user) {
        super();
        Assert.notNull(user);
        this.user = user;
    }

    public static SearchBookingCriteria newSearchBookingCriteria(User user) {
        return new SearchBookingCriteria(user);
    }

    @Override
    public Query toMongoQuery() {
        return new Query(where("user").is(user));
    }

}
