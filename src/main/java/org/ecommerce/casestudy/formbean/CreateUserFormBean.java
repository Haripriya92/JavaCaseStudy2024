package org.ecommerce.casestudy.formbean;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
@Getter
@Setter
public class CreateUserFormBean {
    private Integer id;

    @NotEmpty(message = "First name is required ")
    private String firstName;

    @NotEmpty(message = "Last name is required ")
    private String lastName;

    @NotEmpty(message = "Email is required ")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$", message = "Invalid email format")
    private String email;

    @NotNull(message = "Phone number is required.")
    private Integer phone;

    @NotEmpty(message = "Password cannot be empty")
    @Length(min=8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "Password must contain at least one lowercase letter, one uppercase letter, and one digit")
    private String password;

    @NotEmpty(message = "Confirm Password cannot be empty")
    private String confirmPassword;

    @NotEmpty(message = "Country is required.")
    private String country;

}
