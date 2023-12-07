package com.edm.OrderMicroservice.command;

import com.edm.OrderMicroservice.OrderMicroserviceRestModel;
import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderCommandController {

    private final Environment env;
    private final CommandGateway commandGateway;

    public OrderCommandController(Environment env, CommandGateway commandGateway) {
        this.env = env;
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createOrder(@Valid @RequestBody OrderMicroserviceRestModel model) {

        CreateOrderCommand command = CreateOrderCommand.builder()
                .userId(model.getUserId())
                .productId(model.getProductId())
                .quantity(model.getQuantity())
                .addressId(model.getAddressId())
                .orderStatus(model.getOrderStatus()).build();

        String commandValue;

        commandValue = commandGateway.sendAndWait(command);

        return commandValue;
    }
}
