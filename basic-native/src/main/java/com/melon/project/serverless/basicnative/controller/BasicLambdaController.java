package com.melon.project.serverless.basicnative.controller;

import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import com.melon.project.serverless.lambda.bind.annotation.*;
import com.melon.project.serverless.lambda.events.ApiGatewayRequestEvent;
import com.melon.project.serverless.basicnative.model.BasicResponse;

import java.util.Map;

@LambdaController
@PathMapping("basic")
public class BasicLambdaController {

    @PathGetMapping("example/get")
    public String exampleGet(){
        return "example GET";
    }

    @PathGetMapping("example/get/param/{id}")
    public String exampleGetParam(@PathParameter(name = "id") Long id){
        return "exampleGetParam GET ID: " + id;
    }

    @PathPostMapping("example/post")
    public APIGatewayV2HTTPResponse examplePost(@Body String body){
        return APIGatewayV2HTTPResponse.builder()
                .withStatusCode(200)
                .withBody(body)
                .build();
    }

    @PathPostMapping("example/post/fullRequest")
    public APIGatewayV2HTTPResponse examplePostFullRequest(ApiGatewayRequestEvent apiGatewayRequestEvent){
        return APIGatewayV2HTTPResponse.builder()
                .withStatusCode(200)
                .withBody(apiGatewayRequestEvent.getBody())
                .build();
    }

    @PathPutMapping("example/put")
    public BasicResponse examplePut(@Header(name = "username") String username,
                                    @QueryParameter(name = "apiVersion") String apiVersion){
        return new BasicResponse(username, apiVersion);
    }

    @PathPatchMapping("example/patch")
    public String examplePatch(@Headers Map<String, String> headers){
        return "Headers size: " + headers.size();
    }

    @PathDeleteMapping("example/delete/{name}")
    public String exampleDelete(@PathParameter(name = "name") String name){
        return name;
    }

}
