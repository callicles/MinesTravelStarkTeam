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

    /**
     * Create a user from username password and Name
     * @param username the usermane used to login
     * @param password the password to login
     * @param Name the name to be displayed
     * @return the created user
     */
    User save(String username, String password, String Name) throws Exception;
}
