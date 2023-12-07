package com.edm.productmicroservice.query;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class ProductRestModel {

    private String productId;
    private String name;
    private BigDecimal price;
    private Integer quantity;
}
