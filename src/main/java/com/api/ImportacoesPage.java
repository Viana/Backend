package com.api;

import java.io.IOException;

import com.core.automation.UtilidadesSelenium;

import org.openqa.selenium.By;

public class ImportacoesPage extends UtilidadesSelenium {

	public ImportacoesPage() throws Throwable, IOException {
		acessarFrame(By.xpath(FramesDasTelas.FrameDaTelaAtiva.getFrame()));
		aguardarTelaCarregar();
	}

	public static void aguardarTelaCarregar() throws Throwable, IOException, InterruptedException {
		Thread.sleep(1000);
		if (elementIsPresent(By.xpath("//div[@class='k-i-loading ng-star-inserted']"))) {
			while (elementIsPresent(By.xpath("//div[@class='k-i-loading ng-star-inserted']"))) {
				Thread.sleep(1000);
			}
		} else if (elementIsPresent(By.xpath("//div[@class='k-loading-image']"))) {
			while (elementIsPresent(By.xpath("//div[@class='k-loading-image']"))) {
				Thread.sleep(1000);
			}
		} else if (elementIsPresent(By.xpath("//h2[text()='Autenticando usuário...']"))) {
			while (elementIsPresent(By.xpath("//h2[text()='Autenticando usuário...']"))) {
				Thread.sleep(1000);
			}
		}
		acessarFrame(By.xpath(FramesDasTelas.FrameDaTelaAtiva.getFrame()));
	}
}
