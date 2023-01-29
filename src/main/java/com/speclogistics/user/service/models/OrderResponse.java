package com.speclogistics.user.service.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderResponse {
    private Integer clientId;

    private Integer parentId;

    private String clientType;

    List<ProductResponse> productResponseList;

    private Double totalBillingAmount;
}
