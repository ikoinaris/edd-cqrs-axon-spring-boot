package com.edm.OrderMicroservice;

import com.edm.OrderMicroservice.command.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderMicroserviceRestModel {

    private String userId;
    private String productId;
    private int quantity;
    private String addressId;
    private OrderStatus orderStatus;
}
