package com.api.support.apis;

import com.api.support.domain.User;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class UserApi {
    private static final String CREATE_USER_ENDPOINT = "/v3/user";
    private static final String USER_ENDPOINT = "/v3/user/{name}";
    Response response;

    public Response createUser(User user) {
        return given().
                body(user).
                when().
                post(CREATE_USER_ENDPOINT);
    }
}
