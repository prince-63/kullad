package com.kullad.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @NotBlank(message = "Product name is mandatory!")
    private String productName;

    @NotNull(message = "Price is mandatory!")
    @Min(value = 0, message = "Price must be positive!")
    private Double productPrice;

    @NotNull(message = "Discount is mandatory!")
    @Min(value = 0, message = "Discount must be positive!")
    private Double productDiscount;

    @NotNull(message = "Quantity is mandatory!")
    @Min(value = 0, message = "Quantity cannot be negative!")
    private Integer productQuantity;

    @NotBlank(message = "Shop address is mandatory!")
    private String shopAddress;

    @Size(max = 255, message = "Description cannot exceed 255 characters!")
    private String productDescription;
}
