package org.ecommerce.casestudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SlashController {
    @GetMapping("/auth/home")
    public ModelAndView home() {
        ModelAndView response = new ModelAndView();
        response.setViewName("auth/home");
        return response;
    }


}
