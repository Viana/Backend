package com.api.steps;

import com.api.support.config.ConfigProperties;
import cucumber.api.java.Before;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;

import java.io.IOException;

public class Hook {
    ConfigProperties prop = new ConfigProperties();

    @Before
    public void setup() throws IOException {
        System.out.println("9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.baseURI = String.format("%s:%s",
                prop.getProp().getProperty("api.base.url"),
                prop.getProp().getProperty("api.base.port"));
        RestAssured.basePath = prop.getProp().getProperty("api.base.path");
        RestAssured.requestSpecification = new RequestSpecBuilder().
                setContentType(ContentType.JSON).
                addHeader("Authorization", getToken()).
                build();
    }

    private String getToken() {
        return "";
    }
}
//	@Before
//	public void verificarToken() throws Throwable {
//		Path path = Paths
//				.get(System.getProperty("user.dir") + "/" + UtilidadesSelenium.verificarNomeAmbiente() + "_token.txt");
//		String arq = System.getProperty("user.dir") + "/" + UtilidadesSelenium.verificarNomeAmbiente() + "_token.txt";
//		File fileToken = new File(arq);
//
//		if (fileToken.exists()) {
//			final BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
//			final Instant lastModifiedTime = attributes.lastModifiedTime().toInstant();
//			final Instant currentTime = Instant.now();
//
//			if (Duration.between(lastModifiedTime, currentTime).toMinutes() > 60 || fileToken.length() <= 0) {
//				fileToken.delete();
//				selecionarToken(arq, fileToken);
//			}
//		} else {
//			selecionarToken(arq, fileToken);
//		}
//	}
//
//	public void selecionarToken(String arq, File fileToken) throws InterruptedException, Throwable {
//		new File(arq).createNewFile();
//		FileWriter fw = new FileWriter(fileToken, true);
//		BufferedWriter bw = new BufferedWriter(fw);
//
//		LoginPage loginPage = PageFactory.initElements(BrowserDriver.getCurrentDriver(), LoginPage.class);
//		loginPage.authenticate();
//		String[] itensDoMenu = "Menu > Menu2".split(" > ");// Acessando menu no portal
//		HomePage.aceitaCookies();
//		HomePage.acessarMenu(itensDoMenu);
//		ImportacoesPage inTax = PageFactory.initElements(BrowserDriver.getCurrentDriver(), ImportacoesPage.class);
//		inTax.aguardarTelaCarregar();
//		RemoteExecuteMethod executeMethod = new RemoteExecuteMethod((RemoteWebDriver) BrowserDriver.getCurrentDriver());
//		RemoteWebStorage webStorage = new RemoteWebStorage(executeMethod);
//		SessionStorage sessionStorage = webStorage.getSessionStorage();
//		bw.write(sessionStorage.getItem("access_token"));
//		bw.newLine();
//		bw.close();
//		fw.close();
//		HomePage home = PageFactory.initElements(BrowserDriver.getCurrentDriver(), HomePage.class);
//		home.logout();
//		BrowserDriver.fecharBrowser();
//	}
//}
