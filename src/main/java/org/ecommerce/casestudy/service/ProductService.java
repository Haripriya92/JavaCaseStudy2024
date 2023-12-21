package org.ecommerce.casestudy.service;

import org.ecommerce.casestudy.database.dao.ProductDao;
import org.ecommerce.casestudy.database.dao.ProductDetailDao;
import org.ecommerce.casestudy.database.entity.ProductDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductDetailDao productDetailDao;

    public ProductDetail updateProductDetails(Integer productId, String selectedSize,Integer quantity) {
        ProductDetail productDetail = productDetailDao.findByMainProductIdAndSize(productId, selectedSize);
        BigDecimal total;
        Integer quantityAdded;
        if(productDetail.getProductStatus().equals("CART")){
            quantityAdded=productDetail.getCartQuantity()+quantity;
            productDetail.setCartQuantity(quantityAdded);
           total=(productDetail.getMainProduct().getProductPrice().
                   multiply(new BigDecimal(quantity))).add(productDetail.getCartTotal());
            productDetail.setCartTotal(total);
        }
        else {
            productDetail.setProductStatus("CART");
            productDetail.setCartQuantity(quantity);
            total = productDetail.getMainProduct().getProductPrice().multiply(new BigDecimal(quantity));
            productDetail.setCartTotal(total);
        }
    return productDetailDao.save(productDetail);
    }
    public void deleteProductFromCart( Integer productId){
        ProductDetail productDetail = productDetailDao.findById(productId);
        productDetail.setCartQuantity(0);
        productDetail.setProductStatus("UNSELECTED");
        productDetail.setCartTotal(null);
        productDetailDao.save(productDetail);
    }

}
