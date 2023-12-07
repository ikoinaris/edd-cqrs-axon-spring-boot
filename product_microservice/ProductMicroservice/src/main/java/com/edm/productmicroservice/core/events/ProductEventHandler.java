package com.edm.productmicroservice.core.events;

import com.edm.productmicroservice.core.data.ProductEntity;
import com.edm.productmicroservice.core.data.ProductRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
public class ProductEventHandler {

    private final ProductRepository productRepository;

    public ProductEventHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @ExceptionHandler(resultType = Exception.class)
    // will handle exceptions that are thrown from event handling methods of this class
    public void handleException(Exception exception) throws Exception {
        throw exception;
    }

    @ExceptionHandler(resultType = IllegalArgumentException.class)
    // will handle exceptions that are thrown from event handling methods of this class
    public void handleIllegalArgumentException(IllegalArgumentException illegalArgumentException) {

    }

    @EventHandler
    public void on(ProductCreatedEvent event) {

        ProductEntity entity = new ProductEntity();
        BeanUtils.copyProperties(event, entity);

        try {
            productRepository.save(entity);
        } catch (IllegalArgumentException exception) {
            exception.printStackTrace();
        }
    }
}
