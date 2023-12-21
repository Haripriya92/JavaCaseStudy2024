package org.ecommerce.casestudy.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.ecommerce.casestudy.database.dao.UserDao;
import org.ecommerce.casestudy.database.entity.User;
import org.ecommerce.casestudy.formbean.CreateUserFormBean;
import org.ecommerce.casestudy.security.AuthenticatedUserService;
import org.ecommerce.casestudy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class UserController {
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticatedUserService authenticatedUserService;

    @GetMapping("/auth/userRegister")
    public ModelAndView registerSubmit(@Valid CreateUserFormBean form, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            log.debug("######################### In register user - has errors #########################");
            ModelAndView response = new ModelAndView("auth/register");

            for (ObjectError error : bindingResult.getAllErrors()) {
                log.debug("error: " + error.getDefaultMessage());
            }

            response.addObject("form", form);
            response.addObject("errors", bindingResult);
            return response;
        }
        log.debug("######################### In register user - no error found #########################");

        User u = userService.createNewUser(form);

        authenticatedUserService.authenticateNewUser(session, u.getEmail(), form.getPassword());
        ModelAndView response = new ModelAndView();
        response.setViewName("redirect:/auth/login"+"?success=Registered Successfully");

        return response;
    }
}
