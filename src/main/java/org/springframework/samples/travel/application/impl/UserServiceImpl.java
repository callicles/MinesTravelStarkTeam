package org.springframework.samples.travel.application.impl;

import org.springframework.samples.travel.application.UserService;
import org.springframework.samples.travel.domain.model.user.User;
import org.springframework.samples.travel.domain.model.user.UserRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: Nicolas
 * Date: 04/02/14
 * Time: 14:18
 * To change this template use File | Settings | File Templates.
 */

@Service("userService")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Inject
    UserServiceImpl(UserRepository userRepository){
        super();
        this.userRepository = userRepository;
    }

    public User createUser(String userName, String password, String name) throws Exception{
        return  userRepository.save(userName,password,name);
    }

    @Override
    public User createUser() throws Exception {
        return new User();
    }

    @Override
    public User saveUser(User user) throws Exception {
        return createUser(user.getUsername(),user.getPassword(),user.getName());
    }
}
