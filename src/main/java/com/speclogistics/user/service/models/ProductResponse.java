package com.speclogistics.user.service.models;

import lombok.Data;

@Data
public class ProductResponse {
    private Integer productId;

    private Integer quantity;

    private Double price;

}
