package com.kullad.models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @NotNull(message = "Orders is mandatory!")
    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Orders orders;

    @NotNull(message = "Amount is mandatory!")
    @Min(value = 0, message = "Amount must be positive!")
    private Double amount;

    @NotNull(message = "Payment status is mandatory!")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @NotBlank(message = "Payment method is mandatory!")
    private String paymentMethod;

    private String transactionId;
}
