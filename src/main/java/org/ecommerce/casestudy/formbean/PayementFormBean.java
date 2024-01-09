package org.ecommerce.casestudy.formbean;


import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class PayementFormBean {
    @NotEmpty(message = "First name is required ")
    private String firstName;

    @NotEmpty(message = "Last name is required ")
    private String lastName;

    @NotEmpty(message = "Email is required ")
    private String email;

    @NotNull(message = "Phone number is required.")
    private Integer phone;

    @NotEmpty(message = "Country is required.")
    private String country;

    @NotEmpty(message = "Address is required.")
    private String address1;

    private String address2;

    @NotEmpty(message = "City is required.")
    private String city;

    @NotEmpty(message = "Zip is required.")
    private String zip;

    @NotEmpty(message = "State is required.")
    private String state;

}
