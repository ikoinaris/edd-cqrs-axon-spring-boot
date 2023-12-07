package com.edm.productmicroservice.command;

import com.edm.productmicroservice.core.data.ProductLookupEntity;
import com.edm.productmicroservice.core.data.ProductLookupRepository;
import com.edm.productmicroservice.core.events.ProductCreatedEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group") // logically groups event handlers together
// makes sure that these handlers and handled once in the same thread
public class ProductLookupEventHandler {

    private final ProductLookupRepository productLookupRepository;

    public ProductLookupEventHandler(ProductLookupRepository productLookupRepository) {
        this.productLookupRepository = productLookupRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event) {

        ProductLookupEntity productLookupEntity = new ProductLookupEntity(
                event.getProductId(), event.getName()
        );

        productLookupRepository.save(productLookupEntity);
    }
}
