package com.kan01234.spring.grpc.quickstart.mapper;

/**
 * Abstract mapper class
 *
 * @param <REQ_PROTO> request proto type
 * @param <REQ_DTO> request dto type
 * @param <RES_PROTO> response proto type
 * @param <RES_DTO> response dto type
 */
public interface BaseMapper<REQ_PROTO, REQ_DTO, RES_PROTO, RES_DTO> {

    /**
     * Request proto to request DTO
     *
     * @param reqProto the request proto
     * @return request DTO
     */
    REQ_DTO reqProtoToDTO(REQ_PROTO reqProto);

    /**
     * Response DTO to response proto
     *
     * @param resDTO response DTO
     * @return response proto
     */
    RES_PROTO resDTOToProto(RES_DTO resDTO);
}
