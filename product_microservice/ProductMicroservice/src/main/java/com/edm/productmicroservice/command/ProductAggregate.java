package com.edm.productmicroservice.command;

import com.edm.productmicroservice.core.events.ProductCreatedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Aggregate
@NoArgsConstructor
public class ProductAggregate {

    @AggregateIdentifier
    private String productId;
    private String name;
    private BigDecimal price;
    private Integer quantity;

    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand) throws Exception {
        //Validate Create Product Command
        if (createProductCommand.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price cannot be less or equal to 0.");
        }

        if(createProductCommand.getName() == null
            || createProductCommand.getName().isBlank()) {
            throw new IllegalArgumentException("Product must have a valid title.");
        }

        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();
        // command -> event
        BeanUtils.copyProperties(createProductCommand, productCreatedEvent);

        //when apply method is called event will be patched to all event handlers
        // so the state of this aggregate can be updated with new information
        AggregateLifecycle.apply(productCreatedEvent);

        if (true) throw new Exception("An error took place in ProductAggregate");
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent productCreatedEvent) {

        this.productId = productCreatedEvent.getProductId();
        this.name = productCreatedEvent.getName();
        this.price = productCreatedEvent.getPrice();
        this.quantity = productCreatedEvent.getQuantity();

    }

}
