package org.springframework.samples.travel.infrastructure.persistence.mongo.user;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import javax.inject.Inject;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.samples.travel.domain.model.user.User;
import org.springframework.samples.travel.domain.model.user.UserRepository;
import org.springframework.samples.travel.infrastructure.persistence.mongo.shared.AbstractMongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Mongo implementation of {@linkplain UserRepository}
 */
@Repository
public class MongoUserRepository extends AbstractMongoRepository<User> implements UserRepository {

    @Inject
    public MongoUserRepository(MongoTemplate mongoTemplate) {
        super(mongoTemplate, User.class);
    }

    @Override
    public User findByUsername(String username) {
        return findOneByQuery(query(where("username").is(username)));
    }

    @Override
    public User save(String username, String password, String name) throws Exception{
        /*
        MessageDigest messageDigest = MessageDigest.getInstance("md5");
        messageDigest.update(password.getBytes(),0, password.length());
        String hashedPass = new BigInteger(1,messageDigest.digest()).toString(16);

        if (hashedPass.length() < 32) {
            hashedPass = "0" + hashedPass;
        } */

        return save(new User(username,password,name));
    }

}
