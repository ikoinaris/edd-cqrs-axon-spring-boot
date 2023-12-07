package com.edm.OrderMicroservice.core.events;

import com.edm.OrderMicroservice.core.data.OrderEntity;
import com.edm.OrderMicroservice.core.data.OrderRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("order-group")
public class OrderEventsHandler {

    private final OrderRepository orderRepository;

    public OrderEventsHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @EventHandler
    public void on(OrderCreatedEvent event) {

        OrderEntity entity = new OrderEntity();
        BeanUtils.copyProperties(event, entity);

        try {
            orderRepository.save(entity);
        } catch (IllegalArgumentException exception) {
            exception.printStackTrace();
        }
    }
}
