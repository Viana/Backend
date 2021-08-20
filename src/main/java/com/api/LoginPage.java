package com.api;

import java.io.IOException;

import com.core.automation.BrowserDriver;
import com.core.automation.ConfiguracaoArquivoPropertiesUsers;
import com.core.automation.UtilidadesSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Página de "Autenticação" do Sistema.
 * 
 * @author Rodrigo
 */

public class LoginPage extends UtilidadesSelenium {

	public LoginPage() throws IOException, Throwable {
	BrowserDriver.getCurrentDriver().get(ConfiguracaoArquivoPropertiesUsers.getProp().getProperty("config.url"));
	}

	public static HomePage authenticate() throws InterruptedException, IOException, Throwable {
		BrowserDriver.getCurrentDriver().findElement(By.id("username"))
				.sendKeys(ConfiguracaoArquivoPropertiesUsers.getProp().getProperty("config.user1"));
				BrowserDriver.getCurrentDriver().findElement(By.id("password"))
				.sendKeys(ConfiguracaoArquivoPropertiesUsers.getProp().getProperty("config.pass1"));
				BrowserDriver.getCurrentDriver().findElement(By.id("btnSubmit")).click();
		WebDriverWait wait = new WebDriverWait(BrowserDriver.getCurrentDriver(), 20);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("username")));

		String Url = BrowserDriver.getCurrentDriver().getCurrentUrl();

		if (Url.contains("Session/DuplicatedSession?")) {
			BrowserDriver.getCurrentDriver().findElement(By.xpath("//input[@value='Prosseguir']")).click();
		}

		return PageFactory.initElements(BrowserDriver.getCurrentDriver(), HomePage.class);
	}
}
