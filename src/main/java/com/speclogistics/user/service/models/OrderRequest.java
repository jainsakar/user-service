package com.speclogistics.user.service.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.speclogistics.user.service.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class OrderRequest {
    private Integer clientId;

    private UserType userType;

    List<Product> product;

}
