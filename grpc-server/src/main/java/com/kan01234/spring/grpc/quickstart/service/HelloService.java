package com.kan01234.spring.grpc.quickstart.service;

import com.kan01234.spring.grpc.quickstart.dto.HelloRequestDto;
import com.kan01234.spring.grpc.quickstart.dto.HelloResponseDto;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public HelloResponseDto sayHello(HelloRequestDto helloRequestDto) {
        return HelloResponseDto.builder().message("hello, " + helloRequestDto.getName()).build();
    }
}
