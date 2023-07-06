package com.kan01234.spring.grpc.quickstart.endpoint;

import com.kan01234.spring.grpc.quickstart.HelloRequest;
import com.kan01234.spring.grpc.quickstart.HelloResponse;
import com.kan01234.spring.grpc.quickstart.HelloServiceGrpc;
import com.kan01234.spring.grpc.quickstart.dto.HelloResponseDto;
import com.kan01234.spring.grpc.quickstart.service.HelloService;
import com.kan01234.spring.grpc.quickstart.mapper.SayHelloMapper;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@AllArgsConstructor
@GrpcService
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

    private HelloService helloService;
    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        HelloResponseDto responseDTO = helloService.sayHello(SayHelloMapper.INSTANCE.reqProtoToDTO(request));
        responseObserver.onNext(SayHelloMapper.INSTANCE.resDTOToProto(responseDTO));
        responseObserver.onCompleted();
    }
}
