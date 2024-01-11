package org.ecommerce.casestudy.formbean;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class ValidFileValidator implements ConstraintValidator<ValidFile, MultipartFile> {

    private long maxSize;

    @Override
    public void initialize(ValidFile constraintAnnotation) {
        this.maxSize = constraintAnnotation.maxSize();
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if (file == null || file.isEmpty()) {
            return false; // File is empty
        }

        // Optionally, check file size
        if (file.getSize() > maxSize) {
            return false; // File size exceeds the specified limit
        }

        // Add more validation as needed

        return true; // File is valid
    }
}
