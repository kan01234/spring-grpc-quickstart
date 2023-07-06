package com.kan01234.spring.grpc.quickstart;

import com.kan01234.spring.grpc.quickstart.endpoint.HelloServiceImpl;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for {@link HelloServiceImpl}
 */
@SpringJUnitConfig
@SpringBootTest(
    classes = HelloServiceImpl.class,
    properties = {
        "grpc.server.inProcessName=pubTest",
        "grpc.server.port=-1",
        "grpc.client.inProcess.address=in-process:pubTest"
})
@DirtiesContext
class HelloServiceImplIntegrationTest {

    /**
     * gRPC client for pub service
     */
    @GrpcClient(value = "inProcess")
    private HelloServiceGrpc.HelloServiceBlockingStub helloService;

    @Nested
    class SayHelloTest {

        @Test
        void success() {
            // say hello to peter
            HelloResponse response = helloService.sayHello(HelloRequest.newBuilder()
                    .setName("peter")
                    .build());

            Assertions.assertNotNull(response);
            assertEquals(HelloResponse.newBuilder().setMessage("Hello, peter").build(), response);
        }

        @Test
        void emptyName_failed() {
            HelloRequest request = HelloRequest.newBuilder()
                    .setName("      ")
                    .build();
            StatusRuntimeException exception = assertThrows(StatusRuntimeException.class,
                    () -> helloService.sayHello(request));
            Status status = exception.getStatus();
            assertEquals(Status.INVALID_ARGUMENT.getCode(), status.getCode());
            assertEquals("name: must not be blank", status.getDescription());
        }

        @Test
        void tooLongTopicName_failed() {
            HelloRequest request = HelloRequest.newBuilder()
                    .setName("a".repeat(256))
                    .build();
            StatusRuntimeException exception = assertThrows(StatusRuntimeException.class,
                    () -> helloService.sayHello(request));
            Status status = exception.getStatus();
            assertEquals(Status.INVALID_ARGUMENT.getCode(), status.getCode());
            assertEquals("hello: size must be between 1 and 255", status.getDescription());
        }
    }

}
