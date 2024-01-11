package org.ecommerce.casestudy.controller;

import lombok.extern.slf4j.Slf4j;
import org.ecommerce.casestudy.database.dao.OrderDao;
import org.ecommerce.casestudy.database.entity.Order;
import org.ecommerce.casestudy.database.entity.User;
import org.ecommerce.casestudy.security.AuthenticatedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
public class AuthController {
    @Autowired
    OrderDao orderDao;
    @Autowired
    AuthenticatedUserService authenticatedUserService;
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

    @GetMapping("/admin/task")
    public ModelAndView admin() {
        ModelAndView response = new ModelAndView();
        response.setViewName("auth/admintask");
        return response;
    }
    @GetMapping("/auth/user")
    public ModelAndView userDetails() {
        ModelAndView response = new ModelAndView();
        User user=authenticatedUserService.loadCurrentUser();
        List<Order> orders=orderDao.findByCustomerId(user.getId());
        response.addObject("orderlist",orders);
        response.setViewName("auth/userorderdetails");
        return response;
    }
}
