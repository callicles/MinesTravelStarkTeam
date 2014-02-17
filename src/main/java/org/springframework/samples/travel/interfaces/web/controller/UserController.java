package org.springframework.samples.travel.interfaces.web.controller;

import org.springframework.samples.travel.application.BookingService;
import org.springframework.samples.travel.application.UserService;
import org.springframework.samples.travel.domain.model.booking.Booking;
import org.springframework.samples.travel.domain.shared.SearchCriteria;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.security.Principal;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Nicolas
 * Date: 04/02/14
 * Time: 14:13
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class UserController {

    private final UserService userService;

    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String create(String username, String password, String name) throws Exception{
        userService.createUser(username,password,name);
        return "redirect:../hotels/search";
    }
}