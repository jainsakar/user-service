package com.speclogistics.user.service.service;

import com.speclogistics.order.service.grpcservice.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GrpcClientService {
    private ReactorPingPongServiceGrpc.ReactorPingPongServiceStub pongServiceStub;

    private ReactorOrderBookingServiceGrpc.ReactorOrderBookingServiceStub orderBookingServiceStub;

    @PostConstruct
    public void setup(){
        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress("localhost", 9090)
                .usePlaintext()
                .build();
        this.pongServiceStub = ReactorPingPongServiceGrpc.newReactorStub(managedChannel);
        this.orderBookingServiceStub = ReactorOrderBookingServiceGrpc.newReactorStub(managedChannel);
    }

    public Mono<PongResponse> ping() {
        return pongServiceStub.ping(PingRequest.newBuilder()
                .setPing("Sakar")
                .build());
    }

    public Flux<OrderBookingResp> bookOrders(List<OrderBookingDetails> orderBookingDetailsList){
        return orderBookingServiceStub.bookOrders(Flux.fromIterable(orderBookingDetailsList));
    }


}
