package com.kan01234.spring.grpc.quickstart.mapper;

import com.kan01234.spring.grpc.quickstart.HelloRequest;
import com.kan01234.spring.grpc.quickstart.HelloResponse;
import com.kan01234.spring.grpc.quickstart.dto.HelloRequestDto;
import com.kan01234.spring.grpc.quickstart.dto.HelloResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.factory.Mappers.getMapper;

/**
 * Mapper for acknowledge message
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SayHelloMapper
        extends BaseMapper<HelloRequest, HelloRequestDto,
        HelloResponse, HelloResponseDto> {

    String NAME = "name";
    String MESSAGE = "message";

    /**
     * Mapper instance to be reused
     */
    SayHelloMapper INSTANCE = getMapper(SayHelloMapper.class);

    @Mapping(source = NAME, target = NAME)
    @Override
    HelloRequestDto reqProtoToDTO(HelloRequest reqProto);

    @Mapping(source = MESSAGE, target = MESSAGE)
    @Override
    HelloResponse resDTOToProto(HelloResponseDto resDTO);
}
