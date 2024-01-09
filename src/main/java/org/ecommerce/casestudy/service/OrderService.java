package org.ecommerce.casestudy.service;

import org.ecommerce.casestudy.database.dao.CartDao;
import org.ecommerce.casestudy.database.dao.OrderDao;
import org.ecommerce.casestudy.database.dao.OrderDetailsDao;
import org.ecommerce.casestudy.database.entity.Cart;
import org.ecommerce.casestudy.database.entity.Order;
import org.ecommerce.casestudy.database.entity.OrderDetail;
import org.ecommerce.casestudy.database.entity.User;
import org.ecommerce.casestudy.formbean.PayementFormBean;
import org.ecommerce.casestudy.security.AuthenticatedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private CartDao cartDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderDetailsDao orderDetailsDao;
    @Autowired
    private AuthenticatedUserService aunthenticatedUser;
   public BigDecimal findCartTotal(List<Cart> cartList){
       BigDecimal total =  BigDecimal.ZERO;;
       for (Cart cart : cartList) {
           total = total.add(cart.getSubTotal());
       }
       total = total.setScale(2, RoundingMode.HALF_UP);
       return total;
   }
   public BigDecimal findTaxPercentage(BigDecimal total){
       BigDecimal tax=total.multiply(new BigDecimal("0.06"));
       tax = tax.setScale(2, RoundingMode.HALF_UP);
       return tax;
   }

    public Order addToOrderandOrderDetails(Boolean defaultAddress, PayementFormBean form){
        User user=aunthenticatedUser.loadCurrentUser();
        if(defaultAddress==null){
            defaultAddress=false;
               }
        if(defaultAddress){
            user.setAddress1(form.getAddress1());
            user.setAddress2(form.getAddress2());
            user.setCity(form.getCity());
            user.setState(form.getState());
            user.setZip(form.getZip());

        }
        String address=form.getAddress1()+form.getAddress2()+
                form.getCity()+form.getState()+form.getZip();
        String orderNumber=generateOrderNumber("FAS");
       Order order= updateOrderFromCart(orderNumber,address,user);
        updateOrderDetailsFromCart(user.getId(),order);
        cartDao.deleteByUserId(user.getId());
        return order;

    }
    public static String generateOrderNumber(String prefix) {
        // Get the current timestamp
        LocalDateTime timestamp = LocalDateTime.now();

        // Generate a random component using UUID
        String randomComponent = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);

        // Combine prefix, timestamp, and random component to form the order number
        String orderNumber = String.format("%s-%d%s", prefix, timestamp.toEpochSecond(ZoneOffset.UTC), randomComponent);

        return orderNumber;
    }
    public void updateOrderDetailsFromCart(Integer userId, Order order) {
        // Step 1: Retrieve Cart Information
        List<Cart> cartList = cartDao.findByUserId(userId);

        // Step 2: Calculate Order Details and Update OrderDetails Table
        for (Cart cart : cartList) {
            OrderDetail orderDetails = orderDetailsDao.findByOrderIdAndProductId(order.getId(), cart.getProduct().getId());

            if (orderDetails != null) {
                // Update existing order details
                orderDetails.setQuantity(cart.getQuantity());
                orderDetails.setTotal(cart.getSubTotal());
            } else {
                // Create new order details if not exists
                orderDetails = new OrderDetail();
                orderDetails.setOrder(order);
                orderDetails.setProductDetail(cart.getProduct());
                orderDetails.setQuantity(cart.getQuantity());
                orderDetails.setTotal(cart.getSubTotal());
            }
             orderDetailsDao.save(orderDetails);

        }


    }

    public Order updateOrderFromCart(String orderNumber,String address ,User user) {

        // Step 1: Retrieve Cart Information
        List<Cart> cartList = cartDao.findByUserId(user.getId());
        BigDecimal subTotal=findCartTotal(cartList);
        BigDecimal tax=findTaxPercentage(subTotal);
        BigDecimal total=subTotal.add(tax);

        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Convert LocalDateTime to Timestamp
        Timestamp orderDate = Timestamp.valueOf(currentDateTime);
        /*Orders order=orderDao.findByOrderDetail(orderNumber);

            if (order != null) {
                orderNumber=generateOrderNumber("FAS");
                order= new Orders();
                order.setCustomer(user);
                order.setOrderDate(orderDate);
                order.setOrderStatus("ordered");
                order.setTotal(total);
                order.setTax(tax);
                order.setSubTotal(subTotal);
                order.setOrderNumber(orderNumber);
            } else {*/
                // Create new order details if not exists
               Order order= new Order();
                order.setCustomer(user);
                order.setOrderDate(orderDate);
                order.setOrderStatus("ordered");
                order.setTotal(total);
                order.setTax(tax);
                order.setSubTotal(subTotal);
                order.setOrderNumber(orderNumber);
                order.setAddress(address);

            // Save or update order details
           return orderDao.save(order);

    }

}
