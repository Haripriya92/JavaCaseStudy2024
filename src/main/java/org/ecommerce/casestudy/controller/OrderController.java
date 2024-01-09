package org.ecommerce.casestudy.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.ecommerce.casestudy.database.dao.CartDao;
import org.ecommerce.casestudy.database.dao.OrderDao;
import org.ecommerce.casestudy.database.dao.ProductDetailDao;
import org.ecommerce.casestudy.database.entity.Cart;
import org.ecommerce.casestudy.database.entity.Order;
import org.ecommerce.casestudy.database.entity.ProductDetail;
import org.ecommerce.casestudy.database.entity.User;
import org.ecommerce.casestudy.formbean.PayementFormBean;
import org.ecommerce.casestudy.security.AuthenticatedUserService;
import org.ecommerce.casestudy.service.OrderService;
import org.ecommerce.casestudy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
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
    @Autowired
    CartDao cartDao;
    @Autowired
    OrderService orderService;
    @Autowired
    private AuthenticatedUserService aunthenticatedUser;
    @PostMapping("/order/addToCart/{productId}")
    public ModelAndView addToCart(@PathVariable Integer productId, @RequestParam("selectedSize") String selectedSize,
                                  @RequestParam("quantity") Integer quantity) {
        if(selectedSize==null||quantity==null){
            selectedSize="S";
            quantity=1;
        }
        ModelAndView response = new ModelAndView();
        productService.updateProductDetails(productId, selectedSize, quantity);
        List<Cart> cartList = cartDao.findAll();
        BigDecimal subTotal=orderService.findCartTotal(cartList);
        BigDecimal tax=orderService.findTaxPercentage(subTotal);
        BigDecimal total=subTotal.add(tax);
            response.addObject("subtotal", subTotal);
            response.addObject("tax", tax);
            response.addObject("total", total);
        response.addObject("cartproducts", cartList);
        response.setViewName("order/cartdetails");
        return response;
    }

    @GetMapping("/order/addToCartShortcut/{productId}")
    public ModelAndView addToCartShortcut(@PathVariable Integer productId) {
        ModelAndView response = new ModelAndView();
        productService.updateProductDetails(productId, "S", 1);
        response.setViewName("redirect:/product/productlist");
        return response;
    }

    @GetMapping("/order/deleteCartItem/{cartId}")
    public ModelAndView deleteCartItem(@PathVariable Integer cartId) {
        ModelAndView response = new ModelAndView();
        cartDao.deleteById(cartId);
        List<Cart> cartList = cartDao.findAll();
        if(!cartList.isEmpty()) {
            BigDecimal subTotal=orderService.findCartTotal(cartList);
            BigDecimal tax=orderService.findTaxPercentage(subTotal);
            BigDecimal total=subTotal.add(tax);
            response.addObject("subtotal", subTotal);
            response.addObject("tax", tax);
            response.addObject("total", total);
            response.addObject("cartproducts", cartList);
            response.setViewName("order/cartdetails");
        }
        else{
            response.setViewName("redirect:/product/productlist");
        }
        return response;
    }


    @GetMapping("/order/displayCart")
    public ModelAndView displayCart() {
        ModelAndView response = new ModelAndView();
        User user=aunthenticatedUser.loadCurrentUser();
        List<Cart> cartList = cartDao.findByUserId(user.getId());
        BigDecimal subTotal=orderService.findCartTotal(cartList);
        BigDecimal tax=orderService.findTaxPercentage(subTotal);
        BigDecimal total=subTotal.add(tax);
        if(!cartList.isEmpty()) {
            response.addObject("subtotal", subTotal);
            response.addObject("tax", tax);
            response.addObject("total", total);
            response.addObject("cartproducts", cartList);
            response.setViewName("order/cartdetails");

        }
        else{
            response.setViewName("redirect:/product/productlist");
        }
        return response;
    }

    @PostMapping("/order/updateCartItem/{cartId}")
    public ModelAndView updateCartItem(@PathVariable Integer cartId,
                                       @RequestParam String selectedSize,
                                 @RequestParam Integer quantity) {
        ModelAndView response = new ModelAndView();
       Cart cartItem= cartDao.findByCartId(cartId);
      ProductDetail productDetail= productDetailDao.findByMainProductIdAndSize(cartItem.getProduct().getMainProduct().getId(), selectedSize);
        boolean isUpdateSuccessful=productService.updateProductInCart(cartItem,productDetail,quantity);
        if (isUpdateSuccessful) {
            response.addObject("success","Updated Successfully");
        }
        response.setViewName("redirect:/order/displayCart");
        return response;
    }

    @GetMapping("/order/proceedCheckout")
    public ModelAndView proceedToCheckout() {
        ModelAndView response = new ModelAndView();
        User user=aunthenticatedUser.loadCurrentUser();
        response.addObject("user",user);

        List<Cart> cartList = cartDao.findAll();
        BigDecimal subTotal=orderService.findCartTotal(cartList);
        BigDecimal tax=orderService.findTaxPercentage(subTotal);
        BigDecimal total=subTotal.add(tax);
        response.addObject("subtotal", subTotal);
        response.addObject("tax", tax);
        response.addObject("total", total);
        response.addObject("cartproducts", cartList);

        response.setViewName("order/checkout");
        return response;
    }

    @GetMapping("/order/placeOrder")
    public ModelAndView placeOrder(@Valid PayementFormBean  form, BindingResult bindingResult, HttpSession session,
                                   @RequestParam(required=false) Boolean defaultAddress) {
        ModelAndView response = new ModelAndView();

        if (bindingResult.hasErrors()) {
            response.addObject("form", form);
            response.addObject("errors", bindingResult);
            List<Cart> cartList = cartDao.findAll();
            BigDecimal subTotal=orderService.findCartTotal(cartList);
            BigDecimal tax=orderService.findTaxPercentage(subTotal);
            BigDecimal total=subTotal.add(tax);
            if(!cartList.isEmpty()) {
                response.addObject("subtotal", subTotal);
                response.addObject("tax", tax);
                response.addObject("total", total);
            }
            response.setViewName("order/checkout");
            return response;
        }

       Order order= orderService.addToOrderandOrderDetails(defaultAddress,form);
       response.addObject("order",order);
        response.setViewName("order/orderplacement");
        return response;
    }
}
