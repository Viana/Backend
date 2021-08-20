package com.api.steps;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;

import com.api.Token;
import com.core.automation.ConfiguracaoArquivoPropertiesUsers;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Steps {
	private Response response;
	
	@Given("^que acesse a API \"([^\"]*)\"$")
	public void que_acesse_a_API(final String urlApi) throws Throwable {
		RestAssured.baseURI = ConfiguracaoArquivoPropertiesUsers.getProp().getProperty(urlApi);
	}

	@Given("^que acessei o endpoint \"([^\"]*)\"$")
	public void que_acessei_o_endpoint(final String endPoint) throws Throwable {
		RestAssured.basePath = endPoint;
	}

	@When("^executar o metodo GET com Content-Type igual \"([^\"]*)\"$")
	public void executar_o_metodo_GET_com_contenttype_igual_something(String valorContentType) throws Throwable {
		HashMap<String, String> listHeaders = new HashMap<String, String>();
		listHeaders.put("Content-Type", valorContentType);
		listHeaders.put("Accept-Charset", "UTF8");
		listHeaders.put("Authorization", "Bearer " + Token.getToken());
		response = given().headers(listHeaders).when().get();
	}

	@Then("^o status da requisição deve ser (\\d+)$")
	public void o_status_da_requisição_deve_ser(final int statusEsperado) throws Throwable {
		response.then().statusCode(statusEsperado);
	}

	@Then("^a resposta deve conter as informações:$")
	public void a_resposta_deve_conter_as_informações(final List<String> tags) throws Throwable {
		for (final String tag : tags) {
			assertEquals("Erro!!! O elemento '" + tag + "' não encontrado", true, response.asString().contains(tag));
		}
	}

	@Then("^a quantidade de elementos igual a (\\d+)$")
	public void a_quantidade_de_elementos_igual_a(final int quantidadeElementosEsperado) throws Throwable {
		final String[] elementos = response.asString().split("\":");
		assertEquals(quantidadeElementosEsperado, elementos.length - 1);
	}
}
