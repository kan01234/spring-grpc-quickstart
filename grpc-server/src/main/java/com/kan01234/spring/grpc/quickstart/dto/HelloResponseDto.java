package com.kan01234.spring.grpc.quickstart.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HelloResponseDto {
    private String message;
}
