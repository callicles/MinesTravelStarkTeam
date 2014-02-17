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
public class SearchUserCriteria implements CriteriaToMongoQuery {

    private static final String INSENSTIVE_CASE_REGEX = "i";

    private final SearchCriteria searchCriteria;

    private SearchUserCriteria(SearchCriteria searchCriteria) {
        Assert.notNull(searchCriteria);
        this.searchCriteria = searchCriteria;
    }

    public static SearchUserCriteria newSearchHotelCriteria(SearchCriteria searchCriteria) {
        return new SearchUserCriteria(searchCriteria);
    }

    @Override
    public Query toMongoQuery() {
        Criteria findUsersCriteria = buildFindUsersCriteria(searchCriteria);
        /*String name = searchCriteria.;
        Integer pageIndex = searchCriteria.getPage();*/
        return findUsersCriteria == null
                ? new Query()
                : new Query(findUsersCriteria);
    }

    private Criteria buildFindUsersCriteria(SearchCriteria searchCriteria) {
        String pattern = getSearchPattern(searchCriteria);
        if (pattern == null) {
            return null;
        }
        Criteria nameCriteria = where("name").regex(pattern, INSENSTIVE_CASE_REGEX);
        Criteria userNameCriteria = where("userName").regex(pattern, INSENSTIVE_CASE_REGEX);
        //Criteria roleCriteria = where("roles").regex(pattern, INSENSTIVE_CASE_REGEX);
        return new Criteria().orOperator(nameCriteria, userNameCriteria);
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
