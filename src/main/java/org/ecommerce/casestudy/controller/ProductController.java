package org.ecommerce.casestudy.controller;

import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.ecommerce.casestudy.database.dao.ProductDao;
import org.ecommerce.casestudy.database.entity.Product;
import org.ecommerce.casestudy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
public class ProductController {
    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductService productService;
    @GetMapping("/product/productlist")
    public ModelAndView listProducts() {
        ModelAndView response = new ModelAndView();
        List<Product> productList = productDao.getAllProducts();
        response.addObject("productlist", productList);
        log.debug(" Inside product controller list product function");
        response.setViewName("product/productSearch");
        return response;
    }

    @GetMapping("/product/search")
    public ModelAndView searchProducts(@RequestParam(required = false) String itemName) {
        ModelAndView response = new ModelAndView("product/productSearch");
        log.debug(" Inside product controller search product function");
        if (!StringUtils.isEmpty(itemName)) {
                itemName = "%" + itemName + "%";
            List<Product> productList = productDao.findBySearchNameIgnoreCase(itemName);
            response.addObject("productlist", productList);
        }
        else{
            response.setViewName("redirect:/product/productlist");
        }
        return response;
    }
    @GetMapping("/product/selectedCategory")
    public ModelAndView selectedCategoryProducts(@RequestParam(required = false) String selectedValue) {
        log.debug(" Inside product controller selectedCategoryProducts function");
        ModelAndView response = new ModelAndView("product/productSearch");
        List<Product> productList;
        if(selectedValue.equals("asc")){
            productList= productDao.findAllOrderByProductPriceAsc();
        }
        else if(selectedValue.equals("desc")){
            productList= productDao.findAllOrderByProductPriceDesc();
        }
    else {
            productList= productDao.findBySelectedCategoryIgnoreCase(selectedValue);
        }
        response.addObject("productlist", productList);
        return response;
    }

    @GetMapping("/product/productdetails")
    public ModelAndView productDetails(@RequestParam(required = false) Integer selectedId) {
        log.debug(" Inside product controller listproductDetails function");
        ModelAndView response = new ModelAndView();
        if (selectedId!=null) {
         Product product=productDao.findByproductId(selectedId);
         response.addObject("selectedProduct",product);
            response.setViewName("product/productdetails");
        }
        else{
            response.setViewName("redirect:/product/productlist");
        }
        return response;
    }

}
