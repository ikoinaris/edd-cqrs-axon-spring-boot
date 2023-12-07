package com.edm.productmicroservice.command;

import com.edm.productmicroservice.ProductMicroserviceRestModel;
import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductCommandController {

    private final Environment env;
    private final CommandGateway commandGateway;

    public ProductCommandController(Environment env, CommandGateway commandGateway) {
        this.env = env;
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createProduct(@Valid @RequestBody ProductMicroserviceRestModel model) {

        CreateProductCommand command = CreateProductCommand.builder()
                .price(model.getPrice())
                .quantity(model.getQuantity())
                .name(model.getName())
                .productId(UUID.randomUUID().toString()).build();

        String commandValue;

        commandValue = commandGateway.sendAndWait(command);

        /*try {
            commandValue = commandGateway.sendAndWait(command);
        } catch (Exception e) {
            commandValue = e.getLocalizedMessage();
        }*/

        return commandValue;
    }

    //@GetMapping
    //public String getProduct() {
    //    return "HTTP GET handled " + env.getProperty("local.server.port");
    //}

    @PutMapping
    public String updateProduct() {
        return "HTTP PUT handled";
    }

    @DeleteMapping
    public String deleteProduct() {
        return "HTTP DELETE handled";
    }
}
