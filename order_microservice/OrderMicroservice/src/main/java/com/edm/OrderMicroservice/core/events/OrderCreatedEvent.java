package com.edm.OrderMicroservice.core.events;

import com.edm.OrderMicroservice.command.OrderStatus;
import lombok.Data;

@Data
public class OrderCreatedEvent {

    public String orderId;
    private String userId;
    private String productId;
    private int quantity;
    private String addressId;
    private OrderStatus orderStatus;
}
