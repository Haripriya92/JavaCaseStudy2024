package org.ecommerce.casestudy.controller;

import lombok.extern.slf4j.Slf4j;
import org.ecommerce.casestudy.database.dao.OrderDao;
import org.ecommerce.casestudy.database.dao.ProductDetailDao;
import org.ecommerce.casestudy.database.entity.ProductDetail;
import org.ecommerce.casestudy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
public class OrderController {
    @Autowired
    OrderDao orderDao;
    @Autowired
    ProductDetailDao productDetailDao;
    @Autowired
    ProductService productService;

    @PostMapping("/order/listcart/{productId}")
    public ModelAndView addtoCart(@PathVariable Integer productId, @RequestParam("selectedSize") String selectedSize,
                                  @RequestParam("quantity") Integer quantity) {
        ModelAndView response = new ModelAndView();
        ProductDetail productDetail = productService.updateProductDetails(productId, selectedSize, quantity);
        List<ProductDetail> productList = productDetailDao.findByProductStatus();
        response.addObject("cartproducts", productList);
        response.setViewName("order/cartdetails");
        return response;
    }

    @GetMapping("/order/deleteCartItem/{productId}")
    public ModelAndView deleteCartItem(@PathVariable Integer productId) {
        ModelAndView response = new ModelAndView();
        productService.deleteProductFromCart(productId);
        List<ProductDetail> productList = productDetailDao.findByProductStatus();
        if(!productList.isEmpty()) {
            response.addObject("cartproducts", productList);
            response.setViewName("order/cartdetails");
        }
        else{
            response.setViewName("redirect:/product/productlist");
        }
        return response;
    }

}
