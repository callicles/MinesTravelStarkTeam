package org.springframework.samples.travel.infrastructure.persistence.mongo.booking.criteria;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.samples.travel.domain.shared.SearchCriteria;
import org.springframework.samples.travel.infrastructure.persistence.mongo.shared.CriteriaToMongoQuery;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * A backing bean for the main hotel search form. Encapsulates the criteria
 * needed to perform a hotel search.
 * 
 */
public class SearchHotelCriteria implements CriteriaToMongoQuery {

    private static final String INSENSTIVE_CASE_REGEX = "i";

    private final SearchCriteria searchCriteria;

    private SearchHotelCriteria(SearchCriteria searchCriteria) {
        Assert.notNull(searchCriteria);
        this.searchCriteria = searchCriteria;
    }

    public static SearchHotelCriteria newSearchHotelCriteria(SearchCriteria searchCriteria) {
        return new SearchHotelCriteria(searchCriteria);
    }

    @Override
    public Query toMongoQuery() {
        Criteria findHotelsCriteria = buildFindHotelsCriteria(searchCriteria);
        return findHotelsCriteria == null ? new Query() : new Query(findHotelsCriteria);
    }

    private Criteria buildFindHotelsCriteria(SearchCriteria searchCriteria) {
        String pattern = getSearchPattern(searchCriteria);
        if (pattern == null) {
            return null;
        }
        Criteria nameCriteria = where("name").regex(pattern, INSENSTIVE_CASE_REGEX);
        Criteria cityCriteria = where("city").regex(pattern, INSENSTIVE_CASE_REGEX);
        Criteria zipCriteria = where("zip").regex(pattern, INSENSTIVE_CASE_REGEX);
        Criteria addressCriteria = where("address").regex(pattern, INSENSTIVE_CASE_REGEX);
        return new Criteria().orOperator(nameCriteria, cityCriteria, zipCriteria, addressCriteria);
    }

    private String getSearchPattern(SearchCriteria searchCriteria) {
        String searchCriteriaAsString = searchCriteria.getSearchString();
        if (StringUtils.hasText(searchCriteriaAsString)) {
            return ".*" + searchCriteriaAsString.trim().toLowerCase() + ".*";
        } else {
            return null;
        }
    }

}
