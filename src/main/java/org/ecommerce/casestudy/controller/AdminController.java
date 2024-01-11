package org.ecommerce.casestudy.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.ecommerce.casestudy.database.dao.OrderDao;
import org.ecommerce.casestudy.database.dao.ProductDao;
import org.ecommerce.casestudy.database.dao.UserDao;
import org.ecommerce.casestudy.database.entity.Order;
import org.ecommerce.casestudy.database.entity.Product;
import org.ecommerce.casestudy.database.entity.User;
import org.ecommerce.casestudy.formbean.ProductFormBean;
import org.ecommerce.casestudy.service.OrderService;
import org.ecommerce.casestudy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;


@Slf4j
@Controller
public class AdminController {
    @Autowired
    private ProductDao productDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @PostMapping("/admin/addProduct")
    public ModelAndView fileUploadSubmit(@RequestParam("file") MultipartFile file, @Valid ProductFormBean form,
                                         BindingResult bindingResult
                                         ) {

        ModelAndView response = new ModelAndView();
       if (bindingResult.hasErrors()) {
            response.addObject("form", form);
            response.addObject("errors", bindingResult);
           response.addObject("hasErrors", true);
            response.setViewName("auth/admintask");
            return response;
        }
        File f = new File("./src/main/webapp/pub/images/product/" + file.getOriginalFilename());
        try (OutputStream outputStream = new FileOutputStream(f.getAbsolutePath())) {
            IOUtils.copy(file.getInputStream(), outputStream);
        } catch (Exception e) {


            e.printStackTrace();
        }
        Product product = productDao.findByproductName(form.getProductName());
        if(product!=null){
            response.setViewName("redirect:/admin/task"+ "?message=Product already Exist");
        }
        else{
          product=  productService.addNewProductAndProductDetails(form,file.getOriginalFilename());
            response.setViewName("redirect:/product/productdetails?selectedId="+product.getId());
        }
        return response;
    }

    @GetMapping("/admin/selectOrderDetails")
    public ModelAndView selectOrderDetails(@RequestParam String orderNumber)
    {

        ModelAndView response = new ModelAndView();
        Order order=orderDao.findByOrderDetail(orderNumber);
        if(order!=null) {
            response.addObject("hasOrder", true);
            response.addObject("order",order);
        }
        else {
            response.addObject("hasOrder", false);
        }
        response.addObject("detailsFetched",true);
        response.setViewName("auth/admintask");
        return response;
    }
    @GetMapping("/admin/updateOrderStatus")
    public ModelAndView updateStatus(@RequestParam Integer orderId,
                                     @RequestParam  String newOrderStatus) {
        ModelAndView response = new ModelAndView();

        Order order = orderDao.findById(orderId);
        orderService.updateOrderStatus(order,newOrderStatus);
        response.setViewName("redirect:/admin/selectOrderDetails?orderNumber=" + order.getOrderNumber());

        return response;

    }

    @GetMapping("/admin/viewUsers")
    public ModelAndView viewUser() {
        ModelAndView response = new ModelAndView();

        List<User> users=userDao.getAllUsers();
        response.addObject("usersFetched",true);
        response.addObject("userlist",users);
        response.setViewName("auth/admintask");

        return response;

    }
}
