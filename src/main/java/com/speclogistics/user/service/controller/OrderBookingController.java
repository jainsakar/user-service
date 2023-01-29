package com.speclogistics.user.service.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.speclogistics.order.service.grpcservice.ClientType;
import com.speclogistics.order.service.grpcservice.Item;
import com.speclogistics.order.service.grpcservice.OrderBookingDetails;
import com.speclogistics.order.service.grpcservice.OrderBookingResp;
import com.speclogistics.user.service.models.OrderRequestConstruct;
import com.speclogistics.user.service.models.OrderResponse;
import com.speclogistics.user.service.models.ProductResponse;
import com.speclogistics.user.service.service.GrpcClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users/order-booking")
@Slf4j
public class OrderBookingController {

    private final GrpcClientService grpcClientService;

    @PostMapping
    public Flux<OrderResponse> bookOrders(@Valid @RequestBody OrderRequestConstruct orderRequestConstruct){
        long st = System.currentTimeMillis();
        List<OrderBookingDetails> orderBookingDetailsList = new ArrayList<>();
        List<Item> items = new ArrayList<>();
        Item item = Item.newBuilder().setProductId(101).setQuantity(5).build();
        items.add(item);
        orderRequestConstruct.getOrderRequestList().forEach(orderRequest -> {
            OrderBookingDetails orderBookingDetails = OrderBookingDetails.newBuilder().setClientId(orderRequest.getClientId()).setParentId(100).addAllItems(items).setClientType(ClientType.SILVER).build();
            orderBookingDetailsList.add(orderBookingDetails);
        });
        return grpcClientService.bookOrders(orderBookingDetailsList).flatMap(resp -> {
            log.info("Response received : {} | time taken: {}ms" , resp, System.currentTimeMillis() - st);
            List<ProductResponse> productResponseList = new ArrayList<>();
            OrderResponse orderResponse = OrderResponse.builder().clientId(resp.getClientId()).parentId(resp.getParentId()).totalBillingAmount(resp.getTotalBillingAmount()).build();
            resp.getItemResponsesList().forEach(itemResp -> {
                ProductResponse productResponse = new ProductResponse();
                productResponse.setProductId(itemResp.getProductId());
                productResponse.setQuantity(itemResp.getQuantity());
                productResponse.setPrice(itemResp.getPrice());
                productResponseList.add(productResponse);
            });
            orderResponse.setProductResponseList(productResponseList);
            return Flux.just(orderResponse);
        });
    }
}
