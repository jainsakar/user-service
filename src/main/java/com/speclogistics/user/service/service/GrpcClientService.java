package com.speclogistics.user.service.service;

import com.speclogistics.order.service.grpcservice.PingRequest;
import com.speclogistics.order.service.grpcservice.PongResponse;
import com.speclogistics.order.service.grpcservice.ReactorPingPongServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class GrpcClientService {
    private ManagedChannel managedChannel;
    private ReactorPingPongServiceGrpc.ReactorPingPongServiceStub serviceStub;

    @PostConstruct
    public void setup(){
        this.managedChannel = ManagedChannelBuilder
                .forAddress("localhost", 9090)
                .usePlaintext()
                .build();
        this.serviceStub = ReactorPingPongServiceGrpc.newReactorStub(managedChannel);
    }

    public Mono<PongResponse> ping() {
        return serviceStub.ping(PingRequest.newBuilder()
                .setPing("Sakar")
                .build());
    }


}
