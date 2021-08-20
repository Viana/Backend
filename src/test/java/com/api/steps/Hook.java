package com.api.steps;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Duration;
import java.time.Instant;

import com.core.automation.BrowserDriver;
import com.core.automation.UtilidadesSelenium;

import com.api.HomePage;
import com.api.ImportacoesPage;
import com.api.LoginPage;

import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.html5.RemoteWebStorage;
import org.openqa.selenium.support.PageFactory;

import cucumber.api.java.Before;

public class Hook {

	@Before
	public void verificarToken() throws Throwable {
		Path path = Paths
				.get(System.getProperty("user.dir") + "/" + UtilidadesSelenium.verificarNomeAmbiente() + "_token.txt");
		String arq = System.getProperty("user.dir") + "/" + UtilidadesSelenium.verificarNomeAmbiente() + "_token.txt";
		File fileToken = new File(arq);

		if (fileToken.exists()) {
			final BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
			final Instant lastModifiedTime = attributes.lastModifiedTime().toInstant();
			final Instant currentTime = Instant.now();

			if (Duration.between(lastModifiedTime, currentTime).toMinutes() > 60 || fileToken.length() <= 0) {
				fileToken.delete();
				selecionarToken(arq, fileToken);
			}
		} else {
			selecionarToken(arq, fileToken);
		}
	}

	public void selecionarToken(String arq, File fileToken) throws InterruptedException, Throwable {
		new File(arq).createNewFile();
		FileWriter fw = new FileWriter(fileToken, true);
		BufferedWriter bw = new BufferedWriter(fw);

		LoginPage loginPage = PageFactory.initElements(BrowserDriver.getCurrentDriver(), LoginPage.class);
		loginPage.authenticate();
		String[] itensDoMenu = "Menu > Menu2".split(" > ");// Acessando menu no portal
		HomePage.aceitaCookies();
		HomePage.acessarMenu(itensDoMenu);
		ImportacoesPage inTax = PageFactory.initElements(BrowserDriver.getCurrentDriver(), ImportacoesPage.class);
		inTax.aguardarTelaCarregar();
		RemoteExecuteMethod executeMethod = new RemoteExecuteMethod((RemoteWebDriver) BrowserDriver.getCurrentDriver());
		RemoteWebStorage webStorage = new RemoteWebStorage(executeMethod);
		SessionStorage sessionStorage = webStorage.getSessionStorage();
		bw.write(sessionStorage.getItem("access_token"));
		bw.newLine();
		bw.close();
		fw.close();
		HomePage home = PageFactory.initElements(BrowserDriver.getCurrentDriver(), HomePage.class);
		home.logout();
		BrowserDriver.fecharBrowser();
	}
}
