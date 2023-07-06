package com.kan01234.spring.grpc.quickstart.advice;

import io.grpc.Status;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

/**
 * gRPC exception advice
 */
@SuppressWarnings("unused")
@GrpcAdvice
public class GrpcExceptionAdvice {

    /**
     * handle not adviced excpetion
     *
     * @param e exception
     * @return internal status runtime exception
     */
    @GrpcExceptionHandler
    public Status handleException(Exception e) {
        return Status.INTERNAL.withCause(e);
    }
}
