package com.edm.productmicroservice.query;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductQueryController {

    @Autowired
    QueryGateway queryGateway;

    @GetMapping
    public List<ProductRestModel> getProducts() {

        FindProductsQuery productsQuery = new FindProductsQuery();
        List<ProductRestModel> products = queryGateway.query(productsQuery,
                ResponseTypes.multipleInstancesOf(ProductRestModel.class)).join();

        return products;
    }
}
