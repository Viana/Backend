package com.api;



import java.io.IOException;

import com.core.automation.BrowserDriver;
import com.core.automation.UtilidadesSelenium;

import org.apache.commons.configuration.ConfigurationException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;



public class HomePage extends UtilidadesSelenium {

	public HomePage() throws ConfigurationException, IOException, InterruptedException, Throwable {
	BrowserDriver.getCurrentDriver().switchTo().defaultContent();
	}

	public static HomePage aceitaCookies() throws ConfigurationException, IOException, InterruptedException {
		aguardeElementoAparecerTela(By.xpath("//ul[@id='side-menu']/li"), 120);
		By aceitaCookies = By.xpath("//a[normalize-space(text())='Aceitar todos os cookies']");
		if (elementIsPresent(aceitaCookies)) {
			cliqueNoElemento(aceitaCookies);
		}
		return PageFactory.initElements(BrowserDriver.getCurrentDriver(), HomePage.class);
	}

	public static HomePage acessarMenu(String[] itensDoMenu)
			throws ConfigurationException, InterruptedException, IOException, Throwable {
		String temp = null;
		for (String menu : itensDoMenu) {
			if (temp != null && temp.equals(menu)) {
				cliqueNoElemento(By.xpath(
						"//ul[@id='side-menu']//ul[contains(@class,'nav-second')]//ul[contains(@class,'nav-third')]//a[@title='"
								+ menu + "']"));
			} else {
				cliqueNoElemento(By.linkText(menu));
			}
			temp = menu;
		}
		return PageFactory.initElements(BrowserDriver.getCurrentDriver(), HomePage.class);
	}

	public LoginPage logout() throws ConfigurationException, IOException, InterruptedException, Throwable {
		cliqueNoElemento(By.linkText("Sair"));
		aguardeElementoDesaparecer(By.linkText("Sair"));
		return PageFactory.initElements(BrowserDriver.getCurrentDriver(), LoginPage.class);
	}
}
