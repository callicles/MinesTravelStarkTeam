package org.springframework.samples.travel.infrastructure.persistence.mongo.user;

import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newHashSet;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.samples.travel.infrastructure.persistence.mongo.booking.criteria.SearchHotelCriteria.newSearchHotelCriteria;

import javax.inject.Inject;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.samples.travel.domain.model.user.User;
import org.springframework.samples.travel.domain.model.user.UserRepository;
import org.springframework.samples.travel.infrastructure.persistence.mongo.shared.AbstractMongoRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Mongo implementation of {@linkplain UserRepository}
 */
@Repository
public class MongoUserRepository extends AbstractMongoRepository<User> implements UserRepository{

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
        MessageDigest messageDigest = MessageDigest.getInstance("md5");
        messageDigest.update(password.getBytes(),0, password.length());
        String hashedPass = new BigInteger(1,messageDigest.digest()).toString(16);

        if (hashedPass.length() < 32) {
            hashedPass = "0" + hashedPass;
        }
        User user = new User(username,hashedPass,name);
        List<String> roles = new ArrayList<String>();
        roles.add("user");
        if(username.startsWith("admin_")){
            roles.add("admin");
        }
        user.setRoles(roles);
        return save(user);
    }

    @Override
    public List<User> findUsers() {
        return mongoTemplate.findAll(User.class);
    }

}
