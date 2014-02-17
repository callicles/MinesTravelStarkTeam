package org.springframework.samples.travel.infrastructure.security;

import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newHashSet;

import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.inject.Inject;

/**
 * <p>
 * This class implements a in memory {@linkplain UserDetailsService}. Users are
 * stored in an internal map which key is the username. The map is statically
 * created and there is no way to add an user.
 * </p>
 * 
 * <p>
 * Available users are :
 * <ul>
 * <li>keith/melbourne</li>
 * <li>erwin/leuver</li>
 * <li>jeremy/atlanta</li>
 * <li>scott/rochester</li>
 * </ul>
 * </p>
 * 
 */
public class InMemoryUserDetailsService implements UserDetailsService {

    private static final SimpleGrantedAuthority ROLE_SUPERVISOR = new SimpleGrantedAuthority("ROLE_SUPERVISOR");
    private static final GrantedAuthority ROLE_USER = new SimpleGrantedAuthority("ROLE_USER");
    private static final Map<String, User> GRANTED_USERS = newHashMap();

    static {
        GRANTED_USERS.put("keith",
                new User("keith", "417c7382b16c395bc25b5da1398cf076", newHashSet(ROLE_USER, ROLE_SUPERVISOR)));
        GRANTED_USERS.put("erwin",
                new User("erwin", "12430911a8af075c6f41c6976af22b09", newHashSet(ROLE_USER, ROLE_SUPERVISOR)));
        GRANTED_USERS.put("jeremy", new User("jeremy", "57c6cbff0d421449be820763f03139eb", newHashSet(ROLE_USER)));
        GRANTED_USERS.put("scott", new User("scott", "942f2339bf50796de535a384f0d1af3e", newHashSet(ROLE_USER)));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.security.core.userdetails.UserDetailsService#
     * loadUserByUsername(java.lang.String)
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = GRANTED_USERS.get(username);
        CountingService.successfulConnections++;//optimistic count, decremented if fails
        if (userDetails == null) {
            throw new UsernameNotFoundException("invalid username");
        }
        return userDetails;
    }
}
