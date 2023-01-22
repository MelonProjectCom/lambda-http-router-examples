package com.melon.project.serverless.lambdabasicexample.controller;

import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import com.melon.project.serverless.basic.LambdaBasicExampleApplication;
import com.melon.project.serverless.lambda.entrypoint.ApiGatewayRequestHandler;
import com.melon.project.serverless.lambda.events.ApiGatewayRequestEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {LambdaBasicExampleApplication.class})
public class LambdaHandlerTest {

    @Autowired
    private ApiGatewayRequestHandler apiGatewayRequestHandler;

    @Test
    public void basicPostTest() throws Exception {
        String testBody = "Test";
        APIGatewayV2HTTPEvent.RequestContext.Http http = APIGatewayV2HTTPEvent.RequestContext.Http.builder()
                .withMethod("POST")
                .build();
        APIGatewayV2HTTPEvent.RequestContext requestContext = APIGatewayV2HTTPEvent.RequestContext.builder()
                .withHttp(http)
                .build();
        ApiGatewayRequestEvent apiGatewayRequestEvent = new ApiGatewayRequestEvent();
        apiGatewayRequestEvent.setBody(testBody);
        apiGatewayRequestEvent.setRawPath("/basic/example/post");
        apiGatewayRequestEvent.setRequestContext(requestContext);

        APIGatewayV2HTTPResponse apiGatewayV2HTTPResponse = apiGatewayRequestHandler.handleRequest(apiGatewayRequestEvent, null);

        assertEquals(200, apiGatewayV2HTTPResponse.getStatusCode());
        assertEquals(testBody, apiGatewayV2HTTPResponse.getBody());

    }
}
