package com.api.steps;

import com.api.support.apis.AnimalApi;
import com.api.support.domain.Animal;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import java.util.List;

import static org.hamcrest.Matchers.is;


public class AnimalStepsDefs {
    List<Animal> animalactualList;
    Response response;
    private AnimalApi animalApi;

    public AnimalStepsDefs() {
        animalApi = new AnimalApi();
    }

    @When("^eu pesquiso por todos os animais \"([^\"]*)\"$")
    public void euPesquisoPorTodosOsAnimais(String statusPesquisado) throws Throwable {

        response = animalApi.getAnimalStatus(statusPesquisado);
//        response =  animalApi.getAnimalStatus(statusPesquisado).body().jsonPath().getList("",Animal.class);
        response.then().statusCode(HttpStatus.SC_OK);
    }

    @Then("^eu recebo a lista com (\\d+) animais \"([^\"]*)\"$")
    public void euReceboAListaComAnimais(int quantidade, String statusPesquisado) throws Throwable {
        response.then().body(
                "size()", is(quantidade),
                "findAll {it.status == '" + statusPesquisado + "'}.size()", is(quantidade)
        );

    }
}

