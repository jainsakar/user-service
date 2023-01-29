package com.speclogistics.user.service.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestConstruct {
    List<@Valid OrderRequest> orderRequestList;
}
