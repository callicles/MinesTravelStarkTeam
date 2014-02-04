package org.springframework.samples.travel.domain.model.user;

/**
 * Repository for {@linkplain User}
 */
public interface UserRepository {

    /**
     * Find an user by its username
     * 
     * @param username
     *            The username to search for
     * @return The user associated with the given username, <code>null</code>
     *         otherwise.
     */
    User findByUsername(String username);
}
