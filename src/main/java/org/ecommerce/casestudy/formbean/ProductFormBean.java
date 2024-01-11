package org.ecommerce.casestudy.formbean;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ProductFormBean {
    @NotEmpty(message = "Product name is required ")
    private String productName;

    @NotEmpty(message = "Product Description is required ")
    private String productDescription;

    @ValidFile(message = "Upload a file", maxSize = 10485760) // Set maximum file size to 10 MB
    private MultipartFile file;

    @NotNull(message = "Quantity is required.")
    private Integer quantity;

    @NotNull(message = "Rating is required.")
    private Integer rating;

    @NotNull(message = "Price is required.")
    private Integer price;

    @NotEmpty(message = "Category is required ")
    private String selectedCategory;
}
