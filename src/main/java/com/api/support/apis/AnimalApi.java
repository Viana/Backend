package com.api.support.apis;

import com.api.support.domain.User;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AnimalApi {
    private static final String ANIMAL_STATUS = "v3/pet/findByStatus?status={status}";

    public Response getAnimalStatus(String statusPesquisado){
        return given().
                    pathParam("status",statusPesquisado).
                when().
                    get(ANIMAL_STATUS);
    }
}
