package com.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.core.automation.UtilidadesSelenium;



public class Token {

	public static String getToken() throws IOException {
		String arq = System.getProperty("user.dir") + "/" + UtilidadesSelenium.verificarNomeAmbiente() + "_token.txt";
		File fileToken = new File(arq);

		String linha = null;
		String tokenSession = null;
		FileReader fr = new FileReader(fileToken);
		BufferedReader br = new BufferedReader(fr);

		if (fileToken.length() != 0) {

			linha = br.readLine();

			while (linha != null) {
				tokenSession = linha;
				linha = br.readLine();
			}
		}
		br.close();
		fr.close();

		return tokenSession;
	}
}
