package org.springframework.samples.travel.infrastructure.persistence.mongo.shared;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

/**
 * Class that share basic mongo queries implementation
 * 
 * @param <T>
 *            The domain model handled by the repository
 */
public abstract class AbstractMongoRepository<T> {

    protected final MongoTemplate mongoTemplate;
    protected final Class<T> clazz;

    public AbstractMongoRepository(MongoTemplate mongoTemplate, Class<T> clazz) {
        super();
        this.mongoTemplate = mongoTemplate;
        this.clazz = clazz;
    }

    protected T findOneById(String id) {
        return mongoTemplate.findById(id, clazz);
    }

    protected List<T> findByQuery(Query query) {
        return mongoTemplate.find(query, clazz);
    }

    protected T findOneByQuery(Query query) {
        return mongoTemplate.findOne(query, clazz);
    }

    public T save(T objectToSave) {
        mongoTemplate.save(objectToSave);
        return objectToSave;
    }

    public void delete(String id) {
        mongoTemplate.remove(query(where("_id").is(new ObjectId(id))), clazz);
    }

}
