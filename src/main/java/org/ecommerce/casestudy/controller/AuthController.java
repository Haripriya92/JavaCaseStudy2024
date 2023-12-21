package org.ecommerce.casestudy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
@Slf4j
@Controller
public class AuthController {
    @GetMapping("/auth/login")
    public ModelAndView login() {
        ModelAndView response = new ModelAndView();
        log.debug(" Inside auth controller login function");
        response.setViewName("auth/login");
        return response;
    }
    @GetMapping("/auth/register")
    public ModelAndView register() {
        ModelAndView response = new ModelAndView();
        response.setViewName("auth/register");
        return response;
    }

}
