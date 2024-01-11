package org.ecommerce.casestudy.service;

import org.ecommerce.casestudy.database.dao.CartDao;
import org.ecommerce.casestudy.database.dao.ProductDao;
import org.ecommerce.casestudy.database.dao.ProductDetailDao;
import org.ecommerce.casestudy.database.entity.Cart;
import org.ecommerce.casestudy.database.entity.Product;
import org.ecommerce.casestudy.database.entity.ProductDetail;
import org.ecommerce.casestudy.formbean.ProductFormBean;
import org.ecommerce.casestudy.security.AuthenticatedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;


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
  public Product  addNewProductAndProductDetails(ProductFormBean form,String filename){
        Product product=addNewProduct(form,filename);
      productDetailDao.insertProductDetails(form.getSelectedCategory(),10,product.getId(),"S");
      productDetailDao.insertProductDetails(form.getSelectedCategory(),10,product.getId(),"M");
      productDetailDao.insertProductDetails(form.getSelectedCategory(),10,product.getId(),"L");
      productDetailDao.insertProductDetails(form.getSelectedCategory(),10,product.getId(),"XL");
        return product;
  }
    public Product  addNewProduct(ProductFormBean form,String filename){
        Product product=new Product();
        LocalDateTime currentDateTime = LocalDateTime.now();

        Timestamp addedDate = Timestamp.valueOf(currentDateTime);
        product.setProductName(form.getProductName());
        product.setProductDescription(form.getProductDescription());
        product.setRating(form.getRating());
        product.setAddedDate(addedDate);
        product.setProductImage("/pub/images/product/" +filename);
        product.setProductPrice(BigDecimal.valueOf(form.getPrice()));
        return productDao.save(product);
    }

}
