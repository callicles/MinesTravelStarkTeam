package org.springframework.samples.travel.infrastructure.persistence.mongo.shared;

import org.springframework.data.mongodb.core.query.Query;

/**
 * This interface serves as a type for object which can be converted to Mongo
 * query
 */
public interface CriteriaToMongoQuery {

    Query toMongoQuery();

}
