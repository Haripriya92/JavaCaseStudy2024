package org.ecommerce.casestudy.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.ecommerce.casestudy.database.dao.ProductDao;
import org.ecommerce.casestudy.database.entity.Cart;
import org.ecommerce.casestudy.database.entity.Product;
import org.ecommerce.casestudy.formbean.PayementFormBean;
import org.ecommerce.casestudy.formbean.ProductFormBean;
import org.ecommerce.casestudy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Controller
public class AdminController {
    @Autowired
    private ProductDao productDao;

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
            response.setViewName("/auth/admintask");
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
            response.addObject("message", "Product already Exist");
        }
        else{
          product=  productService.addNewProduct(form,file.getOriginalFilename());
            response.addObject("message", "Product Added");
        }
        response.setViewName("redirect:/product/productdetails?selectedId="+product.getId());
        return response;
    }
}
