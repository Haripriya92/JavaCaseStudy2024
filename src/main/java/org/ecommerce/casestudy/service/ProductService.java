package org.ecommerce.casestudy.service;

import org.ecommerce.casestudy.database.dao.CartDao;
import org.ecommerce.casestudy.database.dao.ProductDao;
import org.ecommerce.casestudy.database.dao.ProductDetailDao;
import org.ecommerce.casestudy.database.entity.Cart;
import org.ecommerce.casestudy.database.entity.ProductDetail;
import org.ecommerce.casestudy.security.AuthenticatedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;


@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductDetailDao productDetailDao;

    @Autowired
    private CartDao cartDao;
    @Autowired
    private AuthenticatedUserService aunthenticatedUser;

    public Cart updateProductDetails(Integer productId, String selectedSize,Integer quantity) {
        ProductDetail productDetail = productDetailDao.findByMainProductIdAndSize(productId, selectedSize);
        BigDecimal total;
        Integer quantityAdded;
        Cart cartItem= cartDao.findByproductId(productDetail.getId());
        if(cartItem!=null){
            quantityAdded=cartItem.getQuantity()+quantity;
            cartItem.setQuantity(quantityAdded);
           total=(productDetail.getMainProduct().getProductPrice().
                   multiply(new BigDecimal(quantity))).add(cartItem.getSubTotal());
            cartItem.setSubTotal(total);
        }
        else {
            cartItem=new Cart();
            cartItem.setQuantity(quantity);
            total = productDetail.getMainProduct().getProductPrice().multiply(new BigDecimal(quantity));
            cartItem.setSubTotal(total);
            cartItem.setProduct(productDetail);
            cartItem.setUser(aunthenticatedUser.loadCurrentUser());
        }
    return cartDao.save(cartItem);
    }

   public boolean updateProductInCart(Cart cartItem, ProductDetail productDetail,Integer quantity) {
        BigDecimal total;
         cartItem.setProduct(productDetail);
            cartItem.setQuantity(quantity);
            total = productDetail.getMainProduct().getProductPrice().multiply(new BigDecimal(quantity));
            cartItem.setSubTotal(total);
        Cart savedCart = cartDao.save(cartItem);

        // Check if the save operation was successful
        return savedCart != null;
    }
}
