package com.api.steps;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.List;

//import com.api.Token;
//import com.core.automation.ConfiguracaoArquivoPropertiesUsers;

import com.api.support.apis.UserApi;
import com.api.support.domain.User;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

public class UserStepsDefs {
	Response response;
	private User expectedUser;
	private UserApi userApi;

	public UserStepsDefs() {
		userApi = new UserApi();
	}

	@Given("^que acesse a API \"([^\"]*)\"$")
	public void que_acesse_a_API(final String urlApi) throws Throwable {
//		RestAssured.baseURI = ConfiguracaoArquivoPropertiesUsers.getProp().getProperty(urlApi);
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
		listHeaders.put("Authorization", "Bearer " );
//		listHeaders.put("Authorization", "Bearer " + Token.getToken());
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

	@When("^eu faço um POST para criar um usuário$")
	public void euFaçoUmPOSTParaCriarUmUsuário() {
		expectedUser = User.builder().build();
		response = userApi.createUser(expectedUser);
	}

	@Then("^a requisição deve me informar que o usuário foi criado com sucesso$")
	public void aRequisiçãoDeveMeInformarQueOUsuárioFoiCriadoComSucesso() {
		response.then().statusCode(HttpStatus.SC_OK);
		String actualUser = response.jsonPath().get("username");
		assertThat(actualUser ,is(expectedUser.getUsername()));

	}
}
