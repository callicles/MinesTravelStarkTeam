package org.springframework.samples.travel.infrastructure.security;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.samples.travel.domain.model.user.User;
import org.springframework.samples.travel.domain.model.user.UserRepository;
import org.springframework.samples.travel.infrastructure.persistence.mongo.user.MongoUserRepository;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.google.common.collect.Sets;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newHashSet;

/**
 * Created by raphael on 2/17/14.
 */
public class MongoUserService implements UserDetailsService{
    private UserRepository repo;

    private static final SimpleGrantedAuthority ROLE_SUPERVISOR = new SimpleGrantedAuthority("ROLE_SUPERVISOR");
    private static final GrantedAuthority ROLE_USER = new SimpleGrantedAuthority("ROLE_USER");
    private static final Map<String, org.springframework.security.core.userdetails.User> GRANTED_USERS = newHashMap();

    @Inject
    public MongoUserService(UserRepository repo){
        this.repo=repo;
    }
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user=repo.findByUsername(s);
        CountingService.successfulConnections++;
        if (user == null) {
            throw new UsernameNotFoundException("invalid username");
        }
        HashSet<GrantedAuthority> roles = newHashSet();
        for (String role : user.getRoles()){
            if(role.equals("user"))
                    roles.add(ROLE_USER);
             if(role.equals("admin"))
                roles.add(ROLE_SUPERVISOR);
        }

        UserDetails rv=new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),roles);
        return rv;
    }
}
