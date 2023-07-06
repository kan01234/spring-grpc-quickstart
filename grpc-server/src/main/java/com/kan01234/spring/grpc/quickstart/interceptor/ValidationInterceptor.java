package com.kan01234.spring.grpc.quickstart.interceptor;

import io.grpc.*;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

/**
 * Interceptor for validation
 */
@GrpcGlobalServerInterceptor
@Component
@AllArgsConstructor
public class ValidationInterceptor implements ServerInterceptor, Ordered {

    /**
     * Validator
     */
    private final Validator validator;

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {

        ServerCall.Listener<ReqT> listener = next.startCall(new ForwardingServerCall.SimpleForwardingServerCall<>(call) {}, headers);
        return new ForwardingServerCallListener.SimpleForwardingServerCallListener<>(listener) {
            @Override
            public void onMessage(ReqT message) {
                final Set<ConstraintViolation<ReqT>> violations = validator.validate(message);
                if (!violations.isEmpty()) {
                    Status status = Status.INVALID_ARGUMENT.withDescription(new ConstraintViolationException(violations).getMessage());
                    call.close(status, headers);
                } else {
                    super.onMessage(message);
                }

            }
        };

    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE + 10;
    }
}
