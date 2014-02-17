package org.springframework.samples.travel.application;

import org.springframework.samples.travel.domain.model.user.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Nicolas
 * Date: 04/02/14
 * Time: 14:15
 * To change this template use File | Settings | File Templates.
 */
public interface UserService {

    /**
     * Create user
     * @param userName
     * @param password
     * @param name
     * @return created user
     */
    public User createUser(String userName, String password, String name) throws Exception;
    public User createUser() throws Exception;
    public User saveUser(User user) throws Exception;

	List<User> findUsers();
}
